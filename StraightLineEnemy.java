import javafx.scene.image.Image;

public class StraightLineEnemy extends Enemy {
    private boolean moveInX;
    private boolean positiveDirection;

    public StraightLineEnemy(Image sprite, int x, int y, boolean moveInX,
	    boolean positiveDirection) {
	super(sprite, x, y);
	this.moveInX = moveInX;
	this.positiveDirection = positiveDirection;
    }

    public void getNextMove() {
	if (moveInX) {
	    if (positiveDirection) {
		// Moves right
		if (checkSpace(x + 1, y)) {
		    nextXPosition = x + 1;
		} else {
		    // Turn around
		    positiveDirection = !positiveDirection;
		    getNextMove();
		}
	    } else {
		// Moves left
		if (checkSpace(x - 1, y)) {
		    nextXPosition = x - 1;
		} else {
		    // Turn around
		    positiveDirection = !positiveDirection;
		    getNextMove();
		}
	    }
	} else {
	    if (positiveDirection) {
		// Moves up
		if (checkSpace(x, y - 1)) {
		    nextYPosition = y - 1;
		} else {
		    // Turn around
		    positiveDirection = !positiveDirection;
		    getNextMove();
		}
	    } else {
		// Moves down
		if (checkSpace(x, y + 1)) {
		    nextYPosition = y + 1;
		} else {
		    // Turn around
		    positiveDirection = !positiveDirection;
		    getNextMove();
		}
	    }
	}
    }

    public boolean isMoveInX() {
	return moveInX;
    }

    public void setMoveInX(boolean moveInX) {
	this.moveInX = moveInX;
    }

    public boolean isPositiveDirection() {
	return positiveDirection;
    }

    public void setPositiveDirection(boolean positiveDirection) {
	this.positiveDirection = positiveDirection;
    }
    
    public String toString() {
    	String result = "STRAIGHT " + x + " " + y + " " + moveInX + " " + positiveDirection + "\n";
    	return result;
    }

}
