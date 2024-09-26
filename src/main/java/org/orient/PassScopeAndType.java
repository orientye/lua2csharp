package org.orient;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PassScopeAndType extends LuaParserBaseListener {

    private final AnnotatedTree annotatedTree;

    private final Stack<Scope> scopeStack = new Stack<>();

    private final Scope globalScope = new Scope("global", null);

    public PassScopeAndType(AnnotatedTree annotatedTree) {
        this.annotatedTree = annotatedTree;
        this.scopeStack.add(globalScope);
    }

    public void Reset() {
        this.scopeStack.clear();
        this.scopeStack.add(globalScope);
        this.annotatedTree.scopes.clear();
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
                if (parentTree instanceof LuaParser.FuncbodyContext funcbodyContext) {
                    ParseTree parentParentTree = parentTree.getParent();
                    if (parentParentTree instanceof LuaParser.StatContext) {
                        List<Symbol.Type> stList = new ArrayList<>();
                        for (LuaParser.ExpContext expContext : expContextList) {
                            Symbol.Type st = Util.GetExpContextTypeInTree(expContext, this.annotatedTree);
                            stList.add(st);
                        }
                        this.annotatedTree.funcReturns.put(funcbodyContext, stList);
                    } else if (parentParentTree instanceof LuaParser.FunctiondefContext) {
                        throw new UnsupportedOperationException();
                    } else {
                        assert (false);
                    }
                } else if (parentTree instanceof LuaParser.ChunkContext) {
                    assert (expContextList.size() == 1);
                    LuaParser.ExpContext expContext = expContextList.getFirst();

                    Scope curScope = this.scopeStack.peek();
                    assert (curScope == this.globalScope);
                    Symbol symbol = curScope.resolve(expContext.getText());
                    assert (symbol != null);
                    ParseTree parseTree = symbol.getParseTree();
                    this.annotatedTree.refs.put(expContext, parseTree);
                } else {
                    throw new UnsupportedOperationException();
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
        // local' 'function' NAME funcbody
        LuaParser.FuncbodyContext funcbodyContext = ctx.funcbody();
        if (funcbodyContext != null) {
            String name = null;
            LuaParser.FuncnameContext funcnameContext = ctx.funcname();
            if (funcnameContext != null) {
                name = funcnameContext.getText();
            } else {
                name = ctx.NAME().getText();
            }
            if (name != null) {
                //scope
                Scope curScope = this.scopeStack.peek();
                this.annotatedTree.scopes.put(funcbodyContext, curScope);
                Scope scope = new Scope(name, curScope);
                this.scopeStack.push(scope);

                // symbol
                Symbol symbol = Symbol.create(name, Symbol.Type.SYMBOL_TYPE_FUNCTION, funcbodyContext, this.annotatedTree);
                curScope.add(symbol);

                // params
                LuaParser.ParlistContext parlistContext = funcbodyContext.parlist();
                LuaParser.NamelistContext namelistContext = parlistContext.namelist();
                if (namelistContext != null) {
                    List<TerminalNode> terminalNodeList = namelistContext.NAME();
                    for (TerminalNode v : terminalNodeList) {
                        this.annotatedTree.scopes.put(v, scope);
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
    public void exitStat(LuaParser.StatContext ctx) {
        // varlist '=' explist
        LuaParser.VarlistContext varlistContext = ctx.varlist();
        if (varlistContext != null) {
            List<LuaParser.VarContext> varContextList = varlistContext.var();
            LuaParser.ExplistContext explistContext = ctx.explist();
            int returnCount = Util.GetExpContextReturnCount(explistContext, annotatedTree);
            int szVarContextList = varContextList.size();
            assert (szVarContextList <= returnCount);
            Scope curScope = this.scopeStack.peek();
            String scopeName = curScope.getName();
            if (returnCount == 1) {
                assert (szVarContextList == 1);
                Symbol.Type symbolType = Util.GetExpContextTypeInList(0, explistContext, annotatedTree);
                LuaParser.VarContext varContext = varContextList.getFirst();
                LuaParser.PrefixexpContext prefixexpContext = varContext.prefixexp();
                if (prefixexpContext != null) {
                    String symbolName = UtilTable.GetMemberVariableSymbolName(varContext, scopeName);
                    assert (!symbolName.isEmpty());
                    Symbol.create(symbolName, symbolType, prefixexpContext, this.annotatedTree);
                }
            } else {
                assert (returnCount > 1);
                int idx = 0;
                for (; idx < szVarContextList; idx++) {
                    LuaParser.VarContext varContext = varContextList.get(idx);
                    Symbol.Type symbolType = Util.GetExpContextTypeInList(idx, explistContext, annotatedTree);
                    LuaParser.PrefixexpContext prefixexpContext = varContext.prefixexp();
                    if (prefixexpContext != null) {
                        String symbolName = UtilTable.GetMemberVariableSymbolName(varContext, scopeName);
                        assert (!symbolName.isEmpty());
                        Symbol.create(symbolName, symbolType, prefixexpContext, this.annotatedTree);
                    }
                }
            }
        }

        // 'function' funcname funcbody
        // local' 'function' NAME funcbody
        LuaParser.FuncbodyContext funcbodyContext = ctx.funcbody();
        if (funcbodyContext != null) {
            this.scopeStack.pop();
        }

        // 'local' attnamelist ('=' explist)?
        LuaParser.AttnamelistContext attnamelistContext = ctx.attnamelist();
        if (attnamelistContext != null) {
            LuaParser.ExplistContext explistContext = ctx.explist();
            if (explistContext != null) {
                List<LuaParser.ExpContext> expContextList = explistContext.exp();
                List<TerminalNode> terminalNodeList = attnamelistContext.NAME();
                for (int idx = 0; idx < expContextList.size(); idx++) {
                    TerminalNode terminalNode = terminalNodeList.get(idx);
                    String terminalNodeText = terminalNode.getSymbol().getText();
                    Symbol.Type symbolType = Util.GetExpContextTypeInList(idx, explistContext, annotatedTree);
                    Symbol symbolTerminal = Symbol.create(terminalNodeText, symbolType, terminalNode, this.annotatedTree);
                    Scope curScope = this.scopeStack.peek();
                    assert (curScope != null);
                    curScope.add(symbolTerminal);
                }
            } else {
                //TODO:
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
            Symbol.create(name, st, ctx, this.annotatedTree);
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
                    Symbol.create(name, Symbol.Type.SYMBOL_TYPE_INT, ctx, this.annotatedTree);
                }
                if (symbolL.getType() == Symbol.Type.SYMBOL_TYPE_FLOAT && symbolR.getType() == Symbol.Type.SYMBOL_TYPE_FLOAT) {
                    String name = ctx.getText();
                    Symbol.create(name, Symbol.Type.SYMBOL_TYPE_FLOAT, ctx, this.annotatedTree);
                }
                if (ctx.PLUS() != null) {
                    if (symbolL.getType() == Symbol.Type.SYMBOL_TYPE_STRING && symbolR.getType() == Symbol.Type.SYMBOL_TYPE_STRING) {
                        String name = ctx.getText();
                        Symbol.create(name, Symbol.Type.SYMBOL_TYPE_STRING, ctx, this.annotatedTree);
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
            List<Symbol.Type> stList = new ArrayList<>();
            LuaParser.ExplistContext explistContext = argsContext.explist();
            if (explistContext != null) {
                List<LuaParser.ExpContext> expContextList = explistContext.exp();
                for (LuaParser.ExpContext expContext : expContextList) {
                    Symbol symbol = this.annotatedTree.symbols.get(expContext);
                    Symbol.Type st;
                    if (symbol != null) {
                        st = symbol.getType();
                    } else {
                        st = Util.GetExpContextType(expContext);
                    }
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
                if (namelistContext != null) {
                    List<TerminalNode> terminalNodes = namelistContext.NAME();
                    for (int i = 0; i < terminalNodes.size(); i++) {
                        TerminalNode terminalNode = terminalNodes.get(i);
                        Symbol terminalNodeSymbol = this.annotatedTree.symbols.get(terminalNode);
                        if (terminalNodeSymbol == null) {
                            Symbol.Type st = stList.get(i);
                            assert (st != Symbol.Type.SYMBOL_TYPE_UNKNOWN);
                            Symbol sym = Symbol.create(terminalNode.getText(), st, terminalNode, annotatedTree);
                            Scope funcScope = this.annotatedTree.scopes.get(funcbodyContext);
                            if (funcScope != null) {
                                funcScope.add(sym);
                            }
                        }
                    }
                }

                //exp
                ParseTree parent = ctx.getParent();
                assert (parent != null);
                ParseTree parentParent = parent.getParent();
                assert (parentParent != null);
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
