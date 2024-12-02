package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Scanner;

/**
 * Interface responsible to standardize the common functions among
 * text games, such as starting and stopping one match.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public interface TextGame extends Game {

    /**
     * Starts a match of the given game.
     */
    void startMatch();

    /**
     * Function that receives user input and checks if the user
     * wants to stop playing or not.
     *
     * @return true if user wants to stop playing
     */
    static boolean stopMatch() {

        final Scanner sc;

        sc = InputScanner.getInstance();

        do {
            final String input;

            System.out.println("Would you like to play again? (Y)es/(N)o");

            // TODO: TEST SC.HASNEXT()
            if(sc.hasNext()) {
                input = sc.nextLine();

                if(input.equalsIgnoreCase("yes") ||
                        input.equalsIgnoreCase("y")) {

                    return false;

                } else if(input.equalsIgnoreCase("no") ||
                        input.equalsIgnoreCase("n")) {

                    return true;

                }
            }

        } while (true);

    }

}
