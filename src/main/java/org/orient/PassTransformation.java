package org.orient;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class PassTransformation extends LuaParserBaseListener {

    private final AnnotatedTree annotatedTree;

    BufferedTokenStream tokens;
    TokenStreamRewriter rewriter;

    public PassTransformation(AnnotatedTree annotatedTree, BufferedTokenStream tokens) {
        this.annotatedTree = annotatedTree;
        this.tokens = tokens;
        rewriter = new TokenStreamRewriter(tokens);
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
                    LuaParser.StatContext statContext = (LuaParser.StatContext) parentParentTree;
                    assert(statContext.FUNCTION() != null);
                    if (statContext.LOCAL() != null) {
                        rewriter.replace(statContext.LOCAL().getSymbol(), "");
                    }
                    List<Symbol.Type> typeList = this.annotatedTree.funcReturns.get(funcbodyContext);
                    //TODO: multi return value
                    for (int i = 0; i < expContextList.size(); i++) {
                        Symbol.Type st = typeList.get(i);
                        rewriter.replace(statContext.FUNCTION().getSymbol(), Util.SymbolType2Str(st));
                    }
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
        Token semi = ctx.getStart();
        int i = semi.getTokenIndex();
        List<Token> cmtChannel = tokens.getHiddenTokensToLeft(i, LuaLexer.COMMENTS);
        if (cmtChannel != null) {
            Token cmt = cmtChannel.getFirst();
            if (cmt != null) {
                String txt = cmt.getText().substring(2);
                String newCmt = "/* " + txt.trim() + " */\n";
                rewriter.insertBefore(ctx.start, newCmt);
                rewriter.replace(cmt, "");
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
        // 'local' attnamelist ('=' explist)?
        LuaParser.AttnamelistContext attnamelistContext = ctx.attnamelist();
        if (attnamelistContext != null) {
            LuaParser.ExplistContext explistContext = ctx.explist();
            if (explistContext != null) {
                List<LuaParser.ExpContext> expContextList = explistContext.exp();
                List<TerminalNode> terminalNodeList = attnamelistContext.NAME();
                assert (expContextList.size() == terminalNodeList.size());
                TerminalNode terminalNode;
                for (int idx = 0; idx < expContextList.size(); idx++) {
                    terminalNode = terminalNodeList.get(idx);
                    Symbol symbol = this.annotatedTree.symbols.get(terminalNode);
                    Token t = ctx.start;
                    rewriter.replace(t, Util.SymbolType2Str(symbol.getType()));
                }
            } else {

            }
        }

        // 'function' funcname funcbody
        // local' 'function' NAME funcbody
        LuaParser.FuncbodyContext funcbodyContext = ctx.funcbody();
        if (funcbodyContext != null) {
            LuaParser.ParlistContext parlistContext = funcbodyContext.parlist();
            LuaParser.NamelistContext namelistContext = parlistContext.namelist();// TODO: ... Varargs
            List<TerminalNode> terminalNodes = namelistContext.NAME();
            for (TerminalNode terminalNode : terminalNodes) {
                Symbol terminalNodeSymbol = annotatedTree.symbols.get(terminalNode);
                Symbol.Type st = terminalNodeSymbol.getType();
                rewriter.insertBefore(terminalNode.getSymbol(), Util.SymbolType2Str(st) + " ");
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
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void exitExp(LuaParser.ExpContext ctx) {
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
        Token token = ctx.start;
        String funcName = token.getText();
        //TODO: generalization
        if (funcName.equals("print")) {
            rewriter.replace(token, "Console.WriteLine");
        }
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