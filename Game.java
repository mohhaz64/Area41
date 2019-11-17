import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
	// The dimensions of the window
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;

	// The dimensions of the canvas
	private static final int CANVAS_WIDTH = 420;
	private static final int CANVAS_HEIGHT = 420;

	// The size of each cell
	private static int GRID_CELL_WIDTH = 60;
	private static int GRID_CELL_HEIGHT = 60;
	
	// The canvas in the GUI. This needs to be a global variable
	// (in this setup) as we need to access it in different methods.
	// We could use FXML to place code in the controller instead.
	private Canvas canvas;
		
	// Loaded images
	Image player;
	Image dirt;
	
	// X and Y coordinate of player
	int playerX = 0;
	int playerY = 0;
	
	int width;
	int height;
	ArrayList<String> map;
	
	
	public void start(Stage primaryStage) {
		
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
		
		// Build the GUI 
		Pane root = buildGUI();
		
		// Create a scene from the GUI
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		// Load images
		player = new Image("Idle.png", 70, 70, false, false);
		dirt = new Image("dirt.png");
		
		// Register an event handler for key presses
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
		
		// Display the scene on the stage
		drawGame();
		primaryStage.setScene(scene);
		primaryStage.show();		
	}

	public void processKeyEvent(KeyEvent event) {
		switch (event.getCode()) {
			
		    case RIGHT:
		    	// Right key was pressed. So move the player right by one cell.
	        	playerX = playerX + 1;
	        	break;	
		    case LEFT:
		    	// Right key was pressed. So move the player right by one cell.
	        	playerX = playerX - 1;
	        	break;	
		    case UP:
		    	// Right key was pressed. So move the player right by one cell.
	        	playerY = playerY - 1;
	        	break;	
		    case DOWN:
		    	// Right key was pressed. So move the player right by one cell.
	        	playerY = playerY + 1;
	        	break;	
	        default:
	        	// Do nothing
	        	break;
		}
		
		// Redraw game as the player may have moved.
		drawGame();
		
		// Consume the event. This means we mark it as dealt with. This stops other GUI nodes (buttons etc) responding to it.
		event.consume();
	}

	public void drawGame() {
		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		// Clear canvas
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

		for(int k = 0; k < height; k++) {
			
			for(int i = 0; i < width; i++) {
				
				String instance = map.get((k*width)+i);
				
                if (instance.equals("Wall")) {
                	gc.setStroke(Color.BLACK);
                	gc.strokeRect((i - playerX + 3) * GRID_CELL_WIDTH, (k - playerY + 3) * GRID_CELL_HEIGHT, GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
                }
                else if (instance.equals("Ground")) {
                	gc.setFill(Color.WHITE);
                	gc.fillRect((i - playerX + 3) * GRID_CELL_WIDTH, (k - playerY + 3) * GRID_CELL_HEIGHT, GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
                }
                else if (instance.equals("Goal")) {    
                	gc.setFill(Color.GREEN);
                	gc.fillRect((i - playerX + 3) * GRID_CELL_WIDTH, (k - playerY + 3) * GRID_CELL_HEIGHT, GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
                }
                else {
                    System.out.println("Error: instance not found.");
                }
			}
		}
		// Draw player at centre cell
		gc.drawImage(player, 3 * GRID_CELL_WIDTH, 3 * GRID_CELL_HEIGHT);			
	}
	
	public void restartGame() {
		// We just move the player to cell (0, 0) 
		playerX = 0;
		playerY = 0;
		drawGame();
	}
	
	private Pane buildGUI() {
		// Create top-level panel that will hold all GUI
		BorderPane root = new BorderPane();
				
		// Create canvas
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setCenter(canvas);
		
		// Create a toolbar with some nice padding and spacing
		HBox toolbar = new HBox();
		toolbar.setSpacing(10);
		toolbar.setPadding(new Insets(10, 10, 10, 10)); 
		root.setTop(toolbar);
		
		// Create toolbar content
		Button restartButton = new Button("Restart");
		toolbar.getChildren().add(restartButton);
		
		Button exitButton = new Button("Exit");
		toolbar.getChildren().add(exitButton);

		// Add button event handlers
		restartButton.setOnAction(e -> {
			restartGame();
		});
		exitButton.setOnAction(e -> {
			System.exit(0);
		});
		
		return root;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}