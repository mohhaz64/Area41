import javafx.scene.image.Image;

/**
 * Implements fireboots entity.
 * @author Ben Hyde, Bryan Khong
 * @version 1.0
 */
public class Fireboots extends Entity {

	/**
	 * sets fireboots attributes such as position and sprite.
	 * @param sprite sprite image to set.
	 * @param x x coordinate of fireboots entity.
	 * @param y y coordinate of fireboots entity.
	 */
	public Fireboots (Image sprite, int x, int y) {
		super(sprite, x, y);
	}

	/**
	 * If the fireboots is touched then send it to GameController and change it to a null position;
	 */
	public void doTouched () {
		if (checkIfTouched()) {
			x = -100;
			y = -100;
			System.out.println("Fireboots Collected");
			GameController.pickUpFireboots();
		}
	}

	/**
	 * prints string to save to save file.
	 * @return string to save.
	 */
    public String toString() {
    	String result = "FIREBOOTS " + x + " " + y + "\n";
    	return result;
    }


}