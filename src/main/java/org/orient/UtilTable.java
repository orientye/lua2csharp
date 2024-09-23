package org.orient;

import org.antlr.v4.runtime.tree.TerminalNode;

public class UtilTable {
    public static boolean IsConstructorFunction(String funcName) {
        if (funcName.equals("ctor") || funcName.endsWith(":ctor") || funcName.endsWith(".ctor"))
            return true;
        if (funcName.equals("new") || funcName.endsWith(":new") || funcName.endsWith(".new"))
            return true;
        return false;
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
}
