import javafx.scene.image.Image;

/**
 * Implements token entity.
 * @author Ben Hyde
 * @version 1.1
 */
public class Token extends Entity {

	/**
	 * Creates an instance of a token.
	 * @param sprite The image of the token.
	 * @param x The x position of a token.
	 * @param y The y position of a token.
	 */
	public Token (Image sprite, int x, int y) {
		super(sprite, x, y);
	}

	/**
	 * If the token is touched then send it to GameController and change it to a null position;
	 */
	public void doTouched () {
		if (checkIfTouched()) {
			x = -100;
			y = -100;
			System.out.println("Token Collected");
			GameController.pickupToken();
		}
	}

	/**
	 * prints string to save to save file.
	 * @return string to save.
	 */
    public String toString() {
    	String result = "TOKEN " + x + " " + y + "\n";
    	return result;
    }
	
}