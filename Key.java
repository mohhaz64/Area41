import javafx.scene.image.Image;

public class Key extends Entity {
	
	protected boolean collected = false;
	protected String col = null;
	
	public Key (Image sprite, int x, int y, String colour) {
		super(sprite, x, y);
		this.col = colour;
	}
	
	public boolean checkIfTouched () {
		if ((GameController.playerX == x) && (GameController.playerY == y)) {
			collected = true;
			x = -100;
			y = -100;
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
			return true;
		}
		else {
			return false;
		}
	}
	
}
