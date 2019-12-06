import javafx.scene.image.Image;

public class Fireboots extends Entity {
	
	protected boolean collected = false;

	public Fireboots (Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
	public boolean checkIfTouched () {
		if ((GameController.playerX == x) && (GameController.playerY == y)) {
			collected = true;
			x = -100;
			y = -100;
			System.out.println("Fireboots Collected");
			GameController.pickUpFireboots();
			return true;
		}
		else {
			return false;
		}
	}

}