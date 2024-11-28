package ca.bcit.comp2522.setB.project.marcuslages;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NumberGame extends Application
        implements Game {

    private static final int FIRST_BUTTON_IDX = 0;
    private static final int BUTTON_GRID_Y = 5;
    private static final int BUTTON_GRID_X = 5;

    // Command line arguments used when setting up javafx
    private final String[] args;
    private final Button[][] buttonGrid;

    public NumberGame() {
        this.args = null;
        buttonGrid = new Button[BUTTON_GRID_X][BUTTON_GRID_Y];
    }

    public NumberGame(final String[] args) {
        this.args = args;
        buttonGrid = new Button[BUTTON_GRID_X][BUTTON_GRID_Y];
    }

    @Override
    public void startGame() {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {

        final Scene scene;
        scene = getScene();

        stage.setTitle("Number game");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void startMatch() {

    }

    private Scene getScene() {

        final VBox root;
        final HBox header;
        final GridPane grid;

        header = getHeader();
        grid = populateNumberGrid();

        root = new VBox(header, grid);

        return new Scene(root, 300, 200);
    }

    private HBox getHeader() {

        final HBox header;
        final Label headerText;

        headerText = new Label("Header text");
        header = new HBox(headerText);

        return header;
    }

    private GridPane populateNumberGrid() {

        final GridPane grid;
        grid = new GridPane();

        for(int i = FIRST_BUTTON_IDX; i < BUTTON_GRID_X; i++)  {
            for(int j = FIRST_BUTTON_IDX; j < BUTTON_GRID_Y; j++) {

                buttonGrid[i][j] = new Button(i + "" + j);
                grid.add(buttonGrid[i][j], i, j);

            }
        }

        return grid;
    }

}
