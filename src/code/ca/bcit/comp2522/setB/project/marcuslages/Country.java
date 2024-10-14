package ca.bcit.comp2522.setB.project.marcuslages;

/**
 * Class that represents a country and its information, including
 * name, capital city and some facts about it.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class Country {

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

}
