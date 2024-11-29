package ca.bcit.comp2522.setB.project.marcuslages;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

// TODO: DOCUMENT IT
// TODO: CHECK FOR PRIVATE, FINALS AND ETC.
public class NumberGame extends Application
        implements Game, Resettable {

    private static final int BUTTON_GRID_Y = 5;
    private static final int BUTTON_GRID_X = 5;

    // Command line arguments used when setting up javafx
    private final String[] args;
    private ButtonGrid grid;
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
        grid.setCurrentValue(currentNumber);

        scene = getScene();

        stage.setTitle("Number game");
        stage.setScene(scene);
        stage.show();
    }

    private Scene getScene() {

        final VBox root;

        header = new Label("Number: " + currentNumber);
        root = new VBox(header, grid.getGridPane());

        // TODO: MAGIC NUMBER
        return new Scene(root, 300,200);
    }

    private void newCurrentNumber() {

        currentNumber = NumberGrid.getRandomNumber();
        grid.setCurrentValue(currentNumber);
        header.setText("Number: " + currentNumber);

        if(grid.isGameLost(currentNumber)) {
            endGame();

        }
    }

    // TODO
    private void endGame() {
        final Alert popup;  // Set popup type
        final Optional<ButtonType> result;

        popup = getConfirmationPopUp();
        result = popup.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            reset();

        } else {
            Platform.exit();

        }

    }

    private static Alert getConfirmationPopUp() {

        final Alert popup;
        popup = new Alert(Alert.AlertType.CONFIRMATION);

        popup.setTitle("Try Again?");
        popup.setHeaderText("Would you like to play again?");
        popup.setContentText("Click OK to restart or Cancel to exit.");

        return popup;
    }

    @Override
    public void reset() {
        grid.reset();
        newCurrentNumber();
    }

}
