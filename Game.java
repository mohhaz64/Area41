import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

/**
 * The main class for this program.
 * @author Callum Charalambides
 *
 */
public class Game extends Application {
	// Constants for the main window
	// The dimensions of the window
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;

	// The dimensions of the canvas
	protected static final int CANVAS_WIDTH = 420;
	protected static final int CANVAS_HEIGHT = 420;
	
	// The size of each cell
	protected static int GRID_CELL_WIDTH = 60;
	protected static int GRID_CELL_HEIGHT = 60;

	// Loaded images
	static Image player;
		
	// X and Y coordinate of player
	static int playerX = 0;
	static int playerY = 0;
		
	static int width;
	static int height;
	static ArrayList<String> map;

	@Override
	public void start(Stage primaryStage) {
		try {
			//Temporary file input before menu is created
			Scanner input = new Scanner (System.in);
			System.out.println ("Please enter a file to open:");
			String filename = input.next ();
			input.close ();
			
			Level level = new Level();
			level.openFile(filename);
			
			map = level.map;
			width = level.getWidth();
			height = level.getHeight();
			player = new Image("Idle.png", 70, 70, false, false);
			
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("GameController.fxml"));
			Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			
			// Place the main scene on stage and show it.
			primaryStage.setScene(scene);
			primaryStage.setTitle("Chips Challenge");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
		
	public static void main(String[] args) {
		launch(args);
	}
}