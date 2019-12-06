import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The main class for this program.
 * @author Group 41
 * @version 1.1
 *
 */

public class Main extends Application {
	
	//Constants for the Menu window
	private static final int MENU_WINDOW_WIDTH = 600;
	private static final int MENU_WINDOW_HEIGHT = 400;
	public static final String WINDOW_TITLE = "Chips Challenge";

	// Constants for the Game window, Create User window, and Edit User window.
	// We make them public and allow the controllers to access these,
	// so that we keep all window constants in one place.
	public static final int GAME_WINDOW_WIDTH = 600;
	public static final int GAME_WINDOW_HEIGHT = 600;
	
	public static final int CREATE_USER_WINDOW_WIDTH = 400;
	public static final int CREATE_USER_WINDOW_HEIGHT = 150;
	
	public static final int EDIT_USER_WINDOW_WIDTH = 400;
	public static final int EDIT_USER_WINDOW_HEIGHT = 500;
	
	public void start(Stage primaryStage) {
		try {
			// Load the Menu scene.
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root, MENU_WINDOW_WIDTH, MENU_WINDOW_HEIGHT);
		
			// Place the Menu scene on stage and show it.
			primaryStage.setScene(scene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
