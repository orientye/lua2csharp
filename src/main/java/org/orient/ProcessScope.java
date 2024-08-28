package org.orient;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.Stack;

public class ProcessScope extends LuaParserBaseListener {

    private final AnnotatedTree annotatedTree;

    private final Stack<Scope> scopeStack = new Stack<Scope>();

    public ProcessScope(AnnotatedTree annotatedTree) {
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
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterStat(LuaParser.StatContext ctx) {
        //'function' funcname funcbody
        LuaParser.FuncnameContext funcnameContext = ctx.funcname();
        LuaParser.FuncbodyContext funcbodyContext = ctx.funcbody();
        if (funcnameContext != null) {
            List<TerminalNode> names = funcnameContext.NAME();
            int sz = names.size();
            if (1 == sz) {
                // scope
                String name = names.get(0).getText();
                Scope curScope = this.scopeStack.peek();
                Scope scope = new Scope(name, curScope);
                scopeStack.push(scope);
                annotatedTree.scopesOfNodes.put(funcnameContext, scope);

                // params
                LuaParser.ParlistContext parlistContext = funcbodyContext.parlist();
                LuaParser.NamelistContext namelistContext = parlistContext.namelist();
                List<TerminalNode> terminalNodeList = namelistContext.NAME();
                for (int i = 0; i < terminalNodeList.size(); i++) {
                    String paramName = terminalNodeList.get(i).getText();
                    annotatedTree.scopesOfNodes.put(terminalNodeList.get(i), scope);
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
        // varlist '=' explist
        // TODO:

        //'function' funcname funcbody
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
                LuaParser.ExpContext expContext = null;
                TerminalNode terminalNode = null;
                String terminalNodeText = null;
                for (int idx = 0; idx < expContextList.size(); idx++) {
                    expContext = expContextList.get(idx);
                    terminalNode = terminalNodeList.get(idx);
                    terminalNodeText = terminalNode.getSymbol().getText();

                    Symbol.Type symbolType = Symbol.Type.SYMBOL_TYPE_UNKNOWN;
                    Symbol symbolExp = this.annotatedTree.symbolsOfNodes.get(expContext);
                    if (symbolExp != null) {
                        symbolType = symbolExp.getType();
                    }

                    Symbol symbolTerminal = new Symbol(terminalNodeText, symbolType);
                    this.annotatedTree.symbolsOfNodes.put(terminalNode, symbolTerminal);
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
        LuaParser.NumberContext i = ctx.number();
        Symbol.Type st = Symbol.Type.SYMBOL_TYPE_UNKNOWN;
        if (i != null) {
            st = Symbol.Type.SYMBOL_TYPE_LUA_NUMBER;
        }
        LuaParser.StringContext s = ctx.string();
        if (s != null) {
            st = Symbol.Type.SYMBOL_TYPE_LUA_STRING;
        }
        TerminalNode bt = ctx.TRUE();
        if (bt != null) {
            st = Symbol.Type.SYMBOL_TYPE_LUA_BOOLEAN;
        }
        TerminalNode bf = ctx.FALSE();
        if (bf != null) {
            st = Symbol.Type.SYMBOL_TYPE_LUA_BOOLEAN;
        }

        if (st != Symbol.Type.SYMBOL_TYPE_UNKNOWN) {
            String name = ctx.getText();
            Symbol symbol = new Symbol(name, st);
            this.annotatedTree.symbolsOfNodes.put(ctx, symbol);
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
            LuaParser.ExpContext l = expContextList.get(0);
            Symbol symbolL = this.annotatedTree.symbolsOfNodes.get(l);
            if (symbolL == null) {
                String lText = l.getText();
                Scope curScope = this.scopeStack.peek();
                symbolL = curScope.resolve(lText);
            }
            LuaParser.ExpContext r = expContextList.get(1);
            Symbol symbolR = this.annotatedTree.symbolsOfNodes.get(r);
            if (symbolR == null) {
                String rText = r.getText();
                Scope curScope = this.scopeStack.peek();
                symbolR = curScope.resolve(rText);
            }
            if (symbolL.getType() == Symbol.Type.SYMBOL_TYPE_LUA_NUMBER && symbolR.getType() == Symbol.Type.SYMBOL_TYPE_LUA_NUMBER) {
                String name = ctx.getText();
                Symbol symbol = new Symbol(name, Symbol.Type.SYMBOL_TYPE_LUA_NUMBER);
                this.annotatedTree.symbolsOfNodes.put(ctx, symbol);
            }
            if (ctx.PLUS() != null) {
                if (symbolL.getType() == Symbol.Type.SYMBOL_TYPE_LUA_STRING && symbolR.getType() == Symbol.Type.SYMBOL_TYPE_LUA_STRING) {
                    String name = ctx.getText();
                    Symbol symbol = new Symbol(name, Symbol.Type.SYMBOL_TYPE_LUA_STRING);
                    this.annotatedTree.symbolsOfNodes.put(ctx, symbol);
                }
            }
        } else {
            //TODO: other case
            if (this.annotatedTree.symbolsOfNodes.get(ctx) == null) {
                String ctxText = ctx.getText();
                Scope curScope = this.scopeStack.peek();
                Symbol symbol = curScope.resolve(ctxText);
                if (symbol != null) {
                    this.annotatedTree.symbolsOfNodes.put(ctx, symbol);
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
