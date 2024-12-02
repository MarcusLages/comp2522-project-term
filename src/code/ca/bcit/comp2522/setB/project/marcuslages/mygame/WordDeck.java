package ca.bcit.comp2522.setB.project.marcuslages.mygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Represents a collection of words, functioning as a deck from which words can be drawn,
 * added to the head or rear, shuffled, and checked for containment.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public abstract class WordDeck {

    private static final int WORD_NOT_FOUND = -1;
    protected static final int FIRST_WORD = 0;

    private final List<Word> deck;

    /**
     * Constructs a new empty `WordDeck` with a list of words.
     */
    public WordDeck() {

        deck = new ArrayList<>();
    }

    /**
     * Adds a word to the deck.
     *
     * @param word the word to add to the deck.
     */
    protected void add(final Word word) {

        if(word != null) {
            deck.add(word);

        }
    }

    /**
     * Adds a word to the front (head) of the deck.
     *
     * @param word the word to add at the front of the deck.
     */
    protected void addHead(final Word word) {

        if(word != null) {

            deck.addFirst(word);
        }
    }

    /**
     * Adds a word to the rear (end) of the deck.
     *
     * @param word the word to add to the rear of the deck.
     */
    protected void addRear(final Word word) {

        add(word);
    }

    /**
     * Draws a word from the deck by its index.
     *
     * @param index the index of the word to draw.
     * @return the word at the specified index in the deck.
     */
    public Word draw(final int index) {

        return deck.remove(index);
    }

    /**
     * Draws a word from the deck by its string representation.
     *
     * @param word the string representation of the word to draw.
     * @return the word object matching the string, or null if not found.
     */
    public Word draw(final String word) {

        final Word searchedWord;
        final int searchedWordIdx;

        searchedWord = new Word(word);
        searchedWordIdx = deck.indexOf(searchedWord);

        if(searchedWordIdx == WORD_NOT_FOUND) {

            return null;
        }

        return draw(searchedWordIdx);
    }

    /**
     * Draws a word from the deck by its object.
     *
     * @param word the word object to draw from the deck.
     * @return the word object if found, or null if not found.
     */
    public Word draw(final Word word) {

        final int searchedWordIdx;
        searchedWordIdx = deck.indexOf(word);

        if(searchedWordIdx == WORD_NOT_FOUND) {

            return null;
        }

        return draw(searchedWordIdx);
    }

    /**
     * Draws a word from the end (rear) of the deck.
     *
     * @return the last word in the deck.
     */
    public Word draw() {

        return deck.removeLast();
    }

    /**
     * Checks if the deck contains a specific word.
     *
     * @param word the word to check for in the deck.
     * @return true if the word is present in the deck, false otherwise.
     */
    public boolean contains(final Word word) {

        return deck.contains(word);
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true if the deck has no words, false otherwise.
     */
    public boolean isEmpty() {

        return deck.isEmpty();
    }

    /**
     * Clears the deck, removing all words.
     */
    public void clear() {

        deck.clear();
    }

    /**
     * Shuffles the words in the deck randomly.
     */
    public void shuffle() {

        Collections.shuffle(deck);
    }

    /**
     * Returns a stream of words in the deck, filtering out any null values.
     *
     * @return a stream of words in the deck.
     */
    public Stream<Word> stream() {

        return deck.stream()
                .filter(Objects::nonNull);
    }

    /**
     * Returns a string representation of the deck of words.
     *
     * @return a string representing the list of words in the deck.
     */
    @Override
    public String toString() {

        final StringBuilder sb;
        sb = new StringBuilder();

        sb.append("[ ");
        deck.stream()
                .filter(Objects::nonNull)
                .forEach(word -> sb.append(word).append(" "));
        sb.append("]");

        return sb.toString();
    }

}
