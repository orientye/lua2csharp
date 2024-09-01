package org.orient;

public class Util {

    public static Symbol.Type GetExpContextType(LuaParser.ExpContext ctx) {
        if (ctx.number() != null) {
            return Symbol.Type.SYMBOL_TYPE_LUA_NUMBER;
        }
        if (ctx.string() != null) {
            return Symbol.Type.SYMBOL_TYPE_LUA_STRING;
        }
        if (ctx.TRUE() != null) {
            return Symbol.Type.SYMBOL_TYPE_LUA_BOOLEAN;
        }
        if (ctx.FALSE() != null) {
            return Symbol.Type.SYMBOL_TYPE_LUA_BOOLEAN;
        }
        return Symbol.Type.SYMBOL_TYPE_UNKNOWN;
    }

    public static String SymbolType2Str(Symbol.Type st) {
        switch (st) {
            case Symbol.Type.SYMBOL_TYPE_LUA_BOOLEAN -> {
                return "bool";
            }
            case Symbol.Type.SYMBOL_TYPE_LUA_NUMBER -> {
                return "int";
            }
            case Symbol.Type.SYMBOL_TYPE_LUA_STRING -> {
                return "string";
            }
        }
        return "unknown";
    }
}
