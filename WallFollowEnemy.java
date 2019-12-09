import javafx.scene.image.Image;

/**
 * Enemy class that allows enemies to follow a wall.
 *
 * @author Ben Hyde
 * @version 1.0
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

    /**
     * Creates instance of a wall following enemy
     * @param sprite The image of an wall follow enemy
     * @param x The x position of a wall follow enemy
     * @param y The y position of a wall follow enemy
     * @param preferRight true if the enemy prefers to turn right.
     */
    public WallFollowEnemy(Image sprite, int x, int y, boolean preferRight) {
        super(sprite, x, y);
        this.preferRight = preferRight;
    }

    /**
     * Method to turn right.
     */
    public void turnRight() {
        if (direction == 3) {
            direction = 0;
        } else {
            direction = direction + 1;
        }
    }

    /**
     * Method to turn left.
     */
    public void turnLeft() {
        if (direction == 0) {
            direction = 3;
        } else {
            direction = direction - 1;
        }
    }

    /**
     * @return a new x position based on direction.
     */
    public int newX() {
        if (direction == 1) {
            return x + 1;
        } else if (direction == 3) {
            return x - 1;
        } else {
            return x;
        }
    }

    /**
     * @return a new y position based on direction.
     */
    public int newY() {
        if (direction == 0) {
            return y - 1;
        } else if (direction == 2) {
            return y + 1;
        } else {
            return y;
        }
    }

    /**
     * Finds out the enemy's next move based on what direction they are going and if they prefer to turn right.
     */
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

    /*
     * @see Entity#toString()
     */
    public String toString() {
        String result = "WALL " + x + " " + y + " " + preferRight + "\n";
        return result;
    }


}
