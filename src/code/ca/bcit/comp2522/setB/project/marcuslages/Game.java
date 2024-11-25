package ca.bcit.comp2522.setB.project.marcuslages;

/**
 * Class that represents common abstract parts of a game, such as
 * score and user input.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public abstract class Game {

    /**
     * Method used to start the overall game.
     */
    public abstract void start();

    /**
     * Method used to start a single match of the game.
     */
    public abstract void startMatch();

}