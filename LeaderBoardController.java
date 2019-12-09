import java.util.ArrayList;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The controller for the leaderboard.
 *
 * @author Ben Hyde, Callum Charalambides
 * @version 2.1
 */
public class LeaderBoardController {

	@FXML private GridPane gridPane;
    @FXML private Label levelBeingDisplayed;
    @FXML private Label firstUser;
    @FXML private Label secondUser;
    @FXML private Label thirdUser;
    @FXML private Label secondTime;
    @FXML private Label firstTime;
    @FXML private Label thirdTime;
    @FXML private Button backButton;
    private MenuController parentController;
    
    private Level levelBeingLoaded;
    private String name;
	private ArrayList<String> users;
	private ArrayList<Double> times;
    
    public void setParentController(MenuController parentController) {
        this.parentController = parentController;
    }

	/**
	 * Initialize the controller. This method is called automatically and everything within is run IN ORDER.
	 */
    public void initialize() {
       backButton.setOnAction(e -> {
    	   Stage stage = (Stage) gridPane.getScene().getWindow();
   	    	stage.close();
       });
    }

	/**
	 * Finds the user name and time of the top three fastest level completions.
	 */
	public void findTimes() {
    	
    	levelBeingDisplayed.setText(name);
    	
    	for (int i = 1; i <= 3; i++) {
    		if (times.size() > 0) {
    			int index = times.indexOf(Collections.min(times));
    			if (i == 1) {
    				firstUser.setText(users.get(index));
    		    	firstTime.setText(String.valueOf(times.get(index)));
    			} else if (i == 2) {
    				secondUser.setText(users.get(index));
    		    	secondTime.setText(String.valueOf(times.get(index)));
    			} else if (i == 3) {
    				thirdUser.setText(users.get(index));
    		    	thirdTime.setText(String.valueOf(times.get(index)));
    			}
    			users.remove(index);
    	    	times.remove(index);
    		}
    	}
    	
    }
    
    /**
     * Setting the scanned in values to the designated variables, and then
     * calling the findTimes method.
     * 
     * @param levelToLoad The instance of Level which we want to load
     */
    public void setLevel(Level levelToLoad) {
	this.levelBeingLoaded = levelToLoad;

	users = levelBeingLoaded.getUsers();
	times = levelBeingLoaded.getTimes();
	name = levelBeingLoaded.getName();
	findTimes();

    }
    

}
