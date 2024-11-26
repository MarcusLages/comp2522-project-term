package ca.bcit.comp2522.setB.project.marcuslages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class that represents a World full of countries and Country objects
 * and their information.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class World {

    private static final int FIRST_LINE = 0;
    private static final int COUNTRY_NAME_IDX = 0;
    private static final int COUNTRY_CAPITAL_IDX = 1;
    private static final int COUNTRY_FACTS = 3;

    /*
    * Map containing all the country's objects.
    * Key: country's name
    * Value: country's Country object
    */
    private static final Map<String, Country> countryMap;

    // Creates the countryMap HashMap and populates it with all the countries.
    static {
        countryMap = new HashMap<>();
        populateCountryMap();
    }

    /**
     * Doesn't allow the creation of a World object.
     */
    private World() {
    }

    public static Country getRandomCountry() {

        final List<String> countryNameList;
        final Random randGenerator;
        final int randomCountryNameIdx;

        countryNameList = new ArrayList<>(countryMap.keySet());
        randGenerator = new Random();
        randomCountryNameIdx = randGenerator.nextInt(countryNameList.size());

        return countryMap.get(countryNameList.get(randomCountryNameIdx));
    }

    // Helper method to populate the countryMap a map with all the countries.
    private static void populateCountryMap() {

        final Path folderPath;
        folderPath = Paths.get("src","resources");

        for(char fileName = 'a'; fileName <= 'z'; fileName++) {

            final String filenameTxtExtension;
            final Path currentFilepath;

            // Resolving the filepath with the filename and its txt extension into one Path
            filenameTxtExtension = fileName + ".txt";
            currentFilepath = folderPath.resolve(filenameTxtExtension);

            if(Files.exists(currentFilepath)) {
                parseCountriesToMap(currentFilepath);

            }

        }
    }

    // Helper method to parse countries from a file into a
    // map (key: country name, value: Country object).
    private static void parseCountriesToMap(final Path currentFilepath) {

        try {

            final List<String> lines;
            int currentLine;

            lines = Files.readAllLines(currentFilepath);
            currentLine = FIRST_LINE;

            while (currentLine < lines.size()) {

                // Skip empty lines between entries
                if (lines.get(currentLine).trim().isEmpty()) {
                    currentLine++;
                    continue;
                }

                final Country country;
                final String[] nameCapital;
                final String countryName;
                final String capitalCityName;
                final String[] countryFacts;

                // Extract country name and capital
                nameCapital = lines.get(currentLine).split(":");
                countryName = nameCapital[COUNTRY_NAME_IDX].trim();
                capitalCityName = nameCapital[COUNTRY_CAPITAL_IDX].trim();

                // Jumps to the next line, which are the country facts lines.
                currentLine++;

                // Extract the next COUNTRY_FACTS lines as facts and
                // skips those COUNTRY_FACTS lines
                countryFacts = Country.parseCountryFacts(lines, currentLine, COUNTRY_FACTS);
                currentLine += COUNTRY_FACTS;

                country = new Country(countryName, capitalCityName, countryFacts);

                countryMap.put(countryName, country);
            }

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Helper function to only add a country to a map (key: country name, value: Country object)
    // if validated.
    private static void addCountry(final Country country) {

        if(country != null &&
            (country.getName() != null || !country.getName().isBlank()) &&
            (country.getCapitalCityName() != null || !country.getCapitalCityName().isBlank()) &&
            country.getFacts() != null) {

            countryMap.put(country.getName(), country);
        }
    }

}
