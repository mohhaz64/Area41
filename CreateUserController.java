import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import javafx.stage.Stage;

public class CreateUserController {

    @FXML private Button createUserButton;
    @FXML private TextField newUserName;
    private MenuController parentController;

    public void initialize() {

        createUserButton.setOnAction(e -> {
            createUser();
        });

    }

    public void setParentController(MenuController parentController) {
        this.parentController = parentController;
    }

    private void createUser() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        String userName = newUserName.getText();

        if (User.validName(userName)) {

            try {
                FileWriter fileWriter = new FileWriter("users.txt", true); //Set true for append mode
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(userName + "\n");
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            parentController.addUser(userName);

            Stage stage = (Stage) createUserButton.getScene().getWindow();
            stage.close();

            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("User Has Been Created!");
            alert.showAndWait();

         }

    }

}
