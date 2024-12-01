package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Optional;
import java.util.Scanner;

public class MyGame
        implements TextGame, Resettable {

    private static final int USER_HAND_SIZE = 15;

    private final WordPile deck;
    private final WordBoard board;
    private final WordHand userHand;
    private final WordHand botHand;
    private final MyGameScore score;

    public MyGame() {

        deck = new WordPile();
        board = new WordBoard(deck.draw());
        userHand = new WordHand(deck, USER_HAND_SIZE);

        while(!board.playableDeck(userHand)) {
            userHand.reset(deck);
        }

        botHand = new WordHand(deck);
        score = new MyGameScore();

    }

    @Override
    public void startGame() {

        System.out.println("Welcome to Word Domino's.");

        do {
           startMatch();
           reset();

        } while(!TextGame.stopMatch());

    }

    @Override
    public void reset() {

        deck.reset();
        board.reset(deck.draw());
        botHand.reset(deck, USER_HAND_SIZE);

        do {
            userHand.reset(deck, USER_HAND_SIZE);

        } while (!board.playableDeck(userHand));
    }

    public void startMatch() {

        renderGame();

        do {
            userRound();
            renderGame();
            botRound();
            renderGame();
            // Checks if card exists, if so passes it down to the table
            // If table accepts it, draws card from user and shows his points
            // If not, makes the user write it again

        } while(board.playableDeck(userHand));

        if(userHand.isEmpty()) {
            System.out.println("YOU WIN!");

        } else {
            System.out.println("YOU LOST!");

        }

        System.out.println("Your score was: " + score);

    }

    private void renderGame() {

        final StringBuilder sb;
        sb = new StringBuilder();

        sb.append("-----------------------")
                .append(System.lineSeparator())
                .append("Board: ")
                .append(board)
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
                score.increaseRightWords();

                break;

            } else {
                System.out.println("Please enter a valid word.");

            }

        } while(true);

    }

    private void botRound() {

        final Optional<Word> optionalWord;

        optionalWord = botHand.stream()
                .filter(word -> board.canPlayWord(word) != Word.NO_POSITION)
                .findAny();

        if(optionalWord.isPresent()) {

            final Word word;
            word = optionalWord.get();

            board.playWord(word);
            botHand.draw(word);

        } else {
            System.out.println("The house did not place any card." +
                    System.lineSeparator());
        }
    }

    private static Word getWordInput() {
        final Scanner sc;
        final String userInput;
        final Word userWord;

        sc = InputScanner.getInstance();

        System.out.print("Card to play: ");
        userInput = sc.nextLine();

        userWord = new Word(userInput);

        return userWord;

    }

}
