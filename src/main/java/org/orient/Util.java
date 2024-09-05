package org.orient;

import java.util.List;
import java.util.Stack;

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
        assert (annotatedTree != null);
        Symbol.Type st = GetExpContextType(ctx);
        if (st == Symbol.Type.SYMBOL_TYPE_UNKNOWN) {
            Symbol symbol = annotatedTree.symbols.get(ctx);
            if (symbol != null) {
                st = symbol.getType();
            }
        }
        if (st == Symbol.Type.SYMBOL_TYPE_UNKNOWN) {
            List<Symbol.Type> typeList = annotatedTree.funcReturns.get(ctx);
            if (typeList != null) {
                st = typeList.getFirst();
            }
        }
        return st;
    }

    public static Symbol.Type GetExpContextTypeInTreeWithResolve(LuaParser.ExpContext ctx, AnnotatedTree annotatedTree, Stack<Scope> scopeStack) {
        assert (annotatedTree != null);
        assert (scopeStack != null);
        Symbol.Type st = GetExpContextType(ctx);
        if (st == Symbol.Type.SYMBOL_TYPE_UNKNOWN) {
            Symbol symbol = annotatedTree.symbols.get(ctx);
            if (symbol != null) {
                st = symbol.getType();
            }
        }
        if (st == Symbol.Type.SYMBOL_TYPE_UNKNOWN) {
            List<LuaParser.ExpContext> expContextList = ctx.exp();
            if (ctx.PLUS() != null || ctx.MINUS() != null || ctx.STAR() != null || ctx.SLASH() != null) {
                assert (expContextList.size() == 2);
                LuaParser.ExpContext l = expContextList.getFirst();
                Symbol symbolL = annotatedTree.symbols.get(l);
                if (symbolL == null) {
                    String lText = l.getText();
                    Scope curScope = scopeStack.peek();
                    symbolL = curScope.resolve(lText);
                }
                LuaParser.ExpContext r = expContextList.get(1);
                Symbol symbolR = annotatedTree.symbols.get(r);
                if (symbolR == null) {
                    String rText = r.getText();
                    Scope curScope = scopeStack.peek();
                    symbolR = curScope.resolve(rText);
                }
                if (symbolL != null && symbolR != null) {
                    if (symbolL.getType() == Symbol.Type.SYMBOL_TYPE_INT && symbolR.getType() == Symbol.Type.SYMBOL_TYPE_INT) {
//                        String name = ctx.getText();
//                        Symbol.create(name, Symbol.Type.SYMBOL_TYPE_INT, ctx, annotatedTree);
                        return Symbol.Type.SYMBOL_TYPE_INT;
                    }
                    if (symbolL.getType() == Symbol.Type.SYMBOL_TYPE_FLOAT && symbolR.getType() == Symbol.Type.SYMBOL_TYPE_FLOAT) {
//                        String name = ctx.getText();
//                        Symbol.create(name, Symbol.Type.SYMBOL_TYPE_FLOAT, ctx, annotatedTree);
                        return Symbol.Type.SYMBOL_TYPE_FLOAT;
                    }
                    if (ctx.PLUS() != null) {
                        if (symbolL.getType() == Symbol.Type.SYMBOL_TYPE_STRING && symbolR.getType() == Symbol.Type.SYMBOL_TYPE_STRING) {
//                            String name = ctx.getText();
//                            Symbol.create(name, Symbol.Type.SYMBOL_TYPE_STRING, ctx, annotatedTree);
                            return Symbol.Type.SYMBOL_TYPE_STRING;
                        }
                    }
                }
            }
        }
        if (st == Symbol.Type.SYMBOL_TYPE_UNKNOWN) {
            List<Symbol.Type> typeList = annotatedTree.funcReturns.get(ctx);
            if (typeList != null) {
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
