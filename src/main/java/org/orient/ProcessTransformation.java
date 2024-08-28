package org.orient;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class ProcessTransformation extends LuaParserBaseListener {

    private final AnnotatedTree annotatedTree;

    BufferedTokenStream tokens;
    TokenStreamRewriter rewriter;

    public ProcessTransformation(AnnotatedTree annotatedTree, BufferedTokenStream tokens) {
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
            Token cmt = cmtChannel.get(0);
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
                TerminalNode terminalNode = null;
                for (int idx = 0; idx < expContextList.size(); idx++) {
                    terminalNode = terminalNodeList.get(idx);

                    Symbol symbol = this.annotatedTree.symbolsOfNodes.get(terminalNode);
                    Token t = ctx.start;
                    switch (symbol.getType()) {
                        case Symbol.Type.SYMBOL_TYPE_LUA_BOOLEAN -> {
                            rewriter.replace(t, "bool");
                        }
                        case Symbol.Type.SYMBOL_TYPE_LUA_NUMBER -> {
                            rewriter.replace(t, "int");
                        }
                        case Symbol.Type.SYMBOL_TYPE_LUA_STRING -> {
                            rewriter.replace(t, "string");
                        }
                    }
                }
            } else {

            }
        }

        //'function' funcname funcbody
        LuaParser.FuncnameContext funcnameContext = ctx.funcname();
        if (funcnameContext != null) {
            List<TerminalNode> names = funcnameContext.NAME();
            int sz = names.size();
            if (1 == sz) {

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