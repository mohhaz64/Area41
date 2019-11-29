import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

	public class Entity {
		
		/**
		 *  The x position of the Entity.
		 */
		protected int x;
	 	/**
	 	 * The y position of the Entity.
	 	 */
	 	protected int y;

		/**
		 *  The sprite of the Entity.
		 */
		protected Image sprite = new Image("Idle.png", 60, 60, false, false);

		/**
		 * Creates a closed Entity object.
		 * @param x The x position.
		 * @param y the y position.
		 * @param sprite The Image of the entity.
		 */
		protected Entity (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public String toString () {
			String result = "Its position is " + x + " " + y;
			return result;
		}
		
		/**
		 * Resets the x position.
		 */
	 	public void setX (int x) {
			this.x = x;
		}

	 	/**
	 	 * Resets the y position.
	 	 */
	 	public void setY (int y) {
			this.y = y;
		}

		 /**
		  * Resets the sprite.
		  */
		 public void setImage (Image sprite) {
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
		 
		 public void draw (GraphicsContext g) {
			//System.out.println("Entity method 'draw' needs to be overwritten");
			g.setFill(Color.RED);
		 	g.fillRect((x - GameController.playerX + 3) * GameController.getGridCellWidth(), (y - GameController.playerY + 3) * GameController.getGridCellHeight(), GameController.getGridCellWidth(), GameController.getGridCellHeight());
		 }
		 
		 public void checkIfTouched (GraphicsContext g) {
			//System.out.println("Entity method 'onTouched' needs to be overwritten");
		 }
	}