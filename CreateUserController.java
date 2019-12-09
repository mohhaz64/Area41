import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import javafx.stage.Stage;

/**
 *
 * @author Mo Hazrati, Jeffrey Edeh
 * @version 1.6
 *
 */
public class CreateUserController {

    @FXML private Button createUserButton;
    @FXML private TextField newUserName;
    private MenuController parentController;

    /**
     * Initializes create user functionality on create button click.
     */
    public void initialize() {

        createUserButton.setOnAction(e -> {
            createUser();
        });

    }

    /**
     * sets the local parent controller variable to reference the parent controller.
     * @param parentController parent controller reference.
     */
    public void setParentController(MenuController parentController) {
        this.parentController = parentController;
    }


    /**
     * this reads in the new userName from the text field.
     * then runs check to see if the name is valid.
     * if the name is valid it is added to the list of users.
     */
    private void createUser() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        String userName = newUserName.getText();

        if (User.validName(userName)) {

            try {
                FileWriter fileWriter = new FileWriter("users.txt", true); //Set true for append mode
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(userName + " 0\n");
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            parentController.addUser(userName + " 0");

            Stage stage = (Stage) createUserButton.getScene().getWindow();
            stage.close();

            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("User Has Been Created!");
            alert.showAndWait();

         }

    }

}
