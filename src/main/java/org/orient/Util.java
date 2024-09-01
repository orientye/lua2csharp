package org.orient;

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
