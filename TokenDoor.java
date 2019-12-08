import javafx.scene.image.Image;

public class TokenDoor extends Entity {
	
	protected int tokensRequired;
	
	public TokenDoor (Image sprite, int x, int y, int tokensRequired) {
		super(sprite, x, y);
		this.tokensRequired = tokensRequired;
	}

	public void doTouched () {
		if (checkIfTouched()) {
			x = -100;
			y = -100;
		}
	}
    
    public int getTokensRequired() {
    	return tokensRequired;
    }
    
    public String toString() {
    	String result = "DOOR " + x + " " + y + " TOKEN" + tokensRequired + "\n";
    	return result;
    }

}