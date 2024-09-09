package org.orient;

import java.io.File;

public class TestMain {
    public static void main(String[] args) throws Exception {

        File folder = new File("./src/test/examples");
        traverseFolder(folder);
    }

    public static void traverseFolder(File folder) {
        if (folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile()) {
                        String fileName = listOfFile.getName();
                        if (fileName.endsWith("lua")) {
                            System.out.println("lua: " + listOfFile.getName());
                            System.out.println("lua: " + listOfFile.getAbsolutePath());
                            System.out.println("lua: " + listOfFile.getPath());
                        } else if (fileName.endsWith("cs")) {
                            System.out.println("cs:  " + listOfFile.getName());
                            System.out.println("cs:  " + listOfFile.getAbsolutePath());
                            System.out.println("cs:  " + listOfFile.getPath());
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
}