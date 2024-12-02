package ca.bcit.comp2522.setB.project.marcuslages.numbergame;

import ca.bcit.comp2522.setB.project.marcuslages.Game;
import ca.bcit.comp2522.setB.project.marcuslages.Resettable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * NumberGame is a JavaFX-based game where players place numbers in a grid,
 * aiming to maintain ascending order. The game tracks player progress and offers
 * the option to restart upon losing.
 * Implements Game and Resettable interfaces  for starting and resetting the game.
 *
 * @author Marcus Vinicius Santos Lages
 * @version 1.0
 */
public class NumberGame extends Application
        implements Game, Resettable {

    public static final int FONT_SIZE = 24;
    private static final int WINDOW_HEIGHT = 450;
    private static final int WINDOW_WIDTH = 550;
    private static final int BUTTON_GRID_Y = 5;
    private static final int BUTTON_GRID_X = 5;

    // Command line arguments used for setting up javafx
    private final String[] args;
    private final NumberGameScore score;

    private ButtonGrid grid;
    private Label header;
    private int currentNumber;

    /**
     * Default constructor, initializes with javafx with no command-line arguments.
     */
    public NumberGame() {

        this.args = null;
        score = new NumberGameScore();
        currentNumber = NumberGrid.getRandomNumber();
    }

    /**
     * Constructor accepting command-line arguments for javafx configs
     *
     * @param args command-line arguments for JavaFX.
     */
    public NumberGame(final String[] args) {

        this.args = args;
        score = new NumberGameScore();
        currentNumber = NumberGrid.getRandomNumber();
    }

    /**
     * Start the game by launching the JavaFX application.
     */
    @Override
    public void startGame() {
        Application.launch(args);
    }

    /**
     * Initializes the JavaFX stage and sets up the UI components.
     *
     * @param stage primary stage for the application.
     * @throws Exception if any error occurs during initialization.
     */
    @Override
    public void start(final Stage stage) throws Exception {

        final Scene scene;

        grid = new ButtonGrid(BUTTON_GRID_X, BUTTON_GRID_Y, this::newCurrentNumber);
        grid.setCurrentValue(currentNumber);

        scene = getScene();

        stage.setTitle("Number game");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates and returns the main scene containing the grid and header label.
     *
     * @return main JavaFX Scene
     */
    private Scene getScene() {

        final VBox root;

        header = new Label("Number: " + currentNumber);
        header.setFont(new Font(FONT_SIZE));

        root = new VBox(header, grid.getGridPane());

        root.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(grid.getGridPane(), javafx.scene.layout.Priority.ALWAYS);
        root.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        return new Scene(root, WINDOW_WIDTH,WINDOW_HEIGHT);
    }

    private void newCurrentNumber() {

        currentNumber = NumberGrid.getRandomNumber();
        grid.setCurrentValue(currentNumber);
        header.setText("Number: " + currentNumber);
        score.incrementPlacement();

        if(grid.isGameEnd(currentNumber)) {
            endGame();

        }
    }

    private void endGame() {
        final Alert confirmPopup;  // Set confirmPopup type
        final Optional<ButtonType> result;

        confirmPopup = getConfirmationPopUp(score, grid.noEmptySpots());
        result = confirmPopup.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            reset();

        } else {

            final Alert exitPopup;
            exitPopup = getExitPopup(score);

            exitPopup.showAndWait();
            Platform.exit();

        }

    }

    private static Alert getConfirmationPopUp(final NumberGameScore score,
                                              final boolean win) {

        final Alert popup;
        popup = new Alert(Alert.AlertType.CONFIRMATION);

        if(win) {
            popup.setTitle("You win!");
            score.incrementWins();

        } else {
            popup.setTitle("You lost!");

        }
        popup.setHeaderText("Would you like to play again?");
        popup.setContentText(score + "\nClick OK to restart or Cancel to exit.");

        return popup;
    }

    private static Alert getExitPopup(final NumberGameScore score) {

        final Alert popup;
        popup = new Alert(Alert.AlertType.INFORMATION);

        popup.setTitle("Exit");
        popup.setHeaderText(null);
        popup.setContentText(score.toString());

        return popup;
    }

    @Override
    public void reset() {

        grid.reset();
        newCurrentNumber();
        score.incrementGames();

    }

}
