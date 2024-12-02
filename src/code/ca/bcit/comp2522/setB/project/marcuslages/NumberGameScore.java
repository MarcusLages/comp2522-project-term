package ca.bcit.comp2522.setB.project.marcuslages;

public class NumberGameScore {

    // This value gets added beforehand on NumberGame.newCurrentNumber,
    // so we have to set it to -1
    private static int INITAL_PLACEMENT_NUMBER = -1;
    private static int INITAL_WINS = 0;
    private static int INITAL_NUM_OF_GAMES = 1;

    private int numberPlacements;
    private int totalGames;
    private int wins;

    public NumberGameScore() {

        numberPlacements = INITAL_PLACEMENT_NUMBER;
        totalGames = INITAL_NUM_OF_GAMES;
        wins = INITAL_WINS;
    }

    public void incrementPlacement() {
        numberPlacements++;

    }

    public void incrementGames() {
        totalGames++;

    }

    public void incrementWins() {
        wins++;

    }

    public double getAveragePlacements() {

        return (double) numberPlacements / totalGames;
    }

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
