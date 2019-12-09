import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeadController {

    @FXML
    private GridPane gridPane;
    private GameController parentController;

    /**
     * Called when the quit button is pressed.
     *
     * @param event
     */
    @FXML
    void clickQuit(ActionEvent event) {
        Stage stage = (Stage) gridPane.getScene().getWindow();
        parentController.clickQuit(event);
        stage.close();
    }

    /**
     * Called when the restart button is pressed.
     *
     * @param event
     */
    @FXML
    void clickRestart(ActionEvent event) {
        Stage stage = (Stage) gridPane.getScene().getWindow();
        parentController.clickRestart(event);
        stage.close();

    }

    /**
     * Sets the controllers parent
     *
     * @param gameController parent controller.
     */
    public void setParentController(GameController gameController) {
        this.parentController = gameController;

    }

}