package ca.bcit.comp2522.setB.project.marcuslages;

public class WordHand extends WordDeck {

    public WordHand() {
        super();
    }

    public WordHand(final WordPile pile,
                    final int initialHandSize) {

        super();
        drawFrom(pile, initialHandSize);
    }

    public void drawFrom(final WordPile pile,
                         final int drawSize) {

        if(pile == null) {
            throw new IllegalArgumentException("Invalid pile. Pile is null.");
        }

        for(int i = FIRST_WORD; i < drawSize; i++) {

            if(!pile.isEmpty()) {
                super.add(pile.draw());

            }
        }

    }

}
