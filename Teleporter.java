import javafx.scene.image.Image;

public class Teleporter extends Entity {
	protected int newX;
	protected int newY;
	
	public Teleporter(Image sprite, int x, int y, int newX, int newY) {
		super(sprite, x, y);
		this.newX = newX;
		this.newY = newY;
	}
	
	public void teleport() {
		if((GameController.playerX == x) && (GameController.playerY = y)) {
			GameController.playerX = newX;
			GameController.playerY = newY;
		} else if((GameController.playerX == newX) && (GameController.playerY == newY)) {
			GameController.playerX = x;
			GameController.playerY = y;
		}
	}
}
