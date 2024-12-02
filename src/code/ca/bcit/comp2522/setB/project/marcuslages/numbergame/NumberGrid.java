package ca.bcit.comp2522.setB.project.marcuslages.numbergame;

import ca.bcit.comp2522.setB.project.marcuslages.Resettable;

import java.util.Arrays;
import java.util.Random;

// TODO: CHECK FOR PRIVATE, FINALS AND ETC.
/**
 * NumberGrid is an abstract class that represents a grid of numbers for a game
 * where values are placed within a grid and must adhere to certain rules
 * (e.g., ascending/descending order).
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public abstract class NumberGrid
        implements Resettable {

    // Number that represents an empty grid position.
    protected static final int EMPTY = 0;
    protected static final int FIRST_NUM = 0;
    protected static final int MIN_VAL = 1;
    protected static final int MAX_VAL = 1000;

    private int emptySpots;

    protected final int[][] numberGrid;

    /**
     * Initializes the grid with specified dimensions and sets all
     * elements as EMPTY.
     *
     * @param gridSizeX number of rows in the grid
     * @param gridSizeY number of columns in the grid
     */
    public NumberGrid(final int gridSizeX, final int gridSizeY) {

        numberGrid = new int[gridSizeX][gridSizeY];
        reset();
    }

    /**
     * Resets the grid by making all cells EMPTY.
     * This method should be called when the grid needs to be cleared.
     */
    @Override
    public void reset() {

        for(int i = FIRST_NUM; i < numberGrid.length; i++) {

            Arrays.fill(numberGrid[i], EMPTY);
        }

        emptySpots = numberGrid.length * numberGrid[FIRST_NUM].length;
    }

    /**
     * Generates a random number between MIN_VAL and MAX_VAL.
     *
     * @return randomly generated number between MIN_VAL and MAX_VAL.
     */
    public static int getRandomNumber() {

        final Random random;
        final int num;

        random = new Random();
        num = random.nextInt(MAX_VAL - MIN_VAL) + MIN_VAL;

        return num;
    }

    /**
     * Function used to decrease the count of the amount of empty spots in the grid.
     */
    protected void decreaseEmptySpots() {

        if(emptySpots > EMPTY) {

            emptySpots--;
        }
    }

    /**
     * Checks and returns true if there's no more empty spots in the number grid.
     *
     * @return true if there's no more empty spots in the grid
     */
    public boolean noEmptySpots() {

        return emptySpots == EMPTY;
    }

    /**
     * Abstract method for placing a value into the grid at the specified position.
     * This method needs to be implemented by subclasses to define the logic for
     * placing a value based on the game's rules.
     *
     * @param value value that will be placed in the grid
     * @param x     x-coordinate where the value will be placed in the grid
     * @param y     y-coordinate where the value will be placed in the grid
     * @return      true if the value was successfully placed
     */
    public abstract boolean placeValue(final int value, final int x, final int y);

    /**
     * Abstract method for checking if it's possible to place a specific value in
     * the current state of the grid according to the game's rules.
     * This method needs to be implemented by subclasses to define the game's loss logic.
     *
     * @param value value to check for a loss/end condition.
     * @return      true if the value cannot be placed in the grid
     */
    public abstract boolean isGameEnd(final int value);

}
