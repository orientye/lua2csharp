package org.orient;

import org.antlr.v4.runtime.tree.TerminalNode;

public class UtilTable {
    public static String GetClassNameFromFuncName(String funcName) {
        int index = funcName.indexOf(":");
        if (index != -1) {
            return funcName.substring(0, index);
        }
        index = funcName.indexOf(".");
        if (index != -1) {
            return funcName.substring(0, index);
        }
        return null;
    }

    public static Symbol.Type GetExpContextTypeInClass(LuaParser.ExpContext ctx, String funcName) {
        String className = GetClassNameFromFuncName(funcName);
        return Symbol.Type.SYMBOL_TYPE_UNKNOWN;
    }

    public static boolean IsConstructorFunction(String funcName) {
        if (funcName.equals("ctor") || funcName.endsWith(":ctor") || funcName.endsWith(".ctor")) return true;
        return funcName.equals("new") || funcName.endsWith(":new") || funcName.endsWith(".new");
    }

    public static String GetModifierOfMemberVariable(LuaParser.VarContext varContext) {
        TerminalNode terminalNode = varContext.NAME();
        String name = terminalNode.getText();
        String upperName = name.toUpperCase();
        boolean isAllUpperCase = name.equals(upperName);
        if (isAllUpperCase) {
            return "public const";
        } else {
            return "private";
        }
    }

    public static String GetMemberVariableName(LuaParser.VarContext varContext, String scopeName) {
        LuaParser.PrefixexpContext prefixexpContext = varContext.prefixexp();
        TerminalNode dotTerminalNode = varContext.DOT();
        if (dotTerminalNode != null && prefixexpContext != null) {
            String prefix = prefixexpContext.getText();// self or ClassName
                int index = scopeName.indexOf(":");
                assert (index != -1);
                String className = scopeName.substring(0, index);
                if (prefix.equals(className) || prefix.equals("self")) {
                    TerminalNode nameTerminalNode = varContext.NAME();
                    return nameTerminalNode.getText();
                }
        }
        return null;
    }

    public static String GetMemberVariableName(LuaParser.PrefixexpContext prefixexpContext, String scopeName) {
        if (prefixexpContext != null) {
            String prefix = prefixexpContext.getText();// self or ClassName
            int index = scopeName.indexOf(":");
            if (index != -1) {
                if (prefix.startsWith("self.")) {
                    return prefix.substring(5);
                }
            }
        }
        return null;
    }

    public static void AddField(LuaParser.VarContext varContext, String scopeName, Symbol.Type symbolType, AnnotatedTree annotatedTree) {
        LuaParser.PrefixexpContext prefixexpContext = varContext.prefixexp();
        if (prefixexpContext != null) {
            String fieldName = UtilTable.GetMemberVariableName(varContext, scopeName);
            assert (fieldName != null);
            Symbol symbol = Symbol.create(fieldName, symbolType, prefixexpContext, annotatedTree);
            String className = UtilTable.GetClassNameFromFuncName(scopeName);
            assert (className != null);
            Class cls = annotatedTree.classes.get(className);
            assert (cls != null);
            cls.fields.put(fieldName, symbol);
        }
    }
}
