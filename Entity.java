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
	String result = "Entity " + x + " " + y + "\n";
	return result;
    }

    /**
     * Resets the x position.
     */
    public void setX(int x) {
	this.x = x;
    }

    /**
     * Resets the y position.
     */
    public void setY(int y) {
	this.y = y;
    }

    /**
     * Resets the sprite.
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
    
    /**
     * @return The sprite image.
     */
    public Image getSprite() {
    return sprite;
    }

    public void draw(GraphicsContext g, Image image) {

    	double offsetX = 4.75;
		double offsetY = 2;
		
		double X = (x - GameController.playerX + offsetX) * GameController.getGridCellWidth();
		double Y = (y - GameController.playerY + offsetY) * GameController.getGridCellHeight();
		
		double XIso = xToIso(X,Y);
		double YIso = yToIso(X,Y);
			
		g.drawImage(image, XIso, YIso, GameController.getGridCellWidth(), GameController.getGridCellHeight());
    }

    public boolean checkIfTouched() {
    	if ((GameController.playerX == x) && (GameController.playerY == y)) {
			return true;
		}
		else {
			return false;
		}
	}
    
    public void doTouched() {

	}
    
    public boolean hasKilledPlayer() {
    	return false;
    }
    
    private double xToIso(double X, double Y) {
    	return X - Y;
    }
    
    private double yToIso(double X, double Y) {
    	return (X + Y) / 2;
    }
}