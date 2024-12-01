package ca.bcit.comp2522.setB.project.marcuslages;

public class WordBoard extends WordDeck {

    private Word headWord;
    private Word rearWord;

    public WordBoard(final Word initialWord) {
        super();

        super.add(initialWord);
        headWord = initialWord;
        rearWord = initialWord;
    }

    public boolean playWord(final Word word) {

        if(super.contains(word)) {
            return false;

        }

        if(headWord.positionWord(word) == Word.HEAD_POSITION) {

            addHead(word);
            headWord = word;
            return true;

        }

        if(rearWord.positionWord(word) == Word.REAR_POSITION) {

            addRear(word);
            rearWord = word;
            return true;
        }

        return false;
    }

}
