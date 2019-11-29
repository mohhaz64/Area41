
public class Level {

	private String name;
	private int width;
	private int height;
	private int xStart;
	private int yStart;
	private String map[][];
	private Queue<Entity> entityQueue;
	
	public Level(String name, int width, int height, int xStart, int yStart, String map[][], Queue<Entity> entityQueue) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.xStart = xStart;
		this.yStart = yStart;
		this.map = map;
		this.entityQueue = entityQueue;
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

	

}