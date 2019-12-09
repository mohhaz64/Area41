import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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

	@FXML private GridPane gridPane;
    @FXML private Button cancelButton;
    @FXML private Button confirmButton;
    @FXML private Button deleteUserButton;
    @FXML private TextField userNameText;
    
    private MenuController parentController;
    private String currentUserName;


    public void initialize() {
        confirmButton.setOnAction(e -> {
            updateUser();
        });
        cancelButton.setOnAction(e -> {
        	closeWindow();
        });
        deleteUserButton.setOnAction(e -> {
        	deleteUser();
        });
    }

    public void closeWindow() {
    	Stage stage = (Stage) gridPane.getScene().getWindow();
	    stage.close();
    }
    
    public void deleteUser() {
        BufferedReader reader;
        ArrayList<String> userList = new ArrayList<>();
        ArrayList<Integer> userLevel = new ArrayList<>();
        

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setContentText("Change Username to Delete the User?");
        alert.showAndWait();

        if (alert.getResult().getButtonData().isDefaultButton()) {
            try {
                reader = new BufferedReader(new FileReader("users.txt"));
                String line = reader.readLine();
                while (line != null) {
                	String[] data = line.split(" ");
            		userList.add(data[0]);
            		userLevel.add(Integer.parseInt(data[1]));
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).equals(currentUserName)) {
                    userList.remove(i);
                    userLevel.remove(i);
                    i--;
                }
            }

            try {
                FileWriter fileWriter = new FileWriter("users.txt", false); //Set true for append mode
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for (String user : userList) {
                	int index = userList.indexOf(user);
                    printWriter.println(user + " " + userLevel.get(index));
                }
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            parentController.removeUser(currentUserName);
            Stage stage = (Stage) deleteUserButton.getScene().getWindow();
            stage.close();

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setContentText("User Deleted!");
            alert.showAndWait();
        }
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
