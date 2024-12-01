package ca.bcit.comp2522.setB.project.marcuslages;

public class WordHand extends WordDeck {

    public WordHand() {
        super();
    }

    public WordHand(final WordDeck drawingDeck,
                    final int initialHandSize) {

        super();
        drawFrom(drawingDeck, initialHandSize);
    }

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

    public void reset(final WordDeck drawingDeck,
                      final int drawSize) {

        super.clear();
        drawFrom(drawingDeck, drawSize);
    }

}
