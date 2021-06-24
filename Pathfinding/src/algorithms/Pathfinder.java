package algorithms;

import java.util.PriorityQueue;
import java.util.Queue;

import graph.Node;

public abstract class Pathfinder implements Pathfinding {
	
	//priority queue used in node search
	@SuppressWarnings("unused")
	private Queue<Node> queue = new PriorityQueue<Node>();

}
