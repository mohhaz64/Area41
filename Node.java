
public class Node {
    private int x;
    private int y;
    private Node parent;

    public Node(int x, int y, Node parent) {
	this.x = x;
	this.y = y;
	this.parent = parent;
    }

    public String toString() {
	return "Position: " + x + "," + y;
    }

    public int getX() {
	return x;
    }

    public void setX(int x) {
	this.x = x;
    }

    public int getY() {
	return y;
    }

    public void setY(int y) {
	this.y = y;
    }

    public Node getParent() {
	return parent;
    }

    public void setParent(Node parent) {
	this.parent = parent;
    }

}
