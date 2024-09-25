package org.orient;

import org.antlr.v4.runtime.tree.ParseTree;

public class Symbol {

    private String name;
    private Type ty;
    private ParseTree parseTree;

    private Symbol() {
    }

    public static Symbol create(String name, Type type, ParseTree parseTree, AnnotatedTree annotatedTree) {
        System.out.println("Symbol create name[" + name +"]" + " type[" + type +"]");
        Symbol symbol = new Symbol();
        symbol.name = name;
        symbol.ty = type;
        symbol.parseTree = parseTree;
        annotatedTree.symbols.put(parseTree, symbol);
        return symbol;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return ty;
    }

    public ParseTree getParseTree() {
        return parseTree;
    }

    public String toString() {
        if (ty != Type.SYMBOL_TYPE_UNKNOWN) return "Symbol<" + getName() + ":" + ty + ">";
        return getName();
    }

    public enum Type {
        SYMBOL_TYPE_UNKNOWN,

        SYMBOL_TYPE_LUA_NIL,

        //SYMBOL_TYPE_LUA_BOOLEAN,
        SYMBOL_TYPE_BOOLEAN,

        //SYMBOL_TYPE_LUA_LIGHTUSERDATA,

        //SYMBOL_TYPE_LUA_NUMBER,
        SYMBOL_TYPE_INT, SYMBOL_TYPE_FLOAT,

        //SYMBOL_TYPE_LUA_STRING,
        SYMBOL_TYPE_STRING,

        SYMBOL_TYPE_LUA_TABLE,
        SYMBOL_TYPE_CLASS,

        //SYMBOL_TYPE_LUA_FUNCTION,
        SYMBOL_TYPE_FUNCTION,

        //SYMBOL_TYPE_LUA_USERDATA,

        //SYMBOL_TYPE_LUA_THREAD
    }

    //https://github.com/antlr/grammars-v4/blob/master/csharp/CSharpParser.g4
}
