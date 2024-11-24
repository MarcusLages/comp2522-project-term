package ca.bcit.comp2522.setB.project.marcuslages;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class used to store the score of a player in a match of a game
 * and the metadata of that score (such as when the match was played).
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class Score {

    private static final int INITIAL_ATTEMPT_VALUE = 0;
    private static final int INITIAL_GAME_COUNT = 0;

    private int numGamesPlayed;
    private int numCorrectFirstAttempt;
    private int numCorrectSecondAttempt;
    private int numIncorrectTwoAttempts;

    private final LocalDateTime dateTimePlayed;
    private final String dateTimePlayedStr;
    private final DateTimeFormatter formatter;

    /**
     * Creates a new blank Score object with the current date.
     */
    public Score() {

        numGamesPlayed = INITIAL_GAME_COUNT;
        numCorrectFirstAttempt = INITIAL_ATTEMPT_VALUE;
        numCorrectSecondAttempt = INITIAL_ATTEMPT_VALUE;
        numIncorrectTwoAttempts = INITIAL_ATTEMPT_VALUE;

        dateTimePlayed = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTimePlayedStr = dateTimePlayed.format(formatter);
    }

    /**
     * Creates a new Score object based on the given information.
     */
    public Score(final LocalDateTime dateTimePlayed,
                 final int numGamesPlayed,
                 final int numCorrectFirstAttempt,
                 final int numCorrectSecondAttempt,
                 final int numIncorrectTwoAttempts) {

        this.dateTimePlayed = dateTimePlayed;
        this.numGamesPlayed = numGamesPlayed;
        this.numCorrectFirstAttempt = numCorrectFirstAttempt;
        this.numCorrectSecondAttempt = numCorrectSecondAttempt;
        this.numIncorrectTwoAttempts = numIncorrectTwoAttempts;

        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTimePlayedStr = dateTimePlayed.format(formatter);
    }

    public void writeToFile(final Path filePath) {

    }

    // TODO: getter/setter documentation
    public String getDateTimePlayedStr() {
        return dateTimePlayedStr;
    }

    public LocalDateTime getDateTimePlayed() {
        return dateTimePlayed;
    }

    public int getNumIncorrectTwoAttempts() {
        return numIncorrectTwoAttempts;
    }

    public void increaseIncorrectTwoAttempts() {
        numIncorrectTwoAttempts++;
    }

    public int getNumCorrectSecondAttempt() {
        return numCorrectSecondAttempt;
    }

    public void increaseNumCorrectSecondAttempt() {
        numCorrectSecondAttempt++;
    }

    public int getNumCorrectFirstAttempt() {
        return numCorrectFirstAttempt;
    }

    public void increaseNumCorrectFirstAttempt() {
        numCorrectFirstAttempt++;
    }

    public int getNumGamesPlayed() {
        return numGamesPlayed;
    }

    public void increaseNumGamesPlayed() {
        numGamesPlayed++;
    }
}
