import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fireboots extends Entity {
	
	protected boolean collected = false;

	public Fireboots (Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
	public void checkIfTouched (GraphicsContext g) {
		if ((GameController.playerX == x) && (GameController.playerY == y)) {
			collected = true;
			x = -100;
			y = -100;
			//Redraw cell and player to remove token image
			g.fillRect(GameController.getGridCellWidth() * 3,  GameController.getGridCellHeight() * 3, GameController.getGridCellWidth(), GameController.getGridCellHeight());
			g.drawImage(GameController.ground,3 * GameController.getGridCellWidth(), 3 * GameController.getGridCellHeight());
			System.out.println("FireBoots Collected");
			GameController.pickedUpFireBoots();
		}
	}
	
}