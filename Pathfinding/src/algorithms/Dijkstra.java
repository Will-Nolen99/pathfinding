
package algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import graph.Grid;
import graph.Node;
import graph.Node.Type;

public final class Dijkstra extends Pathfinder {

	public Dijkstra() {
		
	}

	@Override
	public void start(Grid grid) {
		
		Node[][] cells = grid.getCells();
		
		
		Priority compare = new Priority();
		this.queue  = new PriorityQueue<Node>(compare);
		
		Node start = null;
		
		for(Node[] row: cells) {
			for(Node node: row) {
				//infinity doens't exist  for ints so use this instead
				node.setDistance(Integer.MAX_VALUE);
				node.setParent(null);
				
				if(node.getType() == Type.START) {
					
					node.visit();
					start = node;
				}
				
			}
		}
		
		// This may cause an exception in the future if the run button is pressed when the start node is not place on the grid
		// Will fix this later
		start.setDistance(0);
		
		this.queue.add(start);
		
		
	}

	@Override
	public boolean step(Grid grid) {
		
		
		//Get node from priority Queue
		
		if(this.queue.size() == 0) {
			return true;
		}
		
		Node currentNode = this.queue.poll();
		ArrayList<Node> neighbors = grid.getNeightbors(currentNode);
		
		for(Node node: neighbors) {
			if(!node.isVisited() && node.getType() != Node.Type.WALL) {
				node.visit();
				node.setDistance(currentNode.getDistance() + 1); //because in this program each distance increases by one, no reason to see if a better path has been found.
				node.setParent(currentNode);
				
				
				if(node.getType() == Node.Type.TARGET) {
					this.finish(node);
					return true;
				}
				node.setType(Node.Type.SEARCH);
				
				this.queue.add(node);
			}
		}
		
		return false;
		
	}

	@Override
	public void finish(Node node) {
	
		Node currentNode = node.getParent();
		

		
		while(currentNode.getParent() != null) {
			
			currentNode.setType(Node.Type.PATH);
			currentNode = currentNode.getParent();

		}
		

		
		
		
	}

}

class Priority implements Comparator<Node>{

	@Override
	public int compare(Node node1, Node node2) {
		return node1.getDistance() - node2.getDistance();
	}
	
}




