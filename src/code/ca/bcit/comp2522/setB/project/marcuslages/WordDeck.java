package ca.bcit.comp2522.setB.project.marcuslages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class WordDeck extends Deck {

    private static final String wordsFilename = "my-game-words";

    public WordDeck() {

        refillDeck();
    }

    public void drawTo(final Deck deck,
                     final int drawSize) {

        if(deck == null) {
            throw new IllegalArgumentException("Invalid deck. Deck is null.");
        }

        for(int i = FIRST_WORD; i < drawSize; i++) {

            if(!super.isEmpty()) {
                deck.add(super.draw());

            }
        }
    }

    public void refillDeck() {

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
