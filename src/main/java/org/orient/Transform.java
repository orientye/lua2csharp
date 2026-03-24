package org.orient;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

public class Transform {
    public static String transform(CharStream charStream) {
        assert (charStream != null);
        LuaLexer lexer = new LuaLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LuaParser parser = new LuaParser(tokens);
        ParseTree tree = parser.start_();
        System.out.println(tree.toStringTree(parser) + "\n");

        AnnotatedTree annotatedTree = new AnnotatedTree(tree);

        ParseTreeWalker walker = new ParseTreeWalker();

        // semantic pass: scope and type
        PassScopeAndType passScopeAndType = new PassScopeAndType(annotatedTree);
        System.out.println("\nPassScopeAndType 1st:");
        walker.walk(passScopeAndType, tree);
        passScopeAndType.Reset();
        System.out.println("\nPassScopeAndType 2nd:");
        walker.walk(passScopeAndType, tree);

        PassTransformation passTransformation = new PassTransformation(annotatedTree, tokens);
        System.out.println("\nPassTransformation:");
        walker.walk(passTransformation, tree);

        return passTransformation.getResult();
    }

    /**
     * Experimental IR-based transformation entry point.
     * This uses the Lua AST + semantic info to build an IR module
     * and then generates C# directly, without relying on TokenStreamRewriter.
     */
    public static String transformWithIr(CharStream charStream) {
        assert (charStream != null);
        String sourceText = charStream.toString();
        LuaLexer lexer = new LuaLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LuaParser parser = new LuaParser(tokens);
        ParseTree tree = parser.start_();

        AnnotatedTree annotatedTree = new AnnotatedTree(tree);
        ParseTreeWalker walker = new ParseTreeWalker();

        PassScopeAndType passScopeAndType = new PassScopeAndType(annotatedTree);
        walker.walk(passScopeAndType, tree);
        passScopeAndType.Reset();
        walker.walk(passScopeAndType, tree);

        LuaToIrTransformer luaToIr = new LuaToIrTransformer(annotatedTree, sourceText);
        Ir.Module module = luaToIr.transform(tree, "LuaModule");

        CSharpGenerator generator = new CSharpGenerator(module);
        return generator.generate();
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