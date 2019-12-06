import javafx.scene.image.Image;

/**
 * Enemy class that allows enemies to follow a wall.
 * @author Ben Hyde
 */
public class WallFollowEnemy extends Enemy {
    private boolean preferRight;
    private int direction;
    private boolean foundMove;
    /* 
    direction = 0 is up
    direction = 1 is right   
    direction = 2 is down
    direction = 3 is left
    */

    public WallFollowEnemy(Image sprite, int x, int y, boolean preferRight) {
	super(sprite, x, y);
	this.preferRight = preferRight;
    }
    
    public void turnRight() {
    	if (direction == 3) {
    		direction = 0;
    	}
    	else {
    		direction = direction + 1;
    	}
    }
    
    public void turnLeft() {
    	if (direction == 0) {
    		direction = 3;
    	}
    	else {
    		direction = direction - 1;
    	}
    }
    
    public int newX() {
    	if (direction == 1) {
    		return x+1;
    	}
    	else if (direction == 3) {
    		return x-1;
    	}
    	else {
    		return x;
    	}
    }
    
    public int newY() {
    	if (direction == 0) {
    		return y-1;
    	}
    	else if (direction == 2) {
    		return y+1;
    	}
    	else {
    		return y;
    	}
    }

    public void getNextMove() {
    	foundMove = false;
		if (preferRight) {
			turnRight();
			for (int i = 0; i < 4 && foundMove == false; i++) {
			    if (checkSpace(newX(), newY())) {
			    	nextXPosition = newX();
			    	nextYPosition = newY();
			    	foundMove = true;
			    } else {
			    	turnLeft();
			    }
			}
		} else {
			turnLeft();
			for (int i = 0; i < 4 && foundMove == false; i++) {
			    if (checkSpace(newX(), newY())) {
			    	nextXPosition = newX();
			    	nextYPosition = newY();
			    	foundMove = true;
			    } else {
			    	turnRight();
			    }
			}
		}
    }

}
