package org.orient;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class UtilFormat {

    public static void TryAddSemicolon(LuaParser.StatContext ctx, BufferedTokenStream tokens, TokenStreamRewriter rewriter) {
        if (IsNecessaryAddSemicolon(ctx, tokens)) {
            rewriter.insertAfter(ctx.stop, ";");
        }
    }

    private static boolean IsNecessaryAddSemicolon(LuaParser.StatContext ctx, BufferedTokenStream tokens) {
        boolean bCheck = false;
        //varlist '=' explist
        LuaParser.VarlistContext varlistContext = ctx.varlist();
        if (varlistContext != null) {
            bCheck = true;
        }

        //functioncall
        LuaParser.FunctioncallContext functioncallContext = ctx.functioncall();
        if (functioncallContext != null) {
            bCheck = true;
        }

        // 'local' attnamelist ('=' explist)?
        LuaParser.AttnamelistContext attnamelistContext = ctx.attnamelist();
        if (attnamelistContext != null) {
            bCheck = true;
        }

        if (bCheck) {
            int i = ctx.stop.getTokenIndex();
            Token nextToken = tokens.get(i+1);
            if (nextToken != null) {
                if (!nextToken.getText().equals(";")) {
                    return true;
                }
            }
        }
        return false;
    }
}
