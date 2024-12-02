package ca.bcit.comp2522.setB.project.marcuslages.mygame;

import java.util.Objects;

/**
 * Represents a word in the game, with methods to access its head and rear characters.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class Word {

    // Constant to access the rear index after
    // getting the length of the String
    private static final int REAR_OFFSET = 1;
    private static final int HEAD_INDEX = 0;
    private static final int SECOND_HEAD_INDEX = 1;
    private static final int ONE_LETTER_WORD = 1;
    private static final int TWO_LETTER_WORD = 2;

    public static final int HEAD_POSITION = -1;
    public static final int NO_POSITION = 0;
    public static final int REAR_POSITION = 1;

    public static final Word GIVE_UP;

    private final String word;
    private final char head;
    private final char rear;

    static {

        GIVE_UP = new Word("give up");
    }

    /**
     * Constructs a new `Word` object with the provided word string.
     * The word is trimmed, converted to lowercase, and its head and rear characters are extracted.
     *
     * @param word the word string to create the Word object from.
     * @throws IllegalArgumentException if the word is null or blank.
     */
    public Word(final String word) {

        validateWord(word);

        this.word = word.trim().toLowerCase();
        head = Character.toLowerCase(word.charAt(HEAD_INDEX));
        rear = Character.toLowerCase(word.charAt(word.length() - REAR_OFFSET));
    }

    /**
     * Determines the relative position of this word to another word based on their head and rear characters.
     *
     * @param word the word to compare against.
     * @return the position of the word relative to this word:
     *         -1 for rear position, 1 for head position, and 0 for no position.
     */
    public int positionWord(final Word word) {

        if(this.rear == word.head) {

            return REAR_POSITION;
        }

        if(this.head == word.rear) {

            return HEAD_POSITION;
        }

        return NO_POSITION;
    }

    /**
     * Compares this word to another object for equality.
     * Words are considered equal if their word strings are the same, ignoring case.
     *
     * @param obj the object to compare this word with.
     * @return true if the object is a Word and its word string
     *         is equal to this word's string (case-insensitive)
     */
    @Override
    public boolean equals(final Object obj) {

        if(this == obj) {

            return true;
        }

        if(obj == null || getClass() != obj.getClass()) {

            return false;
        }

        final Word comparedWord;
        comparedWord = (Word) obj;

        return this.word.equalsIgnoreCase(comparedWord.word);
    }

    /**
     * Returns a hash code for this word based on its word string.
     *
     * @return a hash code for this word.
     */
    @Override
    public int hashCode() {

        return Objects.hashCode(word);
    }

    /**
     * Returns a string representation of the word, highlighting head and rear words.
     *
     * @return a string representation of the word.
     */
    @Override
    public String toString() {

        final StringBuilder sb;
        sb = new StringBuilder();

        sb.append(Character.toUpperCase(head));

        if(word.length() > TWO_LETTER_WORD) {

            sb.append(word, SECOND_HEAD_INDEX, word.length() - REAR_OFFSET);
        }

        if(word.length() > ONE_LETTER_WORD) {

            sb.append(Character.toUpperCase(rear));
        }

        return sb.toString();
    }

    /**
     * Validates the word to ensure it is not null or blank.
     *
     * @param word word string to validate.
     * @throws IllegalArgumentException if the word is null or blank.
     */
    private static void validateWord(final String word) {

        if(word == null || word.isBlank()) {

            throw new IllegalArgumentException("Invalid word.");
        }
    }

}
