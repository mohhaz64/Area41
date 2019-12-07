import java.io.File;
import java.io.FileNotFoundException;
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
    static Image enemySprite = new Image("Golem.png", 40, 40, false, false);
    
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

	while (in.hasNextLine()) {

	    String l = in.nextLine();
	    System.out.println(l);

	    Scanner line = new Scanner(l);
	    String ent = line.next().toLowerCase();

	    String entity = ent.substring(0, 1).toUpperCase()
		    + ent.substring(1);

	    try {
		Class.forName(entity);
	    } catch (ClassNotFoundException e) {
		System.out.println("Error: " + entity + " class needed.");
	    }

	    if (entity.equalsIgnoreCase("DUMB")) {
		entityQueue.enqueue(readDumb(line));
		
	    } else if (entity.equalsIgnoreCase("DOOR")) {
	    	
		
	    } else if (entity.equalsIgnoreCase("FIREBOOTS")) {
		entityQueue.enqueue(readFireBoots(line));
		
	    } else if (entity.equalsIgnoreCase("TOKEN")) {
		entityQueue.enqueue(readToken(line));
		
	    } else if (entity.equalsIgnoreCase("TELEPORTER")) {
	    	
	    	
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
		System.out.println("Error: Entity not found.");
	    }

	}

	Level level = new Level(levelFilename, name, width, height, xStart, yStart, map,
		entityQueue);

	return level;

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
