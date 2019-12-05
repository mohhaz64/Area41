
public class WallFollowEnemy extends Enemy {
    private boolean positiveDirection;
    private int lastX;
    private int lastY;

    public WallFollowEnemy(int xPosition, int yPosition) {
	super(xPosition, yPosition);
	positiveDirection = true;
    }

    public void getNextMove() {
	if (!checkSpace(xPosition + 1, yPosition)
		&& !checkSpace(xPosition - 1, yPosition)
		&& !checkSpace(xPosition, yPosition + 1)
		&& !checkSpace(xPosition, yPosition - 1)) {
	    // Nowhere for enemy to go
	    setLastPosition();
	    nextXPosition = xPosition;
	    nextYPosition = yPosition;
	} else if (!checkSpace(xPosition - 1, yPosition)
		&& !checkSpace(xPosition, yPosition + 1)
		&& !checkSpace(xPosition, yPosition - 1)) {
	    // Enemy can only move right
	    setLastPosition();
	    nextXPosition = xPosition + 1;
	    nextYPosition = yPosition;
	} else if (!checkSpace(xPosition + 1, yPosition)
		&& !checkSpace(xPosition, yPosition + 1)
		&& !checkSpace(xPosition, yPosition - 1)) {
	    // Enemy can only move left
	    setLastPosition();
	    nextXPosition = xPosition - 1;
	    nextYPosition = yPosition;
	} else if (!checkSpace(xPosition + 1, yPosition)
		&& !checkSpace(xPosition - 1, yPosition)
		&& !checkSpace(xPosition, yPosition - 1)) {
	    // Enemy can only move down
	    setLastPosition();
	    nextXPosition = xPosition;
	    nextYPosition = yPosition + 1;
	} else if (!checkSpace(xPosition + 1, yPosition)
		&& !checkSpace(xPosition - 1, yPosition)
		&& !checkSpace(xPosition, yPosition + 1)) {
	    // Enemy can only move up
	    setLastPosition();
	    nextXPosition = xPosition;
	    nextYPosition = yPosition - 1;
	} else if (checkSpace(xPosition + 1, yPosition)
		&& checkSpace(xPosition - 1, yPosition)) {
	    // Enemy can only move left or right
	    if (lastX == xPosition + 1) {
		setLastPosition();
		nextXPosition = xPosition - 1;
	    } else {
		setLastPosition();
		nextXPosition = xPosition + 1;
	    }
	} else if (checkSpace(xPosition, yPosition + 1)
		&& checkSpace(xPosition, yPosition - 1)) {
	    // Enemy can only move up or down
	    if (lastY == yPosition + 1) {
		setLastPosition();
		nextYPosition = yPosition - 1;
	    } else {
		setLastPosition();
		nextYPosition = yPosition + 1;
	    }
	}
	// TODO Need fresh eyes on if there's a better way to do this, if not
	// then need to add behaviour for remaining possibilities

    }

    public void setLastPosition() {
	lastX = xPosition;
	lastY = yPosition;
    }
}