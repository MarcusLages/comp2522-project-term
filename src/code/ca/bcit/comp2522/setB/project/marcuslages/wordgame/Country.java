package ca.bcit.comp2522.setB.project.marcuslages.wordgame;

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
     * @throws IllegalArgumentException If any of the arguments is null or if
     *                                  name or capitalCityName is blank
     */
    public Country(final String name,
                   final String capitalCityName,
                   final String[] facts) {

        validateCountry(name, capitalCityName, facts);

        this.name = name;
        this.capitalCityName = capitalCityName;
        this.facts = facts;
    }


    /**
     * Function that returns the name of the country.
     *
     * @return name of the country
     */
    public String getName() {

        return name;
    }

    /**
     * Function that returns the capital city of the country.
     *
     * @return capital city of the country
     */
    public String getCapitalCityName() {

        return capitalCityName;
    }


    /**
     * Function that returns an array of facts about the country.
     *
     * @return array of facts about the country
     */
    public String[] getFacts() {

        return facts;
    }

    /**
     * Method used to create a random Question about a given country based on
     * its name, capital or array of facts.
     *
     * @param country given Country which the Question will be based on
     * @return Question about the country
     * @throws IllegalArgumentException if country is null
     */
    public static Question getCountryQuestion(final Country country) {

        if(country == null) {

            throw new IllegalArgumentException("Invalid country.");
        }

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

    /**
     * Function that parses a list of lines (String) into a String Array of facts about a country.
     *
     * @param lines         list of String lines
     * @param startLine     line which the parsing will start
     * @param factsCount    how many facts(lines) will be scanned/parsed
     * @return array with facts about the country
     */
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

    // Helper function to choose a random question type.
    private static int getRandomQuestionType() {

        final Random randomGenerator;
        randomGenerator = new Random();

        return randomGenerator.nextInt(NUM_COUNTRY_QUESTION_TYPES);
    }

    // Helper function that creates a Question about what is the name of the Country from its capital.
    private static Question countryFromCapitalQuestion(final Country country) {

        final String question;
        question = "Which country has " + country.capitalCityName + " as its capital?";

        return new Question(question, country.name);
    }

    // Helper function that creates a Question about what is the capital of a given Country.
    private static Question capitalQuestion(final Country country) {

        final String question;
        question = "What is the capital of " + country.name + "?";

        return new Question(question, country.capitalCityName);
    }

    // Helper function that creates a Question about one of the facts of a country.
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

    // Helper method to validate the Country constructor arguments to not be null and for
    // name and capitalCityName to not be blank.
    private void validateCountry(final String name,
                                 final String capitalCityName,
                                 final String[] facts) {

        if(name == null || name.isBlank()) {

            throw new IllegalArgumentException("Invalid name: " + name);
        }

        if(capitalCityName == null || capitalCityName.isBlank()) {

            throw new IllegalArgumentException("Invalid capitalCityName: " + capitalCityName);
        }

        if(facts == null) {

            throw new IllegalArgumentException("Facts array cannot be null.");
        }

    }

}
