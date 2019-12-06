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
    private int maxCompletedLevel;

    public User(String line) {
	String[] data = line.split(" ");
	name = data[0];
	System.out.println(name);
	savedFile = data[0] + "SavedGame.txt";
	System.out.println(savedFile);
	//Just commented this out for now so the game runs
	//maxCompletedLevel = Integer.parseInt(data[1]);
	//System.out.println(maxCompletedLevel);
    }

    public void updateTextFile() {
	BufferedReader reader;
	FileOutputStream fileOut;
	try {
	    reader = new BufferedReader(new FileReader("users.txt"));
	    fileOut = new FileOutputStream("users.txt");
	    String line = reader.readLine();
	    while (line != null) {
		String[] userInfo = line.split(" ");
		if (userInfo[0] == name) {
		    userInfo[1] = Integer.toString(maxCompletedLevel);
		    String newline = userInfo[0] + userInfo[1];
		    fileOut.write(newline.getBytes());
		} else {
		    fileOut.write(line.getBytes());
		}
	    }
	    reader.close();
	    fileOut.close();
	} catch (IOException e) {
	    e.printStackTrace();
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
		users.add(line);
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
