package ca.bcit.comp2522.setB.project.marcuslages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class WordDeck {

    private static final String wordsFilename = "my-game-words";

    final List<Word> words;

    public WordDeck() {

        words = new ArrayList<>();
        refillDeck(words);
        Collections.shuffle(words);
    }

    private static void refillDeck(final List<Word> words) {

        final Path wordsFilepath;
        wordsFilepath = getWordsFilepath();

        if(Files.exists(wordsFilepath)) {

            final List<String> wordList;

            try {
                wordList = Files.readAllLines(wordsFilepath);
                getFilteredStream(wordList)
                        .map(str -> new Word(str.trim()))
                        .forEach(words::add);

            } catch (final IOException e) {

                throw new RuntimeException("Txt file was not found." +
                        e.getMessage());
            }
        }
    }

    private static Path getWordsFilepath() {

        final Path resourceFolder;
        final Path wordsFilepath;

        resourceFolder = Paths.get("src", "resources");
        wordsFilepath = resourceFolder.resolve(wordsFilename + ".txt");

        return wordsFilepath;
    }

    private static Stream<String> getFilteredStream(final List<String> list) {
        return list.stream()
                .filter(str -> str != null && !str.isBlank());
    }

}
