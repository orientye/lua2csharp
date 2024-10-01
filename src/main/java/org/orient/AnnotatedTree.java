package org.orient;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotatedTree {
    final Map<ParseTree, Scope> scopes = new HashMap<>();
    final Map<ParseTree, Symbol> symbols = new HashMap<>();
    final Map<ParseTree, List<Symbol.Type>> funcReturns = new HashMap<>();
    final Map<ParseTree, ParseTree> refs = new HashMap<>();
    final Map<String, Class> classes = new HashMap<>();
    private final ParseTree ast;

    public AnnotatedTree(ParseTree parseTree) {
        this.ast = parseTree;
    }

    public void dump() {
        System.out.println(ast.toStringTree());
    }
}
