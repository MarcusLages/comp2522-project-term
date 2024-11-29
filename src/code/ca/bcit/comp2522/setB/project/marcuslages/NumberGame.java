package ca.bcit.comp2522.setB.project.marcuslages;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class NumberGame extends Application
        implements Game {

    private static final int FIRST_BUTTON_IDX = 0;
    private static final int BUTTON_GRID_Y = 5;
    private static final int BUTTON_GRID_X = 5;

    // Command line arguments used when setting up javafx
    private final String[] args;
    private ButtonGrid grid;
    private VBox root;
    private Label header;
    private int currentNumber;

    public NumberGame() {

        this.args = null;
        currentNumber = NumberGrid.getRandomNumber();
    }

    public NumberGame(final String[] args) {

        this.args = args;
        currentNumber = NumberGrid.getRandomNumber();
    }

    @Override
    public void startGame() {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {

        final Scene scene;

        grid = new ButtonGrid(BUTTON_GRID_X, BUTTON_GRID_Y, this::newCurrentNumber);
        scene = getScene();

        stage.setTitle("Number game");
        stage.setScene(scene);
        stage.show();
    }

    private Scene getScene() {

        final VBox root;

        header = new Label("Number: " + currentNumber);
        root = new VBox(header, grid.getGridPane());

        return new Scene(root, 300,200);
    }

    private void handleClick(final int value,
                             final int i,
                             final int j) {

//        if(grid.checkPlacement(value, i, j)) {
//
//            buttonGrid[i][j].setText("" + value);
//            buttonGrid[i][j].setDisable(true);
//            getCurrentNumber();
//        }
//
//        buttonGrid[i][j].setText("" + value);

    }

    private void newCurrentNumber() {

        currentNumber = NumberGrid.getRandomNumber();
        grid.setCurrentValue(currentNumber);
        header.setText("Number: " + currentNumber);

        if(grid.checkLoss(currentNumber)) {
            endGame();

        }
    }

    // TODO
    private void endGame() {

    }

}
