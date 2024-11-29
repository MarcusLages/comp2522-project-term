package ca.bcit.comp2522.setB.project.marcuslages;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ButtonGrid extends NumberGrid {

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

//        if((x < FIRST_NUM || x > numberGrid.length) ||
//                (y < FIRST_NUM || y > numberGrid[FIRST_NUM].length)) {
//
//            return false;
//
//        }
//
//        if(numberGrid[x][y] != EMPTY && isInValidInterval(currentValue, x, y)) {
//
//            numberGrid[x][y] = currentValue;
//            return true;
//        }
//
//        return false;
        return true;

    }

    // TODO
    @Override
    public boolean checkLoss(final int value) {
//        final int floor;
//        final int ceil;
//
//        floor = getFloor(value);
//        ceil = getCeil(value);
//
//        return floor > ceil;
        return false;
    }

    public boolean isInValidInterval(final int value,
                                     final int x,
                                     final int y) {

//        final int floor;
//        final int ceil;
//        final int linearIdx;
//
//        floor = getFloor(value);
//        ceil = getCeil(value);
//        linearIdx = x * numberGrid[x].length + y;
//
//        return floor < linearIdx && linearIdx < ceil;
        return true;
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

    public void setCurrentValue(final int value) {

        if(value >= MIN_VAL && value <= MAX_VAL) {
            currentValue = value;
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    // TODO
    private int getFloor(final int value) {

        final int widthX;
        final int heightY;
        int floor;

        widthX = numberGrid[FIRST_NUM].length ;
        heightY = numberGrid.length ;
        floor = widthX * heightY;

        while(floor > FIRST_NUM) {

            final int floorX;
            final int floorY;

            floorX = floor / widthX;
            floorY = floor % widthX;

//            if()

            floor--;
        }

        return 2;
    }

    // TODO
    private int getCeil(final int value) {
        return 2;
    }

}