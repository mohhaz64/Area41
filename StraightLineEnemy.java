
public class StraightLineEnemy extends Enemy {
    private boolean moveInX;
    private boolean positiveDirection;

    public StraightLineEnemy(int xPosition, int yPosition, boolean moveInX,
	    boolean positiveDirection) {
	super(xPosition, yPosition);
	this.moveInX = moveInX;
	this.positiveDirection = positiveDirection;
    }

    public void getNextMove() {
	if (moveInX) {
	    if (positiveDirection) {
		// Moves right
		if (checkSpace(xPosition + 1, yPosition)) {
		    nextXPosition = xPosition++;
		} else {
		    // Turn around
		    positiveDirection = !positiveDirection;
		    getNextMove();
		}
	    } else {
		// Moves left
		if (checkSpace(xPosition - 1, yPosition)) {
		    nextXPosition = xPosition--;
		} else {
		    // Turn around
		    positiveDirection = !positiveDirection;
		    getNextMove();
		}
	    }
	} else {
	    if (positiveDirection) {
		// Moves up
		if (checkSpace(xPosition, yPosition + 1)) {
		    nextYPosition = yPosition++;
		} else {
		    // Turn around
		    positiveDirection = !positiveDirection;
		    getNextMove();
		}
	    } else {
		// Moves down
		if (checkSpace(xPosition, yPosition - 1)) {
		    nextYPosition = yPosition--;
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

}
