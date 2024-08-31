package org.orient;

import org.antlr.v4.runtime.tree.ParseTree;

public class Symbol {

    private final String name;
    private final Type ty;
    private final ParseTree parseTree;

    public Symbol(String name, Type type, ParseTree parseTree, AnnotatedTree annotatedTree) {
        this.name = name;
        this.ty = type;
        this.parseTree = parseTree;

        annotatedTree.symbols.put(parseTree, this);
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
        SYMBOL_TYPE_UNKNOWN, SYMBOL_TYPE_LUA_NIL, SYMBOL_TYPE_LUA_BOOLEAN, SYMBOL_TYPE_LUA_LIGHTUSERDATA, SYMBOL_TYPE_LUA_NUMBER, SYMBOL_TYPE_LUA_STRING, SYMBOL_TYPE_LUA_TABLE, SYMBOL_TYPE_LUA_FUNCTION, SYMBOL_TYPE_LUA_USERDATA, SYMBOL_TYPE_LUA_THREAD
    }

    //https://github.com/antlr/grammars-v4/blob/master/csharp/CSharpParser.g4
    public enum CSharpType {
        SYMBOL_TYPE_UNKNOWN,
    }
}
