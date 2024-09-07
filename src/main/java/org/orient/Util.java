package org.orient;

import java.util.ArrayList;
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
        Symbol.Type st = GetExpContextType(ctx);
        if (st == Symbol.Type.SYMBOL_TYPE_UNKNOWN) {
            Symbol symbol = annotatedTree.symbols.get(ctx);
            if (symbol != null) {
                st = symbol.getType();
            }
        }
        return st;
    }

    public static List<Symbol.Type> GetExpContextMultiTypeInTree(LuaParser.ExpContext ctx, AnnotatedTree annotatedTree) {
        Symbol.Type st = GetExpContextTypeInTree(ctx, annotatedTree);
        if (st != Symbol.Type.SYMBOL_TYPE_UNKNOWN) {
            List<Symbol.Type> typeList = new ArrayList<>();
            typeList.add(st);
            return typeList;
        }

        List<Symbol.Type> typeList = annotatedTree.funcReturns.get(ctx);
        return typeList;
    }

    public static Symbol.Type GetExpContextTypeInList(int idx, LuaParser.ExplistContext explistContext, AnnotatedTree annotatedTree) {
        Symbol.Type symbolType;
        List<LuaParser.ExpContext> expContextList = explistContext.exp();
        int szExpContextList = expContextList.size();
        assert (szExpContextList > 0);
        if (1 == szExpContextList) {
            LuaParser.ExpContext expContext = expContextList.getFirst();
            List<Symbol.Type> typeList = Util.GetExpContextMultiTypeInTree(expContext, annotatedTree);
            symbolType = typeList.get(idx);
        } else {
            LuaParser.ExpContext expContext = expContextList.get(idx);
            List<Symbol.Type> typeList = Util.GetExpContextMultiTypeInTree(expContext, annotatedTree);
            symbolType = typeList.getFirst();
        }
        return symbolType;
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

    public static String SymbolType2Str(List<Symbol.Type> typeList) {
        int sz = typeList.size();
        assert (sz >= 0);
        if (typeList.size() == 1) {
            return SymbolType2Str(typeList.getFirst());
        }
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < sz; i++) {
            String s = SymbolType2Str(typeList.get(i));
            sb.append(s);
            if (i != (sz - 1)) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
