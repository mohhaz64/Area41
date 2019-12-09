import javafx.scene.image.Image;

/**
 * @author George
 * @version 1.0
 */
public class StraightLineEnemy extends Enemy {
    private boolean moveInX;
    private boolean positiveDirection;

    /**
     * @param sprite            - Image for the Straight-Line Enemy
     * @param x                 - X coordinate of the Enemy on the game map
     * @param y                 - Y coordinate of the Enemy on the game map
     * @param moveInX           - True if the enemy moves in the x direction,
     *                          false if it moves in the y direction
     * @param positiveDirection - True if the enemy moves in a positive
     *                          direction (up or right) at the start of the
     *                          level, false if it moves in a negative direction
     *                          (down or left) at the start of the level
     */
    public StraightLineEnemy(Image sprite, int x, int y, boolean moveInX,
                             boolean positiveDirection) {
        super(sprite, x, y);
        this.moveInX = moveInX;
        this.positiveDirection = positiveDirection;
    }

    /**
     * Moves one position in x or y direction.
     */
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

    /**
     * @return - Current value of moveInX
     * <p>
     * Gets the current value of moveInX
     */
    public boolean isMoveInX() {
        return moveInX;
    }

    /**
     * @param moveInX - New value for moveInX
     *                <p>
     *                Sets moveInX to a new value
     */
    public void setMoveInX(boolean moveInX) {
        this.moveInX = moveInX;
    }

    /**
     * @return - Current value of positiveDirection
     * <p>
     * Gets the current value of positiveDirection
     */
    public boolean isPositiveDirection() {
        return positiveDirection;
    }

    /**
     * @param positiveDirection - New value for positiveDirection
     *                          <p>
     *                          Sets positiveDirection to a new value
     */
    public void setPositiveDirection(boolean positiveDirection) {
        this.positiveDirection = positiveDirection;
    }


    /*
     * @see Entity#toString()
     */
    public String toString() {
        String result = "STRAIGHT " + x + " " + y + " " + moveInX + " "
                + positiveDirection + "\n";
        return result;
    }

}
