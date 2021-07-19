package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import graph.Grid;
import graph.Node;
import graph.Node.Type;

public class DepthFirstSearch extends Pathfinder {

	public DepthFirstSearch() {

	}

	@Override
	public void start(Grid grid) {

		Node[][] cells = grid.getCells();

		// Use linked list as a stack for dfs
		this.stack = new Stack<Node>();

		Node start = null;

		for (Node[] row : cells) {
			for (Node node : row) {
				node.setParent(null);

				if (node.getType() == Type.START) {
					this.stack.push(node);
					start = node;
				}

			}
		}

	}

	@Override
	public boolean step(Grid grid) {

		Node currentNode = this.stack.pop();
		

		if(!currentNode.isVisited()) {
			currentNode.visit();
			if(currentNode.getType() != Node.Type.START) {
				currentNode.setType(Node.Type.SEARCH);
			}
			ArrayList<Node> neighbors = grid.getNeightbors(currentNode);
			for(Node node: neighbors) {

				if(!(node.getType() == Node.Type.WALL) && !node.isVisited()) {
					this.stack.push(node);
					node.setParent(currentNode);
					node.setDistance(currentNode.getDistance() + 1);
				}
				
				
				if (node.getType() == Node.Type.TARGET) {
					this.finish(node);
					return true;
				}
				
			}
			
			
		}
		
		

		return false;
	}

	@Override
	public void finish(Node node) {
		Node currentNode = node.getParent();

		while (currentNode.getParent() != null) {

			currentNode.setType(Node.Type.PATH);
			currentNode = currentNode.getParent();

		}

	}

}
