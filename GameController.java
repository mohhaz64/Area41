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
    
    // Loaded images
 	static Image player = new Image("Idle.png", 70, 70, false, false);
 		
 	// X and Y coordinate of player
 	static int playerX;
 	static int playerY;

 	int width;
 	int height;
 	ArrayList<String> map;
 	int xStart;
 	int yStart;
 	Queue<Entity> entitysToAdd;
 	
 	ArrayList<Entity> activeEntitys = new ArrayList<>();

    public void initialize() {
    	gridPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> keyPressed(event));
    }
    
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
    void clickQuit(ActionEvent event) {
    	Stage stage = (Stage) gridPane.getScene().getWindow();
	    stage.close();
    }

    @FXML
    void clickRestart(ActionEvent event) {
    	playerX = xStart;
    	playerY = yStart;
    	drawGame();
    }

    @FXML
    void clickSave(ActionEvent event) {

    }

    @FXML
    void keyPressed(KeyEvent event) {
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
		
		insertEntitys();
		
		for (Entity s : activeEntitys)
		{
			s.draw(gc);
		}
		
		// Draw player at centre cell
		gc.drawImage(player, 3 * GRID_CELL_WIDTH, 3 * GRID_CELL_HEIGHT);			
	}
    
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