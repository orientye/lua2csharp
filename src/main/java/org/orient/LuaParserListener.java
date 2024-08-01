// Generated from LuaParser.g4 by ANTLR 4.13.1
package org.orient;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LuaParser}.
 */
public interface LuaParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LuaParser#start_}.
	 * @param ctx the parse tree
	 */
	void enterStart_(LuaParser.Start_Context ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#start_}.
	 * @param ctx the parse tree
	 */
	void exitStart_(LuaParser.Start_Context ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#chunk}.
	 * @param ctx the parse tree
	 */
	void enterChunk(LuaParser.ChunkContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#chunk}.
	 * @param ctx the parse tree
	 */
	void exitChunk(LuaParser.ChunkContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(LuaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(LuaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(LuaParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(LuaParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#attnamelist}.
	 * @param ctx the parse tree
	 */
	void enterAttnamelist(LuaParser.AttnamelistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#attnamelist}.
	 * @param ctx the parse tree
	 */
	void exitAttnamelist(LuaParser.AttnamelistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#attrib}.
	 * @param ctx the parse tree
	 */
	void enterAttrib(LuaParser.AttribContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#attrib}.
	 * @param ctx the parse tree
	 */
	void exitAttrib(LuaParser.AttribContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#retstat}.
	 * @param ctx the parse tree
	 */
	void enterRetstat(LuaParser.RetstatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#retstat}.
	 * @param ctx the parse tree
	 */
	void exitRetstat(LuaParser.RetstatContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(LuaParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(LuaParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#funcname}.
	 * @param ctx the parse tree
	 */
	void enterFuncname(LuaParser.FuncnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#funcname}.
	 * @param ctx the parse tree
	 */
	void exitFuncname(LuaParser.FuncnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#varlist}.
	 * @param ctx the parse tree
	 */
	void enterVarlist(LuaParser.VarlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#varlist}.
	 * @param ctx the parse tree
	 */
	void exitVarlist(LuaParser.VarlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#namelist}.
	 * @param ctx the parse tree
	 */
	void enterNamelist(LuaParser.NamelistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#namelist}.
	 * @param ctx the parse tree
	 */
	void exitNamelist(LuaParser.NamelistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#explist}.
	 * @param ctx the parse tree
	 */
	void enterExplist(LuaParser.ExplistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#explist}.
	 * @param ctx the parse tree
	 */
	void exitExplist(LuaParser.ExplistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(LuaParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(LuaParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(LuaParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(LuaParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#prefixexp}.
	 * @param ctx the parse tree
	 */
	void enterPrefixexp(LuaParser.PrefixexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#prefixexp}.
	 * @param ctx the parse tree
	 */
	void exitPrefixexp(LuaParser.PrefixexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#functioncall}.
	 * @param ctx the parse tree
	 */
	void enterFunctioncall(LuaParser.FunctioncallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#functioncall}.
	 * @param ctx the parse tree
	 */
	void exitFunctioncall(LuaParser.FunctioncallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(LuaParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(LuaParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#functiondef}.
	 * @param ctx the parse tree
	 */
	void enterFunctiondef(LuaParser.FunctiondefContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#functiondef}.
	 * @param ctx the parse tree
	 */
	void exitFunctiondef(LuaParser.FunctiondefContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#funcbody}.
	 * @param ctx the parse tree
	 */
	void enterFuncbody(LuaParser.FuncbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#funcbody}.
	 * @param ctx the parse tree
	 */
	void exitFuncbody(LuaParser.FuncbodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#parlist}.
	 * @param ctx the parse tree
	 */
	void enterParlist(LuaParser.ParlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#parlist}.
	 * @param ctx the parse tree
	 */
	void exitParlist(LuaParser.ParlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#tableconstructor}.
	 * @param ctx the parse tree
	 */
	void enterTableconstructor(LuaParser.TableconstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#tableconstructor}.
	 * @param ctx the parse tree
	 */
	void exitTableconstructor(LuaParser.TableconstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#fieldlist}.
	 * @param ctx the parse tree
	 */
	void enterFieldlist(LuaParser.FieldlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#fieldlist}.
	 * @param ctx the parse tree
	 */
	void exitFieldlist(LuaParser.FieldlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(LuaParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(LuaParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#fieldsep}.
	 * @param ctx the parse tree
	 */
	void enterFieldsep(LuaParser.FieldsepContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#fieldsep}.
	 * @param ctx the parse tree
	 */
	void exitFieldsep(LuaParser.FieldsepContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(LuaParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(LuaParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(LuaParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(LuaParser.StringContext ctx);
}