package graph;

import processing.core.PApplet;
import processing.core.PVector;

public class Node {

	enum Type {
		
		EMPTY,
		WALL,
		PATH,
		SEARCH,
		TARGET,
		START
		
	}
	
	private Type nodeType = Type.EMPTY;
	private PVector coords;
	private int size;
	
	public Node(int xCoord, int yCoord, int size) {
		this.coords = new PVector(xCoord, yCoord);
		this.size = size;
	}
	
	
	public void draw(PApplet win) {
		win.push();
		
		win.stroke(0);
		win.strokeWeight(1);
		win.fill(255);
		
		win.square(this.coords.x, this.coords.y, this.size);
		
		
		win.pop();
		
	}
	
	
	@Override
	public String toString() {
		return this.nodeType.toString();
	}
	
}
