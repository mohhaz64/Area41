import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Level extends Game {
	
	
	ArrayList<String> lines = new ArrayList<>();
	Scanner in = null;
	
	public void openFile() {
		try {
			in = new Scanner(new File("tempLevel.txt"));
		}
		catch(Exception e) {
			System.out.println("Error..Can't load level");
		}
	}
	
	public void readFile() {
		while(in.hasNextLine()) {

			
		}
	}
	
	public void closeFile() {
		in.close();
	}
}
