package org.orient;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.nio.file.Files;

public class IrReturnOneDemoMain {

    public static void main(String[] args) throws Exception {
        String luaPath = "./src/test/examples/ReturnOne.lua";
        String csPath = "./src/test/examples/ReturnOne.cs";

        CharStream charStream = CharStreams.fromFileName(luaPath);
        String result = Transform.transformWithIr(charStream);
        String csResult = normalize(result);
        System.out.println("IR result:\n" + result);

        File csFile = new File(csPath);
        byte[] bytes = Files.readAllBytes(csFile.toPath());
        String targetContent = new String(bytes);
        String csFileContent = normalize(targetContent);
        System.out.println("Target:\n" + targetContent);

        if (csResult.equals(csFileContent)) {
            System.out.println("ReturnOne.lua IR transform success");
        } else {
            System.err.println("ReturnOne.lua IR transform failed!!!");
        }
    }

    private static String normalize(String s) {
        String withoutComments = s.replaceAll("(?m)//.*$", "");
        return withoutComments.replaceAll("\\s", "");
    }
}

