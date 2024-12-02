package ca.bcit.comp2522.setB.project.marcuslages.wordgame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private static final int SECOND_ATTEMPT_POINTS = 1;

    // Constants used to get the line in a block of text that refers to that specific data.
    private static final int DATE_TIME_LINE = 0;
    private static final int GAMES_PLAYED_LINE = 1;
    private static final int CORRECT_FIRST_ATTEMPT_LINE = 2;
    private static final int CORRECT_SECOND_ATTEMPT_LINE = 3;
    private static final int INCORRECT_ATTEMPTS_LINE = 4;

    private static final DateTimeFormatter formatter;

    public static final Path DEFAULT_SCORE_FILEPATH;

    // How many times will a line will be split in a file so we can
    // get the data from that line in the second part.
    // Format of line:
    //      header : data
    private static final int SPLIT_LINE_LIMIT = 2;
    private static final int DATA_INDEX_IN_LINE = 1;

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
        DEFAULT_SCORE_FILEPATH = Paths.get("score.txt");
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

    /**
     * Appends a score to a file specified by filename.
     * If the file doesn't exist, it's created first.
     *
     * @param score    Score object to be appended.
     * @param filename name of the file.
     * @throws IOException If an I/O error occurs.
     */
    public static void appendScoreToFile(final Score score,
                                         final String filename) throws IOException {

        final Path filepath;
        filepath = Paths.get(filename);

        appendScoreToFile(score, filepath);

    }

    /**
     * Appends a score to a file specified by filename.
     * If the file doesn't exist, it's created first.
     *
     * @param score    Score object to be appended.
     * @param filepath Path to the specified file.
     * @throws IOException If an I/O error occurs.
     */
    public static void appendScoreToFile(final Score score,
                                         final Path filepath) throws IOException {

        final String scoreString;
        scoreString = score.toString() + System.lineSeparator();
        Files.writeString(filepath, scoreString, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    /**
     * Reads scores from a file specified by filename.
     * Returns null if file doesn't exist.
     *
     * @param filename name of the file to read from.
     * @return list of Score objects.
     *         null if file doesn't exist.
     * @throws IOException If an I/O error occurs.
     */
    public static List<Score> readScoresFromFile(final String filename)
            throws IOException {

        final Path filepath;
        filepath = Paths.get(filename);

        return readScoresFromFile(filepath);
    }

    /**
     * Reads scores from a file specified by filename.
     * Returns null if file doesn't exist.
     *
     * @param filepath Path to the file to read from.
     * @return list of Score objects.
     *         null if file doesn't exist.
     * @throws IOException If an I/O error occurs.
     */
    public static List<Score> readScoresFromFile(final Path filepath)
            throws IOException {

        final List<Score> scores;
        scores = new ArrayList<>();

        if(Files.exists(filepath)) {

            final List<String> lines;

            lines = Files.readAllLines(filepath);
            parseScoresFromLines(lines, scores);

        }

        return scores;

    }

    // Function to parse and transform a list of String lines into a list of Score objects.
    private static void parseScoresFromLines(final List<String> lines,
                                             final List<Score> scores) {

        final List<String> block;
        block = new ArrayList<>();

        for(final String line: lines) {

            if(line.trim().isEmpty()) {
                if(!block.isEmpty()) {

                    scores.add(parseStringBlock(block));
                    block.clear();
                }

            } else {
                block.add(line);
            }
        }

        if(!block.isEmpty()) {
            scores.add(parseStringBlock(block));
            block.clear();
        }

    }

    /**
     * Function used parse a block (list of String lines) into a Score object,
     * getting the right information from each line.
     *
     * @param block block of lines that will be read
     * @return Score object correspondent to the block
     */
    private static Score parseStringBlock(final List<String> block) {

        final Score score;
        final LocalDateTime dateTimePlayed;
        final int numGamesPlayed;
        final int numCorrectFirstAttempt;
        final int numCorrectSecondAttempt;
        final int numIncorrectTwoAttempts;


        dateTimePlayed = parseDateTime(block.get(DATE_TIME_LINE));
        numGamesPlayed = parseLineIntData(block.get(GAMES_PLAYED_LINE));
        numCorrectFirstAttempt = parseLineIntData(block.get(CORRECT_FIRST_ATTEMPT_LINE));
        numCorrectSecondAttempt = parseLineIntData(block.get(CORRECT_SECOND_ATTEMPT_LINE));
        numIncorrectTwoAttempts = parseLineIntData(block.get(INCORRECT_ATTEMPTS_LINE));

        score = new Score(dateTimePlayed, numGamesPlayed, numCorrectFirstAttempt,
                numCorrectSecondAttempt, numIncorrectTwoAttempts);
        return score;
    }

    /**
     * Helper function to parse a String line from a Score file into a LocalDateTime
     * with the format based on the formatter object: "yyyy-MM-dd HH:mm:ss".
     *
     * @param dateTimeLine String with information about the date and time
     * @return date and time parsed from the String
     */
    private static LocalDateTime parseDateTime(final String dateTimeLine) {

        final LocalDateTime dateTime;

        dateTime = LocalDateTime.parse(
                dateTimeLine.split(": ", SPLIT_LINE_LIMIT)[DATA_INDEX_IN_LINE].trim(),
                formatter
        );

        return dateTime;
    }

    /**
     * Helper function to parse an integer data (int) from a String line from a Score file.
     *
     * @param dataLine String with integer data
     * @return integer data parsed from the String
     */
    private static int parseLineIntData(final String dataLine) {

        final int data;
        final String dataStr;

        dataStr = dataLine.split(": ", SPLIT_LINE_LIMIT)[DATA_INDEX_IN_LINE].trim();
        data = Integer.parseInt(dataStr);

        return data;
    }

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
     * @attention I used System.lineSeparator() for the lines at first,
     *            but it didn't pass the tests, so I used '\n'
     *
     * @return Score information
     */
    @Override
    public String toString() {

        final StringBuilder sb;
        sb = new StringBuilder();

        // I used System.lineSeparator() for the lines at first, but it
        // didn't pass the tests, so I used '\n'
        sb.append("Date and Time: ")
                .append(dateTimePlayedStr)
                .append("\nGames Played: ")
                .append(numGamesPlayed)
                .append("\nCorrect First Attempts: ")
                .append(numCorrectFirstAttempt)
                .append("\nCorrect Second Attempts: ")
                .append(numCorrectSecondAttempt)
                .append("\nIncorrect Attempts: ")
                .append(numIncorrectTwoAttempts)
                .append("\nScore: ")
                .append(getScore())
                .append(" points\n");

        return sb.toString();
    }

    /**
     * Validates the score input so dateTimePlayed is not null or in the future,
     * numGamesPlayed is greater or equal to MIN_GAME_COUNT and numCorrectFirstAttempt,
     * numCorrectSecondAttempt and numIncorrectTwoAttempts are greater or equal to MIN_ATTEMPT_VALUE.
     *
     * @param dateTimePlayed            date and time of the match
     * @param numGamesPlayed            number of matches played in a row
     * @param numCorrectFirstAttempt    number of attempts right on first try
     * @param numCorrectSecondAttempt   number of attempts right on second try
     * @param numIncorrectTwoAttempts   number of incorrect attempts
     */
    private static void validateScore(final LocalDateTime dateTimePlayed,
                                      final int numGamesPlayed,
                                      final int numCorrectFirstAttempt,
                                      final int numCorrectSecondAttempt,
                                      final int numIncorrectTwoAttempts) {

        if(dateTimePlayed == null) {

            throw new IllegalArgumentException("Invalid LocalDateTime. " +
                    "Date for Score cannot be null.");
        }

        if(dateTimePlayed.isAfter(LocalDateTime.now())) {

            throw new IllegalArgumentException("Invalid LocalDateTime. " +
                    "Date for Score cannot be in the future. " +
                    "LocalDateTime: " + dateTimePlayed.format(formatter));

        }

        if(numGamesPlayed < MIN_GAME_COUNT) {

            throw new IllegalArgumentException("Invalid numGamesPlayed. " +
                    "Must be greater than " + MIN_GAME_COUNT +
                    ". numGamesPlayed: " + numGamesPlayed);

        }

        if(numCorrectFirstAttempt < MIN_ATTEMPT_VALUE) {

            throw new IllegalArgumentException("Invalid numCorrectFirstAttempt. " +
                    "Must be greater than " + MIN_ATTEMPT_VALUE +
                    ". numCorrectFirstAttempt: " + numGamesPlayed);

        }

        if(numCorrectSecondAttempt < MIN_ATTEMPT_VALUE) {

            throw new IllegalArgumentException("Invalid numCorrectSecondAttempt. " +
                    "Must be greater than " + MIN_ATTEMPT_VALUE +
                    ". numCorrectSecondAttempt: " + numGamesPlayed);

        }

        if(numIncorrectTwoAttempts < MIN_ATTEMPT_VALUE) {

            throw new IllegalArgumentException("Invalid numIncorrectTwoAttempts. " +
                    "Must be greater than " + MIN_ATTEMPT_VALUE +
                    ". numIncorrectTwoAttempts: " + numGamesPlayed);

        }

    }

}
