package org.orient;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

public class Transform {
    public static String transform(CharStream charStream) throws Exception {
        assert (charStream != null);
        LuaLexer lexer = new LuaLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LuaParser parser = new LuaParser(tokens);
        ParseTree tree = parser.start_();
        System.out.println(tree.toStringTree(parser) + "\n");

        AnnotatedTree annotatedTree = new AnnotatedTree(tree);

        ParseTreeWalker walker = new ParseTreeWalker();

        //Pass
        PassScopeAndType PassScopeAndType = new PassScopeAndType(annotatedTree);
        System.out.println("\nPassScopeAndType 1st:");
        walker.walk(PassScopeAndType, tree);
        PassScopeAndType.Reset();
        System.out.println("\nPassScopeAndType 2nd:");
        walker.walk(PassScopeAndType, tree);

        //Pass
        PassTransformation passTransformation = new PassTransformation(annotatedTree, tokens);
        System.out.println("\nPassTransformation:");
        walker.walk(passTransformation, tree);

        String result = passTransformation.getResult();
        return result;
    }

    public static String transformFromFileName(String fileName) throws Exception {
        CharStream charStream = CharStreams.fromFileName(fileName);
        return transform(charStream);
    }

    public static String transformFromInputStream(InputStream inputStream) throws Exception {
        CharStream charStream = CharStreams.fromStream(inputStream);
        return transform(charStream);
    }
}