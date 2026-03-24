package org.orient;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

/**
 * Lua AST + semantic information (AnnotatedTree) to IR (Ir.Module) transformer.
 */
public class LuaToIrTransformer {

    private final AnnotatedTree annotatedTree;
    private final BufferedTokenStream tokens;
    private final Set<Integer> processedCommentTokens = new HashSet<>();

    public LuaToIrTransformer(AnnotatedTree annotatedTree, BufferedTokenStream tokens) {
        this.annotatedTree = annotatedTree;
        this.tokens = tokens;
    }

    public Ir.Module transform(ParseTree root, String moduleName) {
        List<Ir.Statement> topLevelStatements = new ArrayList<>();
        List<Ir.Method> topLevelMethods = new ArrayList<>();
        Map<LuaParser.FuncbodyContext, List<Ir.Statement>> bodyStatements = new HashMap<>();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new LuaParserBaseListener() {
            private final Deque<List<Ir.Statement>> blockStack = new ArrayDeque<>();
            private final Deque<Set<String>> declaredLocalsStack = new ArrayDeque<>();
            private final Map<String, Ir.VariableDeclaration> nilDeclarations = new HashMap<>();
            { declaredLocalsStack.push(new HashSet<>()); }

            private boolean isDeclaredLocal(String name) {
                for (Set<String> scope : declaredLocalsStack) {
                    if (scope.contains(name)) return true;
                }
                return false;
            }

            @Override
            public void enterFuncbody(LuaParser.FuncbodyContext ctx) {
                blockStack.push(new ArrayList<>());
                declaredLocalsStack.push(new HashSet<>());
            }

            @Override
            public void exitFuncbody(LuaParser.FuncbodyContext ctx) {
                List<Ir.Statement> stmts = blockStack.pop();
                declaredLocalsStack.pop();
                bodyStatements.put(ctx, stmts);
            }

            @Override
            public void exitStat(LuaParser.StatContext ctx) {
                LuaParser.AttnamelistContext attnamelistContext = ctx.attnamelist();
                LuaParser.ExplistContext explistContext = ctx.explist();
                LuaParser.FuncbodyContext funcbodyContext = ctx.funcbody();
                LuaParser.FunctioncallContext functioncallContext = ctx.functioncall();

                List<Ir.Statement> currentStatements =
                        blockStack.isEmpty() ? topLevelStatements : blockStack.peek();
                emitStandaloneCommentsBefore(ctx.getStart(), currentStatements);

                // function definition
                if (funcbodyContext != null) {
                    String funcName = null;
                    LuaParser.FuncnameContext funcnameContext = ctx.funcname();
                    if (funcnameContext != null) {
                        funcName = funcnameContext.getText();
                    } else if (ctx.NAME() != null) {
                        funcName = ctx.NAME().getText();
                    }
                    if (funcName != null) {
                        List<Ir.Parameter> parameters = new ArrayList<>();
                        LuaParser.ParlistContext parlistContext = funcbodyContext.parlist();
                        if (parlistContext != null) {
                            LuaParser.NamelistContext namelistContext = parlistContext.namelist();
                            if (namelistContext != null) {
                                for (TerminalNode tn : namelistContext.NAME()) {
                                    Symbol sym = annotatedTree.symbols.get(tn);
                                    Symbol.Type t = sym != null ? sym.getType() : Symbol.Type.SYMBOL_TYPE_UNKNOWN;
                                    parameters.add(new Ir.Parameter(tn.getText(), t, sym));
                                }
                            }
                        }
                        List<Symbol.Type> retTypes = annotatedTree.funcReturns.get(funcbodyContext);
                        if (retTypes == null) {
                            retTypes = List.of();
                        }
                        List<Ir.Statement> stmts = bodyStatements.getOrDefault(funcbodyContext, List.of());
                        Ir.Block body = new Ir.Block(stmts, null);
                        Ir.Method method = new Ir.Method(
                                funcName,
                                parameters,
                                retTypes,
                                true,
                                false,
                                body
                        );
                        topLevelMethods.add(method);
                        currentStatements.add(new Ir.MethodDeclaration(method));
                    }
                } else if (ctx.LOCAL() != null && attnamelistContext != null && explistContext != null) {
                    // 'local' attnamelist '=' explist  (top-level or in function)
                    List<TerminalNode> names = attnamelistContext.NAME();
                    List<LuaParser.ExpContext> exps = explistContext.exp();
                    if (exps.size() == 1) {
                        LuaParser.ExpContext expContext = exps.getFirst();
                        Ir.Expression rhs = toIrExpression(expContext);

                        // multi-variable = func_return(...);
                        if (names.size() > 1 && rhs instanceof Ir.Call call && call.getReturnTypes().size() > 1) {
                            List<Ir.VariableDeclaration> vars = new ArrayList<>();
                            for (int i = 0; i < names.size(); i++) {
                                TerminalNode tn = names.get(i);
                                String varName = tn.getText();
                                Symbol.Type t = Util.GetExpContextTypeInList(i, explistContext, annotatedTree);
                                vars.add(new Ir.VariableDeclaration(varName, t, null, null));
                            }
                            currentStatements.add(new Ir.TupleDeconstruction(vars, call));
                        }
                        // single name but multi-return function: local n2 = func_return(...)
                        else if (names.size() == 1 && rhs instanceof Ir.Call call && call.getReturnTypes().size() > 1) {
                            TerminalNode tn = names.getFirst();
                            String varName = tn.getText();
                            List<Ir.VariableDeclaration> vars = new ArrayList<>();
                            Symbol.Type firstType = call.getReturnTypes().getFirst();
                            vars.add(new Ir.VariableDeclaration(varName, firstType, null, null));
                            Symbol.Type secondType = call.getReturnTypes().size() > 1
                                    ? call.getReturnTypes().get(1)
                                    : firstType;
                            vars.add(new Ir.VariableDeclaration("_", secondType, null, null));
                            currentStatements.add(new Ir.TupleDeconstruction(vars, call));
                        }
                        // simple single local declaration
                        else if (names.size() == 1) {
                            String varName = names.getFirst().getText();
                            declaredLocalsStack.peek().add(varName);
                            Symbol.Type varType = Util.GetExpContextTypeInList(0, explistContext, annotatedTree);
                            Symbol symbol = annotatedTree.symbols.get(expContext);
                            Ir.VariableDeclaration decl = new Ir.VariableDeclaration(varName, varType, rhs, symbol);
                            if (varType == Symbol.Type.SYMBOL_TYPE_LUA_NIL) {
                                nilDeclarations.put(varName, decl);
                            }
                            currentStatements.add(decl);
                        }
                    }
                } else if (ctx.varlist() != null && explistContext != null) {
                    // simple assignment / tuple deconstruction
                    LuaParser.VarlistContext varlistContext = ctx.varlist();
                    List<LuaParser.VarContext> vars = varlistContext.var();
                    List<LuaParser.ExpContext> exps = explistContext.exp();
                    if (exps.size() == 1) {
                        LuaParser.ExpContext valueExp = exps.getFirst();
                        Ir.Expression value = toIrExpression(valueExp);
                        if (value instanceof Ir.Call call && call.getReturnTypes().size() > 1) {
                            List<Ir.VariableDeclaration> tupleVars = new ArrayList<>();
                            int varCount = vars.size();
                            int slotCount = Math.max(varCount, 2);
                            for (int i = 0; i < slotCount; i++) {
                                String varName = "_";
                                if (i < varCount) {
                                    varName = vars.get(i).getText();
                                }
                                Symbol.Type t;
                                if (i < call.getReturnTypes().size()) {
                                    t = call.getReturnTypes().get(i);
                                } else {
                                    t = call.getReturnTypes().get(call.getReturnTypes().size() - 1);
                                }
                                tupleVars.add(new Ir.VariableDeclaration(varName, t, null, null));
                            }
                            currentStatements.add(new Ir.TupleDeconstruction(tupleVars, call));
                        } else if (vars.size() == 1) {
                            LuaParser.VarContext varContext = vars.getFirst();
                            String varName = varContext.getText();
                            if (isDeclaredLocal(varName)) {
                                Ir.VariableDeclaration nilDecl = nilDeclarations.get(varName);
                                if (nilDecl != null) {
                                    Symbol.Type rhsType = Util.GetExpContextTypeInList(0, explistContext, annotatedTree);
                                    if (rhsType != Symbol.Type.SYMBOL_TYPE_UNKNOWN && rhsType != Symbol.Type.SYMBOL_TYPE_LUA_NIL) {
                                        nilDecl.setType(rhsType);
                                    }
                                }
                                Ir.Expression target = new Ir.VariableRef(varName, null, Symbol.Type.SYMBOL_TYPE_UNKNOWN);
                                currentStatements.add(new Ir.Assignment(target, value));
                            } else {
                                Symbol.Type t = Util.GetExpContextTypeInList(0, explistContext, annotatedTree);
                                Ir.VariableDeclaration decl = new Ir.VariableDeclaration(varName, t, value, null);
                                currentStatements.add(decl);
                            }
                        }
                    }
                } else if (functioncallContext != null) {
                    // function call statement
                    Ir.Expression callExpr = toFunctionCallExpression(functioncallContext);
                    currentStatements.add(new Ir.ExpressionStatement(callExpr));
                }

                emitTrailingComment(ctx, currentStatements);
            }

            @Override
            public void exitRetstat(LuaParser.RetstatContext ctx) {
                LuaParser.ExplistContext explistContext = ctx.explist();
                if (explistContext != null) {
                    List<Ir.Expression> values = new ArrayList<>();
                    for (LuaParser.ExpContext e : explistContext.exp()) {
                        values.add(toIrExpression(e));
                    }
                    if (!blockStack.isEmpty()) {
                        blockStack.peek().add(new Ir.Return(values));
                    }
                }
            }
        }, root);

        String name = moduleName != null ? moduleName : "LuaModule";
        return new Ir.Module(name, List.of(), topLevelStatements, topLevelMethods);
    }

    private Ir.Expression toIrExpression(LuaParser.ExpContext ctx) {
        // nil
        if (ctx.NIL() != null) {
            return new Ir.Literal("null", Symbol.Type.SYMBOL_TYPE_LUA_NIL);
        }

        // Literal numbers, booleans, strings
        if (ctx.number() != null || ctx.string() != null || ctx.TRUE() != null || ctx.FALSE() != null) {
            String text = ctx.getText();
            Symbol.Type type = Util.GetExpContextTypeInTree(ctx, annotatedTree);
            return new Ir.Literal(text, type);
        }

        // prefixexp: 可能是变量引用或函数调用
        LuaParser.PrefixexpContext prefixexpContext = ctx.prefixexp();
        if (prefixexpContext != null) {
            LuaParser.FunctioncallContext fcall = prefixexpContext.functioncall();
            if (fcall != null) {
                // func_return(...) 之类的函数调用
                return toFunctionCallExpression(fcall);
            }
            // 否则，当作简单变量引用（如 n、n1、s1）
            if (!prefixexpContext.NAME().isEmpty()) {
                String name = prefixexpContext.getText();
                Symbol symbol = annotatedTree.symbols.get(ctx);
                Symbol.Type type = symbol != null ? symbol.getType() : Symbol.Type.SYMBOL_TYPE_UNKNOWN;
                return new Ir.VariableRef(name, symbol, type);
            }
        }

        // Simple binary expressions: exp op exp
        if (ctx.getChildCount() == 3
                && ctx.getChild(0) instanceof LuaParser.ExpContext leftCtx
                && ctx.getChild(2) instanceof LuaParser.ExpContext rightCtx
                && ctx.getChild(1) instanceof TerminalNode opNode) {
            Ir.Expression left = toIrExpression(leftCtx);
            Ir.Expression right = toIrExpression(rightCtx);

            Token opToken = opNode.getSymbol();
            String opText = opToken.getText();
            Ir.Binary.Operator op;
            switch (opText) {
                case "+" -> op = Ir.Binary.Operator.ADD;
                case "-" -> op = Ir.Binary.Operator.SUBTRACT;
                case "*" -> op = Ir.Binary.Operator.MULTIPLY;
                case "/" -> op = Ir.Binary.Operator.DIVIDE;
                default -> {
                    // fallback: treat as unknown literal of original text
                    Symbol.Type type = Util.GetExpContextTypeInTree(ctx, annotatedTree);
                    return new Ir.Literal(ctx.getText(), type);
                }
            }

            Symbol.Type resultType = Util.GetExpContextTypeInTree(ctx, annotatedTree);
            return new Ir.Binary(op, left, right, resultType);
        }

        // Fallback: use raw text as a literal of unknown type
        Symbol.Type type = Util.GetExpContextTypeInTree(ctx, annotatedTree);
        return new Ir.Literal(ctx.getText(), type);
    }

    private Ir.Expression toFunctionCallExpression(LuaParser.FunctioncallContext ctx) {
        List<TerminalNode> nameNodes = ctx.NAME();
        if (nameNodes == null || nameNodes.isEmpty()) {
            // fallback
            return new Ir.Literal(ctx.getText(), Symbol.Type.SYMBOL_TYPE_UNKNOWN);
        }
        String funcName = nameNodes.getFirst().getText();
        LuaParser.ArgsContext argsContext = ctx.args();

        List<Ir.Expression> args = new ArrayList<>();
        if (argsContext != null && argsContext.explist() != null) {
            for (LuaParser.ExpContext e : argsContext.explist().exp()) {
                args.add(toIrExpression(e));
            }
        }

        // print(x) -> Console.WriteLine(x)
        if ("print".equals(funcName)) {
            Ir.Expression target = new Ir.VariableRef("Console.WriteLine", null, Symbol.Type.SYMBOL_TYPE_UNKNOWN);
            return new Ir.Call(target, args, List.of());
        }

        // 根据函数定义推断返回类型：从 funcbody 上的 funcReturns 获取
        List<Symbol.Type> retTypes = List.of(Symbol.Type.SYMBOL_TYPE_UNKNOWN);
        Symbol funcSymbol = null;

        // annotatedTree.scopes: Map<ParseTree, Scope>，值里可能有重复的 Scope，这里用 Set 去重
        Set<Scope> visitedScopes = new HashSet<>();
        for (Scope scope : annotatedTree.scopes.values()) {
            if (scope != null && visitedScopes.add(scope)) {
                Symbol s = scope.resolve(funcName);
                if (s != null) {
                    funcSymbol = s;
                    break;
                }
            }
        }

        if (funcSymbol != null) {
            ParseTree defTree = funcSymbol.getParseTree();
            if (defTree instanceof LuaParser.FuncbodyContext fbCtx) {
                List<Symbol.Type> ts = annotatedTree.funcReturns.get(fbCtx);
                if (ts != null && !ts.isEmpty()) {
                    retTypes = ts;
                }
            }
        }

        Ir.Expression target = new Ir.VariableRef(funcName, funcSymbol, Symbol.Type.SYMBOL_TYPE_UNKNOWN);
        return new Ir.Call(target, args, retTypes);
    }

    private String toCSharpComment(String luaComment) {
        if (luaComment == null || luaComment.isEmpty()) {
            return "//";
        }
        if (luaComment.startsWith("--[[")) {
            String inner;
            if (luaComment.length() >= 8) {
                inner = luaComment.substring(4, luaComment.length() - 4).trim();
            } else {
                inner = "";
            }
            return "/**\n" + inner + "\n*/";
        }
        if (luaComment.startsWith("--")) {
            String txt = luaComment.substring(2).trim();
            return "// " + txt;
        }
        return "// " + luaComment;
    }

    private void emitStandaloneCommentsBefore(Token startToken, List<Ir.Statement> statements) {
        if (tokens == null) return;
        List<Token> comments = tokens.getHiddenTokensToLeft(startToken.getTokenIndex(), LuaLexer.COMMENTS);
        if (comments == null) return;
        for (Token cmt : comments) {
            if (processedCommentTokens.add(cmt.getTokenIndex())) {
                statements.add(new Ir.Comment(toCSharpComment(cmt.getText())));
            }
        }
    }

    private void emitTrailingComment(LuaParser.StatContext ctx, List<Ir.Statement> statements) {
        if (tokens == null) return;
        int stopLine = ctx.getStop().getLine();
        List<Token> comments = tokens.getHiddenTokensToRight(ctx.getStop().getTokenIndex(), LuaLexer.COMMENTS);
        if (comments == null) return;
        for (Token cmt : comments) {
            if (cmt.getLine() != stopLine) break;
            if (processedCommentTokens.add(cmt.getTokenIndex())) {
                statements.add(new Ir.Comment(toCSharpComment(cmt.getText())));
            }
        }
    }
}

