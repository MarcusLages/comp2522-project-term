package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.List;
import java.util.Random;

/**
 * Class that represents a country and its information, including
 * name, capital city and some facts about it.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class Country {

    private static final int NUM_COUNTRY_QUESTION_TYPES = 3;
    private static final int QUESTION_A = 0;
    private static final int QUESTION_B = 1;
    private static final int QUESTION_C = 2;
    private static final int FIRST_FACT_IDX = 0;

    private final String name;
    private final String capitalCityName;
    private final String[] facts;

    /**
     * Creates a Country object using the given information.
     *
     * @param name              name of the country
     * @param capitalCityName   country's capital city
     * @param facts             string array of facts about the given country
     */
    public Country(final String name, final String capitalCityName, final String[] facts) {
        // TODO: Validate country

        this.name = name;
        this.capitalCityName = capitalCityName;
        this.facts = facts;
    }

    public String getName() {
        return name;
    }

    public String getCapitalCityName() {
        return capitalCityName;
    }

    public String[] getFacts() {
        return facts;
    }

    public static Question getCountryQuestion(final Country country) {

        final int questionType;
        questionType = getRandomQuestionType();

        switch(questionType) {
            case QUESTION_A:
                return countryFromCapitalQuestion(country);

            case QUESTION_B:
                return capitalQuestion(country);

            case QUESTION_C:
                return countryFactsQuestion(country);

            default:
                throw new RuntimeException("Invalid question type. Question type: " + questionType);
        }
    }

    public static String[] parseCountryFacts(final List<String> lines,
                                              final int startLine,
                                              final int factsCount) {

        int currentLine;
        final String[] countryFacts;

        currentLine = startLine;
        countryFacts = new String[factsCount];

        // Goes through the next factsCount lines and adds them to
        // the countryFacts array.
        for (int j = FIRST_FACT_IDX; j < factsCount && currentLine < lines.size(); j++) {

            countryFacts[j] = lines.get(currentLine).trim();

            // Next country facts line
            currentLine++;
        }

        return countryFacts;
    }

    private static int getRandomQuestionType() {
        final Random randomGenerator;
        randomGenerator = new Random();

        return randomGenerator.nextInt(NUM_COUNTRY_QUESTION_TYPES);
    }

    private static Question countryFromCapitalQuestion(final Country country) {
        final String question;
        question = "Which country has " + country.capitalCityName + " as its capital?";

        return new Question(question, country.name);
    }

    private static Question capitalQuestion(final Country country) {
        final String question;
        question = "What is the capital of " + country.name + "?";

        return new Question(question, country.capitalCityName);
    }

    private static Question countryFactsQuestion(final Country country) {
        final String question;
        final int randFactIdx;
        final Random randomGenerator;

        randomGenerator = new Random();
        randFactIdx = randomGenerator.nextInt(country.facts.length);
        question = "Which country is represented by this fact?\n" +
            country.facts[randFactIdx];

        return new Question(question, country.capitalCityName);
    }

}
