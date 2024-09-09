package org.orient;

import java.io.File;

public class TestMain {
    public static void main(String[] args) throws Exception {

        File folder = new File("./src/test/examples");
        traverseFolder(folder);
    }

    public static void traverseFolder(File folder) throws Exception {
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
                            System.out.println("cs:  " + csharpFile.getAbsolutePath());
                        }
                    } else if (listOfFile.isDirectory()) {
                        traverseFolder(listOfFile);
                    }
                }
            }
        } else {
            System.err.println(folder.getName() + " is not a directory");
        }
    }

    private static String GetCSharpFileName(String luaFileName) {
        assert(luaFileName != null);
        int lastIndexOfDot = luaFileName.lastIndexOf('.');
        assert(lastIndexOfDot != -1);
        return luaFileName.substring(0, lastIndexOfDot) + ".cs";
    }
}