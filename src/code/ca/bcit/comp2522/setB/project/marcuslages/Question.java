package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Random;

/**
 * The Question class represents a question about a Country and its corresponding answer.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class Question {

    private final String question;
    private final String answer;

    /**
     * Constructs a Question object with a specified question and answer.
     *
     * @param question question text
     * @param answer   answer text
     * @throws IllegalArgumentException if the question or answer is null or blank
     */
    public Question(final String question,
                    final String answer) {

        validateQA(question);
        validateQA(answer);

        this.question = question;
        this.answer = answer;
    }

    /**
     * Returns the question being asked as String.
     *
     * @return question being asked
     */
    public String getQuestion(){
        return question;
    }

    /**
     * Returns the answer for the question being asked as String.
     *
     * @return answer for the question
     */
    public String getAnswer(){
        return answer;
    }

    // Validates qa to not be null or blank
    private static void validateQA(final String qa) {
        if(qa == null || qa.isBlank()) {
            throw new IllegalArgumentException("Invalid question/answer. Input: " + qa);
        }
    }

}

