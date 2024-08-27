package org.orient;

import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream inStream = Main.class.getClassLoader().getResourceAsStream("exp.lua");

        ANTLRInputStream input = new ANTLRInputStream(inStream);
        LuaLexer lexer = new LuaLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LuaParser parser = new LuaParser(tokens);
        ParseTree tree = parser.start_(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree

        AnnotatedTree annotatedTree = new AnnotatedTree(tree);

        ParseTreeWalker walker = new ParseTreeWalker();

        //Pass
        ProcessScope processScope = new ProcessScope(annotatedTree);
        walker.walk(processScope, tree);

        //Pass
        ProcessTransformation shifter = new ProcessTransformation(tokens);
        walker.walk(shifter, tree);
        System.out.print(shifter.rewriter.getText());

        System.out.println("\n");

        AnnotatedTree at = new AnnotatedTree(tree);
        at.dump();
    }
}