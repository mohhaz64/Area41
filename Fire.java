
import javafx.scene.image.Image;

public class Fire extends Entity {
	
	public Fire(Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
    public boolean hasKilledPlayer() {
	if (checkIfTouched() && !GameController.isCollectedFireBoots()) {
	    return true;
	} else {
		return false;
	}
    }
	
	public String toString() {
		String result = "FIRE " + x + " " + y + "\n";
		return result;
	}
}
