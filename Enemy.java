import javafx.scene.image.Image;

/**
 * @author George
 * @version 1.0
 */
public class Enemy extends Entity {
    protected int nextXPosition;
    protected int nextYPosition;

    /**
     * @param sprite - Default image of the Enemy
     * @param x      - X coordinate of the Enemy on the game map
     * @param y      - Y coordinate of the Enemy on the game map
     */
    public Enemy(Image sprite, int x, int y) {
        super(sprite, x, y);
        nextXPosition = x;
        nextYPosition = y;
    }

    /**
     * @param spaceToCheckX - X coordinate of the space being checked
     * @param spaceToCheckY - Y coordinate of the space being checked
     * @return - True if the Enemy can move onto the given space, false if it
     * cannot
     * <p>
     * Decides if an enemy is able to move onto a given space on the
     * game map
     */
    public boolean checkSpace(int spaceToCheckX, int spaceToCheckY) {
        // if space is not floor, player or out of array's bounds then true,
        // else false
        if (spaceToCheckX < 0) {
            return false;
        }
        if (spaceToCheckY < 0) {
            return false;
        }
        if (spaceToCheckX >= GameController.getWidth()) {
            return false;
        }
        if (spaceToCheckY >= GameController.getHeight()) {
            return false;
        }
        if (GameController.getMapValue(spaceToCheckX, spaceToCheckY)
                .equalsIgnoreCase("#")) {
            return false;
        }
        if (GameController.getMapValue(spaceToCheckX, spaceToCheckY)
                .equalsIgnoreCase("G")) {
            return false;
        }
        for (Entity entity : GameController.getActiveEntitys()) {
            if (entity.getX() == spaceToCheckX
                    && entity.getY() == spaceToCheckY) {
                return false;
            }
        }
        return true;
    }

    /**
     * Moves the enemy onto the space they are going to move to next, as decided
     * by their movement algorithm
     */
    public void makeMove() {
        x = nextXPosition;
        y = nextYPosition;
    }

    /**
     * Decides where the enemy is going to move to the next time the player
     * makes a move
     */
    public void getNextMove() {
        nextXPosition = x;
        nextYPosition = y;
    }

    /**
     * @see Entity#hasKilledPlayer()
     */
    public boolean hasKilledPlayer() {

        if (checkIfTouched()) {

            return true;
        }
        return false;
    }

    /**
     * @param nextXPosition - New value for nextXPosition
     *                      <p>
     *                      Sets nextXPosition to a given value
     */
    public void setNextXPosition(int nextXPosition) {
        this.nextXPosition = nextXPosition;
    }

    /**
     * @return - Current value of nextYPosition
     * <p>
     * Gets the current value of nextYPosition
     */
    public int getNextYPosition() {
        return nextYPosition;
    }

    /**
     * @return - Current value of nextXPosition
     * <p>
     * Gets the current value of nextXPosition
     */
    public int getNextXPosition() {
        return nextXPosition;
    }

    /**
     * @param nextYPosition - New value for nextXPosition
     *                      <p>
     *                      Sets nextXPosition to a given value
     */
    public void setNextYPosition(int nextYPosition) {
        this.nextYPosition = nextYPosition;
    }

}
