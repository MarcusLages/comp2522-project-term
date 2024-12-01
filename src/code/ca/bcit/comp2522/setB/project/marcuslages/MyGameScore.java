package ca.bcit.comp2522.setB.project.marcuslages;

public class MyGameScore {

    private static int INITIAL_WORDS = 0;

    private int rightWords;

    public MyGameScore() {
        rightWords = INITIAL_WORDS;
    }

    public void increaseRightWords() {
        rightWords++;
    }

    public void displayScore() {
        System.out.println("You got ");
    }

    @Override
    public String toString() {
        return rightWords +
                " words.";
    }
}
