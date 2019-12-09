public class Node {
    /**
     * @author George
     * @version 1.0
     */
    private int x;
    private int y;
    private Node parent;

    /**
     * @param x      - X coordinate of the space on the game map the node
     *               corresponds to
     * @param y      - Y coordinate of the space on the game map the node
     *               corresponds to
     * @param parent - Previous node in the shortest path from the enemy to this
     *               node
     */
    public Node(int x, int y, Node parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Position: " + x + "," + y;
    }

    /**
     * Gets the current value of X
     * @return - Current value of X
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x to a new value
     * @param x - New value of x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the current value of y
     * @return - Current value of y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y to a new value
     * @param y - New value of y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the parent node of this node
     * @return Previous node in the shortest path from the enemy to this node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent of this node to a new node
     * @param parent New parent of this node
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

}