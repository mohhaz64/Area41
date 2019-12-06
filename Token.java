import javafx.scene.image.Image;

public class Token extends Entity {
	
	protected boolean collected = false;

	public Token (Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
	public boolean checkIfTouched () {
		if ((GameController.playerX == x) && (GameController.playerY == y)) {
			collected = true;
			x = -100;
			y = -100;
			System.out.println("Token Collected");
			GameController.pickupToken();
			return true;
		}
		else {
			return false;
		}
	}
	
}