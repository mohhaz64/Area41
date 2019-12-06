import javafx.scene.image.Image;

public class Key extends Entity {
	
	protected String col = null;
	
	public Key (Image sprite, int x, int y, String colour) {
		super(sprite, x, y);
		this.col = colour;
	}
	
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
	
}
