package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Scanner;

/**
 * InputScanner is a Singleton class used to create one instance of a
 * Scanner(System.in). Since there are problems creating many instances
 * of Scanners that receive System.in, a Singleton class is used to make
 * sure all instance of the Scanner(System.in) are the same.
 *
 * ATTENTION! Do not forget to use InputScanner.close() once the Scanner
 * is not needed.
 */
public final class InputScanner {

    private static final Scanner instance;

    // Statically initializes the Scanner(System.in) only once.
    static {

         instance = new Scanner(System.in);
    }

    // Private constructor to prevent instantiation.
    private InputScanner() {}

    /**
     * Returns the one instance of Scanner(System.in) for user input in
     * the terminal.
     *
     * @return instance of Scanner(System.in)
     */
    public static Scanner getInstance() {

        return instance;
    }

    /**
     * Closes the Scanner.
     */
    public static void close() {

        instance.close();
    }

}
