import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Level extends Game {
	
	private int width;
	private int height;
	private int xStart;
	private int yStart;
	ArrayList<String> map = new ArrayList<>();
	Queue<Entity> entityQueue = new Queue<Entity>();
	
	Scanner in = null;
	
	public void openFile(String fileName) {
		try {
			in = new Scanner(new File(fileName));
		}
		catch(Exception e) {
			System.out.println("Error: Can't load level");
		}
		readFile(in);
	}
	
	public void readFile(Scanner in) {
        String size = in.nextLine();
        width = Integer.parseInt(size.split(" ")[0]);
        height = Integer.parseInt(size.split(" ")[1]);
        
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
        xStart = Integer.parseInt(startLine.split(" ")[1]);
        yStart = Integer.parseInt(startLine.split(" ")[2]);
        
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

    }
	
	public static Entity readEntity(Scanner line) {
		
		int x = Integer.parseInt(line.next());
		int y = Integer.parseInt(line.next());
	    
	    Entity entity  = new Entity(x, y);

	    return entity;
	}

	
	public void closeFile() {
		in.close();
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
	
}