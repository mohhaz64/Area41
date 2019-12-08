import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.scene.image.Image;

/**
 * This class scans the selected level, and returns the variables
 * 
 * @author Ben Hyde
 *
 */
public class ReadLevelFile {
	
	static Image fireBootsSprite = new Image("FireBoots.png", 40, 40, false, false);
    static Image tokenSprite = new Image("Token.png", 40, 40, false, false);
    static Image flippersSprite = new Image("Flippers.png", 40, 40, false, false);
    static Image keySprite;
    static Image doorSprite;
    static Image enemySprite = new Image("Golem.png", 40, 40, false, false);
    static Image nullSprite;
    
    static String levelFilename;
    

    /**
     * Scans each line within the txt file, and adds it to the designated array.
     * 
     * @param in The scanner with the txt file.
     * @return returns an instance of the level, with the map and entitiy arrays
     *         full.
     * 
     */
	private static Level readDataFile(Scanner in) {
    	

	Queue<Entity> entityQueue = new Queue<Entity>();

	String name = in.nextLine();

	String size = in.nextLine();
	int width = Integer.parseInt(size.split(" ")[0]);
	int height = Integer.parseInt(size.split(" ")[1]);

	String[][] map = new String[width][height];

	for (int h = 0; h < height; h++) {

	    String l = in.nextLine();

	    for (int w = 0; w < width; w++) {
		String cell = (l.split("")[w]);
		map[w][h] = cell;
	    }

	}

	String startLine = in.nextLine();
	int xStart = Integer.parseInt(startLine.split(" ")[1]);
	int yStart = Integer.parseInt(startLine.split(" ")[2]);
	
    ArrayList<String> users = new ArrayList<String>();
    ArrayList<Double> times = new ArrayList<Double>();

	while (in.hasNextLine()) {

	    String l = in.nextLine();
	    
	    Scanner newline = new Scanner(l);
	    String lineString = newline.nextLine();
	    
	    Scanner line = new Scanner(l);
	    String ent = line.next().toLowerCase();

	    String entity = ent.substring(0, 1).toUpperCase()
		    + ent.substring(1);

	    if (entity.equalsIgnoreCase("WATER")) {
	    	map[readX(lineString)][readY(lineString)] = "W";
			entityQueue.enqueue(readWater(line));
			
	    } else if (entity.equalsIgnoreCase("FIRE")) {
	    	map[readX(lineString)][readY(lineString)] = "F";
			entityQueue.enqueue(readFire(line));
			
		} else if (entity.equalsIgnoreCase("DUMB")) {
		entityQueue.enqueue(readDumb(line));
		
	    } else if (entity.equalsIgnoreCase("DOOR")) {
	    	
	    	if (readType(lineString).equalsIgnoreCase("blue")) {
	    		map[readX(lineString)][readY(lineString)] = "B";
	    		entityQueue.enqueue(readDoor(line));
	    	} else if (readType(lineString).equalsIgnoreCase("red")) {
	    		map[readX(lineString)][readY(lineString)] = "R";
	    		entityQueue.enqueue(readDoor(line));
	    	} else if (readType(lineString).equalsIgnoreCase("yellow")) {
	    		map[readX(lineString)][readY(lineString)] = "Y";
	    		entityQueue.enqueue(readDoor(line));
	    	} else if (readType(lineString).equalsIgnoreCase("token")) {
	    		map[readX(lineString)][readY(lineString)] = "D";
	    		entityQueue.enqueue(readTokenDoor(line));
	    	} else {
	    		System.out.println("Error: Door type not found");
	    	}
		
	    } else if (entity.equalsIgnoreCase("FIREBOOTS")) {
		entityQueue.enqueue(readFireBoots(line));
		
	    } else if (entity.equalsIgnoreCase("TOKEN")) {
		entityQueue.enqueue(readToken(line));
		
	    } else if (entity.equalsIgnoreCase("TELEPORTER")) {
	    	map[readX(lineString)][readY(lineString)] = "T";
			entityQueue.enqueue(readTeleporter(line)); 	
	    	
	    } else if (entity.equalsIgnoreCase("KEY")) {
		entityQueue.enqueue(readKey(line));
		
	    } else if (entity.equalsIgnoreCase("FLIPPERS")) {
		entityQueue.enqueue(readFlippers(line));
		
	    } else if (entity.equalsIgnoreCase("SMART")) {
		entityQueue.enqueue(readSmart(line));
		
	    } else if (entity.equalsIgnoreCase("STRAIGHT")) {
		entityQueue.enqueue(readStraight(line));
		
	    } else if (entity.equalsIgnoreCase("WALL")) {
			entityQueue.enqueue(readWall(line));
		
	    } else {
	    	users.add(readUser(lineString));
	    	times.add(readTime(lineString));
	    }
	    
	    newline.close();

	}
	

	Level level = new Level(levelFilename, name, width, height, xStart, yStart, map,
		entityQueue, users, times);

	return level;

    }
    
    public static int readX(String line) {
    	String[] data = line.split(" ");
    	int x = Integer.parseInt(data[1]);
    	return x;
    }
    
    public static int readY(String line) {
    	String[] data = line.split(" ");
    	int y = Integer.parseInt(data[2]);
    	return y;
    }
    
    public static String readType(String line) {
    	String[] data = line.split(" ");
    	return data[3];
    }
    
    public static String readUser(String line) {
    	String[] data = line.split(" ");
    	return data[0];
    }
    
    public static double readTime(String line) {
    	String[] data = line.split(" ");
    	double time = Double.parseDouble(data[1]);
    	return time;
    }

    public static Entity readDumb(Scanner line) {
	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	DumbFollowEnemy dumbEnemy = new DumbFollowEnemy(enemySprite, x, y);
	return dumbEnemy;
    }

    public static Entity readStraight(Scanner line) {
	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	boolean moveInX = Boolean.parseBoolean(line.next());
	boolean positiveDirection = Boolean.parseBoolean(line.next());
	StraightLineEnemy straightEnemy = new StraightLineEnemy(enemySprite, x, y, moveInX,
		positiveDirection);
	return straightEnemy;
    }

    public static Entity readSmart(Scanner line) {
	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	SmartFollowEnemy smartEnemy = new SmartFollowEnemy(enemySprite, x, y);
	return smartEnemy;
    }
    
    public static Entity readWall(Scanner line) {
	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	boolean preferRight = Boolean.parseBoolean(line.next());
	WallFollowEnemy wallEnemy = new WallFollowEnemy(enemySprite, x, y, preferRight);
	return wallEnemy;
    }

    public static Token readToken(Scanner line) {

	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	
	if (x == -100 && y ==-100) {
		GameController.pickupToken();
	}

	Token token = new Token(tokenSprite, x, y);

	return token;

    }

    public static Fireboots readFireBoots(Scanner line) {

	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	
	if (x == -100 && y ==-100) {
		GameController.pickUpFireboots();
	}

	Fireboots fireBoots = new Fireboots(fireBootsSprite, x, y);

	return fireBoots;

    }

    public static Flippers readFlippers(Scanner line) {

	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	
	if (x == -100 && y ==-100) {
		GameController.pickupFlippers();
	}

	Flippers flippers = new Flippers(flippersSprite, x, y);

	return flippers;

    }
    
    public static Water readWater(Scanner line) {

    	int x = Integer.parseInt(line.next());
    	int y = Integer.parseInt(line.next());
    	
    	Water water = new Water(nullSprite, x, y);

    	return water;

    }
    
    public static Fire readFire(Scanner line) {

    	int x = Integer.parseInt(line.next());
    	int y = Integer.parseInt(line.next());
    	
    	Fire fire = new Fire(nullSprite, x, y);

    	return fire;

    }
    
    public static Teleporter readTeleporter(Scanner line) {
    	int x = Integer.parseInt(line.next());
    	int y = Integer.parseInt(line.next());
    	int xToGo = Integer.parseInt(line.next());
    	int yToGo = Integer.parseInt(line.next());

    	Teleporter teleporter = new Teleporter(nullSprite, x, y, xToGo, yToGo);

    	return teleporter;
	}

    public static Key readKey(Scanner line) {

	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	String col = line.next();
	
	if (col.equalsIgnoreCase("blue")) {
		if (x == -100 && y ==-100) {
			GameController.pickupBlueKey();
		}
		keySprite = new Image("BlueKey.png", 40, 40, false, false);
	} else if (col.equalsIgnoreCase("red")) {
		if (x == -100 && y ==-100) {
			GameController.pickupRedKey();
		}
		keySprite = new Image("RedKey.png", 40, 40, false, false);
	} else if (col.equalsIgnoreCase("yellow")) {
		if (x == -100 && y ==-100) {
			GameController.pickupYellowKey();
		}
		keySprite = new Image("YellowKey.png", 40, 40, false, false);
	}

	Key key = new Key(keySprite, x, y, col);

	return key;

    }
    
    public static Door readDoor(Scanner line) {

    	int x = Integer.parseInt(line.next());
    	int y = Integer.parseInt(line.next());
    	String col = line.next();

    	Door door = new Door(nullSprite, x, y, col);

    	return door;

    }
    
    public static TokenDoor readTokenDoor(Scanner line) {

    	int x = Integer.parseInt(line.next());
    	int y = Integer.parseInt(line.next());
    	line.next();
    	int tokensRequired = Integer.parseInt(line.next());

    	TokenDoor door = new TokenDoor(nullSprite, x, y, tokensRequired);

    	return door;

    }

    public static Level readDataFile(String filename) throws NoSuchElementException {
    	
    levelFilename = filename;
	Scanner in = null;

	// Checks that the file exists.
	try {
	    in = new Scanner(new File(filename));
	    System.out.println("File found.\n");
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	return ReadLevelFile.readDataFile(in);

    }

}
