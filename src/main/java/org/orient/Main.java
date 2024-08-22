package org.orient;

import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream inStream = Main.class.getClassLoader().getResourceAsStream("simple.lua");

        ANTLRInputStream input = new ANTLRInputStream(inStream);
        LuaLexer lexer = new LuaLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LuaParser parser = new LuaParser(tokens);
        ParseTree tree = parser.start_(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree

        ParseTreeWalker walker = new ParseTreeWalker();

        //Pass
        ProcessScope processScope = new ProcessScope();
        walker.walk(processScope, tree);

        //Pass
        Listener shifter = new Listener(tokens);
        walker.walk(shifter, tree);
        System.out.print(shifter.rewriter.getText());

        System.out.println("\n");

        AnnotatedTree at = new AnnotatedTree(tree);
        at.dump();
    }
}