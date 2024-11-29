package ca.bcit.comp2522.setB.project.marcuslages;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * ButtonGrid is a class that represents a grid of buttons for
 * placing values in a game. The class is implemented using JavaFX
 * and the Button objects are placed into a GridPane component.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class ButtonGrid extends NumberGrid {

    // Used to access the last element of array.
    // Use array.length - ARRAY_OFFSET
    private static final int ARRAY_OFFSET = 1;

    private final GridPane gridPane;
    private final Button[][] buttonGrid;
    private final GridListener gridListener;
    private int currentValue;

    /**
     * Initializes the ButtonGrid with specified grid size and a listener
     * for when a number is placed in the grid.
     *
     * @param gridSizeX     width of the grid.
     * @param gridSizeY     height of the grid.
     * @param gridListener the listener to handle grid events when a number is placed.
     */
    public ButtonGrid(final int gridSizeX,
                      final int gridSizeY,
                      final GridListener gridListener) {

        super(gridSizeX, gridSizeY);

        gridPane = new GridPane();
        buttonGrid = new Button[gridSizeX][gridSizeY];
        this.gridListener = gridListener;

        initializeButtonGrid();
    }

    /**
     * Places a value in the specified grid cell if the position is valid.
     *
     * @param value value to be placed in the grid
     * @param x     x-coordinate of where the value will be inserted in the grid
     * @param y     y-coordinate of where the value will be inserted in the grid
     * @return      true if the value was successfully placed
     */
    @Override
    public boolean placeValue(final int value,
                              final int x,
                              final int y) {

        // Validates the coordinates to be inside the bounds of the grid
        if((x < FIRST_NUM || x > numberGrid.length) ||
                (y < FIRST_NUM || y > numberGrid[FIRST_NUM].length)) {

            return false;

        }

        // Only inserts into the grid if the grid is EMPTY and if
        // the interval for inserting the value is valid.
        if(numberGrid[x][y] == EMPTY && isInValidInterval(x, y)) {

            numberGrid[x][y] = currentValue;
            return true;
        }

        return false;

    }

    /**
     * Checks if the game is lost based on the current value that has to be placed.
     *
     * @param value value to check.
     * @return      true if the game is lost, given the value
     */
    @Override
    public boolean isGameLost(final int value) {

        // TODO: explaing algorithm
        final int floor;
        final int ceil;

        floor = getFloor(value);
        ceil = getCeil(value);

        return floor > ceil;
    }

    /**
     * Checks if the specified grid cell is within the valid interval to place the current value.
     *
     * @param x     x-coordinate of where the value will be inserted in the grid
     * @param y     y-coordinate of where the value will be inserted in the grid
     * @return      true if the position is valid for placing the current value
     */
    public boolean isInValidInterval(final int x,
                                     final int y) {

        // TODO: explaing algorithm
        final int floor;
        final int ceil;
        final int linearIdx;

        floor = getFloor(currentValue);
        ceil = getCeil(currentValue);
        linearIdx = x * numberGrid[x].length + y;

        return floor <= linearIdx && linearIdx <= ceil;
    }

    /**
     * Initializes the button grid by adding buttons to each cell in the grid and setting
     * up their actions on click.
     * When a button is clicked, the current value is placed in the grid (according to
     * the rules of the game).
     */
    private void initializeButtonGrid() {

        for(int i = FIRST_NUM; i < numberGrid.length; i++) {
            for(int j = FIRST_NUM; j < numberGrid[i].length; j++) {

                final Button button;
                final int row;
                final int col;

                button = new Button(" ");
                row = i;
                col = j;

                // TODO: explaing algorithm
                button.setOnAction(e -> {

                    if(placeValue(currentValue, row, col)) {

                        button.setText("" + currentValue);

                        if(gridListener != null) {
                            gridListener.onNumberPlaced();
                        }
                    }
                });

                buttonGrid[row][col] = button;
                gridPane.add(button, j, i);
            }
        }
    }

    /**
     * Resets the grid by clearing all button texts in the GridPane,
     * emptying the whole numberGrid and reassigning the buttonGrid.
     */
    @Override
    public void reset() {
        super.reset();

        if(buttonGrid != null && gridPane != null) {

            gridPane.getChildren().clear();
            initializeButtonGrid();

        }
    }

    /**
     * Sets the current value to be placed next in the grid.
     *
     * @param value value to set as the current value.
     */
    public void setCurrentValue(final int value) {

        if(value >= MIN_VAL && value <= MAX_VAL) {
            currentValue = value;
        }
    }

    /**
     * Retrieves the GridPane that holds the buttons for the grid.
     *
     * @return GridPane containing the buttons.
     */
    public GridPane getGridPane() {
        return gridPane;
    }

    /**
     * Calculates the floor index interval for placing a value in the grid.
     * The floor is the first available position where the value can be placed.
     *
     * @Attention: The value returned is a linear value, and not its 2D coordinate.
     *             For example: the coordinate of (2,1) in a 3x3 grid
     *             would be 7, as we are counting: 2x3 + 1.
     *
     * @param value value to check the floor for.
     * @return      floor index where the value can be placed (as a linear index).
     */
    private int getFloor(final int value) {

        // TODO: explaing algorithm
        final int widthX;
        final int heightY;
        int floor;

        widthX = numberGrid[FIRST_NUM].length;
        heightY = numberGrid.length;
        floor = FIRST_NUM;

        while(floor < (widthX * heightY - ARRAY_OFFSET)) {

            final int floorX;
            final int floorY;

            floorX = floor / widthX;
            floorY = floor % widthX;

            if(numberGrid[floorX][floorY] == EMPTY &&
                largerNumAfter(floor)) {

                break;
            }

            floor++;
        }

        return floor;
    }

    /**
     * Calculates the ceiling index interval for placing a value in the grid.
     * The ceiling is the last available position where the value can be placed.
     *
     * @Attention: The value returned is a linear value, and not its 2D coordinate.
     *             For example: the coordinate of (2,1) in a 3x3 grid
     *             would be 7, as we are counting: 2x3 + 1.
     *
     * @param value value to check the ceiling for.
     * @return      ceiling index where the value can be placed (as a linear index).
     */
    private int getCeil(final int value) {

        // TODO: explaing algorithm
        final int widthX;
        final int heightY;
        int ceil;

        widthX = numberGrid[FIRST_NUM].length ;
        heightY = numberGrid.length ;
        ceil = widthX * heightY - ARRAY_OFFSET;

        while(ceil >= FIRST_NUM) {

            final int ceilX;
            final int ceilY;

            ceilX = ceil / widthX;
            ceilY = ceil % widthX;

            if(numberGrid[ceilX][ceilY] == EMPTY &&
                    smallerNumBefore(ceil)) {

                break;
            }

            ceil--;
        }

        return ceil;
    }


    /**
     * Checks if there are any larger numbers after the specified position.
     *
     * @Attention: The value required for linearFloor is a linear value, and not
     *             its 2D coordinate.
     *             For example: the coordinate of (2,1) in a 3x3 grid
     *             would be 7, as we are counting: 2x3 + 1.
     *
     * @param linearFloor   linear index of the position to check.
     * @return              true if there are larger numbers after the position or
     *                      if there's only empty spaces after that index
     */
    private boolean largerNumAfter(final int linearFloor) {

        // TODO: explaing algorithm
        // Variable that will check the value after floor to make
        // sure it's possible to place the value there
        final int widthX;
        final int heightY;

        int floorNext;

        floorNext = linearFloor;
        widthX = numberGrid[FIRST_NUM].length ;
        heightY = numberGrid.length ;

        while(floorNext < (widthX * heightY - ARRAY_OFFSET)) {

            final int floorNextX;
            final int floorNextY;

            floorNextX = floorNext / widthX;
            floorNextY = floorNext % widthX;

            if(numberGrid[floorNextX][floorNextY] != EMPTY) {
                if(numberGrid[floorNextX][floorNextY] < currentValue) {

                    return false;
                } else {

                    return true;
                }
            }

            floorNext++;
        }

        return true;
    }

    /**
     * Checks if there are any smaller numbers before the specified position.
     *
     * @Attention: The value required for linearCeil is a linear value, and not
     *             its 2D coordinate.
     *             For example: the coordinate of (2,1) in a 3x3 grid
     *             would be 7, as we are counting: 2x3 + 1.
     *
     * @param linearCeil    linear index of the position to check.
     * @return              true if there are smaller numbers before the position or
     *                      if there's only empty spaces before the index
     */
    private boolean smallerNumBefore(final int linearCeil) {

        // TODO: explaing algorithm
        // Variable that will check the value before the ceil to make
        // sure it's possible to place the value there
        final int widthX;
        int ceilBefore;

        ceilBefore = linearCeil;
        widthX = numberGrid[FIRST_NUM].length;

        while(ceilBefore >= FIRST_NUM) {

            final int ceilBeforeX;
            final int ceilBeforeY;

            ceilBeforeX = ceilBefore / widthX;
            ceilBeforeY = ceilBefore % widthX;

            if(numberGrid[ceilBeforeX][ceilBeforeY] != EMPTY) {
                if(numberGrid[ceilBeforeX][ceilBeforeY] > currentValue) {

                    return false;
                } else {

                    return true;
                }
            }

            ceilBefore--;
        }

        return true;
    }
}