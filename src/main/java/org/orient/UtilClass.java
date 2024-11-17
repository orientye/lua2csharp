package org.orient;

import org.antlr.v4.runtime.tree.TerminalNode;

public class UtilClass {

    public static void TryAddClass(String name, AnnotatedTree annotatedTree) {
        String className = UtilClass.GetClassNameFromFuncName(name);
        if (className != null) {
            Class cls = annotatedTree.classes.get(className);
            if (cls == null) {
                cls = new Class(className);
                annotatedTree.classes.put(className, cls);
                System.out.println("Added Class: " + cls.getName());
            }
        }
    }

    public static boolean TryAddClass(Symbol.Type symbolType, String name, AnnotatedTree annotatedTree) {
        if (symbolType == Symbol.Type.SYMBOL_TYPE_CLASS) {
            Class cls = annotatedTree.classes.get(name);
            if (cls == null) {
                cls = new Class(name);
                annotatedTree.classes.put(name, cls);
                System.out.println("Added Class: " + cls.getName());
            }
            return true;
        }
        return false;
    }

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

    public static Symbol.Type GetExpContextTypeInClass(LuaParser.ExpContext ctx, String funcName, AnnotatedTree annotatedTree) {
        String className = GetClassNameFromFuncName(funcName);
        if (className != null) {
            LuaParser.PrefixexpContext prefixexpContext = ctx.prefixexp();
            if (prefixexpContext != null) {
                String prefix = prefixexpContext.getText();// self or ClassName
                int index = prefix.indexOf(".");
                String fieldName = prefix.substring(index + 1);
                Class cls = annotatedTree.classes.get(className);
                Symbol symbol = cls.fields.get(fieldName);
                return symbol.getType();
            }
        }
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

    public static String GetClassName(LuaParser.VarContext varContext) {
        LuaParser.PrefixexpContext prefixexpContext = varContext.prefixexp();
        TerminalNode dotTerminalNode = varContext.DOT();
        if (dotTerminalNode != null && prefixexpContext != null) {
            return prefixexpContext.getText();// self or ClassName
        }
        return null;
    }

    public static String GetMemberVariableName(LuaParser.VarContext varContext, String scopeName) {
        LuaParser.PrefixexpContext prefixexpContext = varContext.prefixexp();
        TerminalNode dotTerminalNode = varContext.DOT();
        if (dotTerminalNode != null && prefixexpContext != null) {
            String prefix = prefixexpContext.getText();// self or ClassName
            int index = scopeName.indexOf(":");
            if (index == -1) {
                index = scopeName.indexOf(".");
            }
            if (index != -1) {
                String className = scopeName.substring(0, index);
                if (prefix.equals(className) || prefix.equals("self")) {
                    TerminalNode nameTerminalNode = varContext.NAME();
                    return nameTerminalNode.getText();
                }
            } else {
                TerminalNode name = varContext.NAME();
                return name.getText();
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

    public static void TryAddField(LuaParser.VarContext varContext, String scopeName, Symbol.Type symbolType, AnnotatedTree annotatedTree) {
        LuaParser.PrefixexpContext prefixexpContext = varContext.prefixexp();
        if (prefixexpContext != null) {
            String fieldName = UtilClass.GetMemberVariableName(varContext, scopeName);
            assert (fieldName != null);
            String className = UtilClass.GetClassNameFromFuncName(scopeName);
            if (className == null) {
                className = GetClassName(varContext);
            }
            assert (className != null);
            Class cls = annotatedTree.classes.get(className);
            if (cls != null) {
                Symbol symbol = Symbol.create(fieldName, symbolType, prefixexpContext, annotatedTree);
                cls.fields.put(fieldName, symbol);
            }
        }
    }

    public static void TryAddField(LuaParser.TableconstructorContext tableconstructorContext, String className, AnnotatedTree annotatedTree) {
        Class cls = annotatedTree.classes.get(className);
        if (cls != null) {
            LuaParser.FieldlistContext fieldlistContext = tableconstructorContext.fieldlist();
            if (fieldlistContext != null) {
                for (int i = 0; i < fieldlistContext.field().size(); i++) {
                    LuaParser.FieldContext fieldContext = fieldlistContext.field(i);
                    //'[' exp ']' '=' exp
                    //TODO:

                    //NAME '=' exp
                    TerminalNode terminalNode = fieldContext.NAME();
                    if (terminalNode != null) {
                        assert (fieldContext.exp().size() == 1);
                        String fieldName = terminalNode.getText();
                        LuaParser.ExpContext expContext = fieldContext.exp().getFirst();
                        Symbol.Type symbolType = Util.GetExpContextTypeInTree(expContext, annotatedTree);
                        Symbol symbol = Symbol.create(fieldName, symbolType, fieldlistContext, annotatedTree);
                        cls.fields.put(fieldName, symbol);
                    }

                    //exp
                    //TODO:
                }
            }
        }
    }

    public static String GetClassFields(String className, AnnotatedTree annotatedTree) {
        Class cls = annotatedTree.classes.get(className);
        StringBuilder sb = new StringBuilder();
        cls.fields.forEach((key, value) -> {
            sb.append("private ").append(Util.SymbolType2Str(value.getType())).append(" ").append(key).append(";\n");
        });

        //TODO: for format, could be optimized
        if (!sb.isEmpty()) {
            int len = sb.length();
            sb.delete(len - 2, len);
        }

        return sb.isEmpty() ? null : sb.toString();
    }
}
