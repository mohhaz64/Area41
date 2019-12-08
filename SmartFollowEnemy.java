import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public class SmartFollowEnemy extends Enemy {
    public SmartFollowEnemy(Image sprite, int x, int y) {
	super(sprite, x, y);
    }

    public void getNextMove() {
	Node enemyPosition = new Node(x, y, null);
	Queue<Node> nodesToCheck = new Queue<Node>();
	ArrayList<Node> checkedNodes = new ArrayList<Node>();
	nodesToCheck.enqueue(enemyPosition);
	while (!(nodesToCheck.isEmpty()) && !(nodesToCheck.peek()
		.getX() == GameController.playerX
		&& nodesToCheck.peek().getY() == GameController.playerY)) {
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

    public boolean searchChecked(ArrayList<Node> checkedNodes,
	    Node nodeToSearch) {
	for (Node nodeInList : checkedNodes) {
	    if (nodeInList.getX() == nodeToSearch.getX()
		    && nodeInList.getY() == nodeToSearch.getY()) {
		return true;
	    }
	}
	return false;
    }
    
    public String toString() {
    	String result = "SMART " + x + " " + y + "\n";
    	return result;
    }
}