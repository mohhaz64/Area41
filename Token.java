import javafx.scene.image.Image;

public class Token extends Entity {

	public Token (Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
	public void doTouched () {
		if (checkIfTouched()) {
			x = -100;
			y = -100;
			System.out.println("Token Collected");
			GameController.pickupToken();
		}
	}
	
}