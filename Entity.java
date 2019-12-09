import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Entity {

    /**
     * The x position of the Entity.
     */
    protected int x;
    /**
     * The y position of the Entity.
     */
    protected int y;

    /**
     * The sprite of the Entity.
     */
    protected Image sprite = new Image("Player.png", 60, 60, false, false);

    /**
     * Creates a closed Entity object.
     *
     * @param x      The x position.
     * @param y      the y position.
     * @param sprite The Image of the entity.
     */
    protected Entity(Image sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public String toString() {

        String result = this.getClass().getName() + " " + x + " " + y + "\n";

        return result;
    }

    /**
     * Resets the x position.
     * @param x new x value.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Resets the y position.
     * @param y new y value.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Resets the sprite.
     * @param sprite the new sprite.
     */
    public void setImage(Image sprite) {
        this.sprite = sprite;
    }

    /**
     * @return The x position value.
     */
    public int getX() {
        return x;
    }

    /**
     * @return The y position value.
     */
    public int getY() {
        return y;
    }

    public String getType() {
        return null;
    }

    public int getTokensRequired() {
        return 0;
    }

    /**
     * @return The sprite image.
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Draw the entity onto the canvas.
     * @param g The current graphics context.
     * @param image The sprite image of the entity.
     */
    public void draw(GraphicsContext g, Image image) {

        double offsetX = 4.75;
        double offsetY = 2;

        double X = (x - GameController.getPlayerX() + offsetX) * GameController.getGridCellWidth();
        double Y = (y - GameController.getPlayerY() + offsetY) * GameController.getGridCellHeight();

        double XIso = xToIso(X, Y);
        double YIso = yToIso(X, Y);

        g.drawImage(image, XIso, YIso, GameController.getGridCellWidth(), GameController.getGridCellHeight());
    }

    /**
     * @return True if the entity is touched, otherwise false.
     */
    public boolean checkIfTouched() {
        if ((GameController.getPlayerX() == x) && (GameController.getPlayerY() == y)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Overidden by each entity subclass.
     */
    public void doTouched() {

    }

    /**
     * Overidden by the enemy class.
     * @return false unless overridden.
     */
    public boolean hasKilledPlayer() {
        return false;
    }

    /**
     * Converts the x position into an isometric version.
     * @param X currrent X position.
     * @param Y currrent Y position.
     * @return the new x value.
     */
    private double xToIso(double X, double Y) {
        return X - Y;
    }

    /**
     * Converts the y position into an isometric version.
     * @param X currrent X position.
     * @param Y currrent Y position.
     * @return the new Y value.
     */
    private double yToIso(double X, double Y) {
        return (X + Y) / 2;
    }
}