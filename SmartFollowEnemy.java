import java.util.LinkedList;
import java.util.Queue;

public class SmartFollowEnemy extends Enemy {
    public SmartFollowEnemy(int xPosition, int yPosition) {
	super(xPosition, yPosition);
    }

    public void getNextMove() {
	Node enemyPosition = new Node(xPosition, yPosition, null);
	Queue<Node> nodesToCheck = new LinkedList<Node>();
	Queue<Node> checkedNodes = new LinkedList<Node>();
	nodesToCheck.add(enemyPosition);
	while (!(nodesToCheck.peek().getX() == game.playerX
		&& nodesToCheck.peek().getY() == game.playerY)
		&& !(nodesToCheck.isEmpty())) {
	    // Create node for each possible move
	    if (checkSpace(nodesToCheck.peek().getX() + 1,
		    nodesToCheck.peek().getY())) {
		Node nodeToAdd = new Node(nodesToCheck.peek().getX() + 1,
			nodesToCheck.peek().getY(), nodesToCheck.peek());
		if (!checkedNodes.contains(nodeToAdd)) {
		    nodesToCheck.add(nodeToAdd);
		}
	    }
	    if (checkSpace(nodesToCheck.peek().getX() - 1,
		    nodesToCheck.peek().getY())) {
		Node nodeToAdd = new Node(nodesToCheck.peek().getX() - 1,
			nodesToCheck.peek().getY(), nodesToCheck.peek());
		if (!checkedNodes.contains(nodeToAdd)) {
		    nodesToCheck.add(nodeToAdd);
		}
	    }
	    if (checkSpace(nodesToCheck.peek().getX(),
		    nodesToCheck.peek().getY() + 1)) {
		Node nodeToAdd = new Node(nodesToCheck.peek().getX(),
			nodesToCheck.peek().getY() + 1, nodesToCheck.peek());
		if (!checkedNodes.contains(nodeToAdd)) {
		    nodesToCheck.add(nodeToAdd);
		}
	    }
	    if (checkSpace(nodesToCheck.peek().getX(),
		    nodesToCheck.peek().getY() - 1)) {
		Node nodeToAdd = new Node(nodesToCheck.peek().getX(),
			nodesToCheck.peek().getY() - 1, nodesToCheck.peek());
		if (!checkedNodes.contains(nodeToAdd)) {
		    nodesToCheck.add(nodeToAdd);
		}
	    }

	    checkedNodes.add(nodesToCheck.poll());
	}
	if (nodesToCheck.isEmpty()) {
	    nextXPosition = xPosition;
	    nextYPosition = yPosition;
	} else {
	    Node nodeOnPath = nodesToCheck.peek();
	    while (nodeOnPath.getParent() != enemyPosition) {
		nodeOnPath = nodeOnPath.getParent();
	    }
	    nextXPosition = nodeOnPath.getX();
	    nextYPosition = nodeOnPath.getY();
	}
    }
}
