import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Key extends Entity {
	
	protected boolean collected = false;
	protected String col = null;
	
	public Key (Image sprite, int x, int y, String colour) {
		super(sprite, x, y);
		this.col = colour;
	}
	
	public void checkIfTouched (GraphicsContext g) {
		if ((GameController.playerX == x) && (GameController.playerY == y)) {
			collected = true;
			x = -100;
			y = -100;
			//Redraw cell and player to remove token image
			g.fillRect(GameController.getGridCellWidth() * 3,  GameController.getGridCellHeight() * 3, GameController.getGridCellWidth(), GameController.getGridCellHeight());
			g.drawImage(GameController.ground,3 * GameController.getGridCellWidth(), 3 * GameController.getGridCellHeight());
			System.out.println("Key Collected");
			if(col.equalsIgnoreCase("red")) {
				GameController.pickupRedKey();
			}
			if(col.equalsIgnoreCase("yellow")) {
				GameController.pickupYellowKey();
			}
			if(col.equalsIgnoreCase("blue")) {
				GameController.pickupBlueKey();
			}
		}
	}
	
}
