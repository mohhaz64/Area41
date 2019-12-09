import javafx.scene.image.Image;

public class Door extends Entity {
	
	protected String col = null;
	
	public Door (Image sprite, int x, int y, String colour) {
		super(sprite, x, y);
		this.col = colour;
	}

	public void doTouched () {
		if (checkIfTouched()) {
			x = -100;
			y = -100;
		}
	}
    
    public String getType() {
    	return col;
    }
    
    public String toString() {
    	String result = "DOOR " + x + " " + y + " " + col + "\n";
    	return result;
    }

}