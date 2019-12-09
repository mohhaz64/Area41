import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author George
 * @version 1.0
 */
public class SmartFollowEnemy extends Enemy {
    /**
     * @param sprite - Image of the Smart-Follow Enemy
     * @param x      - X coordinate of the enemy on the game map
     * @param y      - Y coordinate of the enemy on the game map
     */
    public SmartFollowEnemy(Image sprite, int x, int y) {
        super(sprite, x, y);
    }

    /**
     * Finds the best next position in order to get to player using path finding.
     */
    public void getNextMove() {
        Node enemyPosition = new Node(x, y, null);
        Queue<Node> nodesToCheck = new Queue<Node>();
        ArrayList<Node> checkedNodes = new ArrayList<Node>();
        nodesToCheck.enqueue(enemyPosition);
        while (!(nodesToCheck.isEmpty()) && !(nodesToCheck.peek()
                .getX() == GameController.getPlayerX()
                && nodesToCheck.peek().getY() == GameController.getPlayerY())) {
            // Create node for each possible move
            if (checkSpace(nodesToCheck.peek().getX() + 1,
                    nodesToCheck.peek().getY())) {
                Node nodeToAdd = new Node(nodesToCheck.peek().getX() + 1,
                        nodesToCheck.peek().getY(), nodesToCheck.peek());
                if (!searchChecked(checkedNodes, nodeToAdd)) {
                    nodesToCheck.enqueue(nodeToAdd);
                }
            }
            if (checkSpace(nodesToCheck.peek().getX() - 1,
                    nodesToCheck.peek().getY())) {
                Node nodeToAdd = new Node(nodesToCheck.peek().getX() - 1,
                        nodesToCheck.peek().getY(), nodesToCheck.peek());
                if (!searchChecked(checkedNodes, nodeToAdd)) {
                    nodesToCheck.enqueue(nodeToAdd);
                }
            }
            if (checkSpace(nodesToCheck.peek().getX(),
                    nodesToCheck.peek().getY() + 1)) {
                Node nodeToAdd = new Node(nodesToCheck.peek().getX(),
                        nodesToCheck.peek().getY() + 1, nodesToCheck.peek());
                if (!searchChecked(checkedNodes, nodeToAdd)) {
                    nodesToCheck.enqueue(nodeToAdd);
                }
            }
            if (checkSpace(nodesToCheck.peek().getX(),
                    nodesToCheck.peek().getY() - 1)) {
                Node nodeToAdd = new Node(nodesToCheck.peek().getX(),
                        nodesToCheck.peek().getY() - 1, nodesToCheck.peek());
                if (!searchChecked(checkedNodes, nodeToAdd)) {
                    nodesToCheck.enqueue(nodeToAdd);
                }
            }
            checkedNodes.add(nodesToCheck.peek());
            nodesToCheck.dequeue();
        }
        if (nodesToCheck.isEmpty()) {
            Random r = new Random();
            int low = 1;
            int high = 4;
            int result = r.nextInt(high - low) + low;
            if (result == 1 && checkSpace(x + 1, y)) {
                nextXPosition = x + 1;
                nextYPosition = y;
            } else if (result == 2 && checkSpace(x - 1, y)) {
                nextXPosition = x - 1;
                nextYPosition = y;
            } else if (result == 3 && checkSpace(x, y + 1)) {
                nextXPosition = x;
                nextYPosition = y + 1;
            } else if (result == 3 && checkSpace(x, y - 1)) {
                nextXPosition = x;
                nextYPosition = y - 1;
            } else {
                nextXPosition = x;
                nextYPosition = y;
            }
        } else {
            Node nodeOnPath = nodesToCheck.peek();
            while (nodeOnPath.getParent() != enemyPosition) {
                nodeOnPath = nodeOnPath.getParent();
            }
            nextXPosition = nodeOnPath.getX();
            nextYPosition = nodeOnPath.getY();
        }
    }

    /**
     * @param checkedNodes - ArrayList of all nodes that have been checked by
     *                     the algorithm, with all possible movements from that
     *                     node having been added to the nodesToCheck queue
     * @param nodeToSearch - Node being searched for in the checkedNodes list
     * @return - True if the node is already present in the list, false if it is
     * not
     * <p>
     * Searches the checkedNodes list for a given node with the same
     * position (though not necessarily the same parent) as the node
     * being searched for
     */
    public boolean searchChecked(ArrayList<Node> checkedNodes, Node nodeToSearch) {
        for (Node nodeInList : checkedNodes) {
            if (nodeInList.getX() == nodeToSearch.getX()
                    && nodeInList.getY() == nodeToSearch.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see Entity#toString()
     */
    public String toString() {
        String result = "SMART " + x + " " + y + "\n";
        return result;
    }
}