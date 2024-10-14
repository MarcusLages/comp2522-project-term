package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Map;
import java.util.HashMap;

/**
 * Class that represents a World full of countries and their
 * information.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class World {

    /*
    * Hashmap containing all the country's objects.
    * Key: country's name
    * Value: country's Country object
    */
    private final Map<String, Country> countryMap;

    /**
     * Creates a World object and populates it with all the countries.
     */
    public World() {
        countryMap = new HashMap<>();

        populateCountryMap();
    }

    // Method to populate the countryMap hashmap with all the countries.
    private static void populateCountryMap() {

    }

}
