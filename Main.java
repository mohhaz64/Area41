import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The main class for this program.
 *
 * @author Callum Charalambides
 * @version 1.1
 */
public class Main extends Application {

    //Constants for the Menu window
    private static final int MENU_WINDOW_WIDTH = 600;
    private static final int MENU_WINDOW_HEIGHT = 400;
    private static final String WINDOW_TITLE = "AREA41";

    // Constants for the Game window, Create User window, and Edit User window.
    // We make them public and allow the controllers to access these,
    // so that we keep all window constants in one place.
    private static final int GAME_WINDOW_WIDTH = 600;


    /**
     * retrieves the windows title.
     *
     * @return window title.
     */
    public static String getWindowTitle() {
        return WINDOW_TITLE;
    }

    /**
     * retrieves game window width.
     *
     * @return game windows width.
     */
    public static int getGameWindowWidth() {
        return GAME_WINDOW_WIDTH;
    }

    /**
     * retrieves game window height.
     *
     * @return game windows height.
     */
    public static int getGameWindowHeight() {
        return GAME_WINDOW_HEIGHT;
    }

    /**
     * retrieves create user window width.
     *
     * @return user window width
     */
    public static int getCreateUserWindowWidth() {
        return CREATE_USER_WINDOW_WIDTH;
    }

    /**
     * retrieves create user window height.
     *
     * @return create user window height.
     */
    public static int getCreateUserWindowHeight() {
        return CREATE_USER_WINDOW_HEIGHT;
    }

    /**
     * retrieves edit user window width.
     *
     * @return edit user window width.
     */
    public static int getEditUserWindowWidth() {
        return EDIT_USER_WINDOW_WIDTH;
    }

    /**
     * retrieves edit user window height.
     *
     * @return edit user window height.
     */
    public static int getEditUserWindowHeight() {
        return EDIT_USER_WINDOW_HEIGHT;
    }

    private static final int GAME_WINDOW_HEIGHT = 600;

    private static final int CREATE_USER_WINDOW_WIDTH = 400;
    private static final int CREATE_USER_WINDOW_HEIGHT = 150;

    private static final int EDIT_USER_WINDOW_WIDTH = 400;
    private static final int EDIT_USER_WINDOW_HEIGHT = 200;

    /**
     * loads the menu window.
     *
     * @param primaryStage the staged being passed through.
     */
    public void start(Stage primaryStage) {
        try {
            // Load the Menu scene.
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root, MENU_WINDOW_WIDTH, MENU_WINDOW_HEIGHT);

            // Place the Menu scene on stage and show it.
            primaryStage.setScene(scene);
            primaryStage.setTitle(WINDOW_TITLE);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
