package algorithms;

import graph.Node;

public interface Pathfinding {
	
	//Begin the search
	public void start(Node[][] grid);
	
	//iterate one step of the algorithm
	public void step();
	
	//signals the end of the algorithm
	public void finish();
	
}
