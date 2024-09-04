package org.orient;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PassScopeAndType extends LuaParserBaseListener {

    private final AnnotatedTree annotatedTree;

    private final Stack<Scope> scopeStack = new Stack<>();

    public PassScopeAndType(AnnotatedTree annotatedTree) {
        this.annotatedTree = annotatedTree;
        Scope globalScope = new Scope("global", null);
        scopeStack.add(globalScope);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterStart_(LuaParser.Start_Context ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitStart_(LuaParser.Start_Context ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterChunk(LuaParser.ChunkContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitChunk(LuaParser.ChunkContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterBlock(LuaParser.BlockContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitBlock(LuaParser.BlockContext ctx) {
        LuaParser.RetstatContext retstatContext = ctx.retstat();
        if (retstatContext != null) {
            LuaParser.ExplistContext explistContext = retstatContext.explist();
            if (explistContext != null) { // return a, b, c;
                List<LuaParser.ExpContext> expContextList = explistContext.exp();
                ParseTree parentTree = ctx.getParent();
                assert (parentTree instanceof LuaParser.FuncbodyContext);
                LuaParser.FuncbodyContext funcbodyContext = (LuaParser.FuncbodyContext) parentTree;
                ParseTree parentParentTree = parentTree.getParent();
                if (parentParentTree instanceof LuaParser.StatContext) {
                    List<Symbol.Type> stList = new ArrayList<>();
                    for (int i = 0; i < expContextList.size(); i++) {
                        LuaParser.ExpContext expContext = expContextList.get(i);
                        Symbol.Type st = Util.GetExpContextTypeInTree(expContext, this.annotatedTree);
                        stList.add(st);
                    }
                    this.annotatedTree.funcReturns.put(funcbodyContext, stList);
                } else if (parentParentTree instanceof LuaParser.FunctiondefContext) {
                    throw new UnsupportedOperationException();
                } else {
                    assert(false);
                }
            } else { //void
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterStat(LuaParser.StatContext ctx) {
        // 'function' funcname funcbody
        LuaParser.FuncnameContext funcnameContext = ctx.funcname();
        LuaParser.FuncbodyContext funcbodyContext = ctx.funcbody();
        if (funcnameContext != null) {
            List<TerminalNode> names = funcnameContext.NAME();
            int sz = names.size();
            if (1 == sz) {
                // scope
                String name = names.getFirst().getText();
                Scope curScope = this.scopeStack.peek();
                annotatedTree.scopes.put(funcnameContext, curScope);
                Scope scope = new Scope(name, curScope);
                scopeStack.push(scope);

                // symbol
                Symbol symbol = Symbol.create(name, Symbol.Type.SYMBOL_TYPE_FUNCTION, funcbodyContext, annotatedTree);
                curScope.add(symbol);

                // params
                LuaParser.ParlistContext parlistContext = funcbodyContext.parlist();
                LuaParser.NamelistContext namelistContext = parlistContext.namelist();
                List<TerminalNode> terminalNodeList = namelistContext.NAME();
                for (TerminalNode v : terminalNodeList) {
                    annotatedTree.scopes.put(v, scope);
                }
            } else {
                //TODO: class:func
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitStat(LuaParser.StatContext ctx) {
        // 'function' funcname funcbody
        LuaParser.FuncnameContext funcnameContext = ctx.funcname();
        if (funcnameContext != null) {
            List<TerminalNode> names = funcnameContext.NAME();
            int sz = names.size();
            if (1 == sz) {
                scopeStack.pop();
            } else {
                //TODO: class:func
            }
        }

        // 'local' attnamelist ('=' explist)?
        LuaParser.AttnamelistContext attnamelistContext = ctx.attnamelist();
        if (attnamelistContext != null) {
            LuaParser.ExplistContext explistContext = ctx.explist();
            if (explistContext != null) {
                List<LuaParser.ExpContext> expContextList = explistContext.exp();
                List<TerminalNode> terminalNodeList = attnamelistContext.NAME();
                assert (expContextList.size() == terminalNodeList.size());
                LuaParser.ExpContext expContext;
                TerminalNode terminalNode;
                String terminalNodeText;
                for (int idx = 0; idx < expContextList.size(); idx++) {
                    expContext = expContextList.get(idx);
                    terminalNode = terminalNodeList.get(idx);
                    terminalNodeText = terminalNode.getSymbol().getText();

                    Symbol.Type symbolType = Symbol.Type.SYMBOL_TYPE_UNKNOWN;
                    Symbol symbolExp = this.annotatedTree.symbols.get(expContext);
                    if (symbolExp != null) {
                        symbolType = symbolExp.getType();
                    }

                    Symbol symbolTerminal = Symbol.create(terminalNodeText, symbolType, terminalNode, annotatedTree);
                    Scope curScope = this.scopeStack.peek();
                    assert (curScope != null);
                    curScope.add(symbolTerminal);
                }

            } else {

            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterAttnamelist(LuaParser.AttnamelistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitAttnamelist(LuaParser.AttnamelistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterAttrib(LuaParser.AttribContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitAttrib(LuaParser.AttribContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterRetstat(LuaParser.RetstatContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitRetstat(LuaParser.RetstatContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterLabel(LuaParser.LabelContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitLabel(LuaParser.LabelContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterFuncname(LuaParser.FuncnameContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitFuncname(LuaParser.FuncnameContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterVarlist(LuaParser.VarlistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitVarlist(LuaParser.VarlistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterNamelist(LuaParser.NamelistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitNamelist(LuaParser.NamelistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterExplist(LuaParser.ExplistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitExplist(LuaParser.ExplistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterExp(LuaParser.ExpContext ctx) {
        Symbol.Type st = Util.GetExpContextType(ctx);
        if (st != Symbol.Type.SYMBOL_TYPE_UNKNOWN) {
            String name = ctx.getText();
            Symbol.create(name, st, ctx, annotatedTree);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitExp(LuaParser.ExpContext ctx) {
        List<LuaParser.ExpContext> expContextList = ctx.exp();
        if (ctx.PLUS() != null || ctx.MINUS() != null || ctx.STAR() != null || ctx.SLASH() != null) {
            assert (expContextList.size() == 2);
            LuaParser.ExpContext l = expContextList.getFirst();
            Symbol symbolL = this.annotatedTree.symbols.get(l);
            if (symbolL == null) {
                String lText = l.getText();
                Scope curScope = this.scopeStack.peek();
                symbolL = curScope.resolve(lText);
            }
            LuaParser.ExpContext r = expContextList.get(1);
            Symbol symbolR = this.annotatedTree.symbols.get(r);
            if (symbolR == null) {
                String rText = r.getText();
                Scope curScope = this.scopeStack.peek();
                symbolR = curScope.resolve(rText);
            }
            if (symbolL != null && symbolR != null) {
                if (symbolL.getType() == Symbol.Type.SYMBOL_TYPE_INT && symbolR.getType() == Symbol.Type.SYMBOL_TYPE_INT) {
                    String name = ctx.getText();
                    Symbol.create(name, Symbol.Type.SYMBOL_TYPE_INT, ctx, annotatedTree);
                }
                if (symbolL.getType() == Symbol.Type.SYMBOL_TYPE_FLOAT && symbolR.getType() == Symbol.Type.SYMBOL_TYPE_FLOAT) {
                    String name = ctx.getText();
                    Symbol.create(name, Symbol.Type.SYMBOL_TYPE_FLOAT, ctx, annotatedTree);
                }
                if (ctx.PLUS() != null) {
                    if (symbolL.getType() == Symbol.Type.SYMBOL_TYPE_STRING && symbolR.getType() == Symbol.Type.SYMBOL_TYPE_STRING) {
                        String name = ctx.getText();
                        Symbol.create(name, Symbol.Type.SYMBOL_TYPE_STRING, ctx, annotatedTree);
                    }
                }
            }
        } else {
            // TODO: other case
            if (this.annotatedTree.symbols.get(ctx) == null) {
                String ctxText = ctx.getText();
                Scope curScope = this.scopeStack.peek();
                Symbol symbol = curScope.resolve(ctxText);
                if (symbol != null) {
                    this.annotatedTree.symbols.put(ctx, symbol);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterVar(LuaParser.VarContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitVar(LuaParser.VarContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterPrefixexp(LuaParser.PrefixexpContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitPrefixexp(LuaParser.PrefixexpContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterFunctioncall(LuaParser.FunctioncallContext ctx) {
        int childrenCount = ctx.children.size();

        // NAME ('[' exp ']' | '.' NAME)* args
        List<TerminalNode> terminalNodeList = ctx.NAME();
        LuaParser.ArgsContext argsContext = ctx.args();
        if (2 == childrenCount) {  //func(a,b,c)
            LuaParser.ExplistContext explistContext = argsContext.explist();
            List<LuaParser.ExpContext> expContextList = explistContext.exp();
            List<Symbol.Type> stList = new ArrayList<>();
            for (LuaParser.ExpContext expContext : expContextList) {
                Symbol symbol = annotatedTree.symbols.get(expContext);
                if (symbol != null) {
                    Symbol.Type st = symbol.getType();
                    stList.add(st);
                } else {
                    Symbol.Type st = Util.GetExpContextType(expContext);
                    stList.add(st);
                }
            }
            Scope curScope = this.scopeStack.peek();
            Symbol symbol = curScope.resolve(terminalNodeList.getFirst().getText());
            if (symbol != null) {
                ParseTree parseTree = symbol.getParseTree();
                assert (parseTree instanceof LuaParser.FuncbodyContext);
                LuaParser.FuncbodyContext funcbodyContext = (LuaParser.FuncbodyContext) parseTree;

                // params
                LuaParser.ParlistContext parlistContext = funcbodyContext.parlist();
                LuaParser.NamelistContext namelistContext = parlistContext.namelist();// TODO: ... Varargs
                List<TerminalNode> terminalNodes = namelistContext.NAME();
                for (int i = 0; i < terminalNodes.size(); i++) {
                    TerminalNode terminalNode = terminalNodes.get(i);
                    Symbol terminalNodeSymbol = annotatedTree.symbols.get(terminalNode);
                    if (terminalNodeSymbol == null) {
                        Symbol.Type st = stList.get(i);
                        assert (st != Symbol.Type.SYMBOL_TYPE_UNKNOWN);
                        Symbol.create(terminalNode.getText(), st, terminalNode, annotatedTree);
                    }
                }

                //exp
                ParseTree parent = ctx.getParent();
                assert(parent != null);
                ParseTree parentParent = parent.getParent();
                assert(parentParent != null);
                if (parentParent instanceof LuaParser.ExpContext) {
                    List<Symbol.Type> typeList = this.annotatedTree.funcReturns.get(funcbodyContext);
                    if (typeList != null) {
                        this.annotatedTree.funcReturns.put(parentParent, typeList);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitFunctioncall(LuaParser.FunctioncallContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterArgs(LuaParser.ArgsContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitArgs(LuaParser.ArgsContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterFunctiondef(LuaParser.FunctiondefContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitFunctiondef(LuaParser.FunctiondefContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterFuncbody(LuaParser.FuncbodyContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitFuncbody(LuaParser.FuncbodyContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterParlist(LuaParser.ParlistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitParlist(LuaParser.ParlistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterTableconstructor(LuaParser.TableconstructorContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitTableconstructor(LuaParser.TableconstructorContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterFieldlist(LuaParser.FieldlistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitFieldlist(LuaParser.FieldlistContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterField(LuaParser.FieldContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitField(LuaParser.FieldContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterFieldsep(LuaParser.FieldsepContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitFieldsep(LuaParser.FieldsepContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterNumber(LuaParser.NumberContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitNumber(LuaParser.NumberContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterString(LuaParser.StringContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitString(LuaParser.StringContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void visitTerminal(TerminalNode node) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void visitErrorNode(ErrorNode node) {
    }
}
