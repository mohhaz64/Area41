import javafx.scene.image.Image;

/**
 * Implements door entity.
 *
 * @author Ben Hyde, Bryan Khong
 * @version 1.0
 */
public class Door extends Entity {

    protected String col = null;

    /**
     * Sets door attributes such as position and sprite.
     *
     * @param sprite sprite image to set.
     * @param x      x coordinate of door entity.
     * @param y      y coordinate of tokendoor entity.
     * @param colour colour of key needed.
     */
    public Door(Image sprite, int x, int y, String colour) {
        super(sprite, x, y);
        this.col = colour;
    }

    /**
     * If the door is touched then change it to a null position;
     */
    public void doTouched() {
        if (checkIfTouched()) {
            x = -100;
            y = -100;
        }
    }

    /**
     * @return the type of door.
     */
    public String getType() {
        return col;
    }

    /**
     * prints string to save to save file.
     *
     * @return string to save.
     */
    public String toString() {
        String result = "DOOR " + x + " " + y + " " + col + "\n";
        return result;
    }

}