package ca.bcit.comp2522.setB.project.marcuslages;

public class MyGameScore {

    private static final int CARD_POINTS = 3;
    private static final int TYPE_POINTS = 1;
    private static final int INITIAL_WORDS = 0;
    private static final int INITIAL_TYPE_CHANCES = 5;
    private static final int NO_CHANCES = 0;

    private int points;
    private int typeChances;

    public MyGameScore() {
        points = INITIAL_WORDS;
        typeChances = INITIAL_TYPE_CHANCES;
    }

    public int getTypeChances() {
        return typeChances;
    }

    public void increaseCardPoints() {
        points += CARD_POINTS;
    }

    public void increaseTypePoints() {
        points += TYPE_POINTS;
        typeChances--;
    }

    public boolean typeChancesLeft() {
        return typeChances > NO_CHANCES;
    }

    public void displayScore() {
        System.out.println("You got " + this);
    }

    @Override
    public String toString() {
        return points +
                " points.";
    }
}
