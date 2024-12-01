package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class WordDeck {

    private static final int WORD_NOT_FOUND = -1;
    protected static final int FIRST_WORD = 0;

    private final List<Word> deck;

    public WordDeck() {

        deck = new ArrayList<>();
    }

    protected void add(final Word word) {

        if(word != null) {
            deck.add(word);

        }
    }

    protected void addHead(final Word word) {

        if(word != null) {
            deck.addFirst(word);

        }
    }

    protected void addRear(final Word word) {
        add(word);
    }

    public Word draw(final int index) {

        return deck.remove(index);
    }

    public Word draw(final String word) {

        final Word searchedWord;
        final int searchedWordIdx;

        searchedWord = new Word(word);
        searchedWordIdx = deck.indexOf(searchedWord);

        if(searchedWordIdx == WORD_NOT_FOUND) {
            return null;

        }

        return draw(searchedWordIdx);
    }

    public Word draw(final Word word) {

        final int searchedWordIdx;
        searchedWordIdx = deck.indexOf(word);

        if(searchedWordIdx == WORD_NOT_FOUND) {
            return null;

        }

        return draw(searchedWordIdx);
    }

    public Word draw() {

        return deck.removeLast();
    }

    public boolean contains(final Word word) {

        return deck.contains(word);
    }

    public boolean isEmpty() {

        return deck.isEmpty();
    }

    public void clear() {

        deck.clear();
    }

    public void shuffle() {

        Collections.shuffle(deck);
    }

    public Stream<Word> stream() {

        return deck.stream()
                .filter(Objects::nonNull);
    }

    @Override
    public String toString() {

        final StringBuilder sb;
        sb = new StringBuilder();

        sb.append("[ ");
        deck.stream()
                .filter(Objects::nonNull)
                .forEach(word -> sb.append(word).append(" "));
        sb.append("]");

        return sb.toString();
    }

}
