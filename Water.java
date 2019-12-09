
import javafx.scene.image.Image;

/**
 * Implements water entity.
 * @author Ben Hyde, Bryan Khong
 * @version 1.0
 */
public class Water extends Entity {

	/**
	 * Sets water attributes such as position and sprite.
	 * @param sprite sprite image to set.
	 * @param x x coordinate of water entity.
	 * @param y y coordinate of water entity.
	 */
	public Water(Image sprite, int x, int y) {
		super(sprite, x, y);
	}

	/**
	 * checks if the fire has killed player if the player doesn't have the flippers.
	 * @return true if killed else false.
	 */
    public boolean hasKilledPlayer() {
	if (checkIfTouched() && !GameController.isCollectedFlippers()) {
	    return true;
	} else {
		return false;
	}
    }

	/**
	 * prints string to save to save file.
	 * @return string to save.
	 */
	public String toString() {
		String result = "WATER " + x + " " + y + "\n";
		return result;
	}
}

