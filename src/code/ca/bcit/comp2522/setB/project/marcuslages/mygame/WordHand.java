package ca.bcit.comp2522.setB.project.marcuslages.mygame;

/**
 * Represents a hand of words drawn from a deck.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class WordHand extends WordDeck {

    /**
     * Constructs an empty `WordHand` object.
     */
    public WordHand() {

        super();
    }

    /**
     * Constructs a `WordHand` object by drawing all words from the specified drawing deck.
     *
     * @param drawingDeck the deck from which words will be drawn.
     */
    public WordHand(final WordDeck drawingDeck) {

        super();
        drawAll(drawingDeck);
    }

    /**
     * Constructs a `WordHand` object by drawing a specific number of words from the
     * specified drawing deck.
     *
     * @param drawingDeck the deck from which words will be drawn.
     * @param initialHandSize the number of words to draw from the deck.
     */
    public WordHand(final WordDeck drawingDeck,
                    final int initialHandSize) {

        super();
        drawFrom(drawingDeck, initialHandSize);
    }

    /**
     * Draws a specific number of words from the provided drawing deck and adds them to the hand.
     *
     * @param drawingDeck the deck from which words will be drawn.
     * @param drawSize the number of words to draw from the deck.
     * @throws IllegalArgumentException if the drawingDeck is null.
     */
    public void drawFrom(final WordDeck drawingDeck,
                         final int drawSize) {

        if(drawingDeck == null) {

            throw new IllegalArgumentException("Invalid drawingDeck. Pile is null.");
        }

        for(int i = FIRST_WORD; i < drawSize; i++) {

            if(!drawingDeck.isEmpty()) {
                super.add(drawingDeck.draw());

            }
        }

    }

    /**
     * Draws all the words from the provided drawing deck and adds them to the hand.
     *
     * @param drawingDeck the deck from which all words will be drawn.
     * @throws IllegalArgumentException if the drawingDeck is null.
     */
    public void drawAll(final WordDeck drawingDeck) {

        if(drawingDeck == null) {

            throw new IllegalArgumentException("Invalid drawingDeck. Pile is null.");
        }

        while(!drawingDeck.isEmpty()) {

            super.add(drawingDeck.draw());
        }
    }

    /**
     * Resets the hand by clearing the current words and drawing a specific number of words
     * from the provided drawing deck.
     *
     * @param drawingDeck the deck from which words will be drawn.
     * @param drawSize the number of words to draw from the deck.
     */
    public void reset(final WordDeck drawingDeck,
                      final int drawSize) {

        super.clear();
        drawFrom(drawingDeck, drawSize);
    }

    /**
     * Resets the hand by clearing the current words and drawing all words from the provided
     * drawing deck.
     *
     * @param drawingDeck the deck from which all words will be drawn.
     */
    public void reset(final WordDeck drawingDeck) {

        super.clear();
        drawAll(drawingDeck);
    }

}
