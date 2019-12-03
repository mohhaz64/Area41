import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Key extends Entity {
	
	protected boolean collected = false;
	protected int size = 40;
	protected Image sprite;
	
	public Key (int x, int y, String colour) {
		super(x, y);
		if (colour.equalsIgnoreCase("blue")) {
			sprite = new Image("BlueKey.png", size, size, false, false);
		} else if (colour.equalsIgnoreCase("red")) {
			sprite = new Image("RedKey.png", size, size, false, false);
		} else if (colour.equalsIgnoreCase("yellow")) {
			sprite = new Image("YellowKey.png", size, size, false, false);
		}
	}
	
	public void draw (GraphicsContext g) {
		if (collected == false) {
			g.drawImage(sprite, (x - GameController.playerX + 3) * GameController.getGridCellWidth()
		              + (GameController.getGridCellWidth() - size)/2,
		            (y - GameController.playerY + 3) * GameController.getGridCellHeight()
		              + (GameController.getGridCellHeight() - size)/2);		}
	}
	
	public void checkIfTouched (GraphicsContext g) {
		if ((GameController.playerX == x) && (GameController.playerY == y)) {
			collected = true;
			x = -100;
			y = -100;
			//Redraw cell and player to remove token image
			g.fillRect(GameController.getGridCellWidth() * 3,  GameController.getGridCellHeight() * 3, GameController.getGridCellWidth(), GameController.getGridCellHeight());
			g.drawImage(GameController.player, 3 * GameController.getGridCellWidth(), 3 * GameController.getGridCellHeight());
			System.out.println("Key Collected");
		}
	}
	
}
