package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyGame
        implements TextGame, Resettable {

    private static final int HAND_SIZE = 7;

    private final WordPile deck;
    private final WordHand userHand;
    private final WordHand botHand;

    public MyGame() {

        deck = new WordPile();
        userHand = new WordHand(deck, HAND_SIZE);
        botHand = new WordHand(deck, HAND_SIZE);

    }

    @Override
    public void startGame() {

        System.out.println("Welcome to Word Domino's.");

        do {
//           startMatch();
//
        } while(!TextGame.stopMatch());

    }

    @Override
    public void reset() {

        userHand.clear();
        botHand.clear();
        deck.refillDeck();
        startGame();
    }

    public void startMatch() {

//        do {
            // Display both decks and table
            // Player chooses which word to play
            // Checks if card exists, if so passes it down to the table
            // If table accepts it, draws card from user and shows his points
            // If not, makes the user write it again

//        } while(!noMoves());

        if(userHand.isEmpty()) {
            System.out.println("YOU WIN!");

        }

        // TODO: Print score.
//        System.out.println("Your score was: " + score);

    }

}
