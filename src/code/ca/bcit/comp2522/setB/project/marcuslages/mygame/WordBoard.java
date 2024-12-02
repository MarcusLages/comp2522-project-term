package ca.bcit.comp2522.setB.project.marcuslages.mygame;

/**
 * Represents a board for the game, which allows playing words onto the board,
 * resetting the board with an initial word, and checking if a word can be played.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class WordBoard extends WordDeck {

    private Word headWord;
    private Word rearWord;

    /**
     * Constructs a `WordBoard` object with an initial word placed on the board.
     *
     * @param initialWord the initial word to place on the board.
     * @throws IllegalArgumentException if initialWord is null.
     */
    public WordBoard(final Word initialWord) {

        super();
        validateWord(initialWord);
        reset(initialWord);
    }

    /**
     * Attempts to play a word on the board. If the word can be placed either at the head or rear,
     * it will be added accordingly.
     *
     * @param word the word to play on the board.
     * @return true if the word was successfully played; false otherwise.
     */
    public boolean playWord(final Word word) {

        final int wordPosition;
        wordPosition = canPlayWord(word);

        if(wordPosition == Word.HEAD_POSITION) {

            addHead(word);
            headWord = word;
            return true;
        }

        if(wordPosition == Word.REAR_POSITION) {

            addRear(word);
            rearWord = word;
            return true;
        }

        return false;
    }

    /**
     * Resets the board by clearing any existing words and adding the provided initial word.
     * The head and rear words are both set to the initial word.
     *
     * @param initialWord the word to set as both the head and rear of the board.
     */
    public void reset(final Word initialWord) {

        super.clear();
        super.add(initialWord);
        headWord = initialWord;
        rearWord = initialWord;
    }

    /**
     * Checks if the given word can be played on the board. A word can be played if it matches
     * either the head or the rear word in the board.
     *
     * @param word the word to check.
     * @return the position of the word:
     *         -1 for head position, 1 for rear position, and 0 if it cannot be played.
     */
    public int canPlayWord(final Word word) {

        if(super.contains(word)) {

            return Word.NO_POSITION;
        }

        final int positionHead;
        final int positionRear;

        positionHead = headWord.positionWord(word);
        positionRear = rearWord.positionWord(word);

        if(positionHead == Word.HEAD_POSITION) {

            return positionHead;
        }

        if(positionRear == Word.REAR_POSITION) {

            return positionRear;
        }

        return Word.NO_POSITION;
    }

    /**
     * Checks if any word in the provided deck can be played on the board.
     *
     * @param deck the deck of words to check.
     * @return true if any word in the deck can be played, false otherwise.
     */
    public boolean playableDeck(final WordDeck deck) {

        return deck.stream()
                .anyMatch(word -> canPlayWord(word) != Word.NO_POSITION);
    }

    /**
     * Helper function to validate word so it is not null.
     *
     * @param word Word to be validated
     */
    private static void validateWord(final Word word) {

        if(word == null) {

            throw new IllegalArgumentException("Word not valid.");
        }
    }

}
