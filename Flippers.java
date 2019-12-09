import javafx.scene.image.Image;

/**
 * The controller for the game logic.
 *
 * @author Ben Hyde, Callum Charalambides
 * @version 2.1
 */
public class Flippers extends Entity {

	/**
	 * Creates an instance of a flippers.
	 * @param sprite The image of the flippers.
	 * @param x The x position of a flippers.
	 * @param y The y position of a flippers.
	 */
	public Flippers (Image sprite, int x, int y) {
		super(sprite, x, y);
	}

	/**
	 * If the flippers is touched then send it to GameController and change it to a null position;
	 */
	public void doTouched () {
		if (checkIfTouched()) {
			x = -100;
			y = -100;
			System.out.println("Flippers Collected");
			GameController.pickupFlippers();
		}
	}

	/**
	 * prints string to save to save file.
	 * @return string to save.
	 */
    public String toString() {
    	String result = "FLIPPERS " + x + " " + y + "\n";
    	return result;
    }


}