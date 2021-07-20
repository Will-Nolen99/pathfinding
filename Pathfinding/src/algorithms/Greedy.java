package algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import algorithms.AStar.Priority;
import graph.Grid;
import graph.Node;
import graph.Node.Type;

public class Greedy extends Pathfinder {
	
	private Node finish;

	public Greedy() {

	}

	@Override
	public void start(Grid grid) {
		Node[][] cells = grid.getCells();

		Priority compare = new Priority();
		this.queue = new PriorityQueue<Node>(compare);

		Node start = null;
		Node finish = null;

		for (Node[] row : cells) {
			for (Node node : row) {
				// infinity doens't exist for ints so use this instead
				node.setGScore(Integer.MAX_VALUE);
				node.setFScore(Integer.MAX_VALUE);
				node.setParent(null);

				if (node.getType() == Type.START) {
					node.visit();
					start = node;

				}

				if (node.getType() == Type.TARGET) {
					finish = node;
					this.finish = node;
				}

			}
		}

		// This may cause an exception in the future if the run button is pressed when
		// the start node is not place on the grid
		// Will fix this later
		start.setFScore(grid.getHeuristic(start, finish));
		start.setGScore(0);

		this.queue.add(start);

	}

	@Override
	public boolean step(Grid grid) {
		if(this.queue.isEmpty()) {
			return true;
		}
		Node currentNode = this.queue.poll();
		
		grid.removePath();
		if(currentNode.getType() != Node.Type.START) this.finish(currentNode);
		
		ArrayList<Node> neighbors = grid.getNeightbors(currentNode);
		System.out.println(currentNode + "FS: " + currentNode.getFScore() + " GS: " + currentNode.getGScore());

		for (Node node : neighbors) {

			if (node.getType() != Node.Type.WALL && node.getType() != Node.Type.START) {
				
				if (node.getType() == Node.Type.TARGET) {
					node.setParent(currentNode);
					this.finish(node);
					return true;
				}
				
				
				int tempGScore = currentNode.getGScore() + 0;
				if (tempGScore < node.getGScore()) {
					node.setParent(currentNode);
					node.setGScore(tempGScore);
					node.setDistance(currentNode.getDistance() + 1);
					node.setType(Node.Type.SEARCH);
					node.setFScore (grid.getHeuristic(node, this.finish));

					if (!this.queue.contains(node)) {
						this.queue.add(node);
					}

				}
			}
		}

		return false;
	}

	@Override
	public void finish(Node node) {
		Node currentNode = node.getParent();
		System.out.println(currentNode.getDistance());
		

		
		while(currentNode.getParent() != null) {
			
			currentNode.setType(Node.Type.PATH);
			currentNode = currentNode.getParent();

		}

	}
	
	class Priority implements Comparator<Node> {

		@Override
		public int compare(Node node1, Node node2) {
			return node1.getFScore() - node2.getFScore();
		}

	}

}
