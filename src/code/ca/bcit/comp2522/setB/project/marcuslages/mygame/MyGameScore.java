package ca.bcit.comp2522.setB.project.marcuslages.mygame;

/**
 * Represents the score and statistics tracking for the MyGame game.
 * Tracks the player's points and the number of type chances remaining.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class MyGameScore {

    private static final int CARD_POINTS = 3;
    private static final int TYPE_POINTS = 1;
    private static final int INITIAL_WORDS = 0;
    private static final int INITIAL_TYPE_CHANCES = 10;
    private static final int NO_CHANCES = 0;

    private int points;
    private int typeChances;

    /**
     * Constructs a new `MyGameScore` instance with initial values for
     * points and type chances.
     */
    public MyGameScore() {

        points = INITIAL_WORDS;
        typeChances = INITIAL_TYPE_CHANCES;
    }

    /**
     * Returns the number of type chances remaining.
     *
     * @return number of type chances left.
     */
    public int getTypeChances() {

        return typeChances;
    }

    /**
     * Increases the player's points by the value associated with a card play.
     */
    public void increaseCardPoints() {

        points += CARD_POINTS;
    }

    /**
     * Increases the player's points by the value associated with a type play
     * and decreases the number of type chances by one.
     */
    public void increaseTypePoints() {

        points += TYPE_POINTS;
        typeChances--;
    }

    /**
     * Checks if the player still has type chances remaining.
     *
     * @return true if there are type chances left.
     */
    public boolean typeChancesLeft() {

        return typeChances > NO_CHANCES;
    }

    /**
     * Displays the current score, printing it to the console.
     */
    public void displayScore() {

        System.out.println("You got " + this);
    }

    /**
     * Returns a string representation of the current score, showing the number
     * of points the player has.
     *
     * @return a string representation of the score.
     */
    @Override
    public String toString() {

        return points + " points.";
    }
}
