package org.orient;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n=================================================\n");

        InputStream inStream = Main.class.getClassLoader().getResourceAsStream("HelloWorld.lua");
        assert (inStream != null);
        String result = Transform.transformFromInputStream(inStream);
        System.out.println(result);

        System.out.println("\n=================================================\n");
    }
}