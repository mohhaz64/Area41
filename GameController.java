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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The controller for the game logic.
 *
 * @author Ben Hyde, Callum Charalambides
 * @version 2.1
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

    private double levelTime;


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

    /**
     * @return Grid cell width.
     */
    public static int getGridCellWidth() {
        return GRID_CELL_WIDTH;
    }

    /**
     * @return Grid cell height.
     */
    public static int getGridCellHeight() {
        return GRID_CELL_HEIGHT;
    }

    /**
     * Increases the total tokens collected by 1.
     */
    public static void pickupToken() {
        totalTokens = totalTokens + 1;
    }

    /**
     * Sets the red key collected boolean to true.
     */
    public static void pickupRedKey() {
        collectedRed = true;
    }

    /**
     * Sets the yellow key collected boolean to true.
     */
    public static void pickupYellowKey() {
        collectedYellow = true;
    }

    /**
     * Sets the blue key collected boolean to true.
     */
    public static void pickupBlueKey() {
        collectedBlue = true;
    }

    /**
     * Sets the flippers collected boolean to true.
     */
    public static void pickupFlippers() {
        collectedFlippers = true;
    }

    /**
     * Sets the fireboots collected boolean to true.
     */
    public static void pickUpFireboots() {
        collectedFireBoots = true;
    }

    // Loaded image for the players character
    private static Image player = new Image("PlayerRight.png", false);
    private static Image wall;
    private static Image ground;
    private static Image water = new Image("Water.png", false);
    private static Image fire = new Image("Fire.png", false);
    private static Image finish = new Image("Goal.png", false);
    private static Image redKey = new Image("RedKey.png", false);
    private static Image yellowKey = new Image("YellowKey.png", false);
    private static Image blueKey = new Image("BlueKey.png", false);
    private static Image emptyKey = new Image("emptyKey.png", false);
    private static Image flippers = new Image("Flippers.png", false);
    private static Image fireBoots = new Image("FireBoots.png", false);
    private static Image playerPickup = new Image("PlayerPickup.png", false);
    private static Image teleporter = new Image("Teleporter.png", false);

    private static Image blueDoor = new Image("BlueDoor.png", false);
    private static Image redDoor = new Image("RedDoor.png", false);
    private static Image yellowDoor = new Image("YellowDoor.png", false);
    private static Image tokenDoor = new Image("TokenDoor.png", false);

    private static boolean redOpen;
    private static boolean yellowOpen;
    private static boolean blueOpen;
    private static boolean tokenOpen;
    private static boolean collectedRed;
    private static boolean collectedYellow;
    private static boolean collectedBlue;
    private static boolean collectedFlippers;
    private static boolean collectedFireBoots;

    private boolean isMute = true;

    // X and Y coordinate of player
    private static int playerX;
    private static int playerY;

    // Variables that are scanned in from the .txt level file.
    private static String name;


    /**
     * @return The total amount of tokens collected.
     */
    public static int getTotalTokens() {
        return totalTokens;
    }


    /**
     * @return true if red key collected else false.
     */
    public static boolean isCollectedRed() {
        return collectedRed;
    }

    /**
     * @return true if yellow key collected else false.
     */
    public static boolean isCollectedYellow() {
        return collectedYellow;
    }

    /**
     * @return true if blue key collected else false.
     */
    public static boolean isCollectedBlue() {
        return collectedBlue;
    }

    /**
     * @return true if flippers collected else false.
     */
    public static boolean isCollectedFlippers() {
        return collectedFlippers;
    }

    /**
     * @return true if fireboots collected else false.
     */
    public static boolean isCollectedFireBoots() {
        return collectedFireBoots;
    }

    /**
     * @return players x coordinate.
     */
    public static int getPlayerX() {
        return playerX;
    }

    /**
     * sets players x coordinate.
     *
     * @param playerX x coordinate to set.
     */
    public static void setPlayerX(int playerX) {
        GameController.playerX = playerX;
    }

    /**
     * @return players y coordinate.
     */
    public static int getPlayerY() {
        return playerY;
    }

    /**
     * sets players y coordinate.
     *
     * @param playerY y coordinate to set.
     */
    public static void setPlayerY(int playerY) {
        GameController.playerY = playerY;
    }

    /**
     * @return width of map array.
     */
    public static int getWidth() {
        return width;
    }

    /**
     * @return height of map array.
     */
    public static int getHeight() {
        return height;
    }

    /**
     * @param x x coordinate of entity.
     * @param y y coordinate of entity.
     * @return entity at (x, y) in map array.
     */
    public static String getMapValue(int x, int y) {
        return map[x][y];
    }


    /**
     * @return array of active entities.
     */
    public static ArrayList<Entity> getActiveEntitys() {
        return activeEntitys;
    }

    private static int width;
    private static int height;
    private static String map[][];
    private int xStart;
    private int yStart;
    private Queue<Entity> entitysToAdd;
    private static ArrayList<Entity> activeEntitys = new ArrayList<>();

    // User info
    private String userSavedGame;

    /**
     * Initialize the controller. This method is called automatically and everything within is run IN ORDER.
     * Even for the Pane for key presses, if a key is pressed we send the event to the keyPressed method.
     */
    public void initialize() {
        gridPane.addEventFilter(KeyEvent.KEY_PRESSED,
                event -> keyPressed(event));
    }

    /**
     * Setting the scanned in values to the designated variables, and then calling the drawGame method.
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
     * Sets visual theme of level.
     *
     * @param theme theme of level to set.
     */
    public void setTheme(String theme) {
        if (theme.equals("Snowy")) {
            wall = new Image("SnowyWall.png", false);
            ground = new Image("SnowyFloor.png", false);
        } else if (theme.equals("Sandy")) {
            wall = new Image("SandyWall.png", false);
            ground = new Image("SandyFloor.png", false);
        } else if (theme.equals("Dungeon")) {
            wall = new Image("DungeonWall.png", false);
            ground = new Image("DungeonFloor.png", false);
        } else if (theme.equals("Neon")) {
            wall = new Image("NeonWall.png", false);
            ground = new Image("NeonFloor.png", false);
        } else {
            wall = new Image("WallISO.png", false);
            ground = new Image("GrassISO.png", false);
        }
        drawGame();
    }

    /**
     * Allows GameController to access the current user's saved game.
     *
     * @param selectedUser new user to set.
     */
    public void setUser(User selectedUser) {
        this.currentUser = selectedUser;

        userSavedGame = currentUser.getName() + "SavedGame.txt";

    }

    @FXML
    /**
     * Closes the stage and returns the user to the Menu interface.
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
     * @param event The ActionEvent being handled when the button 'Restart' is
     *              pressed
     */
    void clickRestart(ActionEvent event) {

        reloadLevel();

    }

    @FXML
    /**
     * Saves game state to user save file.
     * @param event The ActionEvent being handled when the button 'Save' is pressed.
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

    /**
     * Checks wether space at certain (x, y) is a floor or not.
     *
     * @param spaceToCheckX x coordinate of space.
     * @param spaceToCheckY y coordinate of space.
     * @return true if floor else false.
     */
    public boolean checkSpace(int spaceToCheckX, int spaceToCheckY) {
        // if space is not floor, player or out of array's bounds then false,
        // else true
        if (spaceToCheckX < 0) {
            return false;
        }
        if (spaceToCheckY < 0) {
            return false;
        }
        if (spaceToCheckX > width - 1) {
            return false;
        }
        if (spaceToCheckY > height - 1) {
            return false;
        }
        if (map[spaceToCheckX][spaceToCheckY].equalsIgnoreCase("#")) {
            return false;
        }
        for (Entity checkEntity : activeEntitys) {
            if (checkEntity instanceof Door && (checkEntity.getX() == spaceToCheckX && checkEntity.getY() == spaceToCheckY)) {
                if (checkEntity.getType().equalsIgnoreCase("blue") && isCollectedBlue()) {
                    blueOpen = true;
                    collectedBlue = false;
                    return true;
                } else if (checkEntity.getType().equalsIgnoreCase("red") && isCollectedRed()) {
                    redOpen = true;
                    collectedRed = false;
                    return true;
                } else if (checkEntity.getType().equalsIgnoreCase("yellow") && isCollectedYellow()) {
                    yellowOpen = true;
                    collectedYellow = false;
                    return true;
                } else {
                    return false;
                }
            } else if (checkEntity instanceof TokenDoor && (checkEntity.getX() == spaceToCheckX && checkEntity.getY() == spaceToCheckY)) {
                if (getTotalTokens() >= checkEntity.getTokensRequired()) {
                    tokenOpen = true;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Handles events after player has died.
     */
    public void playerDead() {
        timeline.pause();
        System.out.println("Time was: " + timerLabel.getText() + " seconds");
        levelTime = Double.parseDouble(timerLabel.getText());
        drawGame();
        activeEntitys.clear();
        try {
            // Create a FXML loader for loading the Edit User FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dead.fxml"));
            GridPane deadUserRoot = (GridPane) fxmlLoader.load();

            DeadController deadController = fxmlLoader.<DeadController>getController();
            deadController.setParentController(this);

            Scene deadUser = new Scene(deadUserRoot, Main.getCreateUserWindowWidth(), Main.getCreateUserWindowHeight());

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
     * Moves the player in the desired direction by changing the X or Y coordinate.
     *
     * @param event The ActionEvent being handled when a key is pressed.
     */
    void keyPressed(KeyEvent event) {
        switch (event.getCode()) {

            case RIGHT:
                // Right key was pressed. So move the player right by one cell.
                player = new Image("PlayerRight.png", false);
                if (checkSpace(playerX + 1, playerY)) {
                    playerX = playerX + 1;
                } else {
                    break;
                }
                break;
            case LEFT:
                // Left key was pressed. So move the player Left by one cell.
                player = new Image("PlayerLeft.png", false);
                if (checkSpace(playerX - 1, playerY)) {
                    playerX = playerX - 1;
                } else {
                    break;
                }
                break;
            case UP:
                // Up key was pressed. So move the player Up by one cell.
                player = new Image("PlayerUp.png", false);
                if (checkSpace(playerX, playerY - 1)) {
                    playerY = playerY - 1;
                } else {
                    break;
                }
                break;
            case DOWN:
                // Down key was pressed. So move the player Down by one cell.
                player = new Image("PlayerDown.png", false);
                if (checkSpace(playerX, playerY + 1)) {
                    playerY = playerY + 1;
                } else {
                    break;
                }
                break;
            default:
                // Do nothing
                break;
        }

        checkIfDead();

        if (hasStarted == false) {
            startTimer();
            timeline.play();
            hasStarted = !hasStarted;
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

    /**
     * Check if player has been killed.
     */
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
     * Draws the selected level onto the canvas.
     */
    public void drawGame() {

        // Get the Graphic Context of the canvas. This is what we draw on.
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

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

                    gc.drawImage(finish, XIso, YIso, isoWidth, isoHeight);

                } else if (instance.equals("W")) {

                    gc.drawImage(water, XIso, YIso + 8, isoWidth, isoHeight);

                } else if (instance.equals("F")) {

                    gc.drawImage(fire, XIso, YIso + 4, isoWidth, isoHeight);

                } else if (instance.equals("T")) {

                    gc.drawImage(teleporter, XIso, YIso, isoWidth, isoWidth);

                } else if (instance.equals("B")) {

                    if (blueOpen) {
                        gc.drawImage(blueDoor, XIso, YIso, isoWidth, isoHeight);
                    } else {
                        gc.drawImage(blueDoor, XIso, YIso - 30, isoWidth, isoHeight);
                    }

                } else if (instance.equals("R")) {

                    if (redOpen) {
                        gc.drawImage(redDoor, XIso, YIso, isoWidth, isoHeight);
                    } else {
                        gc.drawImage(redDoor, XIso, YIso - 30, isoWidth, isoHeight);
                    }

                } else if (instance.equals("Y")) {

                    if (yellowOpen) {
                        gc.drawImage(yellowDoor, XIso, YIso, isoWidth, isoHeight);
                    } else {
                        gc.drawImage(yellowDoor, XIso, YIso - 30, isoWidth, isoHeight);
                    }

                } else if (instance.equals("D")) {

                    if (tokenOpen) {
                        gc.drawImage(tokenDoor, XIso, YIso, isoWidth, isoHeight);
                    } else {
                        gc.drawImage(tokenDoor, XIso, YIso - 30, isoWidth, isoHeight);
                    }

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
        gc.drawImage(player, 3 * GRID_CELL_WIDTH, 3 * GRID_CELL_HEIGHT, 60, 60);

    }

    /**
     * Draws users inventory to inventory canvas.
     */
    public void drawInventory() {

        // Get the Graphic Context of the canvas. This is what we draw on.
        GraphicsContext gc = invCanvas.getGraphicsContext2D();

        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (collectedRed) {
            gc.drawImage(redKey, 10, 1, 100, 100);
        } else {
            gc.drawImage(emptyKey, 10, 1, 100, 100);
        }
        if (collectedYellow) {
            gc.drawImage(yellowKey, 10, 62, 100, 100);
        } else {
            gc.drawImage(emptyKey, 10, 62, 100, 100);
        }
        if (collectedBlue) {
            gc.drawImage(blueKey, 10, 123, 100, 100);
        } else {
            gc.drawImage(emptyKey, 10, 123, 100, 100);
        }
        if (collectedFlippers) {
            gc.drawImage(flippers, 40, 187, 80, 80);
        }
        if (collectedFireBoots) {
            gc.drawImage(fireBoots, -10, 187, 80, 80);
        }

        tokenCount.setText(String.valueOf(totalTokens));
    }

    /**
     * Resets users inventory so that he carries nothing.
     */
    public void resetInventory() {

        collectedRed = false;
        collectedYellow = false;
        collectedBlue = false;
        redOpen = false;
        yellowOpen = false;
        blueOpen = false;
        tokenOpen = false;
        collectedFlippers = false;
        collectedFireBoots = false;
        totalTokens = 0;
        tokenCount.setText(String.valueOf(totalTokens));

    }

    /**
     * Inserting queue of entities onto the canvas when the method is called in drawGame();.
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

    /**
     * converts cartesian x coordinate to corresponding isometric x coordinate.
     *
     * @param X cartesian x coordinate.
     * @param Y cartesian y coordiante.
     * @return converted x coordinate.
     */
    private double xToIso(double X, double Y) {
        return X - Y;
    }

    /**
     * converts cartesian y coordinate to corresponding isometric y coordinate.
     *
     * @param X cartesian x coordinate.
     * @param Y cartesian y coordinate.
     * @return converted y coordinate.
     */
    private double yToIso(double X, double Y) {
        return (X + Y) / 2;
    }

    /**
     * Handles level progression.
     */
    private void victory() {

        System.out.println("Victory!");
        int levelNum = Integer.parseInt(Character.toString(name.charAt(6)));
        System.out.println(levelNum);
        levelBeingLoaded.updateTextFile(currentUser.getName(), Double.parseDouble(timerLabel.getText()));
        if (currentUser.getMaxCompletedLevel() < levelNum) {
            currentUser.updateTextFile();
            currentUser.setMaxCompletedLevel(levelNum);
        }
        if (levelNum < MenuController.noOfLevels) {

            Level selectedLevel = ReadLevelFile.readDataFile("Level" + (levelNum + 1) + ".txt");
            activeEntitys.clear();
            resetInventory();
            drawInventory();
            hasStarted = !hasStarted;
            setLevel(selectedLevel);

        } else {
            System.out.println("All levels completed");
            congratz();
        }
    }

    public void congratz() {
        try {

            // Create a FXML loader for loading the CreateUser FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Win.fxml"));

            // Run the loader
            GridPane winRoot = (GridPane) fxmlLoader.load();
            // Access the controller that was created by the FXML loader
            WinController winController = fxmlLoader.<WinController>getController();

            winController.setParentController(this);

            Scene winScene = new Scene(winRoot, 200, 184);

            // Create a new stage based on the editUser scene
            Stage winStage = new Stage();
            winStage.setScene(winScene);
            winStage.setTitle("100%");
            winStage.setResizable(false);

            // Make the stage a modal window.
            // This means that it must be closed before you can interact with any other window from this application.
            winStage.initModality(Modality.APPLICATION_MODAL);

            // Show the edit scene and wait for it to be closed
            winStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program with an error code
            System.exit(-1);
        }
    }

    /**
     * Reloads a level.
     */
    public void reloadLevel() {

        Level selectedLevel = ReadLevelFile.readDataFile(levelBeingLoaded.getFilename());

        activeEntitys.clear();
        resetInventory();
        drawInventory();
        setLevel(selectedLevel);
        hasStarted = false;

    }

    /**
     * Starts game timer.
     */
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
                                    Duration duration = ((KeyFrame) t.getSource()).getTime();
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
