import javafx.scene.image.Image;

/**
 * Class represents the Dumb-Follow Enemy type.
 *
 * @author George
 * @version 1.0
 */
public class DumbFollowEnemy extends Enemy {
    /**
     * @param sprite    - Image for the enemy
     * @param xPosition - X coordinate of the enemy on the game map
     * @param yPosition - X coordinate of the enemy on the game map
     */
    public DumbFollowEnemy(Image sprite, int xPosition, int yPosition) {
        super(sprite, xPosition, yPosition);
    }

    /**
     * Moves one step closer to the player.
     */
    public void getNextMove() {
        if (Math.abs(GameController.getPlayerX() - x) > Math
                .abs(GameController.getPlayerY() - y)) {
            // Player is closer in X direction
            if (GameController.getPlayerX() - x == 0) {
                // Player is on the same vertical plane as enemy
                movingInY();
            } else {
                movingInX();
            }
        } else {
            // Player is closer in Y direction
            if (GameController.getPlayerY() - y == 0) {
                movingInX();
            } else {
                movingInY();
            }
        }
    }

    /**
     * Decides which direction (left or right) the enemy should move if the
     * player is closer to the enemy in the x direction than the y direction.
     */
    public void movingInX() {
        if (GameController.getPlayerX() > x) {
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

    /**
     * Decides which direction (left or right) the enemy should move if the
     * player is closer to the enemy in the x direction than the y direction.
     */
    public void movingInY() {
        if (GameController.getPlayerY() > y) {
            // Player is below enemy
            if (checkSpace(x, y + 1)) {
                nextYPosition = y + 1;
            } else {
                nextYPosition = y;
            }
        } else {
            // Player is above enemy
            if (checkSpace(x, y - 1)) {
                nextYPosition = y - 1;
            } else {
                nextYPosition = y;
            }
        }
    }

    /**
     * @see Entity#toString()
     */
    public String toString() {
        String result = "DUMB " + x + " " + y + "\n";
        return result;

    }
}
