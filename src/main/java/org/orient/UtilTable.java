package org.orient;

import org.antlr.v4.runtime.tree.TerminalNode;

public class UtilTable {
    public static String GetClassNameFromFuncName(String funcName) {
        int index = funcName.indexOf(":");
        if (index != -1) {
            String className = funcName.substring(0, index);
            return className;
        }
        index = funcName.indexOf(".");
        if (index != -1) {
            String className = funcName.substring(0, index);
            return className;
        }
        return "UnknownClassName";
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

    public static String GetMemberVariableSymbolName(LuaParser.VarContext varContext, String scopeName) {
        LuaParser.PrefixexpContext prefixexpContext = varContext.prefixexp();
        TerminalNode dotTerminalNode = varContext.DOT();
        if (dotTerminalNode != null && prefixexpContext != null) {
            String prefix = prefixexpContext.getText();// self or ClassName
                int index = scopeName.indexOf(":");
                assert (index != -1);
                String className = scopeName.substring(0, index);
                if (prefix.equals(className) || prefix.equals("self")) {
                    TerminalNode nameTerminalNode = varContext.NAME();
                    String name = nameTerminalNode.getText();
                    String symbolName = className + "." + name;
                    return symbolName;
                }
        }
        return "";
    }
}
