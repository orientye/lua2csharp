package org.orient;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.nio.file.Files;

final class IrDemoSupport {
    private IrDemoSupport() {
    }

    static void runExample(String example) throws Exception {
        String luaPath = "./src/test/examples/" + example + ".lua";
        String csPath = "./src/test/examples/" + example + ".cs";

        CharStream charStream = CharStreams.fromFileName(luaPath);
        String result = Transform.transformWithIr(charStream);
        String normalizedResult = normalize(result);
        System.out.println("IR result:\n" + result);

        File csFile = new File(csPath);
        byte[] bytes = Files.readAllBytes(csFile.toPath());
        String targetContent = new String(bytes);
        String normalizedTarget = normalize(targetContent);
        System.out.println("Target:\n" + targetContent);

        if (normalizedResult.equals(normalizedTarget)) {
            System.out.println(example + ".lua IR transform success");
        } else {
            System.err.println(example + ".lua IR transform failed!!!");
        }
    }

    private static String normalize(String s) {
        return s.replaceAll("\\s", "");
    }
}

