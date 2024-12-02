package ca.bcit.comp2522.setB.project.marcuslages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

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
    private static final double GRID_PADDING = 10;
    private static final double BUTTON_HEIGHT = 70.0;
    private static final double BUTTON_WIDTH = 90.0;

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

        gridPane = createGridPane();
        buttonGrid = new Button[gridSizeX][gridSizeY];
        this.gridListener = gridListener;

        initializeButtonGrid();
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
                button.setMinHeight(BUTTON_HEIGHT);
                button.setMinWidth(BUTTON_WIDTH);
                button.setFont(new Font(NumberGame.FONT_SIZE));

                row = i;
                col = j;

                // TODO: explain algorithm
                button.setOnAction(e -> {

                    if(placeValue(currentValue, row, col)) {

                        button.setText("" + currentValue);
                        decreaseEmptySpots();

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
     * Checks if the specified grid cell is within the valid interval to place the current value.
     *
     * @param x     x-coordinate of where the value will be inserted in the grid
     * @param y     y-coordinate of where the value will be inserted in the grid
     * @return      true if the position is valid for placing the current value
     */
    public boolean isInValidInterval(final int x,
                                     final int y) {

        final int floor;
        final int ceil;
        final int linearIdx;

        // Algorithm checks for the floor (smallest possible value to place the value in
        // the grid) and the ceil (biggest possible value to place the value in the grid)
        floor = getFloor(currentValue);
        ceil = getCeil(currentValue);
        linearIdx = x * numberGrid[x].length + y;

        // If the linear value of the coordinates of where the current value is going
        // to be placed is between floor and ceil, then it allows the value to be placed there.
        return floor <= linearIdx && linearIdx <= ceil;
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
     * Helper function to help creating the GridPane to hold all the buttons.
     *
     * @return configured GridPane
     */
    private static GridPane createGridPane() {
        
        final GridPane grid;
        grid = new GridPane();

        grid.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        grid.setPadding(new Insets(GRID_PADDING));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(GRID_PADDING);
        grid.setVgap(GRID_PADDING);

        return grid;

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

        // TODO: explain algorithm
        final int widthX;
        final int heightY;
        int floor;

        // Floor starts on the first block (top right)
        widthX = numberGrid[FIRST_NUM].length;
        heightY = numberGrid.length;
        floor = FIRST_NUM;

        // Floor goes all the way to the last block (bottom left)
        while(floor < (widthX * heightY - ARRAY_OFFSET)) {

            final int floorX;
            final int floorY;

            floorX = floor / widthX;
            floorY = floor % widthX;

            // Once it finds an empty block, it stops and tries
            // to find if there's a larger number in the next blocks.
            // If there's no more blocks or if the next block is larger,
            // returns the current number as the floor.
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

        final int widthX;
        final int heightY;
        int ceil;

        // Ceiling starts as the last block (bottom right)
        widthX = numberGrid[FIRST_NUM].length ;
        heightY = numberGrid.length ;
        ceil = widthX * heightY - ARRAY_OFFSET;

        // Ceiling goes all the way to the first block (top left)
        while(ceil >= FIRST_NUM) {

            final int ceilX;
            final int ceilY;

            ceilX = ceil / widthX;
            ceilY = ceil % widthX;

            // Once it finds an empty block, it stops and tries
            // to find if there's a smaller number in the previous blocks.
            // If there's no more blocks or if the next block is smaller,
            // returns the current number as the ceiling.
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

        // Variable that will check the value after floor to make
        // sure it's possible to place the value there
        final int widthX;
        final int heightY;

        int floorNext;

        // Starts in the current given linear floor
        floorNext = linearFloor;
        widthX = numberGrid[FIRST_NUM].length ;
        heightY = numberGrid.length ;

        while(floorNext < (widthX * heightY - ARRAY_OFFSET)) {

            final int floorNextX;
            final int floorNextY;

            floorNextX = floorNext / widthX;
            floorNextY = floorNext % widthX;

            // If it finds a spot that is not empty,
            // returns true if it's larger
            if(numberGrid[floorNextX][floorNextY] != EMPTY) {

                return numberGrid[floorNextX][floorNextY] >= currentValue;
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

        // Variable that will check the value before the ceil to make
        // sure it's possible to place the value there
        final int widthX;
        int ceilBefore;

        // Starts in the current given linearCeil
        ceilBefore = linearCeil;
        widthX = numberGrid[FIRST_NUM].length;

        while(ceilBefore >= FIRST_NUM) {

            final int ceilBeforeX;
            final int ceilBeforeY;

            ceilBeforeX = ceilBefore / widthX;
            ceilBeforeY = ceilBefore % widthX;

            // If it finds a spot that is not empty,
            // returns true if it's larger
            if(numberGrid[ceilBeforeX][ceilBeforeY] != EMPTY) {

                return numberGrid[ceilBeforeX][ceilBeforeY] <= currentValue;
            }

            ceilBefore--;
        }

        return true;
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
    public boolean isGameEnd(final int value) {

        if(super.noEmptySpots()) {
            return true;

        }

        // TODO: explain algorithm
        final int floor;
        final int ceil;

        floor = getFloor(value);
        ceil = getCeil(value);

        return floor > ceil;
    }

}