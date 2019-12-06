import javafx.scene.image.Image;

public class Door extends Entity {
	
	protected String col = null;
	
	public Door (Image sprite, int x, int y, String colour) {
		super(sprite, x, y);
		this.col = colour;
	}

    public boolean isTouched() {
	if (checkIfTouched()) {
	    if (col == "blue") {
		return GameController.isCollectedBlue();
	    }
	    if (col == "red") {
		return GameController.isCollectedRed();
	    }
	    if (col == "yellow") {
		return GameController.isCollectedYellow();
	    } else {
		return false;
	    }
	} else {
	    return false;
	}
    }

}