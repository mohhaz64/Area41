import javafx.scene.image.Image;

/**
 * Implements teleporter entity.
 *
 * @author Mo Hazrati, Ben Hyde
 * @version 1.0
 */
public class Teleporter extends Entity {
    private int newX;
    private int newY;

    /**
     * Sets teleporter attirubutes to respective values.
     *
     * @param sprite the image sprite.
     * @param x      x position of teleporter.
     * @param y      y position of teleporter.
     * @param newX   x positon of place to move to.
     * @param newY   y position of place to move to.
     */
    public Teleporter(Image sprite, int x, int y, int newX, int newY) {
        super(sprite, x, y);
        this.newX = newX;
        this.newY = newY;
    }

    /**
     * Telports player to teleporter link if player steps on teleporter.
     */
    public void doTouched() {
        if (checkIfTouched()) {
            GameController.setPlayerX(newX);
            GameController.setPlayerY(newY);
        }
    }

    /**
     * Prints teleporter configuration to save file.
     *
     * @return teleporter configuration as string.
     */
    public String toString() {
        String result = "TELEPORTER " + x + " " + y + " " + newX + " " + newY + "\n";
        return result;
    }
}

