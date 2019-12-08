import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LeaderBoardController {

	@FXML private GridPane gridPane;
    @FXML private Label levelBeingDisplayed;
    @FXML private Label firstUser;
    @FXML private Label secondUser;
    @FXML private Label thirdUser;
    @FXML private Label secondTime;
    @FXML private Label firstTime;
    @FXML private Label thirdTime;
    @FXML private Button backButton;
    private MenuController parentController;
    
    public void setParentController(MenuController parentController) {
        this.parentController = parentController;
    }

}
