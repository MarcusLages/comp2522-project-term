package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Scanner;

public final class InputScanner {

    private static final Scanner instance;

    static {
         instance = new Scanner(System.in);
    }

    private InputScanner() {}  // Private constructor to prevent instantiation

    public static Scanner getInstance() {
        return instance;
    }

    public static void closeScanner() {
        instance.close();
    }

}
