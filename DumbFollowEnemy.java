import javafx.scene.image.Image;

public class DumbFollowEnemy extends Enemy {
    public DumbFollowEnemy(Image sprite, int xPosition, int yPosition) {
	super(sprite, xPosition, yPosition);
    }

    public void getNextMove() {
	if (Math.abs(GameController.playerX - x) > Math
		.abs(GameController.playerY - y)) {
	    // Player is closer in X direction
	    if (GameController.playerX - x == 0) {
		// Player is on the same vertical plane as enemy
		movingInY();
	    } else {
		movingInX();
	    }
	} else {
	    // Player is closer in Y direction
	    if (GameController.playerY - y == 0) {
		movingInX();
	    } else {
		movingInY();
	    }
	}
    }

    public void movingInX() {
	if (GameController.playerX > x) {
	    // Player is right of enemy
	    if (checkSpace(x + 1, y)) {
		nextXPosition = x + 1;
	    } else {
		nextXPosition = x;
	    }
	} else {
	    // Player is left of enemy
	    if (checkSpace(x - 1, y)) {
		nextXPosition = x - 1;
	    } else {
		nextXPosition = x;
	    }
	}
    }

    public void movingInY() {
	if (GameController.playerY > y) {
	    // Player is below enemy
	    if (checkSpace(x, y - 1)) {
		nextYPosition = y - 1;
	    } else {
		nextYPosition = y;
	    }
	} else {
	    // Player is above enemy
	    if (checkSpace(x, y + 1)) {
		nextYPosition = y + 1;
	    } else {
		nextYPosition = y;
	    }
	}
    }
    
    public String toString() {
    	String result = "DUMB " + x + " " + y + "\n";
    	return result;
    }
}
