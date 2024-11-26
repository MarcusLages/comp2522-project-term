package ca.bcit.comp2522.setB.project.marcuslages;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class that represents a WordGame about countries.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class WordGame extends Game {

    private static final int FIRST_QUESTION = 1;
    private static final int LAST_QUESTION = 10;
    private static final int FIRST_ATTEMPT = 1;
    private static final int LAST_ATTEMPT = 2;
    private final Score score;

    /**
     * Creates a WordGame object from scratch.
     */
    public WordGame() {
        score = new Score();
    }

    /**
     * Method used to start the WordGame.
     */
    @Override
    public void start() {

        System.out.println("Welcome to the WordGame." + System.lineSeparator());

        do {

            startMatch();
            if(stopMatch()) {
                break;
            }

        } while(true);

    }

    /**
     * Starts a single match of the WordGame.
     */
    @Override
    public void startMatch() {
        int questionNumber = FIRST_QUESTION;

        do {
            System.out.println("\nQuestion #" + questionNumber + ":");
            askQuestion();
            questionNumber++;

        } while(questionNumber <= LAST_QUESTION);

        score.increaseNumGamesPlayed();

        System.out.println("Your score is: ");
        System.out.println(score);

        try {
            Score.appendScoreToFile(score, Score.DEFAULT_SCORE_FILEPATH);

        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Function that receives user input and checks if the user
     * wants to stop playing or not.
     *
     * @return true if user wants to stop playing
     */
    private static boolean stopMatch() {

        final Scanner sc;

        sc = InputScanner.getInstance();

        do {
            final String input;

            System.out.println("Would you like to play again?");

            // TODO: TEST SC.HASNEXT()
            if(sc.hasNext()) {
                input = sc.nextLine();

                // To avoid scanner error
//            sc.nextLine();

                if(input.equalsIgnoreCase("yes") ||
                        input.equalsIgnoreCase("y")) {

                    return false;

                } else if(input.equalsIgnoreCase("no") ||
                        input.equalsIgnoreCase("n")) {

                    return true;

                }
            }

        } while (true);

    }

    /**
     * Function used to ask a Question about a Country to a user and
     * document the user Score.
     */
    private void askQuestion() {
        final Country country;
        final Question question;
        int questionAttempt;

        country = World.getRandomCountry();
        question = Country.getCountryQuestion(country);
        questionAttempt = FIRST_ATTEMPT;

        do{
            if(!question.ask()) {
                System.out.println("INCORRECT!");
                questionAttempt++;

            } else {
                System.out.println("CORRECT!");
                break;

            }

        } while(questionAttempt <= LAST_ATTEMPT);

        documentNewScore(question, questionAttempt, score);
    }

    // Helper function used to document and assign the new Score from the current match
    // and display a loss message.
    private static void documentNewScore(final Question question,
                                         final int questionAttempt,
                                         final Score score) {

        if(questionAttempt == FIRST_ATTEMPT) {
            score.increaseNumCorrectFirstAttempt();

        } else if(questionAttempt > LAST_ATTEMPT) {
            System.out.println("The answer was " + question.getAnswer() + System.lineSeparator());
            score.increaseIncorrectTwoAttempts();

        } else {
            score.increaseNumCorrectSecondAttempt();
        }
    }

}
