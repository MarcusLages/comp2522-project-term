package ca.bcit.comp2522.setB.project.marcuslages;

public class WordBoard extends WordDeck {

    private Word headWord;
    private Word rearWord;

    public WordBoard(final Word initialWord) {
        super();

        reset(initialWord);
    }

    public boolean playWord(final Word word) {

        final int wordPosition;
        wordPosition = canPlayWord(word);

        if(wordPosition == Word.HEAD_POSITION) {

            addHead(word);
            headWord = word;
            return true;

        }

        if(wordPosition == Word.REAR_POSITION) {

            addRear(word);
            rearWord = word;
            return true;
        }

        return false;
    }

    public void reset(final Word initialWord) {

        super.clear();
        super.add(initialWord);
        headWord = initialWord;
        rearWord = initialWord;
    }

    public int canPlayWord(final Word word) {

        if(super.contains(word)) {
            return Word.NO_POSITION;

        }

        final int positionHead;
        final int positionRear;

        positionHead = headWord.positionWord(word);
        positionRear = rearWord.positionWord(word);

        if(positionHead == Word.HEAD_POSITION) {
            return positionHead;

        }

        if(positionRear == Word.REAR_POSITION) {
            return positionRear;
        }

        return Word.NO_POSITION;
    }

    public boolean playableDeck(final WordDeck deck) {

        return deck.stream()
                .anyMatch(word -> canPlayWord(word) != Word.NO_POSITION);
    }

}
