package ca.bcit.comp2522.setB.project.marcuslages.mygame;

import ca.bcit.comp2522.setB.project.marcuslages.Resettable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a pile of words that can be drawn from.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class WordPile extends WordDeck
        implements Resettable {

    /**
     * The name of the file that contains the list of words.
     */
    private static final String wordsFilename = "my-game-words";

    /**
     * Constructs a `WordPile` object and resets the pile by loading words from a file.
     */
    public WordPile() {

        reset();
    }

    /**
     * Resets the pile by clearing the current words, loading new words from a file, and shuffling the pile.
     */
    @Override
    public void reset() {

        if(!super.isEmpty()) {

            super.clear();
        }

        final Path wordsFilepath;
        wordsFilepath = getWordsFilepath();

        if(Files.exists(wordsFilepath)) {

            final List<String> wordList;

            try {

                wordList = Files.readAllLines(wordsFilepath);
                getFilteredStream(wordList)
                        .map(str -> new Word(str.trim()))
                        .forEach(super::add);

            } catch (final IOException e) {

                throw new RuntimeException("Txt file was not found." +
                        e.getMessage());
            }
        }

        super.shuffle();

    }

    /**
     * Gets the file path of the words file located in the "src/resources" directory.
     *
     * @return the path to the words file.
     */
    private static Path getWordsFilepath() {

        final Path resourceFolder;
        final Path wordsFilepath;

        resourceFolder = Paths.get("src", "resources");
        wordsFilepath = resourceFolder.resolve(wordsFilename + ".txt");

        return wordsFilepath;
    }

    /**
     * Filters out null or blank strings from the provided list of strings.
     *
     * @param list the list of strings to be filtered.
     * @return a stream of non-null, non-blank strings.
     */
    private static Stream<String> getFilteredStream(final List<String> list) {

        return list.stream()
                .filter(str -> str != null && !str.isBlank());
    }

}
