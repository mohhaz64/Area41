import javafx.scene.image.Image;

public class Flippers extends Entity {

	public Flippers (Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
	public void doTouched () {
		if (checkIfTouched()) {
			x = -100;
			y = -100;
			System.out.println("Flippers Collected");
			GameController.pickupFlippers();
		}
	}

	
    public String toString() {
    	String result = "FLIPPERS " + x + " " + y + "\n";
    	return result;
    }


}