import javafx.scene.image.Image;

public class Fireboots extends Entity {

	public Fireboots (Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
	public void doTouched () {
		if (checkIfTouched()) {
			x = -100;
			y = -100;
			System.out.println("Fireboots Collected");
			GameController.pickUpFireboots();
		}
	}

	
    public String toString() {
    	String result = "FIREBOOTS " + x + " " + y + "\n";
    	return result;
    }


}