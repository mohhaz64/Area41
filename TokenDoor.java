import javafx.scene.image.Image;

public class TokenDoor extends Entity {
	protected boolean opened = false;
	
	public TokenDoor (Image sprite, int x, int y) {
		super(sprite, x, y);
	}
	
	public boolean checkIfOpenable() {
		if((GameController.playerX == x) && (GameController.playerY == y)) {
			if(GameController.totalTokens > 0) {
				opened = true;
				GameController.totalTokens--;
				x = -100;
				y = -100;
				System.out.println("Door opened!");
				return true;
			} 
			else {
				GameController.playerX = GameController.playerX - 1;
				GameController.playerY = GameController.playerY - 1;
				System.out.println("Sorry not enough tokens. The door is still locked.");
				return false;
			}
		} else {
			return false;
		}
	}
}
