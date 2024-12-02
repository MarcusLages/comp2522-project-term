package ca.bcit.comp2522.setB.project.marcuslages.numbergame;

/**
 * Represents the score and statistics tracking for the number game.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class NumberGameScore {

    // This value gets added beforehand on NumberGame.newCurrentNumber,
    // so we have to set it to -1
    private static final int INITIAL_PLACEMENT_NUMBER = -1;
    private static final int INITiAL_WINS = 0;
    private static final int INITIAL_NUM_OF_GAMES = 1;

    private int numberPlacements;
    private int totalGames;
    private int wins;

    /**
     * Constructs a new `NumberGameScore` instance with default values.
     */
    public NumberGameScore() {

        numberPlacements = INITIAL_PLACEMENT_NUMBER;
        totalGames = INITIAL_NUM_OF_GAMES;
        wins = INITiAL_WINS;
    }

    /**
     * Increments the number of placements made in the game by one.
     */
    public void incrementPlacement() {

        numberPlacements++;
    }

    /**
     * Increments the number of total games played by one.
     */
    public void incrementGames() {

        totalGames++;
    }

    /**
     * Increments the number of total games played by one.
     */
    public void incrementWins() {

        wins++;
    }

    /**
     * Calculates the average number of placements per game.
     *
     * @return average number of placements per game.
     */
    public double getAveragePlacements() {

        return (double) numberPlacements / totalGames;
    }

    /**
     * Returns a string representation of the score, including the number of
     * losses, total games, successful placements, and average placements per game.
     *
     * @return a string summarizing the score and game statistics.
     */
    @Override
    public String toString() {

        final StringBuilder sb;
        sb = new StringBuilder();

        sb.append("You lost ")
                .append(totalGames - wins)
                .append(" out of ")
                .append(totalGames)
                .append(" games, with ")
                .append(numberPlacements)
                .append(" successful placements, an average of ")
                .append(String.format("%.2f", getAveragePlacements()))
                .append(" per game");

        return sb.toString();
    }
}
