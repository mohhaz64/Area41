import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Alert;

public class User {
    private String name;
    private String savedFile;
    private int maxCompletedLevel = 0;

    public User(String line) {
    System.out.println(line);
	String[] data = line.split(" ");
	name = data[0];
	savedFile = data[0] + "SavedGame.txt";
	maxCompletedLevel = Integer.parseInt(data[1]);
    }

    public void updateTextFile() {
    	try {
    		
            BufferedReader file = new BufferedReader(new FileReader("users.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();


            inputStr = inputStr.replace(name + " " + maxCompletedLevel, name + " " + (maxCompletedLevel + 1)); 

            FileOutputStream fileOut = new FileOutputStream("users.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Error: Can't find users.txt file");
        }
    	
    }

    public int getMaxCompletedLevel() {
	return maxCompletedLevel;
    }

    public void setMaxCompletedLevel(int maxCompletedLevel) {
	this.maxCompletedLevel = maxCompletedLevel;
    }

    public void setSavedFile(String savedFile) {
	this.savedFile = savedFile;
    }

    public String getName() {
	return name;
    }

    public String getSavedFile() {
	return savedFile;
    }

    public void updateSavedFile() {
	File file = new File(savedFile);
	savedFile = name + "SavedGame.txt";
	file.renameTo(new File(savedFile));
    }

    public void setName(String name) {
	this.name = name;
    }

    public static boolean validName(String userName, String currentUserName) {

	if (userName.equals(currentUserName)) {
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText(null);
	    alert.setContentText("Username not Changed!");
	    alert.showAndWait();
	    return false;
	} else if (!validName(userName)) {
	    return false;
	} else {
	    return true;
	}
    }

    public static boolean validName(String userName) {

	BufferedReader reader;
	ArrayList<String> users = new ArrayList<>();
	Alert alert = new Alert(Alert.AlertType.ERROR);
	alert.setTitle("Error");
	alert.setHeaderText(null);

	try {
	    reader = new BufferedReader(new FileReader("users.txt"));
	    String line = reader.readLine();
	    while (line != null) {
	    String[] data = line.split(" ");
		users.add(data[0]);
		line = reader.readLine();
	    }
	    reader.close();
	} catch (IOException e) {
	    System.out.println("Error: 'users.txt' not yet created.");
	}

	if (userName.equals("")) {

	    alert.setContentText("Please Enter a Username");
	    alert.showAndWait();
	    return false;

	} else if (!userName.matches("^[a-zA-Z0-9]+$")) {

	    alert.setContentText("Special characters are not allowed");
	    alert.showAndWait();
	    return false;

	} else if (userName.length() > 20) {

	    alert.setContentText("Has to be 20 characters or less");
	    alert.showAndWait();
	    return false;

	} else if (users.contains(userName)) {

	    alert.setContentText("Username Taken!");
	    alert.showAndWait();
	    return false;

	} else {
	    return true;
	}

    }

}
