package ca.bcit.comp2522.setB.project.marcuslages.mygame;

import ca.bcit.comp2522.setB.project.marcuslages.InputScanner;
import ca.bcit.comp2522.setB.project.marcuslages.Resettable;
import ca.bcit.comp2522.setB.project.marcuslages.TextGame;

import java.util.Optional;
import java.util.Scanner;

/**
 * Represents the main logic of the Word Domino's game. A game which
 * the user needs to use words like domino blocks and append them
 * to each word like the japanese game, Shiritori.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class MyGame
        implements TextGame, Resettable {

    private static final int USER_HAND_SIZE = 10;

    private final WordPile deck;
    private final WordBoard board;
    private final WordHand userHand;
    private final WordHand botHand;
    private final MyGameScore score;

    private boolean giveUp;

    /**
     * Constructs a new instance of MyGame with default values.
     */
    public MyGame() {

        deck = new WordPile();
        board = new WordBoard(deck.draw());
        userHand = new WordHand(deck, USER_HAND_SIZE);

        // Ensures that the first hand the user has is always playable,
        // or else, it draws from the deck again.
        while(!board.playableDeck(userHand)) {

            userHand.reset(deck, USER_HAND_SIZE);
        }

        // The bot receives all the leftover cards.
        botHand = new WordHand(deck);
        score = new MyGameScore();
        giveUp = false;

    }

    /**
     * Starts the game and repeatedly plays matches until the user chooses to stop.
     */
    @Override
    public void startGame() {

        System.out.println("Welcome to Word Domino's.");

        do {

           startMatch();
           reset();

        } while(!TextGame.stopMatch());

    }

    /**
     * Resets the game to its initial state, including resetting the deck, board,
     * and both user and bot hands.
     */
    @Override
    public void reset() {

        deck.reset();
        board.reset(deck.draw());

        do {

            userHand.reset(deck, USER_HAND_SIZE);

        } while (!board.playableDeck(userHand));

        botHand.reset(deck);
        giveUp = false;
    }

    /**
     * Starts a new match, where the user and bot take turns playing cards.
     * The match continues until there are no moves left or the user gives up.
     */
    public void startMatch() {

        renderGame();

        do {

            userRound();

            if(!giveUp) {

                renderGame();
//                botRound();
//                renderGame();

            }

        } while(!noMoves() && !giveUp);

        if(userHand.isEmpty()) {

            System.out.println("YOU WIN!");

        } else {

            System.out.println("YOU LOST!");
        }

        score.displayScore();

    }

    /**
     * Renders the current state of the game, displaying the board, user hand, and remaining typing chances.
     */
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
                .append(System.lineSeparator())
                .append("Typing chances left: ")
                .append(score.getTypeChances())
                .append(System.lineSeparator());

        System.out.println(sb);
    }

    /**
     * Handles the user's turn, where the user selects a word to play, and the
     * game checks if it's valid. If the user gives up, the game ends.
     */
    private void userRound() {

        do {

            final Word userWord;
            userWord = getWordInput();

            if(userHand.contains(userWord) &&
                    board.playWord(userWord)) {

                userHand.draw(userWord);
                score.increaseCardPoints();

                break;

            } else if(score.typeChancesLeft() &&
                    botHand.contains(userWord) &&
                    board.playWord(userWord)) {

                botHand.draw(userWord);
                score.increaseTypePoints();

                break;

            } else if(userWord.equals(Word.GIVE_UP)) {

                giveUp = true;
                break;

            } else {

                System.out.println("Please enter a valid word.");
            }

        } while(true);

    }

    /**
     * Handles the bot's turn. The bot will attempt to play a valid word if possible.
     */
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

    /**
     * Prompts the user to input a word and returns the corresponding Word object.
     *
     * @return Word object representing the user's input.
     */
    private static Word getWordInput() {

        final Scanner sc;
        final Word userWord;

        String userInput = null;

        sc = InputScanner.getInstance();

        System.out.print("Card to play: ");

        do {
            userInput = sc.nextLine();

            if(userInput.trim().isBlank()) {
                userInput = null;

            }

        } while(userInput == null);

        userWord = new Word(userInput);

        return userWord;

    }

    /**
     * Checks if there are no valid moves left for both the user and the bot.
     *
     * @return true if no moves are available
     */
    private boolean noMoves() {

        return !board.playableDeck(userHand) &&
                (!score.typeChancesLeft() ||
                !board.playableDeck(botHand));
    }

}
