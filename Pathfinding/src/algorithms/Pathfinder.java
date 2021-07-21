package algorithms;

import java.util.Queue;
import java.util.Stack;

import graph.Node;

public abstract class Pathfinder implements Pathfinding {
	
	//priority queue used in node search
	protected Queue<Node> queue;
	
	protected Stack<Node> stack;
	protected boolean trace;

}
