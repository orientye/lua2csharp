package org.orient;

public class Symbol {

    private final String name;
    private final Type ty;

    private Scope scope;

    public Symbol(String name, Type type) {
        this.name = name;
        this.ty = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return ty;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public String toString() {
        if (ty != Type.SYMBOL_TYPE_UNKNOWN) return "Symbol<" + getName() + ":" + ty + ">";
        return getName();
    }

    public enum Type {
        SYMBOL_TYPE_UNKNOWN, SYMBOL_TYPE_LUA_NIL, SYMBOL_TYPE_LUA_BOOLEAN, SYMBOL_TYPE_LUA_LIGHTUSERDATA, SYMBOL_TYPE_LUA_NUMBER, SYMBOL_TYPE_LUA_STRING, SYMBOL_TYPE_LUA_TABLE, SYMBOL_TYPE_LUA_FUNCTION, SYMBOL_TYPE_LUA_USERDATA, SYMBOL_TYPE_LUA_THREAD
    }
}
