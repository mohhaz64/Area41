import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Level {

	private String filename;
	private String name;
	private int width;
	private int height;
	private int xStart;
	private int yStart;
	private String map[][];
	private Queue<Entity> entityQueue;
	private ArrayList<String> users;
	private ArrayList<Double> times;
	
	public Level(String filename, String name, int width, int height, int xStart, int yStart, String map[][], Queue<Entity> entityQueue, ArrayList<String> users, ArrayList<Double> times) {
		this.filename = filename;
		this.name = name;
		this.width = width;
		this.height = height;
		this.xStart = xStart;
		this.yStart = yStart;
		this.map = map;
		this.entityQueue = entityQueue;
		this.setUsers(users);
		this.setTimes(times);
	}
	
	public void updateTextFile(String user, double time) {
		try(
				FileWriter fw = new FileWriter(filename, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
    		
    		out.print("\n" + user + " " + time);
    		
        } catch (Exception e) {
            System.out.println("Error: Can't find level file");
        }
    	
    }
	
	public String getFilename() {
		return filename;
	}
	
	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getXStart() {
		return xStart;
	}

	public int getYStart() {
		return yStart;
	}
	
	public String[][] getMap() {
		return map;
	}

	public Queue<Entity> getEntityQueue() {
		return entityQueue;
	}

	public ArrayList<String> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}

	public ArrayList<Double> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<Double> times) {
		this.times = times;
	}

	

}