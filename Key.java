import javafx.scene.image.Image;

/**
 * Implements key entity.
 * @author Ben Hyde, Bryan Khong
 * @version 1.2
 */
public class Key extends Entity {
	
	protected String col = null;

	/**
	 * Creates an instance of a key.
	 * @param sprite The image of the key.
	 * @param x The x position of a key.
	 * @param y The y position of a key.
	 * @param colour The colour of a key.
	 */
	public Key (Image sprite, int x, int y, String colour) {
		super(sprite, x, y);
		this.col = colour;
	}

	/**
	 * If the key is touched then send it to GameController and change it to a null position;
	 */
	public void doTouched () {
		if (checkIfTouched()) {
			x = -100;
			y = -100;
			System.out.println("Key Collected");
			if(col.equalsIgnoreCase("red")) {
				GameController.pickupRedKey();
			}
			if(col.equalsIgnoreCase("yellow")) {
				GameController.pickupYellowKey();
			}
			if(col.equalsIgnoreCase("blue")) {
				GameController.pickupBlueKey();
			}
		}
	}

	/**
	 * prints string to save to save file.
	 * @return string to save.
	 */
    public String toString() {
    	String result = "KEY " + x + " " + y  + " " + col + "\n";
    	return result;
    }
	
}
