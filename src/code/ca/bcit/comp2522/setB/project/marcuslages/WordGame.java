package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Scanner;

/**
 * Class that represents a WordGame about countries.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class WordGame extends Game {

    private final World world;
    private final Score score;

    /**
     * Creates a WordGame object from scratch.
     */
    public WordGame() {
        world = new World();
        score = new Score();
    }

    /**
     * Method used to start the WordGame.
     */
    @Override
    public void start() {

        System.out.println("Welcome to the WordGame." + System.lineSeparator());

        do {

            startMatch();
            if(stopMatch()) {
                break;
            }

        } while(true);

    }

    /**
     * Starts a single match of the WordGame.
     */
    @Override
    public void startMatch() {

        System.out.println(score);

    }

    private static boolean stopMatch() {

        final Scanner sc;
        sc = new Scanner(System.in);

        do {
            final String input;

            System.out.println("Would you like to play again?");
            input = sc.nextLine();

            // To avoid scanner bug
            sc.nextLine();

            if(input.equalsIgnoreCase("yes") ||
                    input.equalsIgnoreCase("y")) {

                sc.close();
                return true;

            } else if(input.equalsIgnoreCase("no") ||
                    input.equalsIgnoreCase("n")) {

                sc.close();
                return false;

            }

        } while (true);

    }

}
