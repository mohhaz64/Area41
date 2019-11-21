import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The controller for the Menu FXML
 * @author Group 41
 *
 */

public class GameController {
	
	private Level levelBeingLoaded;

    @FXML private GridPane gridPane;
    @FXML private ImageView Image;
    @FXML private Canvas canvas;
    @FXML private Label projectLevel;
    @FXML private Label tokenCount;
    @FXML private Label redCount;
    @FXML private Label yellowCount;
    @FXML private Label blueCount;
    
	// The dimensions of the canvas
	private static final int CANVAS_WIDTH = 420;
	private static final int CANVAS_HEIGHT = 420;
	
	// The size of each cell
	static int GRID_CELL_WIDTH = 60;
	static int GRID_CELL_HEIGHT = 60;
    
    // Loaded image for the players character
 	static Image player = new Image("Idle.png", 70, 70, false, false);
 		
 	// X and Y coordinate of player
 	static int playerX;
 	static int playerY;

 	//Variables that are scanned in from the .txt level file.
 	int width;
 	int height;
 	ArrayList<String> map;
 	int xStart;
 	int yStart;
 	Queue<Entity> entitysToAdd;
 	
 	ArrayList<Entity> activeEntitys = new ArrayList<>();

 	
	/**
	 * Initialize the controller.
	 * This method is called automatically and everything within is run IN ORDER.
	 * Even for the Pane for key presses, if a key is pressed we send the event to the keyPressed method.
	 */
    public void initialize() {
    	gridPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> keyPressed(event));
    }
    
	/**
	 * Setting the scanned in values to the designated variables, and then calling the drawGame method.
	 * @param levelToLoad The instance of Level which we want to load
	 */
	public void setLevel(Level levelToLoad) {
		this.levelBeingLoaded = levelToLoad;
		
		String name = levelBeingLoaded.getName();
		width = levelBeingLoaded.getWidth();
     	height = levelBeingLoaded.getHeight();
     	map = levelBeingLoaded.getMap();
     	xStart = levelBeingLoaded.getXStart();
     	yStart = levelBeingLoaded.getYStart();
     	entitysToAdd = levelBeingLoaded.getEntityQueue();
     	
     	playerX = xStart;
     	playerY = yStart;
     	
     	projectLevel.setText(name);

     	drawGame();
	}
    
    @FXML 
	/**
	 * Closes the stage and returns the user to the Menu interface.
	 * @param event The ActionEvent being handled when the button 'Quit' is pressed
	 */
    void clickQuit(ActionEvent event) {
    	Stage stage = (Stage) gridPane.getScene().getWindow();
	    stage.close();
    }

    @FXML
    /**
	 * Resets the players X and Y coordinate to starting position.
	 * @param event The ActionEvent being handled when the button 'Restart' is pressed
	 */
    void clickRestart(ActionEvent event) {
    	playerX = xStart;
    	playerY = yStart;
    	drawGame();
    }

    @FXML
    /**
	 * TODO:: SAVES THE GAME
	 * @param event The ActionEvent being handled when the button 'Save' is pressed
	 */
    void clickSave(ActionEvent event) {

    }

    @FXML
    /**
	 * Moves the player in the desired direction by changing the X or Y coordinate
	 * @param event The ActionEvent being handled when a key is pressed
	 */
    void keyPressed(KeyEvent event) {
    	switch (event.getCode()) {
		
	    case RIGHT:
	    	// Right key was pressed. So move the player right by one cell.
        	playerX = playerX + 1;
        	break;	
	    case LEFT:
	    	// Left key was pressed. So move the player Left by one cell.
        	playerX = playerX - 1;
        	break;	
	    case UP:
	    	// Up key was pressed. So move the player Up by one cell.
        	playerY = playerY - 1;
        	break;	
	    case DOWN:
	    	// Down key was pressed. So move the player Down by one cell.
        	playerY = playerY + 1;
        	break;	
        default:
        	// Do nothing
        	break;
	}
	
	// Redraw game as the player may have moved.
	drawGame();
	
	// Consume the event, mark is as dealt with.
	event.consume();
    }
    
    /**
	 * Draws the selected level onto the canvas
	 */
    public void drawGame() {

		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		// Clear canvas
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		//Draw a black rectangle (border) around the canvas.
		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

		// Reading the map array and placing the instances onto the canvas.
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
		
		insertEntitys();
		
		for (Entity s : activeEntitys)
		{
			s.draw(gc);
		}
		
		// Draw player at center cell
		gc.drawImage(player, 3 * GRID_CELL_WIDTH, 3 * GRID_CELL_HEIGHT);			
	}
    
    /**
	 * Inserting queue of entities onto the canvas when the method is called in drawGame();
	 */
	private void insertEntitys() {
		if (entitysToAdd.isEmpty ()) {
			return;
		}
		
		Entity current = entitysToAdd.peek ();
		while (!entitysToAdd.isEmpty ()) {
			activeEntitys.add(current);
			entitysToAdd.dequeue();
			if (!entitysToAdd.isEmpty ()) {
				current = entitysToAdd.peek();
			}
		}
	}

}