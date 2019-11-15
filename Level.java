import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Level extends Game {
	
	private int width;
	private int height;
	ArrayList<String> map = new ArrayList<>();
	
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
        
        System.out.println(map);
        
        while(in.hasNextLine()) {
            
            String l = in.nextLine();
            System.out.println(l);
            
            Scanner line = new Scanner(l);
            String shape = line.next();

            
        }
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
	
}