import java.util.ArrayList;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    
    public void initialize() {
       backButton.setOnAction(e -> {
    	   Stage stage = (Stage) gridPane.getScene().getWindow();
   	    	stage.close();
       });
    }
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
    	
    	/*
    	int first = times.indexOf(Collections.min(times));
    	firstUser.setText(users.get(first));
    	firstTime.setText(String.valueOf(times.get(first)));
    	users.remove(first);
    	times.remove(first);
    	
    	int second = times.indexOf(Collections.min(times));
    	secondUser.setText(users.get(second));
    	secondTime.setText(String.valueOf(times.get(second)));
    	users.remove(second);
    	times.remove(second);
    	
    	int third = times.indexOf(Collections.min(times));
    	thirdUser.setText(users.get(third));
    	thirdTime.setText(String.valueOf(times.get(third)));
    	*/
    	
    }
    
    /**
     * Setting the scanned in values to the designated variables, and then
     * calling the drawGame method.
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
