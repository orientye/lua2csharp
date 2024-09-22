package org.orient;

public class UtilTable {
    public static boolean IsConstructorFunction(String funcName) {
        if (funcName.equals("ctor") || funcName.endsWith(":ctor") || funcName.endsWith(".ctor"))
            return true;
        if (funcName.equals("new") || funcName.endsWith(":new") || funcName.endsWith(".new"))
            return true;
        return false;
    }
}
