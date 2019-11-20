import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController {

    @FXML private Button startGameButton;
    @FXML private Label selectUserButton;
    @FXML private Button createNewUserButton;
    @FXML private Label selectLevelButton;
    @FXML private ComboBox<String> levelList;
    @FXML private Button editUserButton;
    @FXML private Label messgeOfTheDay;
    @FXML private Button quitButton;
    @FXML private ComboBox<String> userList;
    
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Level> levels = new ArrayList<Level>();
    
    public void initialize() {

    	users.add(new User("Callum"));
		users.add(new User("Link"));
		users.add(new User("Zelda"));
		
		
		Level level1 = ReadLevelFile.readDataFile("tempLevel.txt");
		
		levels.add(level1);
		
    	
		// Setup actions on buttons
		quitButton.setOnAction(e -> {
			handleQuitButtonAction();
		});
		
		editUserButton.setOnAction(e -> {
			handleEditButtonAction();
		});
		
		startGameButton.setOnAction(e -> {
			startGame();
		});
		
		createNewUserButton.setOnAction(e -> {
			handleNewUserButton();
		});
		
		// Display the countries
		refreshUserList();
		refreshLevelList();
	}
    
    private void refreshUserList() {
		// Clear the displayed list
		userList.getItems().clear();
		
		// Add each country to the displayed list
		for (User c : users) {
			userList.getItems().add(c.getName());
		}
	}
    private void refreshLevelList() {
		// Clear the displayed list
		levelList.getItems().clear();
		
		// Add each country to the displayed list
		for (Level c : levels) {
			levelList.getItems().add(c.getName());
		}
	}
    
    public void handleQuitButtonAction() {
    	System.exit(0);
    }
    
    public void handleNewUserButton() {
    	try {
			// Create a FXML loader for loading the Edit Country FXML file.
			GridPane newUserRoot = (GridPane)FXMLLoader.load(getClass().getResource("CreateUser.fxml"));        

			Scene newUserScene = new Scene(newUserRoot, Main.CREATE_USER_WINDOW_WIDTH, Main.CREATE_USER_WINDOW_HEIGHT);
		    
			// Create a new stage (i.e., window) based on the edit scene
			Stage newUserStage = new Stage();
			newUserStage.setScene(newUserScene);
			newUserStage.setTitle(Main.WINDOW_TITLE);
			
			// Make the stage a modal window.
			// This means that it must be closed before you can interact with any other window from this application.
			newUserStage.initModality(Modality.APPLICATION_MODAL);
			
			// Show the edit scene and wait for it to be closed
			newUserStage.showAndWait();
			
			// The above method only returns when the window is closed.
			
			// Any changes that the edit scene made to the country will have taken place to the country object.
			// This object is part of our country list.
			// So we just need to refresh the JavaFX ListView.
			refreshUserList();
			
		} catch (IOException e) {
			e.printStackTrace();
			// Quit the program (with an error code)
			System.exit(-1);
		}
    };
    
    public void handleEditButtonAction() {
    	try {
    		
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditUser.fxml"));     

			// Run the loader
			GridPane editUserRoot = (GridPane)fxmlLoader.load();          
			// Access the controller that was created by the FXML loader
			EditUserController editUserController = fxmlLoader.<EditUserController>getController();
			
			//String userName = userList.get
			
			Scene editUserScene = new Scene(editUserRoot, 400, 500);
		    
			// Create a new stage (i.e., window) based on the edit scene
			Stage editUserStage = new Stage();
			editUserStage.setScene(editUserScene);
			editUserStage.setTitle(Main.WINDOW_TITLE);
			
			// Make the stage a modal window.
			// This means that it must be closed before you can interact with any other window from this application.
			editUserStage.initModality(Modality.APPLICATION_MODAL);
			
			// Show the edit scene and wait for it to be closed
			editUserStage.showAndWait();
			
			// The above method only returns when the window is closed.
			
			// Any changes that the edit scene made to the country will have taken place to the country object.
			// This object is part of our country list.
			// So we just need to refresh the JavaFX ListView.
			refreshUserList();
			
		} catch (IOException e) {
			e.printStackTrace();
			// Quit the program (with an error code)
			System.exit(-1);
		}
    }
    
    private void startGame() {
		// Get the index of the selected item in the displayed list
		int selectedIndex = levelList.getSelectionModel().getSelectedIndex();
		
		// Check if user selected an item
		if (selectedIndex < 0) {
			// Show a message
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Please select a User AND a level");
			alert.showAndWait();
			return;
		}
		
		// Can only get to this line if user has selected a country
		Level selectedLevel = levels.get(selectedIndex);


		// We need to use a try-catch block as the loading of the FXML file can fail.
		try {
			// Create a FXML loader for loading the Edit Country FXML file.
			//GridPane gameRoot = (GridPane)FXMLLoader.load(getClass().getResource("GameController.fxml"));        
			// Access the controller that was created by the FXML loader
			//GameController gameController = fxmlLoader.<GameController>getController();
			
			// Create a FXML loader for loading the Edit Country FXML file.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameController.fxml"));     
			// Run the loader
			GridPane gameRoot = (GridPane)fxmlLoader.load();          
			// Access the controller that was created by the FXML loader
			GameController game = fxmlLoader.<GameController>getController();
			
			game.setLevel(selectedLevel);
			
			//*************
			//* Important *
			//*************
			// Tell the controller which country we are editing.
			// Remember we are passing arrows (i.e., references) around.
			// This means that the edit controller's changes to the country will be reflected here (in our list).
			
			// Create a scene based on the loaded FXML scene graph
			Scene gameScene = new Scene(gameRoot, Main.GAME_WINDOW_WIDTH, Main.GAME_WINDOW_HEIGHT);
		    
			// Create a new stage (i.e., window) based on the edit scene
			Stage gameStage = new Stage();
			gameStage.setScene(gameScene);
			gameStage.setTitle(Main.WINDOW_TITLE);
			
			// Make the stage a modal window.
			// This means that it must be closed before you can interact with any other window from this application.
			gameStage.initModality(Modality.APPLICATION_MODAL);
			
			// Show the edit scene and wait for it to be closed
			gameStage.showAndWait();
			
			// The above method only returns when the window is closed.
			
			// Any changes that the edit scene made to the country will have taken place to the country object.
			// This object is part of our country list.
			// So we just need to refresh the JavaFX ListView.
			refreshLevelList();
			
		} catch (IOException e) {
			e.printStackTrace();
			// Quit the program (with an error code)
			System.exit(-1);
		}
	}

}
