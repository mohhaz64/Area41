import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

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
    private Label timerLabel;
    
    private Timeline timeline;
    private DoubleProperty timeSeconds = new SimpleDoubleProperty();
    private Duration time = Duration.ZERO;
    private boolean hasStarted = false;
    
    double levelTime;

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
    static Image flippers = new Image("Flippers.png", 55, 55, false, false);
    static Image fireBoots = new Image("FireBoots.png", 55, 55, false, false);
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

    public Level getLevelBeingLoaded() {
	return levelBeingLoaded;
    }

    public void setLevelBeingLoaded(Level levelBeingLoaded) {
	this.levelBeingLoaded = levelBeingLoaded;
    }

    public User getCurrentUser() {
	return currentUser;
    }

    public void setCurrentUser(User currentUser) {
	this.currentUser = currentUser;
    }

    public GridPane getGridPane() {
	return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
	this.gridPane = gridPane;
    }

    public ImageView getImage() {
	return Image;
    }

    public void setImage(ImageView image) {
	Image = image;
    }

    public Canvas getCanvas() {
	return canvas;
    }

    public void setCanvas(Canvas canvas) {
	this.canvas = canvas;
    }

    public Canvas getInvCanvas() {
	return invCanvas;
    }

    public void setInvCanvas(Canvas invCanvas) {
	this.invCanvas = invCanvas;
    }

    public Label getProjectLevel() {
	return projectLevel;
    }

    public void setProjectLevel(Label projectLevel) {
	this.projectLevel = projectLevel;
    }

    public Label getTokenCount() {
	return tokenCount;
    }

    public void setTokenCount(Label tokenCount) {
	this.tokenCount = tokenCount;
    }

    public static int getTotalTokens() {
	return totalTokens;
    }

    public static void setTotalTokens(int totalTokens) {
	GameController.totalTokens = totalTokens;
    }

    public static Image getPlayer() {
	return player;
    }

    public static void setPlayer(Image player) {
	GameController.player = player;
    }

    public static Image getWall() {
	return wall;
    }

    public static void setWall(Image wall) {
	GameController.wall = wall;
    }

    public static Image getGround() {
	return ground;
    }

    public static void setGround(Image ground) {
	GameController.ground = ground;
    }

    public static Image getFinish() {
	return finish;
    }

    public static void setFinish(Image finish) {
	GameController.finish = finish;
    }

    public static Image getRedKey() {
	return redKey;
    }

    public static void setRedKey(Image redKey) {
	GameController.redKey = redKey;
    }

    public static Image getYellowKey() {
	return yellowKey;
    }

    public static void setYellowKey(Image yellowKey) {
	GameController.yellowKey = yellowKey;
    }

    public static Image getBlueKey() {
	return blueKey;
    }

    public static void setBlueKey(Image blueKey) {
	GameController.blueKey = blueKey;
    }

    public static Image getEmptyKey() {
	return emptyKey;
    }

    public static void setEmptyKey(Image emptyKey) {
	GameController.emptyKey = emptyKey;
    }

    public static Image getFlippers() {
	return flippers;
    }

    public static void setFlippers(Image flippers) {
	GameController.flippers = flippers;
    }

    public static Image getFireBoots() {
	return fireBoots;
    }

    public static void setFireBoots(Image fireBoots) {
	GameController.fireBoots = fireBoots;
    }

    public static Image getPlayerPickup() {
	return playerPickup;
    }

    public static void setPlayerPickup(Image playerPickup) {
	GameController.playerPickup = playerPickup;
    }

    public static boolean isCollectedRed() {
	return collectedRed;
    }

    public static void setCollectedRed(boolean collectedRed) {
	GameController.collectedRed = collectedRed;
    }

    public static boolean isCollectedYellow() {
	return collectedYellow;
    }

    public static void setCollectedYellow(boolean collectedYellow) {
	GameController.collectedYellow = collectedYellow;
    }

    public static boolean isCollectedBlue() {
	return collectedBlue;
    }

    public static void setCollectedBlue(boolean collectedBlue) {
	GameController.collectedBlue = collectedBlue;
    }

    public static boolean isCollectedFlippers() {
	return collectedFlippers;
    }

    public static void setCollectedFlippers(boolean collectedFlippers) {
	GameController.collectedFlippers = collectedFlippers;
    }

    public static boolean isCollectedFireBoots() {
	return collectedFireBoots;
    }

    public static void setCollectedFireBoots(boolean collectedFireBoots) {
	GameController.collectedFireBoots = collectedFireBoots;
    }

    public boolean isMute() {
	return isMute;
    }

    public void setMute(boolean isMute) {
	this.isMute = isMute;
    }

    public static int getPlayerX() {
	return playerX;
    }

    public static void setPlayerX(int playerX) {
	GameController.playerX = playerX;
    }

    public static int getPlayerY() {
	return playerY;
    }

    public static void setPlayerY(int playerY) {
	GameController.playerY = playerY;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public static int getWidth() {
	return width;
    }

    public static void setWidth(int width) {
	GameController.width = width;
    }

    public static int getHeight() {
	return height;
    }

    public static void setHeight(int height) {
	GameController.height = height;
    }

    public static String[][] getMap() {
	return map;
    }

    public static void setMap(String[][] map) {
	GameController.map = map;
    }

    public int getxStart() {
	return xStart;
    }

    public void setxStart(int xStart) {
	this.xStart = xStart;
    }

    public int getyStart() {
	return yStart;
    }

    public void setyStart(int yStart) {
	this.yStart = yStart;
    }

    public Queue<Entity> getEntitysToAdd() {
	return entitysToAdd;
    }

    public void setEntitysToAdd(Queue<Entity> entitysToAdd) {
	this.entitysToAdd = entitysToAdd;
    }

    public static ArrayList<Entity> getActiveEntitys() {
	return activeEntitys;
    }

    public static void setActiveEntitys(ArrayList<Entity> activeEntitys) {
	GameController.activeEntitys = activeEntitys;
    }

    public String getUserSavedGame() {
	return userSavedGame;
    }

    public void setUserSavedGame(String userSavedGame) {
	this.userSavedGame = userSavedGame;
    }

    public static int getCanvasWidth() {
	return CANVAS_WIDTH;
    }

    public static int getCanvasHeight() {
	return CANVAS_HEIGHT;
    }

    public static int getInvCanvasWidth() {
	return INV_CANVAS_WIDTH;
    }

    public static int getInvCanvasHeight() {
	return INV_CANVAS_HEIGHT;
    }

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
    	activeEntitys.clear();
		resetInventory();
		drawInventory();
		MenuController.mediaPlayer.stop();
		Stage stage = (Stage) gridPane.getScene().getWindow();
		stage.close();
    }

    @FXML
    void clickMute(ActionEvent event) {

	if (isMute) {
	    MenuController.mediaPlayer.setVolume(0);
	    isMute = !isMute;
	} else if (!isMute) {
	    MenuController.mediaPlayer.setVolume(0.7);
	    isMute = !isMute;
	}
    }

    @FXML
    /**
     * Resets the players X and Y coordinate to starting position.
     * 
     * @param event The ActionEvent being handled when the button 'Restart' is
     *              pressed
     */
    void clickRestart(ActionEvent event) {
    	
    	reloadLevel();
	
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
	for (Entity s : activeEntitys) {
		writer.write(s.toString());
	}
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
	for (Entity checkEntity : activeEntitys) {
	    if (checkEntity instanceof Door) {
		return ((Door) checkEntity).checkIfTouched();
	    }
	}
	return true;
    }
    
    public void playerDead() {
    	activeEntitys.clear();
    	timeline.pause();
		System.out.println("Time was: " + timerLabel.getText() + " seconds");
		levelTime = Double.parseDouble(timerLabel.getText());
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
	    if (checkSpace(playerX + 1, playerY)) {
		player = new Image("PlayerRight.png", 70, 70, false, false);
		playerX = playerX + 1;
		checkIfDead();
	    } else {
		break;
	    }
	    break;
	case LEFT:
	    // Left key was pressed. So move the player Left by one cell.
	    if (checkSpace(playerX - 1, playerY)) {
		player = new Image("PlayerLeft.png", 70, 70, false, false);
		playerX = playerX - 1;
		checkIfDead();
	    } else {
		break;
	    }
	    break;
	case UP:
	    // Up key was pressed. So move the player Up by one cell.
	    if (checkSpace(playerX, playerY - 1)) {
		player = new Image("PlayerUp.png", 70, 70, false, false);
		playerY = playerY - 1;
		checkIfDead();
	    } else {
		break;
	    }
	    break;
	case DOWN:
	    // Down key was pressed. So move the player Down by one cell.
	    if (checkSpace(playerX, playerY + 1)) {
		player = new Image("PlayerDown.png", 70, 70, false, false);
		playerY = playerY + 1;
		checkIfDead();
	    } else {
		break;
	    }
	    break;
	default:
	    // Do nothing
	    break;
	}

	if(hasStarted == false) {
		startTimer();
		timeline.play();
		hasStarted =! hasStarted;
	}
	//TODO::
	if (map[playerX][playerY].equalsIgnoreCase("G")) {
		timeline.pause();
		System.out.println("Time was: " + timerLabel.getText() + " seconds");
		levelTime = Double.parseDouble(timerLabel.getText());
	    victory();
	    
	}
	
	for (Entity s : activeEntitys) {

	    if (s instanceof Enemy) {
	    	((Enemy) s).getNextMove();
	    	((Enemy) s).makeMove();
	    	((Enemy) s).hasKilledPlayer();
	    } else {
	    	s.doTouched();
	    }
	}

	// Redraw game as the player may have moved.
	drawGame();
	drawInventory();
	
	checkIfDead();

	// Consume the event, mark is as dealt with.
	event.consume();
    }
    
    public void checkIfDead() {
    	boolean clearActiveEntities = false;
    	for (Entity entity : GameController.activeEntitys) {
    	    if (entity.hasKilledPlayer() == true) {
    	    	clearActiveEntities = true;
    	    }
    	}
    	if (clearActiveEntities) {
    		playerDead();
    	}
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

		double XIso = xToIso(X, Y);
		double YIso = yToIso(X, Y);

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

	for (Entity s : activeEntitys) {
		
		s.draw(gc, s.getSprite());
		
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

	collectedRed = false;
	;
	collectedYellow = false;
	collectedBlue = false;
	;
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
	if (activeEntitys.isEmpty()) {
	    activeEntitys.clear();
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

    private void victory() {
    	System.out.println("Victory!");
		int levelNum = Integer.parseInt(Character.toString(name.charAt(6)));
		System.out.println(levelNum);
		if (currentUser.getMaxCompletedLevel() < levelNum) {
			currentUser.updateTextFile();
		    currentUser.setMaxCompletedLevel(levelNum);
		}
		if (levelNum < MenuController.noOfLevels) {
			
			Level selectedLevel = ReadLevelFile.readDataFile("Level" + (levelNum + 1) + ".txt");
	    	activeEntitys.clear();
			resetInventory();
			drawInventory();
			hasStarted =! hasStarted;
			setLevel(selectedLevel);
			
		}  else {
			System.out.println("All levels completed");
		}
    }
    
    public void reloadLevel() {
    	
    	Level selectedLevel = ReadLevelFile.readDataFile(levelBeingLoaded.getFilename());
    	
    	activeEntitys.clear();
		resetInventory();
		drawInventory();
		setLevel(selectedLevel);
		hasStarted = false;
    	
    }

    public void startTimer() {
    	timerLabel.textProperty().bind(timeSeconds.asString());

        if (timeline != null) {
            time = Duration.ZERO;
            timeSeconds.set(time.toSeconds());
        } else {
            timeline = new Timeline(
                new KeyFrame(Duration.millis(100),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        Duration duration = ((KeyFrame)t.getSource()).getTime();
                        time = time.add(duration);
                        timeSeconds.set(time.toSeconds());
                    }
                })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }
    
}
