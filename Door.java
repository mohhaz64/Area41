import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
public class Door extends Entity {
	protected int size = 40;
	protected Image sprite;
	protected boolean open = false;
	protected String col = null;
	
	public Door (Image sprite, int x, int y, String colour) {
		super(sprite, x, y);
		if(colour.equalsIgnoreCase("blue")) {
			sprite = new Image("Blue Door.png", size, size, false, false);
		} else if(colour.equalsIgnoreCase("red")) {
			sprite = new Image("Red Door.png", size, size, false, false);
		} else if(colour.equalsIgnoreCase("yellow")) {
			sprite = new Image("Yellow Door.png", size, size, false, false);
		}
		this.col = colour;
	}
	public void draw(GraphicsContext g) {
		if(open == false) {
			g.drawImage(sprite, (x - GameController.playerX + 3) * GameController.getGridCellWidth() + (GameController.getGridCellWidth() - size)/2, (y - GameController.playerY + 3) * GameController.getGridCellHeight() + (GameController.getGridCellHeight() - size)/2);
		}
	}
	
	public boolean isOpen() {
		return open;
	}
	public void openDoor() {
		open = true;
	}
	public void closeDoor() {
		open = false;
	}
}