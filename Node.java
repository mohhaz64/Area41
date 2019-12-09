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
     * @return - Current value of X
     *
     *         Gets the current value of X
     */
    public int getX() {
        return x;
    }

    /**
     * @param x - New value of x
     *
     *          Sets x to a new value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return - Current value of y
     *
     *         Gets the current value of y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y - New value of y
     *
     *          Sets y to a new value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return Previous node in the shortest path from the enemy to this node
     *
     *         Gets the parent node of this node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * @param parent New parent of this node
     *
     *               Sets the parent of this node to a new node
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

}