import javafx.scene.image.Image;

/**
 * Implements fire entity.
 *
 * @author Ben Hyde, Bryan Khong
 * @version 1.0
 */
public class Fire extends Entity {

    /**
     * sets fire attributes such as position and sprite.
     *
     * @param sprite sprite image to set.
     * @param x      x coordinate of fire entity.
     * @param y      y coordinate of fire entity.
     */
    public Fire(Image sprite, int x, int y) {
        super(sprite, x, y);
    }

    /**
     * checks if the fire has killed player if the player doesnt have the fire boots.
     *
     * @return true if killed else false.
     */
    public boolean hasKilledPlayer() {
        if (checkIfTouched() && !GameController.isCollectedFireBoots()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * prints string to save to save file.
     *
     * @return string to save.
     */
    public String toString() {
        String result = "FIRE " + x + " " + y + "\n";
        return result;
    }
}
