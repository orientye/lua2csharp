package org.orient;

import java.io.File;
import java.nio.file.Files;

public class TestMain {
    public static void main(String[] args) throws Exception {

        File folder = new File("./src/test/examples");
        doFolder(folder);
    }

    public static void doFolder(File folder) throws Exception {
        if (folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile()) {
                        String fileName = listOfFile.getName();
                        if (fileName.endsWith("lua")) {
                            String path = GetCSharpFileName(listOfFile.getPath());
                            File csharpFile = new File(path);
                            System.out.println("\n=================================================\n\n");
                            System.out.println("lua: " + listOfFile.getAbsolutePath());

                            String cs = Transform.transformFromFileName(listOfFile.getPath());
                            String result = cs.replaceAll("\\s", "");

                            System.out.println(result);
                            byte[] bytes = Files.readAllBytes(csharpFile.toPath());
                            String content = new String(bytes);
                            String csFileContent = content.replaceAll("\\s", "");
                            System.out.println(csFileContent);
                        }
                    } else if (listOfFile.isDirectory()) {
                        doFolder(listOfFile);
                    }
                }
            }
        } else {
            System.err.println(folder.getName() + " is not a directory");
        }
    }

    private static String GetCSharpFileName(String luaFileName) {
        assert (luaFileName != null);
        int lastIndexOfDot = luaFileName.lastIndexOf('.');
        assert (lastIndexOfDot != -1);
        return luaFileName.substring(0, lastIndexOfDot) + ".cs";
    }
}