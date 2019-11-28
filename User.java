
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
	
	public void setName(String name) {
		this.name = name;
	}

}
