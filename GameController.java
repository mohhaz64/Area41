import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The controller for the Menu FXML
 * 
 * @author Group 41
 *
 */

public class GameController {

    private Level levelBeingLoaded;
    private User currentUser;

    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView Image;
    @FXML
    private Canvas canvas;
    @FXML
    private Canvas invCanvas;
    @FXML
    private Label projectLevel;
    @FXML
    private Label tokenCount;
    @FXML

    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 420;
    private static final int CANVAS_HEIGHT = 420;

    // The dimensions of the inventory canvas
    private static final int INV_CANVAS_WIDTH = 110;
    private static final int INV_CANVAS_HEIGHT = 310;

    // The size of each cell
    private static final int GRID_CELL_WIDTH = 60;
    private static final int GRID_CELL_HEIGHT = 60;

    private static int totalTokens = 0;

    public static int getGridCellWidth() {
	return GRID_CELL_WIDTH;
    }

    public static int getGridCellHeight() {
	return GRID_CELL_HEIGHT;
    }

    public static void pickupToken() {
	totalTokens = totalTokens + 1;
    }

    public static void pickupRedKey() {
	collectedRed = true;
    }

    public static void pickupYellowKey() {
	collectedYellow = true;
    }

    public static void pickupBlueKey() {
	collectedBlue = true;
    }

    public static void pickupFlippers() {
	collectedFlippers = true;
    }

    public static void pickUpFireboots() {
	collectedFireBoots = true;
    }

    // Loaded image for the players character
    static Image player = new Image("Player.png", 70, 70, false, false);
    static Image wall = new Image("WallISO.png", 60, 60, false, false);
    static Image ground = new Image("GrassISO.png", 60, 60, false, false);
    static Image finish = new Image("Goal.png", 60, 60, false, false);
    static Image redKey = new Image("RedKey.png", 87, 44, false, false);
    static Image yellowKey = new Image("YellowKey.png", 87, 44, false, false);
    static Image blueKey = new Image("BlueKey.png", 87, 44, false, false);
    static Image emptyKey = new Image("emptyKey.png", 87, 44, false, false);
    static Image flippers = new Image("Flippers.png", 55, 55, false,
	    false);
    static Image fireBoots = new Image("FireBoots.png", 55, 55, false,
	    false);
    static Image playerPickup = new Image("PlayerPickup.png", 60, 60, false,
	    false);

    private static boolean collectedRed;
    private static boolean collectedYellow;
    private static boolean collectedBlue;
    private static boolean collectedFlippers;
    private static boolean collectedFireBoots;
    
    private boolean isMute = true;

    // X and Y coordinate of player
    static int playerX;
    static int playerY;

    // Variables that are scanned in from the .txt level file.
    String name;
    static int width;
    static int height;
    static String map[][];
    int xStart;
    int yStart;
    Queue<Entity> entitysToAdd;
    static ArrayList<Entity> activeEntitys = new ArrayList<>();

    // User info
    String userSavedGame;

    /**
     * Initialize the controller. This method is called automatically and
     * everything within is run IN ORDER. Even for the Pane for key presses, if
     * a key is pressed we send the event to the keyPressed method.
     */
    public void initialize() {
	gridPane.addEventFilter(KeyEvent.KEY_PRESSED,
		event -> keyPressed(event));
    }
  
    /**
     * Setting the scanned in values to the designated variables, and then
     * calling the drawGame method.
     * 
     * @param levelToLoad The instance of Level which we want to load
     */
    public void setLevel(Level levelToLoad) {
		this.levelBeingLoaded = levelToLoad;
	
		name = levelBeingLoaded.getName();
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
		drawInventory();
    }

    /**
     * Allows GameController to access the current user's saved game.
     * 
     * @param currentUser The user currently playing the level
     */
    public void setUser(User selectedUser) {
	this.currentUser = selectedUser;

	userSavedGame = currentUser.getName() + "SavedGame.txt";

    }

    @FXML
    /**
     * Closes the stage and returns the user to the Menu interface.
     * 
     * @param event The ActionEvent being handled when the button 'Quit' is
     *              pressed
     */
    void clickQuit(ActionEvent event) {

    	MenuController.mediaPlayer.stop();
    	Stage stage = (Stage) gridPane.getScene().getWindow();
	    stage.close();
    }
    
    @FXML
    void clickMute(ActionEvent event) {

    	if(isMute) {
    		MenuController.mediaPlayer.setVolume(0);
    		isMute =! isMute;
    	} else if(!isMute) {
    		MenuController.mediaPlayer.setVolume(0.1);
    		isMute =! isMute;
    	}
    }

    @FXML
    /**
     * Resets the players X and Y coordinate to starting position.
     * 
     * @param event The ActionEvent being handled when the button 'Restart' is
     *              pressed
     */
    void clickRestart(ActionEvent event) throws IOException {
      restart();
    }
    
    public void restart() {
    	activeEntitys.clear();
    	
    
    	Level selectedLevel = ReadLevelFile.readDataFile(MenuController.getSelectedLevel());
		
    	resetInventory();
    	drawInventory();
    	setLevel(selectedLevel);
    }

    @FXML
    /**
     * TODO:: SAVES THE GAME
     * 
     * @param event The ActionEvent being handled when the button 'Save' is
     *              pressed
     */
    void clickSave(ActionEvent event) throws IOException {

	File file = new File(userSavedGame);

	if (file.createNewFile()) {
	    System.out.println("Saved Game file is created.");
	} else {
	    System.out.println("Saved Game file already exists.");
	}

	BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	writer.write(name);
	writer.newLine();
	writer.write(width + " " + height);
	writer.newLine();

	for (int k = 0; k < height; k++) {
	    for (int i = 0; i < width; i++) {
		writer.write(map[i][k]);
	    }
	    writer.newLine();
	}
	writer.write("START " + playerX + " " + playerY);
	writer.newLine();

	// Will finish when classes are created
	writer.write("Entity classes needed");

	writer.close();

    }
    
    public boolean checkSpace(int spaceToCheckX, int spaceToCheckY) {
    	// if space is not floor, player or out of array's bounds then true,
    	// else false
    	if (spaceToCheckX < 0) {
    	    return false;
    	}
    	if (spaceToCheckY < 0) {
    	    return false;
    	}
    	if (spaceToCheckX > width) {
    	    return false;
    	}
    	if (spaceToCheckY > height) {
    	    return false;
    	}
    	if (map[spaceToCheckX][spaceToCheckY].equalsIgnoreCase("#")) {
    	    return false;
    	}
    	if (map[spaceToCheckX][spaceToCheckY].equalsIgnoreCase("G")) {
    	    return true;
    	}
    	for (Entity entity : GameController.activeEntitys) {
    	    if (entity.getX() == spaceToCheckX
    		    && entity.getY() == spaceToCheckY) {
    		return true;
    	    }
    	}
    	
    	boolean clearActiveEntities = false;
    	for (Entity entity : GameController.activeEntitys) {
    	    if (entity.hasKilledPlayer() == true) {
    	    	clearActiveEntities = true;
    	    }
    	}
    	if (clearActiveEntities) {
    		playerDead();
    	}
    	return true;
    }
    
    public void playerDead() {
    	activeEntitys.clear();
    	try {
			// Create a FXML loader for loading the Edit User FXML file.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dead.fxml"));
			GridPane deadUserRoot = (GridPane) fxmlLoader.load();

			DeadController deadController = fxmlLoader.<DeadController>getController();
			deadController.setParentController(this);

			Scene deadUser = new Scene(deadUserRoot, Main.CREATE_USER_WINDOW_WIDTH, Main.CREATE_USER_WINDOW_HEIGHT);

			// Create a new stage based on the NewUser scene
			Stage deadUserStage = new Stage();
			deadUserStage.setScene(deadUser);
			deadUserStage.setTitle("Wasted");
			deadUserStage.setResizable(false);

			// Make the stage a modal window.
			// This means that it must be closed before you can interact with any other window from this application.
			deadUserStage.initModality(Modality.APPLICATION_MODAL);

			// Show the Create New User scene and wait for it to be closed
			deadUserStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
			// Quit the program (with an error code)
			System.exit(-1);
		}
    }

    @FXML
    /**
     * Moves the player in the desired direction by changing the X or Y
     * coordinate
     * 
     * @param event The ActionEvent being handled when a key is pressed
     */
    void keyPressed(KeyEvent event) {
    	switch (event.getCode()) {
		
	    case RIGHT:
	    	// Right key was pressed. So move the player right by one cell.
	    	if(checkSpace(playerX + 1, playerY)) {
	    		player = new Image("PlayerRight.png",70,70,false,false);
	        	playerX = playerX + 1;
	    	} else {
	    		break;
	    	}
        	break;	
	    case LEFT:
	    	// Left key was pressed. So move the player Left by one cell.
	    	if(checkSpace(playerX - 1, playerY)) {
		    	player = new Image("PlayerLeft.png",70,70,false,false);
	        	playerX = playerX - 1;
	    	} else {
	    		break;
	    	}
        	break;	
	    case UP:
	    	// Up key was pressed. So move the player Up by one cell.
	    	if(checkSpace(playerX, playerY - 1)) {
		    	player = new Image("PlayerUp.png",70,70,false,false);
	        	playerY = playerY - 1;
	    	} else {
	        		break;
	        }
        	break;	
	    case DOWN:
	    	// Down key was pressed. So move the player Down by one cell.
	    	if(checkSpace(playerX, playerY + 1)) {
		    	player = new Image("PlayerDown.png",70,70,false,false);
	        	playerY = playerY + 1;
	    	} else {
	    		break;
	    	}
        	break;
        default:
        	// Do nothing
        	break;
        	
        
	}
    	for (Entity entity : GameController.activeEntitys) {
    	    if (entity.hasKilledPlayer() == true) {
    	    	playerDead();
    	    }
    	}

	// Redraw game as the player may have moved.
	drawGame();
	drawInventory();	
	

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

	// Draw a black rectangle (border) around the canvas.
	gc.setStroke(Color.BLACK);
	gc.strokeRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

	// Reading the map array and placing the instances onto the canvas.
	for (int k = 0; k < height; k++) {

	    for (int i = 0; i < width; i++) {

		String instance = map[i][k];
		
		double offsetX = 4.75;
		double offsetY = 2.5;
		
		double X = (i - playerX + offsetX) * GRID_CELL_WIDTH;
		double Y = (k - playerY + offsetY) * GRID_CELL_WIDTH;
		
		double XIso = xToIso(X,Y);
		double YIso = yToIso(X,Y);
		
		int isoWidth = GRID_CELL_WIDTH * 2;
		int isoHeight = GRID_CELL_WIDTH * 2;

		if (instance.equals("#")) {
			
		    gc.drawImage(wall, XIso, YIso - 30, isoWidth, isoHeight);
		    
		} else if (instance.equals(" ")) {
			
		    gc.drawImage(ground, XIso, YIso, isoWidth, isoHeight);
		    
		} else if (instance.equals("G")) {

		    gc.drawImage(finish, XIso, YIso - 15, isoWidth, isoHeight);
		    
		} else {
		    System.out.println("Error: instance not found.");
		}
	    }
	}

	insertEntitys();
	
	boolean clearActiveEntities = false;

	for (Entity s : activeEntitys) {
		s.draw(gc, s.getSprite());
	    if (s instanceof Enemy) {
		((Enemy) s).getNextMove();
		((Enemy) s).makeMove();
		((Enemy) s).hasKilledPlayer();
	    } else {
	    	if (s.checkIfTouched()) {
	    		drawGame();
	    	}
	    }
	}
	
	if (clearActiveEntities) {
		activeEntitys.clear();
	}

	// Draw player at center cell
	gc.drawImage(player, 3 * GRID_CELL_WIDTH, 3 * GRID_CELL_HEIGHT);
    }

    public void drawInventory() {

	// Get the Graphic Context of the canvas. This is what we draw on.
	GraphicsContext gc = invCanvas.getGraphicsContext2D();

	// Clear canvas
	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

	if (collectedRed) {
	    gc.drawImage(redKey, 10, 1);
	} else {
	    gc.drawImage(emptyKey, 10, 1);
	}
	if (collectedYellow) {
	    gc.drawImage(yellowKey, 10, 62);
	} else {
	    gc.drawImage(emptyKey, 10, 62);
	}
	if (collectedBlue) {
	    gc.drawImage(blueKey, 10, 123);
	} else {
	    gc.drawImage(emptyKey, 10, 123);
	}
	if (collectedFlippers) {
	    gc.drawImage(flippers, 60, 187);
	}
	if (collectedFireBoots) {
	    gc.drawImage(fireBoots, 3, 187);
	}

	tokenCount.setText(String.valueOf(totalTokens));
    }
  
  public void resetInventory() {
    	
    	collectedRed = false;;
     	collectedYellow = false;
     	collectedBlue = false;;
     	collectedFlippers = false;
     	collectedFireBoots = false;
     	totalTokens = 0;
     	tokenCount.setText(String.valueOf(totalTokens));
    	
    }

    /**
     * Inserting queue of entities onto the canvas when the method is called in
     * drawGame();
     */
    private void insertEntitys() {
	if (entitysToAdd.isEmpty()) {
	    return;
	}

	Entity current = entitysToAdd.peek();
	while (!entitysToAdd.isEmpty()) {
	    activeEntitys.add(current);
	    entitysToAdd.dequeue();
	    if (!entitysToAdd.isEmpty()) {
		current = entitysToAdd.peek();
	    }
	}
    }

    private double xToIso(double X, double Y) {
    	return X - Y;
    }
    
    private double yToIso(double X, double Y) {
    	return (X + Y) / 2;
    }
}
