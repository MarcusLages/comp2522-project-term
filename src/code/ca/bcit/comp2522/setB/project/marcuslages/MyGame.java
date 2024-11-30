package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.ArrayList;
import java.util.List;

public class MyGame
        implements Game, Resettable {

    private final WordDeck deck;
    private final List<Word> userHand;
    private final List<Word> botHand;

    public MyGame() {

        deck = new WordDeck();
        userHand = new ArrayList<>();
        botHand = new ArrayList<>();


    }

    @Override
    public void startGame() {

    }

    @Override
    public void reset() {

        userHand.clear();
        botHand.clear();
        deck.refillDeck();
        startGame();
    }
}
