import javafx.scene.image.Image;

public class Water extends Entity {
	
	public Water(Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
    public boolean hasKilledPlayer() {
	if (checkIfTouched() && !GameController.isCollectedFlippers()) {
	    return true;
	} else {
		return false;
	}
    }
	
	public String toString() {
		String result = "WATER " + x + " " + y + "\n";
		return result;
	}
}
