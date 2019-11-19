import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadLevelFile {

	private static Level readDataFile(Scanner in) {
		
		ArrayList<String> map = new ArrayList<>();
		Queue<Entity> entityQueue = new Queue<Entity>();
		
		String name = in.nextLine();
		
		String size = in.nextLine();
        int width = Integer.parseInt(size.split(" ")[0]);
        int height = Integer.parseInt(size.split(" ")[1]);

        for (int h = 0; h < height; h++) {

            String l = in.nextLine();

            for (int w = 0; w < width; w++) {
                String cell = (l.split("")[w]);
                if (cell.equals("#")) {
                    map.add("Wall");
                }
                else if (cell.equals(" ")) {
                    map.add("Ground");
                }
                else if (cell.equals("G")) {    
                    map.add("Goal");
                }
                else {
                    System.out.println("Error: char not found.");
                }
            }

        }

        String startLine = in.nextLine();
        int xStart = Integer.parseInt(startLine.split(" ")[1]);
        int yStart = Integer.parseInt(startLine.split(" ")[2]);

        while(in.hasNextLine()) {

            String l = in.nextLine();
            System.out.println(l);

            Scanner line = new Scanner(l);
            String entity = line.next();

            try {
				Class.forName(entity);
			}
			catch(ClassNotFoundException e) {
				System.out.println("Error: " + entity + " class needed.");
			}

			if (entity.equalsIgnoreCase("ENEMY")) {
				entityQueue.enqueue(readEntity(line));
			}
			else if (entity.equalsIgnoreCase("DOOR")) {

				entityQueue.enqueue(readEntity(line));
			}
			else if (entity.equalsIgnoreCase("TELEPORTER")) {

				entityQueue.enqueue(readEntity(line));
			}
			else if (entity.equalsIgnoreCase("KEY")) {
				entityQueue.enqueue(readEntity(line));
			}
			else if (entity.equalsIgnoreCase("TOKEN")) {
				entityQueue.enqueue(readEntity(line));
			}
			else {
				System.out.println("Error: Entity not found.");
			}

        }
        
        Level level = new Level(name, width, height, xStart, yStart, map, entityQueue);
        
        return level;

	}
	
	public static Entity readEntity(Scanner line) {

		int x = Integer.parseInt(line.next());
		int y = Integer.parseInt(line.next());

	    Entity entity  = new Entity(x, y);

	    return entity;

	}
	
	public static Level readDataFile(String filename) throws NoSuchElementException {
		
		Scanner in = null;
	    
		//Checks that the file exists.
	    try {
	    	in = new Scanner(new File(filename));
	        System.out.println("File found.\n");
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	    
	    return ReadLevelFile.readDataFile(in);
	    
	}

}
