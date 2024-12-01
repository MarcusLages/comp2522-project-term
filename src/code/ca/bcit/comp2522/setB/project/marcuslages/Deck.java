package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Deck {

    protected static final int FIRST_WORD = 0;

    private final List<Word> deck;

    public Deck() {

        deck = new ArrayList<>();
    }

    protected void add(final Word word) {

        if(word != null) {
            deck.add(word);

        }
    }

    public Word draw(final int index) {

        return deck.remove(index);
    }

    public Word draw() {

        return deck.removeLast();
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
