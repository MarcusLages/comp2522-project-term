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
    private static final char WORD_GAME_INPUT = 'w';
    private static final char NUMBER_GAME_INPUT = 'n';
    private static final char MY_GAME_INPUT = 'm';
    private static final char QUIT_INPUT = 'q';

    /**
     * Programs main entry point.
     *
     * @param args command line arguments (unused)
     */
    public static void main(final String[] args) {

        char input;
        Game game;

        do {
            input = getInput();
            game = getGame(input);

            game.startGame();

        } while(input != QUIT_INPUT);

        System.out.println("Thank you for playing one of Marcus' studios trademark games!");

        InputScanner.close();
    }

    /**
     * Helper function used to return the Game that will be played respective to the input given.
     *
     * @param input input used to get proper Game object
     * @return Game respective to the input
     */
    private static Game getGame(final char input) {

        switch(input) {

            case WORD_GAME_INPUT:
                return new WordGame();

            case NUMBER_GAME_INPUT:
                return new NumberGame();

            case MY_GAME_INPUT:
                return new MyGame();

            default:
                throw new IllegalArgumentException("Invalid input in getGame method.");
        }

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
                    WORD_GAME_INPUT + "/" + Character.toUpperCase(WORD_GAME_INPUT) + " for Word Game\n" +
                    NUMBER_GAME_INPUT + "/" + Character.toUpperCase(NUMBER_GAME_INPUT) + " for Number Game\n" +
                    MY_GAME_INPUT + "/" + Character.toUpperCase(MY_GAME_INPUT) + " for MyGame\n" +
                    QUIT_INPUT + "/" + Character.toUpperCase(QUIT_INPUT) + " to Quit\n" +
                    "Game: ");

            if(sc.hasNext()) {
                input = sc.next().charAt(FIRST_CHAR_IDX);
                input = Character.toLowerCase(input);

                switch(input) {
                    case WORD_GAME_INPUT, NUMBER_GAME_INPUT, MY_GAME_INPUT, QUIT_INPUT:
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