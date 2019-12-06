import javafx.scene.image.Image;

public class Door extends Entity {
    protected int size = 40;
    protected static Image sprite;
    protected boolean opened = false;
    protected String col = null;

    public Door(Image sprite, int x, int y, String colour) {
	super(sprite, x, y);
	if (colour.equalsIgnoreCase("blue")) {
	    sprite = new Image("Blue Door.png", size, size, false, false);
	} else if (colour.equalsIgnoreCase("red")) {
	    sprite = new Image("Red Door.png", size, size, false, false);
	} else if (colour.equalsIgnoreCase("yellow")) {
	    sprite = new Image("Yellow Door.png", size, size, false, false);
	}
	this.col = colour;
    }

    public boolean checkIfTouched() {
	if ((GameController.playerX == x) && (GameController.playerY == y)) {
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