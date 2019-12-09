import javafx.scene.image.Image;

/**
 * Implements token door entity.
 * @author Ben Hyde, Bryan Khong
 * @version 1.0
 */
public class TokenDoor extends Entity {

	protected int tokensRequired;

	/**
	 * Sets tokendoor attributes such as position and sprite.
	 * @param sprite sprite image to set.
	 * @param x x coordinate of tokendoor entity.
	 * @param y y coordinate of tokendoor entity.
	 * @param tokensRequired number of tokens needed to open a door
	 */
	public TokenDoor (Image sprite, int x, int y, int tokensRequired) {
		super(sprite, x, y);
		this.tokensRequired = tokensRequired;
	}

	/**
	 * If the tokendoor is touched then change it to a null position;
	 */
	public void doTouched () {
		if (checkIfTouched()) {
			x = -100;
			y = -100;
		}
	}

	/**
	 * @return the number of tokens required.
	 */
	public int getTokensRequired() {
		return tokensRequired;
	}

	/**
	 * prints string to save to save file.
	 * @return string to save.
	 */
	public String toString() {
		String result = "DOOR " + x + " " + y + " TOKEN" + tokensRequired + "\n";
		return result;
	}

}