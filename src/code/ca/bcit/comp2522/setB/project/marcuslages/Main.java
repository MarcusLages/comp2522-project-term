package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Scanner;

/**
 * Main driver class used to run the classes WordGame,
 * NumberGame and MyGame according to user input.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class Main {
    private static final int FIRST_CHAR_IDX = 0;

    /**
     * Programs main entry point.
     *
     * @param args command line arguments (unused)
     */
    public static void main(final String[] args) {
        char input;

        do {
            input = getInput();

            switch(input) {
                case 'w':
                    playWordGame();
                    break;
                case 'n':
                    playNumberGame();
                    break;
                case 'm':
                    playMyGame();
                    break;
                default:
                    System.out.println("You shouldn't be here.");
            }
        } while(input != 'q');

        System.out.println("Thank you for playing one of Marcus' studios trademark games!");
        InputScanner.close();
    }

    // TODO: CHANGE ALL THIS STUFF TO USE POLYMORPHISM AND GAME.START()
    /* Runs word game. */
    private static void playWordGame() {
        final WordGame game;
        game = new WordGame();

        game.start();
    }

    /* Runs number game. */
    private static void playNumberGame() {
        System.out.println("Playing number game");
    }

    /* Runs my game. */
    private static void playMyGame() {
        System.out.println("Playing my game");
    }

    /**
     * Used to get user input to choose which game user will play.
     *
     * @return character representing which game to play or quit action
     */
    private static char getInput() {
        char input = ' ';
        final Scanner sc;

        sc = InputScanner.getInstance();

        do {
            System.out.print("-----------------------\n" +
                    "Please choose a game to play:\n" +
                    "w/W for Word Game\n" +
                    "n/N for Number Game\n" +
                    "m/M for MyGame\n" +
                    "q/Q to Quit\n" +
                    "Game: ");

            if(sc.hasNext()) {
                input = sc.next().charAt(FIRST_CHAR_IDX);
                input = Character.toLowerCase(input);

                switch(input) {
                    case 'w', 'n', 'm', 'q':
                        break;

                    default:
                        System.out.println("-----------------------\n" +
                                "Invalid input.\n" +
                                "-----------------------\n");
                        input = ' ';
                        break;
                }
            }

        } while(input == ' ');

        sc.nextLine();

        return input;
    }

}