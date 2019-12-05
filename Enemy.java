import javafx.scene.image.Image;

public class Enemy extends Entity {
    protected int nextXPosition;
    protected int nextYPosition;

    public Enemy(Image sprite, int x, int y) {
	super(sprite, x, y);
	nextXPosition = x;
	nextYPosition = y;
    }

    public boolean checkSpace(int spaceToCheckX, int spaceToCheckY) {
	// if space is not floor, player or out of array's bounds then true,
	// else false
	if (spaceToCheckX < 0) {
	    return false;
	}
	if (spaceToCheckY < 0) {
	    return false;
	}
	if (spaceToCheckX > GameController.width) {
	    return false;
	}
	if (spaceToCheckY > GameController.height) {
	    return false;
	}
	if (GameController.map[spaceToCheckX][spaceToCheckY]
		.equalsIgnoreCase("#")) {
	    return false;
	}
	if (GameController.map[spaceToCheckX][spaceToCheckY]
		.equalsIgnoreCase("G")) {
	    return false;
	}
	for (Entity entity : GameController.activeEntitys) {
	    if (entity.getX() == spaceToCheckX
		    && entity.getY() == spaceToCheckY) {
		return false;
	    }
	}
	return true;
    }

    public void makeMove() {
	x = nextXPosition;
	y = nextYPosition;
    }

    public void getNextMove() {
	nextXPosition = x;
	nextYPosition = y;
    }

    public boolean hasKilledPlayer() {
	if (GameController.playerX == x && GameController.playerY == y) {
	    return true;
	}
	return false;
    }

    public void setNextXPosition(int nextXPosition) {
	this.nextXPosition = nextXPosition;
    }

    public int getNextYPosition() {
	return nextYPosition;
    }

    public int getNextXPosition() {
	return nextXPosition;
    }

    public void setNextYPosition(int nextYPosition) {
	this.nextYPosition = nextYPosition;
    }

}
