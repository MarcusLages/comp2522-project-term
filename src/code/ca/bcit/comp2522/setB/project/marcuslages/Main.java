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
    public static final char EMPTY_INPUT = ' ';

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
            game = GameFactory.getGame(input);

            if(game != null) {

                game.startGame();
            }

        } while(input != GameFactory.QUIT_CHAR);

        System.out.println("Thank you for playing one of Marcus' studios trademark games!");

        InputScanner.close();
    }

    /**
     * Used to get user input to choose which game user will play.
     *
     * @return character representing which game to play or quit action
     */
    private static char getInput() {

        char input = EMPTY_INPUT;
        final Scanner sc;

        sc = InputScanner.getInstance();

        do {

            System.out.print("-----------------------\n" +
                    "Please choose a game to play:\n" +
                    GameFactory.WORD_GAME_CHAR + "/" +
                    Character.toUpperCase(GameFactory.WORD_GAME_CHAR) + " for Word Game\n" +
                    GameFactory.NUMBER_GAME_CHAR + "/" +
                    Character.toUpperCase(GameFactory.NUMBER_GAME_CHAR) + " for Number Game\n" +
                    GameFactory.MY_GAME_CHAR + "/" +
                    Character.toUpperCase(GameFactory.MY_GAME_CHAR) + " for MyGame\n" +
                    GameFactory.QUIT_CHAR + "/" +
                    Character.toUpperCase(GameFactory.QUIT_CHAR) + " to Quit\n" +
                    "Game: ");

            if(sc.hasNext()) {

                input = sc.next().charAt(FIRST_CHAR_IDX);
                input = Character.toLowerCase(input);

                if(!GameFactory.validGameChar(input)) {

                    System.out.println("-----------------------\n" +
                            "Invalid input.\n" +
                            "-----------------------\n");
                    input = EMPTY_INPUT;
                }

            }

        } while(input == EMPTY_INPUT);

        sc.nextLine();

        return input;
    }

}