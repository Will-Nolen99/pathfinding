package algorithms;

import graph.Grid;
import graph.Node;

public interface Pathfinding {
	
	//Begin the search
	public void start(Grid grid);
	
	//iterate one step of the algorithm
	public boolean step(Grid grid);
	
	//signals the end of the algorithm
	public void finish(Node node);
	
}
