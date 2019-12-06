import javafx.scene.image.Image;

public class Flippers extends Entity {
	
	protected boolean collected = false;

	public Flippers (Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
	public boolean checkIfTouched () {
		if ((GameController.playerX == x) && (GameController.playerY == y)) {
			collected = true;
			x = -100;
			y = -100;
			System.out.println("Flippers Collected");
			GameController.pickupFlippers();
			return true;
		}
		else {
			return false;
		}
	}

}