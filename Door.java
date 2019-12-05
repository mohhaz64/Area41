import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Door extends Entity {
	protected int size = 40;
	protected Image sprite;
	protected boolean opened = false;
	protected String col = null;
	
	public Door(int x, int y, String colour) {
		super (x, y);
		if(colour.equalsIgnoreCase("blue")) {
			sprite = new Image("Blue Door.png", size, size, false, false);
		} else if(colour.equalsIgnoreCase("red")) {
			sprite = new Image("Red Door.png", size, size, false, false);
		} else if(colour.equalsIgnoreCase("yellow")) {
			sprite = new Image("Yellow Door.png", size, size, false, false);
		}
		this.col = colour;
	}
	
	public void draw(GraphicsContext g) {
		if(opened == false) {
			g.drawImage(sprite, (x - GameController.playerX + 3) * GameController.getGridCellWidth() + (GameController.getGridCellWidth() - size)/2,
					(y - GameController.playerY + 3) * GameController.getGridCellHeight() + (GameController.getGridCellHeight() - size)/2);
		}
	}
	
	public void checkIfOpeneable() {
		if(GameController.collectedRed == true && col.equalsIgnoreCase("red")) {
			System.out.println("You have the right key! The door is now opened");
			GameController.playerX = x;
			GameController.playerY = y;
			x = -100;
			y = -100;
			
			g.fillRect(GameController.getGridCellWidth() * 3, GameController.getGridCellHeight() * 3, GameController.getGridCellWidth(), 
					Game Controller.getGridCellHeight());
		
			g.drawImage(GameController.player, 3 * GameController.getGridCellWidth(), 3 * GameController.getGridCellHeight());
		} else if(GameController.collectedYellow == true && col.equalsIgnoreCase("yellow")) {
			System.out.println("You have the right key! The door is now opened");
			GameController.playerX = x;
			GameController.playerY = y;
			x = -100;
			y = -100;
			
			g.fillRect(GameController.getGridCellWidth() * 3, GameController.getGridCellHeight() * 3, GameController.getGridCellWidth(), 
					Game Controller.getGridCellHeight());
		
			g.drawImage(GameController.player, 3 * GameController.getGridCellWidth(), 3 * GameController.getGridCellHeight());		
		} else if(GameController.collectedBlue == true && col.equalsIgnoreCase("blue")) {
			System.out.println("You have the right key! The door is now opened");
			GameController.playerX = x;
			GameController.playerY = y;
			x = -100;
			y = -100;
			
			g.fillRect(GameController.getGridCellWidth() * 3, GameController.getGridCellHeight() * 3, GameController.getGridCellWidth(), 
					Game Controller.getGridCellHeight());
		
			g.drawImage(GameController.player, 3 * GameController.getGridCellWidth(), 3 * GameController.getGridCellHeight());
		} else {
			System.out.println("Sorry, you have the wrong key. The door is still locked.");
		}
	}
	
}