package org.orient;

import org.antlr.v4.runtime.tree.ParseTree;

public class AnnotatedTree {

    private ParseTree ast = null;

    public AnnotatedTree(ParseTree parseTree) {
        this.ast = parseTree;
    }

    public void dump() {
        System.out.println(ast.toStringTree());
        System.out.println(ast.getText());
    }
}
