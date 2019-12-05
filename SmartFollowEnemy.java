import java.util.ArrayList;

public class SmartFollowEnemy extends Enemy {
    public SmartFollowEnemy(int x, int y) {
	super(x, y);
    }

    public void getNextMove() {
	Node enemyPosition = new Node(x, y, null);
	Queue<Node> nodesToCheck = new Queue<Node>();
	ArrayList<Node> checkedNodes = new ArrayList<Node>();
	nodesToCheck.enqueue(enemyPosition);
	System.out.println(
		"Node added to check queue: " + enemyPosition.toString());
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
		    System.out.println("Node added to check queue: "
			    + nodeToAdd.toString());
		}
	    }
	    if (checkSpace(nodesToCheck.peek().getX() - 1,
		    nodesToCheck.peek().getY())) {
		Node nodeToAdd = new Node(nodesToCheck.peek().getX() - 1,
			nodesToCheck.peek().getY(), nodesToCheck.peek());
		if (!searchChecked(checkedNodes, nodeToAdd)) {
		    nodesToCheck.enqueue(nodeToAdd);
		    System.out.println("Node added to check queue: "
			    + nodeToAdd.toString());
		}
	    }
	    if (checkSpace(nodesToCheck.peek().getX(),
		    nodesToCheck.peek().getY() + 1)) {
		Node nodeToAdd = new Node(nodesToCheck.peek().getX(),
			nodesToCheck.peek().getY() + 1, nodesToCheck.peek());
		if (!searchChecked(checkedNodes, nodeToAdd)) {
		    nodesToCheck.enqueue(nodeToAdd);
		    System.out.println("Node added to check queue: "
			    + nodeToAdd.toString());
		}
	    }
	    if (checkSpace(nodesToCheck.peek().getX(),
		    nodesToCheck.peek().getY() - 1)) {
		Node nodeToAdd = new Node(nodesToCheck.peek().getX(),
			nodesToCheck.peek().getY() - 1, nodesToCheck.peek());
		if (!searchChecked(checkedNodes, nodeToAdd)) {
		    nodesToCheck.enqueue(nodeToAdd);
		    System.out.println("Node added to check queue: "
			    + nodeToAdd.toString());
		}
	    }
	    System.out.println(
		    "Node added to checkedNodes: " + nodesToCheck.peek());
	    checkedNodes.add(nodesToCheck.peek());
	    nodesToCheck.dequeue();
	}
	if (nodesToCheck.isEmpty()) {
	    nextXPosition = x;
	    nextYPosition = y;
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
}