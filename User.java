import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class User {
	private String name;
	private String savedFile;

	public User(String name) {
		this.name = name;
		this.savedFile = name + "SavedGame.txt";
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
			e.printStackTrace();
		}

		if (userName.equals("")){

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
