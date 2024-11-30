package ca.bcit.comp2522.setB.project.marcuslages;

public class Word {

    // Constant to access the rear index after
    // getting the length of the String
    public static final int REAR_OFFSET = 1;
    private final static int HEAD_INDEX = 0;

    private final String word;
    private final char head;
    private final char rear;

    public Word(final String word) {

        this.word = word;
        head = word.charAt(HEAD_INDEX);
        rear = word.charAt(word.length() - REAR_OFFSET);
    }

}
