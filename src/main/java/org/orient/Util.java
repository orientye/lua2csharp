package org.orient;

import java.util.List;

public class Util {

    public static Symbol.Type GetExpContextType(LuaParser.ExpContext ctx) {
        if (ctx.number() != null) {
            if (ctx.number().INT() != null || ctx.number().HEX() != null) {
                return Symbol.Type.SYMBOL_TYPE_INT;
            }
            if (ctx.number().FLOAT() != null || ctx.number().HEX_FLOAT() != null) {
                return Symbol.Type.SYMBOL_TYPE_FLOAT;
            }
        }
        if (ctx.string() != null) {
            return Symbol.Type.SYMBOL_TYPE_STRING;
        }
        if (ctx.TRUE() != null) {
            return Symbol.Type.SYMBOL_TYPE_BOOLEAN;
        }
        if (ctx.FALSE() != null) {
            return Symbol.Type.SYMBOL_TYPE_BOOLEAN;
        }
        return Symbol.Type.SYMBOL_TYPE_UNKNOWN;
    }

    public static Symbol.Type GetExpContextTypeInTree(LuaParser.ExpContext ctx, AnnotatedTree annotatedTree) {
        assert(annotatedTree  != null);
        Symbol.Type st = GetExpContextType(ctx);
        if (st == Symbol.Type.SYMBOL_TYPE_UNKNOWN) {
            Symbol symbol = annotatedTree.symbols.get(ctx);
            if (symbol != null)
            {
                st = symbol.getType();
            }
        }
        if (st == Symbol.Type.SYMBOL_TYPE_UNKNOWN) {
            List<Symbol.Type> typeList = annotatedTree.funcReturns.get(ctx);
            if (typeList != null)
            {
                st = typeList.getFirst();
            }
        }
        return st;
    }

    public static String SymbolType2Str(Symbol.Type st) {
        switch (st) {
            case Symbol.Type.SYMBOL_TYPE_BOOLEAN -> {
                return "bool";
            }
            case Symbol.Type.SYMBOL_TYPE_INT -> {
                return "int";
            }
            case Symbol.Type.SYMBOL_TYPE_FLOAT -> {
                return "float";
            }
            case Symbol.Type.SYMBOL_TYPE_STRING -> {
                return "string";
            }
        }
        return "unknown";
    }
}
