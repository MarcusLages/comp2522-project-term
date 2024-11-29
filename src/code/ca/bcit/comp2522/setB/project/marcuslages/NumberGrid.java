package ca.bcit.comp2522.setB.project.marcuslages;

import java.util.Arrays;
import java.util.Random;

public abstract class NumberGrid {

    protected static final int EMPTY = 0;
    protected static final int FIRST_NUM = 0;
    protected static final int MIN_VAL = 1;
    protected static final int MAX_VAL = 1000;

    protected final int[][] numberGrid;

    public NumberGrid(final int gridSizeX, final int gridSizeY) {
        numberGrid = new int[gridSizeX][gridSizeY];
        reset();
    }

    private void reset() {

        for(int i = FIRST_NUM; i < numberGrid.length; i++) {
            Arrays.fill(numberGrid[i], EMPTY);
        }
    }

    public static int getRandomNumber() {

        final Random random;
        final int num;

        random = new Random();
        num = random.nextInt(MAX_VAL - MIN_VAL) + MIN_VAL;

        return num;
    }

    public abstract boolean placeValue(final int x, final int y);

    public abstract boolean checkLoss(final int value);

}
