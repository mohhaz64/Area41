
public class DumbFollowEnemy extends Enemy {
    public DumbFollowEnemy(int xPosition, int yPosition) {
	super(xPosition, yPosition);
    }

    public void getNextMove() {
	if (Math.abs(game.playerX - xPosition) > Math
		.abs(game.playerY - yPosition)) {
	    // Player is closer in X direction
	    if (game.playerX - xPosition == 0) {
		// Player is on the same vertical plane as enemy
		movingInY();
	    } else {
		movingInX();
	    }
	} else {
	    // Player is closer in Y direction
	    if (game.playerY - yPosition == 0) {
		movingInX();
	    } else {
		movingInY();
	    }
	}
    }

    public void movingInX() {
	if (game.playerX > xPosition) {
	    // Player is right of enemy
	    if (checkSpace(xPosition++, yPosition)) {
		nextXPosition = xPosition++;
	    } else {
		nextXPosition = xPosition;
	    }
	} else {
	    // Player is left of enemy
	    if (checkSpace(xPosition--, yPosition)) {
		nextXPosition = xPosition--;
	    } else {
		nextXPosition = xPosition;
	    }
	}
    }

    public void movingInY() {
	if (game.playerY > yPosition) {
	    // Player is below enemy
	    if (checkSpace(xPosition, yPosition--)) {
		nextYPosition = yPosition--;
	    } else {
		nextYPosition = yPosition;
	    }
	} else {
	    // Player is above enemy
	    if (checkSpace(xPosition, yPosition++)) {
		nextYPosition = yPosition++;
	    } else {
		nextYPosition = yPosition;
	    }
	}
    }
}
