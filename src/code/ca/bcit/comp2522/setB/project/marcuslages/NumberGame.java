package ca.bcit.comp2522.setB.project.marcuslages;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NumberGame extends Application
        implements Game {

    // Command line arguments used when setting up javafx
    final String[] args;

    public NumberGame() {
        this.args = null;
    }

    public NumberGame(final String[] args) {
        this.args = args;
    }

    @Override
    public void startGame() {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        // Create a GridPane
        GridPane grid = new GridPane();

        // Add controls to the grid (row, column)
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        Button button4 = new Button("Button 4");

        grid.add(button1, 0, 0); // Row 0, Column 0
        grid.add(button2, 1, 0); // Row 0, Column 1
        grid.add(button3, 0, 1); // Row 1, Column 0
        grid.add(button4, 1, 1); // Row 1, Column 1

        // Set the scene
        Scene scene = new Scene(grid, 300, 200);
        stage.setTitle("GridPane Example");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void startMatch() {

    }

}
