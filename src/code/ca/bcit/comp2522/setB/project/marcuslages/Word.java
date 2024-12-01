package ca.bcit.comp2522.setB.project.marcuslages;

public class Word {

    // Constant to access the rear index after
    // getting the length of the String
    private final static int REAR_OFFSET = 1;
    private final static int SECOND_REAR_OFFSET = 2;
    private final static int HEAD_INDEX = 0;
    private final static int SECOND_HEAD_INDEX = 1;
    private final static int ONE_LETTER_WORD = 1;
    private final static int TWO_LETTER_WORD = 2;

    private final String word;
    private final char head;
    private final char rear;

    public Word(final String word) {
        validateWord(word);

        this.word = word;
        head = Character.toLowerCase(word.charAt(HEAD_INDEX));
        rear = Character.toLowerCase(word.charAt(word.length() - REAR_OFFSET));
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
