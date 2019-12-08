import javafx.scene.image.Image;

public class Fire extends Entity {
	
	public Fire(Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
	public boolean checkIfTouched() {
		if((GameController.playerX == x) && (GameController.playerY == y)) {
			if(GameController.isCollectedFireboots() == true) {
				GameController.playerX = x;
				GameController.playerY = y;
				x = -100;
				y = -100;
				return true;
			} else {
				//Player dies
			}
		} else {
			return false;
		}
	}
}
