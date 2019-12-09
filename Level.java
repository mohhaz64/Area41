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

	/**
	 * Creates an instance of a level.
	 * @param filename The filename used by the text file.
	 * @param name The name of the level.
	 * @param width The width of the map array.
	 * @param height The height of the map array.
	 * @param xStart The player's starting x position.
	 * @param yStart The player's starting y position.
	 * @param map The map array used to draw walls and ground.
	 * @param entityQueue The queue of entities that need to be added to an arraylist.
	 * @param users The arraylist of users who have completed the level.
	 * @param times The arraylist of times for above users.
	 */
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

	/**
	 * Updates the text file when a user completes a level.
	 * @param user user to be added.
	 * @param time time to be added.
	 */
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

	/**
	 * @return the level filename.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @return the level name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the width of level array.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height of level array.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the players starting X position.
	 */
	public int getXStart() {
		return xStart;
	}

	/**
	 * @return the players starting Y position.
	 */
	public int getYStart() {
		return yStart;
	}

	/**
	 * @return the map array.
	 */
	public String[][] getMap() {
		return map;
	}

	/**
	 * @return the entities in the queue.
	 */
	public Queue<Entity> getEntityQueue() {
		return entityQueue;
	}

	/**
	 * @return the arraylist of users.
	 */
	public ArrayList<String> getUsers() {
		return users;
	}

	/**
	 * @param users resets the user arraylist.
	 */
	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}

	/**
	 * @return the arraylist of times.
	 */
	public ArrayList<Double> getTimes() {
		return times;
	}

	/**
	 * @param times resets the times arraylist.
	 */
	public void setTimes(ArrayList<Double> times) {
		this.times = times;
	}

	

}