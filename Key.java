import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Key extends Entity {
	
	protected boolean pickedUp = false;
	protected int imgSize = 40;
	protected Image sprite;
	
	public Key (int x, int y, String colour) {
		super(x, y);
		if (colour.equalsIgnoreCase("blue")) {
			sprite = new Image("Blue Key.png", size, size, false, false);
		} else if (colour.equalsIgnoreCase("red")) {
			sprite = new Image("Red Key.png", size, size, false, false);
		} else if (colour.equalsIgnoreCase("green")) {
			sprite = new Image("Green Key.png", size, size, false, false);
		}
	}
	
	public void draw (GraphicsContext g) {
		if (pickedUp == false) {
			g.drawImage(sprite, (x - GameController.playerX + 3) * GameController.getGridCellWidth() + GameController.getGridCellWidth() -size)/2, 
									(y - GameController.playerY + 3) * GameController.getGridCellHeight() + (GameController.getGridCellHeight() - size)/2);
		}
	}
	
	public void hasKey (GraphicsContext g) {
		if ((GameController.playerX == x) && (GameController.playerY == y)) {
			pickedUp = true;
			x = -100;
			y = -100;
			
			g.fillRect(GameController.getGridCellWidth() * 3, GameController.getGridCellHeight() * 3, GameController.getGridCellWidth(), 
						Game Controller.getGridCellHeight());
			
			g.drawImage(GameController.player, 3 * GameController.getGridCellWidth(), 3 * GameController.getGridCellHeight());
			System.out.println("Key Collected");
		}
	}
	
}
