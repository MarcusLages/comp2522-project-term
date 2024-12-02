package ca.bcit.comp2522.setB.project.marcuslages;

import ca.bcit.comp2522.setB.project.marcuslages.mygame.MyGame;
import ca.bcit.comp2522.setB.project.marcuslages.numbergame.NumberGame;
import ca.bcit.comp2522.setB.project.marcuslages.wordgame.WordGame;

/**
 * Factory utility class used to create a Game based on
 * the GameInterface.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public final class GameFactory {

    public static final char WORD_GAME_CHAR = 'w';
    public static final char NUMBER_GAME_CHAR = 'n';
    public static final char MY_GAME_CHAR = 'm';
    public static final char QUIT_CHAR = 'q';

    // Private constructor to avoid instantiating this class.
    private GameFactory() {}

    /**
     * Helper function used to return the Game that will be played respective to the input given.
     *
     * @param gameChar input used to get proper Game object
     * @return Game respective to the gameChar, or null if no game was found
     */
    public static Game getGame(final char gameChar) {

        switch(gameChar) {

            case WORD_GAME_CHAR:
                return new WordGame();

            case NUMBER_GAME_CHAR:
                return new NumberGame();

            case MY_GAME_CHAR:
                return new MyGame();

            case QUIT_CHAR:
                return null;

            default:
                throw new IllegalArgumentException("Invalid input in getGame method.");
        }

    }

    /**
     * Method to check for all valid gameChars and if they correspond to
     * a game or the quit char.
     *
     * @param gameChar game char given for checking
     * @return true if given gameChar corresponds to a valid game char or
     *              quit char
     */
    public static boolean validGameChar(final char gameChar) {

        return gameChar == WORD_GAME_CHAR ||
                gameChar == NUMBER_GAME_CHAR ||
                gameChar == MY_GAME_CHAR ||
                gameChar == QUIT_CHAR;
    }
}
