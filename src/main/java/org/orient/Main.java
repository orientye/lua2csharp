package org.orient;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n=================================================\n");

        InputStream inStream = Main.class.getClassLoader().getResourceAsStream("HelloWorld.lua");
        assert (inStream != null);
        CharStream charStream = CharStreams.fromStream(inStream);

        LuaLexer lexer = new LuaLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LuaParser parser = new LuaParser(tokens);
        ParseTree tree = parser.start_();
        System.out.println(tree.toStringTree(parser));
        System.out.println("\n=================================================\n");

        AnnotatedTree annotatedTree = new AnnotatedTree(tree);

        ParseTreeWalker walker = new ParseTreeWalker();

        //Pass
        PassScopeAndType PassScopeAndType = new PassScopeAndType(annotatedTree);
        walker.walk(PassScopeAndType, tree);
        PassScopeAndType.Reset();
        walker.walk(PassScopeAndType, tree);

        //Pass
        PassTransformation passTransformation = new PassTransformation(annotatedTree, tokens);
        walker.walk(passTransformation, tree);
        System.out.println(passTransformation.getResult());

        System.out.println("\n=================================================\n");
    }
}