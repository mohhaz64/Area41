import javafx.scene.image.Image;

public class Water extends Entity {
	
	public Water(Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
	public void doTouched() {
		if(checkIfTouched()) {
			if(GameController.isCollectedFlippers() == true) {
				GameController.playerX = x;
				GameController.playerY = y;
			} else {
				//Player dies
			}
		} else {
			return false;
		}
	}
	
	public String toString() {
		String result = "WATER " + x + " " + y + "\n";
		return result;
	}
}
