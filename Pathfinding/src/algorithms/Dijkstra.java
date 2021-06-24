package algorithms;

import java.util.PriorityQueue;

import graph.Node;
import graph.Node.Type;

public final class Dijkstra extends Pathfinder {

	public Dijkstra() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Node[][] grid) {
		
		this.queue  = new PriorityQueue<Node>();
		
		Node start = null;
		
		for(Node[] row: grid) {
			for(Node node: row) {
				//infinity doens't exist  for ints so use this instead
				node.setDistance(Integer.MAX_VALUE);
				node.setParent(null);
				
				if(node.getType() == Type.START) {
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
	public void step() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}

}
