package ca.bcit.comp2522.setB.project.marcuslages;

/**
 * Class that represents a WordGame about countries.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class WordGame extends Game {

    private final World world;
    private final Score score;

    /**
     * Creates a WordGame object from scratch.
     */
    public WordGame() {
        world = new World();
        score = new Score();
    }

    /**
     * Method used to start the WordGame.
     */
    @Override
    public void start() {
        do {

            startMatch();

        } while(true);


    }

    /**
     * Starts a single match of the WordGame.
     */
    @Override
    public void startMatch() {

    }

}
