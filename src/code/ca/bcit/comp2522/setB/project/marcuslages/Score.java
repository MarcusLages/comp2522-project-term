package ca.bcit.comp2522.setB.project.marcuslages;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Class used to store the score of a player in a match of a game
 * and the metadata of that score (such as when the match was played).
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class Score {

    private static final int MIN_ATTEMPT_VALUE = 0;
    private static final int MIN_GAME_COUNT = 0;
    private static final int FIRST_ATTEMPT_POINTS = 2;
    private static final int SECOND_ATTEMPT_POINTS = 2;
    private static final DateTimeFormatter formatter;

    private final LocalDateTime dateTimePlayed;
    private final String dateTimePlayedStr;

    private int numGamesPlayed;
    private int numCorrectFirstAttempt;
    private int numCorrectSecondAttempt;
    private int numIncorrectTwoAttempts;

    // Initializes the date formatter in a static initializer,
    // since it will be the same for all objects.
    static {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Creates a Score object with the provided game statistics and date.
     *
     * @param dateTimePlayed            date and time when the game was played.
     * @param numGamesPlayed            number of games played.
     * @param numCorrectFirstAttempt    number of correct answers on the first attempt.
     * @param numCorrectSecondAttempt   number of correct answers on the second attempt.
     * @param numIncorrectTwoAttempts   number of incorrect attempts before a second correct try.
     * @throws IllegalArgumentException if dateTimePlayed is null or in the future,
     *                                  if numGamesPlayed is smaller than MIN_GAME_COUNT or
     *                                  if numCorrectFirstAttempt, numCorrectSecondAttempt or
     *                                  numIncorrectTwoAttempts is smaller than MIN_ATTEMPT_VALUE
     */
    public Score(final LocalDateTime dateTimePlayed,
                 final int numGamesPlayed,
                 final int numCorrectFirstAttempt,
                 final int numCorrectSecondAttempt,
                 final int numIncorrectTwoAttempts) {

        validateScore(dateTimePlayed, numGamesPlayed, numCorrectFirstAttempt, numCorrectSecondAttempt,
            numIncorrectTwoAttempts);

        this.numGamesPlayed = numGamesPlayed;
        this.numCorrectFirstAttempt = numCorrectFirstAttempt;
        this.numCorrectSecondAttempt = numCorrectSecondAttempt;
        this.numIncorrectTwoAttempts = numIncorrectTwoAttempts;

        this.dateTimePlayed = dateTimePlayed;
        dateTimePlayedStr = dateTimePlayed.format(formatter);
    }

    /**
     * Creates a new blank Score object with the current date.
     */
    public Score() {
        this(LocalDateTime.now(), MIN_GAME_COUNT,
                MIN_ATTEMPT_VALUE, MIN_ATTEMPT_VALUE, MIN_ATTEMPT_VALUE);
    }

    // TODO: APPEND SCORE TO FILE
//    public static void appendScoreToFile(final Score score,
//                                         final String filename) throws IOException {
//
//
//
//    }
//
//    public static void appendScoreToFile(final Score score,
//                                         final Path filepath) throws IOException {
//
//
//    }
//
    // TODO: READ SCORE FROM FILE
//    public static List<Score> readScoresFromFile(final String filename)
//            throws IOException {
//
//    }
//
//    public static List<Score> readScoresFromFile(final Path filepath)
//            throws IOException {
//
//    }

    /**
     * Returns the date and time when the game was played as a formatted string.
     * The string is formatted according to the static formatter for Score, which
     * formats it this way: "yyyy-MM-dd HH:mm:ss"
     *
     * @return String representing the date and time the game was played.
     */
    public String getDateTimePlayedStr() {
        return dateTimePlayedStr;
    }

    /**
     * Returns the date and time when the game was played.
     *
     * @return LocalDateTime object representing when the game was played.
     */
    public LocalDateTime getDateTimePlayed() {
        return dateTimePlayed;
    }

    /**
     * Returns the number of incorrect attempts made before achieving success on the second try.
     *
     * @return number of incorrect attempts before a correct second attempt.
     */
    public int getNumIncorrectTwoAttempts() {
        return numIncorrectTwoAttempts;
    }

    /**
     * Increments the number of incorrect attempts made before achieving success on the second try.
     */
    public void increaseIncorrectTwoAttempts() {
        numIncorrectTwoAttempts++;
    }

    /**
     * Returns the number of times a correct answer was given on the second attempt.
     *
     * @return number of correct answers on the second attempt.
     */
    public int getNumCorrectSecondAttempt() {
        return numCorrectSecondAttempt;
    }

    /**
     * Increments the number of correct answers given on the second attempt.
     */
    public void increaseNumCorrectSecondAttempt() {
        numCorrectSecondAttempt++;
    }

    /**
     * Returns the number of times a correct answer was given on the first attempt.
     *
     * @return number of correct answers on the first attempt.
     */
    public int getNumCorrectFirstAttempt() {
        return numCorrectFirstAttempt;
    }

    /**
     * Increments the number of correct answers given on the first attempt.
     */
    public void increaseNumCorrectFirstAttempt() {
        numCorrectFirstAttempt++;
    }

    /**
     * Returns the total number of games played.
     *
     * @return total number of games played.
     */
    public int getNumGamesPlayed() {
        return numGamesPlayed;
    }

    /**
     * Increments the total number of games played.
     */
    public void increaseNumGamesPlayed() {
        numGamesPlayed++;
    }

    /**
     * Calculates the amount of points the user got in this
     * Score record.
     *
     * @return how many points the user got in the game
     */
    public int getScore() {
        return numCorrectFirstAttempt * FIRST_ATTEMPT_POINTS +
            numCorrectSecondAttempt * SECOND_ATTEMPT_POINTS;
    }

    /**
     * Returns a String that represents the Score information with
     * games played, correct and incorrect attempts, score in points
     * and date and time of when the game was played.
     *
     * @return Score information
     */
    @Override
    public String toString() {

        final StringBuilder sb;
        sb = new StringBuilder();

        sb.append("Date and Time: ")
                .append(dateTimePlayedStr)
                .append(System.lineSeparator())
                .append("Games Played: ")
                .append(numGamesPlayed)
                .append(System.lineSeparator())
                .append("Correct First Attempts: ")
                .append(numCorrectFirstAttempt)
                .append(System.lineSeparator())
                .append("Correct Second Attempts: ")
                .append(numCorrectSecondAttempt)
                .append(System.lineSeparator())
                .append("Incorrect Attempts: ")
                .append(numIncorrectTwoAttempts)
                .append(System.lineSeparator())
                .append("Score: ")
                .append(getScore())
                .append(" points")
                .append(System.lineSeparator());

        return sb.toString();
    }

    // Validates the score input so dateTimePlayed is not null or in the future,
    // numGamesPlayed is greater or equal to MIN_GAME_COUNT and numCorrectFirstAttempt,
    // numCorrectSecondAttempt and numIncorrectTwoAttempts are greater or equal to MIN_ATTEMPT_VALUE.
    private static void validateScore(final LocalDateTime dateTimePlayed,
                                      final int numGamesPlayed,
                                      final int numCorrectFirstAttempt,
                                      final int numCorrectSecondAttempt,
                                      final int numIncorrectTwoAttempts) {

        if(dateTimePlayed == null) {
            throw new IllegalArgumentException("Invalid LocalDateTime. Date for Score cannot be null.");
        }

        if(dateTimePlayed.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Invalid LocalDateTime. Date for Score cannot be in the future. " +
                    "LocalDateTime: " + dateTimePlayed.format(formatter));

        }

        if(numGamesPlayed < MIN_GAME_COUNT) {
            throw new IllegalArgumentException("Invalid numGamesPlayed, must be greater than " + MIN_GAME_COUNT +
                    ". numGamesPlayed: " + numGamesPlayed);

        }

        if(numCorrectFirstAttempt < MIN_ATTEMPT_VALUE) {
            throw new IllegalArgumentException("Invalid numCorrectFirstAttempt, must be greater than " + MIN_ATTEMPT_VALUE +
                    ". numCorrectFirstAttempt: " + numGamesPlayed);

        }

        if(numCorrectSecondAttempt < MIN_ATTEMPT_VALUE) {
            throw new IllegalArgumentException("Invalid numCorrectSecondAttempt, must be greater than " + MIN_ATTEMPT_VALUE +
                    ". numCorrectSecondAttempt: " + numGamesPlayed);

        }

        if(numIncorrectTwoAttempts < MIN_ATTEMPT_VALUE) {
            throw new IllegalArgumentException("Invalid numIncorrectTwoAttempts, must be greater than " + MIN_ATTEMPT_VALUE +
                    ". numIncorrectTwoAttempts: " + numGamesPlayed);

        }

    }

}
