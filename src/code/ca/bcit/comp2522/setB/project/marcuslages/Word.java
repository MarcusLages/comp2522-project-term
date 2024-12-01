package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Objects;

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

    private final String word;
    private final char head;
    private final char rear;

    public Word(final String word) {
        validateWord(word);

        this.word = word.trim().toLowerCase();
        head = Character.toLowerCase(word.charAt(HEAD_INDEX));
        rear = Character.toLowerCase(word.charAt(word.length() - REAR_OFFSET));
    }

    public int positionWord(final Word word) {

        if(this.rear == word.head) {
            return REAR_POSITION;
        }

        if(this.head == word.rear) {
            return HEAD_POSITION;
        }

        return NO_POSITION;
    }

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

    @Override
    public int hashCode() {
        return Objects.hashCode(word);

    }

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

    private static void validateWord(final String word) {

        if(word == null || word.isBlank()) {
            throw new IllegalArgumentException("Invalid word.");
        }
    }

}
