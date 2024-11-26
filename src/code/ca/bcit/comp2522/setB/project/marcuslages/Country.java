package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.List;

/**
 * Class that represents a country and its information, including
 * name, capital city and some facts about it.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class Country {

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

}
