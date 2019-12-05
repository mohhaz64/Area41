import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStreamReader;

public class EditUserController {

    @FXML private Button cancelButton;
    @FXML private Button confirmButton;
    @FXML private TextField userNameText;
    private MenuController parentController;
    private String currentUserName;


    public void initialize() {
        confirmButton.setOnAction(e -> {
            updateUser();
        });
    }

    public void setParentController(MenuController parentController) {
        this.parentController = parentController;
    }

    public void changeUserNameText(String username) {
        currentUserName = username;
        userNameText.setText(username);
    }

    private void updateUser() {
        BufferedReader reader;
        ArrayList<String> userList = new ArrayList<>();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);

        String newUserName = userNameText.getText();

        try {
            reader = new BufferedReader(new FileReader("users.txt"));
            String line = reader.readLine();
            while (line != null) {
                userList.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (User.validName(newUserName, currentUserName)) {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Edit");
            alert.setContentText("Change Username to " + newUserName + "?");
            alert.showAndWait();

            if (alert.getResult().getButtonData().isDefaultButton()) {
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).equals(currentUserName)) {
                        userList.set(i, newUserName);
                    }
                }
                try {
                    FileWriter fileWriter = new FileWriter("users.txt", false); //Set true for append mode
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    for (String user : userList) {
                        printWriter.println(user);
                    }
                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                parentController.editUser(currentUserName, newUserName);
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.close();

                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setContentText("Username Changed!");
                alert.showAndWait();

            }

        }

    }

}
