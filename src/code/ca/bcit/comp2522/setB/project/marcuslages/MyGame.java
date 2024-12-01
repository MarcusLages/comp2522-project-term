package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Scanner;

public class MyGame
        implements TextGame, Resettable {

    private static final int HAND_SIZE = 7;

    private final WordPile deck;
    private final WordBoard board;
    private final WordHand userHand;
    private final WordHand botHand;

    public MyGame() {

        deck = new WordPile();
        board = new WordBoard(deck.draw());
        userHand = new WordHand(deck, HAND_SIZE);
        botHand = new WordHand(deck, HAND_SIZE);

    }

    @Override
    public void startGame() {

        System.out.println("Welcome to Word Domino's.");

        do {
           startMatch();

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
            renderGame();
            userRound();
            botRound();
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

    private void renderGame() {

        final StringBuilder sb;
        sb = new StringBuilder();

        sb.append("-----------------------")
                .append(System.lineSeparator())
                .append("Board: ")
                .append(board)
                .append(System.lineSeparator())
                .append("House: ")
                .append(botHand)
                .append(System.lineSeparator())
                .append("You: ")
                .append(userHand)
                .append(System.lineSeparator());

        System.out.println(sb);
    }

    private void userRound() {

        do {
            final Word userWord;
            userWord = getWordInput();

            if(userHand.contains(userWord) &&
                    board.playWord(userWord)) {

                userHand.draw(userWord);

                break;

            } else {
                System.out.println("Please enter a valid word.");

            }

        } while(true);

    }

    private void botRound() {}

    private static Word getWordInput() {
        final Scanner sc;
        final String userInput;
        final Word userWord;

        sc = InputScanner.getInstance();

        System.out.println("Card to play.");
        userInput = sc.nextLine();

        userWord = new Word(userInput);

        return userWord;

    }

}
