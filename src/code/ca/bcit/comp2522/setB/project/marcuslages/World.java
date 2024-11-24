package ca.bcit.comp2522.setB.project.marcuslages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class that represents a World full of countries and their
 * information.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class World {

    /* Data lines per country data in the txt file (counting the '\n'). */
    private static final int DATA_LINES_PER_COUNTRY = 4;

    /* Data lines per country data in the txt file (counting the '\n'). */
    private static final int LINES_PER_COUNTRY = 5;
    private static final int INITIAL_LINE_COUNTER = 0;
    private static final int DIVISIBLE = 0;

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
    private void populateCountryMap() {

        for(char fileName = 'a'; fileName <= 'z'; fileName++) {

            final Path currentFilepath;
            final String filenameTxtExtension;

            // Resolving the filepath with the filename and its txt extension into one Path
            filenameTxtExtension = fileName + ".txt";
            currentFilepath = Paths.get("src","resources");
            currentFilepath.resolve(filenameTxtExtension);

            if(Files.exists(currentFilepath)) {
                // TODO: list of lines

            } else {

                final String errorMsg;
                errorMsg = filenameTxtExtension + " does not exist." + System.lineSeparator()

                System.out.println(errorMsg);
                throw new RuntimeException(errorMsg);
            }

            try {
                final Scanner fileScanner;
                fileScanner = new Scanner(countryFactsFile);
                int lineCounter;
                lineCounter = INITIAL_LINE_COUNTER;

                while(fileScanner.hasNextLine()) {

                    final Scanner lineScanner;
                    final String currLine;

                    final String countryName;
                    final String countryCapital;
                    final String[] countryFacts;

                    countryName = null;
                    countryCapital = null;
                    countryFacts = new String[DATA_LINES_PER_COUNTRY];

                    currLine = fileScanner.nextLine();

                    if(lineCounter % LINES_PER_COUNTRY == DIVISIBLE) {
                        // Checking first line
                        lineScanner = new Scanner(currLine);
                        lineScanner.useDelimiter(":");

                        while(lineScanner.hasNext()) {
                            // TODO: Be able to get data from each part of the first line
                            final String word;
                            word = lineScanner.next();

                        }

                    } if(lineCounter % LINES_PER_COUNTRY == DATA_LINES_PER_COUNTRY) {
                        // Checks if we are the last line and adds country to HashMap.
                        final Country currCountry;
                        currCountry = new Country(countryName, countryCapital, countryFacts);
                        addCountry(currCountry);
                    } else {
                        // Checking facts line
                        int factIdx;

                        // TODO: Check at which position we are at facts array and append to it
                    }

                    lineCounter++;
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addCountry(final Country country) {
        if(country != null &&
            country.getName() != null &&
            country.getCapitalCityName() != null &&
            country.getFacts() != null) {

            countryMap.put(country.getName(), country);
        }
    }

}
