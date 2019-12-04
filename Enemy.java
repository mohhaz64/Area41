
public class Enemy {
    protected int xPosition;
    protected int yPosition;
    protected int nextXPosition;
    protected int nextYPosition;

    public Enemy(int xPosition, int yPosition) {
	this.xPosition = xPosition;
	this.yPosition = yPosition;
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
	if (spaceToCheckX > game.width) {
	    return false;
	}
	if (spaceToCheckY > game.height) {
	    return false;
	}
	if (game.map[spaceToCheckX][spaceToCheckY] = '#') {
	    return false;
	}
	if (game.map[spaceToCheckX][spaceToCheckY] = 'G') {
	    return false;
	}
	for (Entity entity : game.activeEntitys) {
	    if (entity.getX() == spaceToCheckX
		    && entity.getY() == spaceToCheckY) {
		return false;
	    }
	}
	return true;
    }

    public void makeMove() {
	xPosition = nextXPosition;
	yPosition = nextYPosition;
	getNextMove();
    }

    public boolean hasKilledPlayer() {
	if (game.playerX == xPosition && game.playerY == yPosition) {
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

    public void setNextYPosition(int nextYPosition) {
	this.nextYPosition = nextYPosition;
    }

    public int getxPosition() {
	return xPosition;
    }

    public void setxPosition(int xPosition) {
	this.xPosition = xPosition;
    }

    public int getyPosition() {
	return yPosition;
    }

    public void setyPosition(int yPosition) {
	this.yPosition = yPosition;
    }
}
