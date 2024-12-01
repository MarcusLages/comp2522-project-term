package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyGame
        implements Game, Resettable {

    private static final int HAND_SIZE = 7;

    private final WordPile deck;
    private final List<Word> userHand;
    private final List<Word> botHand;

    public MyGame() {

        deck = new WordPile();
        userHand = new ArrayList<>();
        botHand = new ArrayList<>();


    }

    @Override
    public void startGame() {

        System.out.println("Welcome to Word Domino's.");

        do {
           startMatch();

        } while(!stopMatch());

    }

    @Override
    public void reset() {

        userHand.clear();
        botHand.clear();
        deck.refillDeck();
        startGame();
    }

    private void startMatch() {

        do {
            // Display both decks and table
            // Player chooses which word to play
            // Checks if card exists, if so passes it down to the table
            // If table accepts it, draws card from user and shows his points
            // If not, makes the user write it again

        } while(!noMoves());

        if(userHand.isEmpty()) {
            System.out.println("YOU WIN!");

        }

        System.out.println("Your score was: " + score);

    }

    // TODO: Pass this to a TextGame interface
    /**
     * Function that receives user input and checks if the user
     * wants to stop playing or not.
     *
     * @return true if user wants to stop playing
     */
    private static boolean stopMatch() {

        final Scanner sc;

        sc = InputScanner.getInstance();

        do {
            final String input;

            System.out.println("Would you like to play again?");

            // TODO: TEST SC.HASNEXT()
            if(sc.hasNext()) {
                input = sc.nextLine();

                // To avoid scanner error
//            sc.nextLine();

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
