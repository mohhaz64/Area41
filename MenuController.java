import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

    private static HttpURLConnection connection;

    //Lists to hold game Levels as well as all the created Users.
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> levels = new ArrayList<String>();
    
    private int noOfLevels = 3;
	
	static MediaPlayer mediaPlayer;
	
	String path = "ChipsMusic2.mp3";  

    //Instantiating Media class  
    Media media = new Media(new File(path).toURI().toString());  
    
    static Level selectedLevel;
    
	/**
	 * Initialize the controller.
	 * This method is called automatically and everything within is run IN ORDER.
	 */
    public void initialize() {

    	addUsersToList();

		// Level .txt files are read in and added to the list of levels
		for (int i = 1; i <= noOfLevels; i++) {
			levels.add("Level " + i);
		}
		
		levels.add("Saved Game");


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

		// Refreshing the User and Level lists in case of any changes (added, edited or deleted)
		refreshUserList();
		refreshLevelList();
		messageOfTheDay();
	}

	private void addUsersToList() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("users.txt"));
			String line = reader.readLine();
			while (line != null) {
				users.add(new User(line));
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addUser(String userName) {
    	users.add(new User(userName));
	}

	public void editUser(String currentUserName, String newUserName) {
    	for (User user : users) {
    		if (user.getName().equals(currentUserName)){
    			user.setName(newUserName);
			}
			user.updateSavedFile();
		}
	}

    /**
	 * Refreshes the User List
	 */
    public void refreshUserList() {
		// Clear the displayed list
		userList.getItems().clear();

		// Add each User to the displayed List
		for (User c : users) {
			userList.getItems().add(c.getName());
		}
	}
	
	/**
	 * Refreshes the Level List
	 */
	public void refreshLevelList() {
		// Clear the displayed list
		levelList.getItems().clear();

		// Add each Level to the displayed list
		for (String c : levels) {
			levelList.getItems().add(c);
		}
	}

	public void removeUser(String userName) {
		User userToRemove = null;
		for (User user : users) {
			if (user.getName().equals(userName)){
				userToRemove = user;
			}
		}
		users.remove(userToRemove);
		refreshUserList();
	}

	private void messageOfTheDay(){
		// Create buffer reader for getting connection response
		// Create line string for each line of buffer reader
		// Create string buffer to hold response text
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();

		// get cipher text
		// build string builder for appending solution chars
		String puzzle = getPuzzle();
		StringBuilder solution = new StringBuilder();

		//index through string if even increment char by one otherwise decrement by one and append to solution string
		for (int i = 0; i < puzzle.length(); i++) {
			char currentChar = puzzle.charAt(i);
			if (i % 2 == 0) {
				solution.append(Character.toString((char)(((int)currentChar + 1 - (int)'A') % 26 + (int)'A')));
			} else {
				solution.append(Character.toString((char)(((int)currentChar - 1 + (int)'A') % 26 + (int)'A')));
			}
		}

		// creates GET request to obtain message using the solution
		try {
			// Connect to URL
			URL url = new URL("http://cswebcat.swan.ac.uk/message?solution=" + solution);
			connection = (HttpURLConnection) url.openConnection();

			// Setup request
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			// Get request status code
			int status = connection.getResponseCode();

			//Check if request is valid
			if (status == 403 ) {
				// if error obtain error response
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
			} else {
				// otherwise get valid response
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}

			// set FXML label to message text
			String message = responseContent.toString();
			messgeOfTheDay.setText(message);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// disconnect from connection
			connection.disconnect();
		}

	}

	private String getPuzzle() {
		BufferedReader reader;
		String line;
		StringBuilder responseContent = new StringBuilder();

		try {
			URL url = new URL("http://cswebcat.swan.ac.uk/puzzle");
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			int status = connection.getResponseCode();

			if (status > 299)  {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}

		// return cipher text
		return responseContent.toString();

	}


	/**
	 * Exits the program the  the 'Quit' button is selected
	 */
    public void handleQuitButtonAction() {
    	System.exit(0);
    }

	/**
	 * Handle the Create New User button.
	 * This will display a window allowing the user to create a new user with their desired name.
	 * After the creation is complete, the displayed User list will be updated.
	 */
	public void handleNewUserButton() {
		try {
			// Create a FXML loader for loading the Edit User FXML file.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
			GridPane newUserRoot = (GridPane) fxmlLoader.load();

			CreateUserController createUserController = fxmlLoader.<CreateUserController>getController();
			createUserController.setParentController(this);

			Scene newUserScene = new Scene(newUserRoot, Main.CREATE_USER_WINDOW_WIDTH, Main.CREATE_USER_WINDOW_HEIGHT);

			// Create a new stage based on the NewUser scene
			Stage newUserStage = new Stage();
			newUserStage.setScene(newUserScene);
			newUserStage.setTitle(Main.WINDOW_TITLE);
			newUserStage.setResizable(false);

			// Make the stage a modal window.
			// This means that it must be closed before you can interact with any other window from this application.
			newUserStage.initModality(Modality.APPLICATION_MODAL);

			// Show the Create New User scene and wait for it to be closed
			newUserStage.showAndWait();

			// Refresh the User List in order to add the created user to the list
			refreshUserList();

		} catch (IOException e) {
			e.printStackTrace();
			// Quit the program (with an error code)
			System.exit(-1);
		}
    };

	/**
	 * Handle the Edit User button.
	 * This will display a window allowing the user to Edit a selected User.
	 * After the edit is complete, the displayed User list will be updated.
	 */
    public void handleEditButtonAction() {
		int selectedUserIndex = userList.getSelectionModel().getSelectedIndex();

		if (selectedUserIndex < 0) {
			// Show a message
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Please Select a User");
			alert.showAndWait();

		} else {

			try {

				// Create a FXML loader for loading the CreateUser FXML file.
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditUser.fxml"));

				// Run the loader
				GridPane editUserRoot = (GridPane) fxmlLoader.load();
				// Access the controller that was created by the FXML loader
				EditUserController editUserController = fxmlLoader.<EditUserController>getController();
				editUserController.changeUserNameText(userList.getItems().get(selectedUserIndex));
				editUserController.setParentController(this);

				Scene editUserScene = new Scene(editUserRoot, Main.EDIT_USER_WINDOW_WIDTH, Main.EDIT_USER_WINDOW_HEIGHT);

				// Create a new stage based on the editUser scene
				Stage editUserStage = new Stage();
				editUserStage.setScene(editUserScene);
				editUserStage.setTitle(Main.WINDOW_TITLE);
				editUserStage.setResizable(false);

				// Make the stage a modal window.
				// This means that it must be closed before you can interact with any other window from this application.
				editUserStage.initModality(Modality.APPLICATION_MODAL);

				// Show the edit scene and wait for it to be closed
				editUserStage.showAndWait();

				// Refresh the user list to update any changes/deletes.
				refreshUserList();



			} catch (IOException e) {
				e.printStackTrace();
				// Quit the program with an error code
				System.exit(-1);
			}
		}
	}
	
	/**
	 * Handle the Start Game button.
	 * This will display a window allowing the user to play the game with the selected level.
	 */
	private void startGame() {
		// Get the index of the selected level & User in the displayed list
		int selectedIndex = levelList.getSelectionModel().getSelectedIndex();
		int selectedUserIndex = userList.getSelectionModel().getSelectedIndex();

		// Check if user selected an level
		if (selectedIndex < 0 || selectedUserIndex < 0) {
			// Show a message
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Please select a User AND a level");
			alert.showAndWait();
			return;
		}

		// Can only get to this line if user has selected a Level
		User selectedUser = users.get(selectedUserIndex);
		
		String savedFile = selectedUser.getName() + "SavedGame.txt";
		
		if (!(selectedIndex < noOfLevels)) {
	    	File temp = new File(savedFile);
	    	if (!temp.isFile()) {
	    		Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("You don't have a saved game yet!");
				alert.showAndWait();
				return;
	    	}
		} else {
			if (selectedIndex > selectedUser.getMaxCompletedLevel()) {
	    		Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("You haven't reached this level yet!");
				alert.showAndWait();
				return;
	    	}
		}

		// We use a try-catch block as the loading of the FXML file can fail.
		try {

			// Create a FXML loader for loading the GameController FXML file.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameController.fxml"));
			// Run the loader
			GridPane gameRoot = (GridPane)fxmlLoader.load();
			// Access the controller that was created by the FXML loader
			GameController game = fxmlLoader.<GameController>getController();

	        //Instantiating MediaPlayer class   
			mediaPlayer = new MediaPlayer(media);  

	        //by setting this property to true, the audio will be played   
	        mediaPlayer.setAutoPlay(true);  
	        
	        if (selectedIndex < noOfLevels) {
	        	game.setLevel(ReadLevelFile.readDataFile("Level" + (selectedIndex + 1) + ".txt"));
	        }
	        else {
	        	game.setLevel(ReadLevelFile.readDataFile(savedFile));
	        }
			
			
			game.setUser(selectedUser);

			// Create a scene based on the loaded FXML scene graph
			Scene gameScene = new Scene(gameRoot, Main.GAME_WINDOW_WIDTH, Main.GAME_WINDOW_HEIGHT);

			// Create a new stage for the game
			Stage gameStage = new Stage();
			gameStage.setScene(gameScene);
			gameStage.setTitle(Main.WINDOW_TITLE);
			gameStage.setResizable(false);
			gameStage.show();

			refreshLevelList();

		} catch (IOException e) {
			e.printStackTrace();
			// Quit the program (with an error code)
			System.exit(-1);
		}
	}

}
