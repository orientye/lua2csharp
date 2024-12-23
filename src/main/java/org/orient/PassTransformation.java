package org.orient;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.Console;
import java.util.List;

public class PassTransformation extends LuaParserBaseListener {

    private final TokenStreamRewriter rewriter;
    private final AnnotatedTree annotatedTree;
    private final BufferedTokenStream tokens;
    private int lastCommentLine = -1;

    public PassTransformation(AnnotatedTree annotatedTree, BufferedTokenStream tokens) {
        this.annotatedTree = annotatedTree;
        this.tokens = tokens;
        this.rewriter = new TokenStreamRewriter(tokens);
    }

    public String getResult() {
        return this.rewriter.getText();
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
                ParseTree parentTree = ctx.getParent();
                if (parentTree instanceof LuaParser.FuncbodyContext funcbodyContext) {
                    ParseTree parentParentTree = parentTree.getParent();
                    if (parentParentTree instanceof LuaParser.StatContext statContext) {
                        assert (statContext.FUNCTION() != null);
                        if (statContext.LOCAL() != null) {
                            this.rewriter.replace(statContext.LOCAL().getSymbol(), "");
                        }
                        List<Symbol.Type> typeList = this.annotatedTree.funcReturns.get(funcbodyContext);
                        this.rewriter.replace(statContext.FUNCTION().getSymbol(), Util.SymbolType2Str(typeList));
                        if (typeList.size() > 1) {
                            this.rewriter.insertBefore(explistContext.exp().getFirst().start, "(");
                            this.rewriter.insertAfter(explistContext.exp().getLast().stop, ")");
                        }
                    } else if (parentParentTree instanceof LuaParser.FunctiondefContext) {
                        throw new UnsupportedOperationException();
                    } else {
                        assert (false);
                    }
                } else if (parentTree instanceof LuaParser.ChunkContext) {
                    List<LuaParser.ExpContext> expContextList = explistContext.exp();
                    assert (expContextList.size() == 1);
                    LuaParser.ExpContext expContext = expContextList.getFirst();
                    Symbol.Type st = Util.GetExpContextTypeInTree(expContext, this.annotatedTree);
                    assert (st == Symbol.Type.SYMBOL_TYPE_LUA_TABLE || st == Symbol.Type.SYMBOL_TYPE_CLASS);

                    //return ClassABC;
                    Token t = retstatContext.RETURN().getSymbol();
                    this.rewriter.replace(t, "}");
                    this.rewriter.replace(expContext.start, "");

                    //local ClassABC = {}
                    ParseTree parseTree = this.annotatedTree.refs.get(expContext);
                    assert (parseTree instanceof TerminalNode);
                    ParseTree parent = parseTree.getParent();
                    assert (parent instanceof LuaParser.AttnamelistContext);
                    ParseTree parentParent = parent.getParent();
                    assert (parentParent instanceof LuaParser.StatContext);
                    LuaParser.StatContext statContext = (LuaParser.StatContext) parentParent;
                    this.rewriter.delete(statContext.EQ().getSymbol());
                    LuaParser.ExplistContext context = statContext.explist();
                    LuaParser.TableconstructorContext tableconstructorContext = context.exp().getFirst().tableconstructor();
                    if (tableconstructorContext != null) {
                        this.rewriter.delete(tableconstructorContext.CCU().getSymbol());

                        LuaParser.FieldlistContext fieldlistContext = tableconstructorContext.fieldlist();
                        if (fieldlistContext != null) {
                            for (int i = 0; i < fieldlistContext.field().size(); i++) {
                                LuaParser.FieldContext fieldContext = fieldlistContext.field(i);
                                //'[' exp ']' '=' exp
                                //TODO:

                                //NAME '=' exp
                                TerminalNode terminalNode = fieldContext.NAME();
                                if (terminalNode != null) {
                                    assert (fieldContext.exp().size() == 1);
                                    Symbol.Type symbolType = Util.GetExpContextTypeInTree(fieldContext.exp().getFirst(), annotatedTree);
                                    this.rewriter.insertBefore(terminalNode.getSymbol(), "private" + " " + Util.SymbolType2Str(symbolType) + " ");
                                }

                                //exp
                                //TODO:
                            }
                        }
                    } else {
                        this.rewriter.delete(expContext.start, expContext.stop);
                        //local CustomClass = class("CustomClass")
                        String classFields = UtilClass.GetClassFields(parseTree.getText(), this.annotatedTree);
                        this.rewriter.replace(context.start, context.stop, "{\n" + classFields);
                    }
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
        Token semi = ctx.getStart();
        int i = semi.getTokenIndex();
        List<Token> cmtChannel = tokens.getHiddenTokensToLeft(i, LuaLexer.COMMENTS);
        //the comment in the last line
        if (cmtChannel != null) {
            for (Token cmt : cmtChannel) {
                int curLine = cmt.getLine();
                if (curLine != lastCommentLine) {
                    String commentTxt = cmt.getText();
                    if (commentTxt.startsWith("--[[")) { // multi line comment
                        String txt = commentTxt.substring(4, commentTxt.length() - 4);
                        String newCmt = "/**\n" + txt.trim() + "\n*/\n";
                        this.rewriter.insertBefore(ctx.start, newCmt);
                        this.rewriter.replace(cmt, "");
                    } else { // single line comment
                        String txt = commentTxt.substring(2);
                        String newCmt = "//" + txt.trim() + "\n";
                        this.rewriter.insertBefore(ctx.start, newCmt);
                        this.rewriter.replace(cmt, "");
                    }
                    lastCommentLine = curLine;
                }
            }
        }
        //the comment in the same line
        cmtChannel = tokens.getHiddenTokensToRight(i, LuaLexer.COMMENTS);
        if (cmtChannel != null) {
            Token cmt = cmtChannel.getFirst();
            int curLine = cmt.getLine();
            if (curLine != lastCommentLine) {
                if (semi.getLine() != curLine) {
                    return;
                }
                String txt = cmt.getText().substring(2);
                String newCmt = "//" + txt.trim() + "\n";
                this.rewriter.insertAfter(ctx.stop, newCmt);
                this.rewriter.replace(cmt, "");
                lastCommentLine = curLine;
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
            if (returnCount == 1) {
                assert (szVarContextList == 1);
                Symbol.Type symbolType = Util.GetExpContextTypeInList(0, explistContext, annotatedTree);
                LuaParser.VarContext varContext = varContextList.getFirst();
                Token t = varContext.start;
                if (varContext.DOT() != null && varContext.prefixexp() != null) {
                    ParseTree parentTree = varlistContext.getParent().getParent().getParent();
                    if (!(parentTree instanceof LuaParser.FuncbodyContext)) {
                        String modifierOfMemberVariable = UtilClass.GetModifierOfMemberVariable(varContext);
                        this.rewriter.insertBefore(t, modifierOfMemberVariable + " " + Util.SymbolType2Str(symbolType) + " ");
                    }
                } else {
                    if (symbolType == Symbol.Type.SYMBOL_TYPE_CLASS) {
                        Class cls = this.annotatedTree.classes.get(varContext.getText());
                        assert(cls != null);
                        this.rewriter.insertBefore(t, cls.getName() + " ");
                        this.rewriter.insertBefore(explistContext.getStart(), "new " + cls.getName() + " ");
                    } else {
                        this.rewriter.insertBefore(t, Util.SymbolType2Str(symbolType) + " ");
                    }
                }
            } else {
                assert (returnCount > 1);
                for (int idx = 0; idx < szVarContextList; idx++) {
                    LuaParser.VarContext varContext = varContextList.get(idx);
                    TerminalNode terminalNode = varContext.NAME();
                    Symbol.Type symbolType = Util.GetExpContextTypeInList(idx, explistContext, annotatedTree);
                    if (terminalNode != null) {
                        Token t = varContext.start;
                        if (idx == 0) {
                            this.rewriter.insertBefore(t, "(" + Util.SymbolType2Str(symbolType) + " ");
                        } else {
                            this.rewriter.insertBefore(t, Util.SymbolType2Str(symbolType) + " ");
                        }
                        if (idx + 1 == szVarContextList) {
                            if (idx + 1 == returnCount) {
                                this.rewriter.insertAfter(t, ")");
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append(", _".repeat(returnCount - idx - 1));
                                sb.append(")");
                                this.rewriter.insertAfter(t, sb);
                            }
                        }
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
            }
        }

        // 'local' attnamelist ('=' explist)?
        LuaParser.AttnamelistContext attnamelistContext = ctx.attnamelist();
        if (attnamelistContext != null) {
            LuaParser.ExplistContext explistContext = ctx.explist();
            if (explistContext != null) {
                int returnCount = Util.GetExpContextReturnCount(explistContext, annotatedTree);
                List<TerminalNode> terminalNodeList = attnamelistContext.NAME();
                int szTerminalNodeList = terminalNodeList.size();
                assert (szTerminalNodeList <= returnCount);
                if (returnCount == 1) {
                    assert (szTerminalNodeList == 1);
                    Symbol.Type symbolType = Util.GetExpContextTypeInList(0, explistContext, annotatedTree);
                    Token t = ctx.start;
                    this.rewriter.replace(t, Util.SymbolType2Str(symbolType));
                } else {
                    assert (returnCount > 1);
                    for (int idx = 0; idx < szTerminalNodeList; idx++) {
                        Symbol.Type symbolType = Util.GetExpContextTypeInList(idx, explistContext, annotatedTree);
                        TerminalNode terminalNode = terminalNodeList.get(idx);
                        Token t = terminalNode.getSymbol();
                        if (idx == 0) {
                            this.rewriter.replace(ctx.start, "(" + Util.SymbolType2Str(symbolType));
                        } else {
                            this.rewriter.insertBefore(t, Util.SymbolType2Str(symbolType) + " ");
                        }
                        if (idx + 1 == szTerminalNodeList) {
                            if (idx + 1 == returnCount) {
                                this.rewriter.insertAfter(t, ")");
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append(", _".repeat(returnCount - idx - 1));
                                sb.append(")");
                                this.rewriter.insertAfter(t, sb);
                            }
                        }
                    }
                }
            } else {
                throw new UnsupportedOperationException();
            }
        }

        // 'function' funcname funcbody
        // local' 'function' NAME funcbody
        LuaParser.FuncbodyContext funcbodyContext = ctx.funcbody();
        if (funcbodyContext != null) {
            //return
            assert (ctx.FUNCTION() != null);
            if (ctx.LOCAL() != null) {
                this.rewriter.replace(ctx.LOCAL().getSymbol(), "");
            }
            LuaParser.FuncnameContext funcnameContext = ctx.funcname();
            List<Symbol.Type> typeList = this.annotatedTree.funcReturns.get(funcbodyContext);
            if (typeList != null) {
                String className = null;
                if (funcnameContext != null) {
                    String funcName = funcnameContext.getText();
                    className = UtilClass.GetClassNameFromFuncName(funcName);
                }
                if (className != null) {
                    this.rewriter.replace(ctx.FUNCTION().getSymbol(), "public " + Util.SymbolType2Str(typeList));
                } else {
                    this.rewriter.replace(ctx.FUNCTION().getSymbol(), Util.SymbolType2Str(typeList));
                }
            } else {
                if (funcnameContext != null) {
                    String funcName = funcnameContext.getText();
                    if (UtilClass.IsConstructorFunction(funcName)) {
                        this.rewriter.replace(ctx.FUNCTION().getSymbol(), "public");
                    } else {
                        this.rewriter.replace(ctx.FUNCTION().getSymbol(), "void");
                    }
                } else {
                    this.rewriter.replace(ctx.FUNCTION().getSymbol(), "void");
                }
            }

            //funcname
            if (funcnameContext != null) {
                if (funcnameContext.COL() != null) {
                    List<TerminalNode> list = funcnameContext.NAME();
                    String className = list.getFirst().getText();
                    String funcName = list.getLast().getText();
                    this.rewriter.delete(list.getFirst().getSymbol()); // delete className
                    this.rewriter.delete(funcnameContext.COL().getSymbol()); // delete ':'
                    if (UtilClass.IsConstructorFunction(funcName)) { //CustomClass
                        this.rewriter.replace(list.getLast().getSymbol(), className);
                    }
                }
            }

            //prarmas
            LuaParser.ParlistContext parlistContext = funcbodyContext.parlist();
            LuaParser.NamelistContext namelistContext = parlistContext.namelist();// TODO: ... Varargs
            if (namelistContext != null) {
                List<TerminalNode> terminalNodes = namelistContext.NAME();
                for (TerminalNode terminalNode : terminalNodes) {
                    Symbol terminalNodeSymbol = this.annotatedTree.symbols.get(terminalNode);
                    Symbol.Type st = terminalNodeSymbol.getType();
                    this.rewriter.insertBefore(terminalNode.getSymbol(), Util.SymbolType2Str(st) + " ");
                }
            }

            TerminalNode cp = funcbodyContext.CP();
            this.rewriter.insertAfter(cp.getSymbol(), "\n{");
            TerminalNode end = funcbodyContext.END();
            this.rewriter.replace(end.getSymbol(), "}");
        }

        UtilFormat.TryAddSemicolon(ctx, this.tokens, this.rewriter);
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
        if (ctx.DD() != null) {
            this.rewriter.replace(ctx.DD().getSymbol(), "+"); //print("Hello " .. t.name)
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void enterVar(LuaParser.VarContext ctx) {
        //self.id_ = 0 or classABC._id = 0
        LuaParser.PrefixexpContext prefixexpContext = ctx.prefixexp();
        TerminalNode dotTerminalNode = ctx.DOT();
        if (dotTerminalNode != null && prefixexpContext != null) {
            //String prefix = prefixexpContext.getText();
            //if (prefix.equals("self")) { //TODO:
            this.rewriter.delete(prefixexpContext.start, prefixexpContext.stop);
            this.rewriter.delete(dotTerminalNode.getSymbol());
            //}
        }
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
        //return self.id_
        String prefix = ctx.getText();
        if (prefix.startsWith("self.")) {
            this.rewriter.delete(ctx.start);
            this.rewriter.delete(ctx.DOT().getFirst().getSymbol());
        }
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
        if (ctx.COL() != null) {
            this.rewriter.replace(ctx.COL().getSymbol(), "."); //t:sayHello() -> t.sayHello()
        }

        Token token = ctx.start;
        String funcName = token.getText();
        //TODO: generalization
        if (funcName.equals("print")) {
            this.rewriter.replace(token, "Console.WriteLine");
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