package ca.bcit.comp2522.setB.project.marcuslages;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ButtonGrid extends NumberGrid {

    // Used to access the last element of array.
    // Use array.length - ARRAY_OFFSET
    private static final int ARRAY_OFFSET = 1;

    private GridPane gridPane;
    private Button[][] buttonGrid;
    private GridListener gridListener;
    private int currentValue;

    public ButtonGrid(final int gridSizeX,
                      final int gridSizeY,
                      final GridListener gridListener) {

        super(gridSizeX, gridSizeY);

        gridPane = new GridPane();
        buttonGrid = new Button[gridSizeX][gridSizeY];
        this.gridListener = gridListener;

        initializeButtonGrid();
    }

    @Override
    public boolean placeValue(final int x,
                              final int y) {

        if((x < FIRST_NUM || x > numberGrid.length) ||
                (y < FIRST_NUM || y > numberGrid[FIRST_NUM].length)) {

            return false;

        }

        if(numberGrid[x][y] == EMPTY && isInValidInterval(x, y)) {

            numberGrid[x][y] = currentValue;
            return true;
        }

        return false;

    }

    // TODO
    @Override
    public boolean checkLoss(final int value) {
        final int floor;
        final int ceil;

        floor = getFloor(value);
        ceil = getCeil(value);

        return floor > ceil;
    }

    public boolean isInValidInterval(final int x,
                                     final int y) {

        final int floor;
        final int ceil;
        final int linearIdx;

        floor = getFloor(currentValue);
        ceil = getCeil(currentValue);
        linearIdx = x * numberGrid[x].length + y;

        return floor <= linearIdx && linearIdx <= ceil;
    }

    private void initializeButtonGrid() {

        for(int i = FIRST_NUM; i < numberGrid.length; i++) {
            for(int j = FIRST_NUM; j < numberGrid[i].length; j++) {

                final Button button;
                final int row;
                final int col;

                button = new Button(" ");
                row = i;
                col = j;

                button.setOnAction(e -> {

                    if(placeValue(row, col)) {

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

    @Override
    public void reset() {
        super.reset();

        if(buttonGrid != null && gridPane != null) {
            gridPane.getChildren().clear();
            initializeButtonGrid();

        }
    }

    public void setCurrentValue(final int value) {

        if(value >= MIN_VAL && value <= MAX_VAL) {
            currentValue = value;
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    private int getFloor(final int value) {

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

    // TODO
    private int getCeil(final int value) {

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


    // Returns true if goes through the whole array and doesn't find anything
    private boolean largerNumAfter(final int linearFloor) {
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

    // Returns true if goes through the whole array and doesn't find anything
    private boolean smallerNumBefore(final int linearCeil) {

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