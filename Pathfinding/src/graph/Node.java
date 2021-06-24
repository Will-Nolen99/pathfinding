package graph;


import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PVector;


//Class representing a node of the graph
public class Node {

	//Enum contains all possibilities for node types
	public enum Type {

		EMPTY, WALL, PATH, SEARCH, TARGET, START, HOVERED

	}

	static Map<Type, PVector> colors;
	
	
	private Type nodeType = Type.EMPTY;
	private PVector coords;
	private float size;
	private boolean hovered = false;
	private int xIndex, yIndex;
	
	private Node parent;
	private int distance;

	
	public Node(float f, float g, int size, int x, int y) {
		this.coords = new PVector(f, g);
		this.size = size;
		this.xIndex = x;
		this.yIndex = y;
	}
	
	//Scale the node to new coords and size
	public void reset(float f, float g, float size) {
		this.coords = new PVector(f, g);
		this.size = size;
	}
	
	
	//Draw node to screen
	public void draw(PApplet win) {
		win.push();

		win.stroke(0);
		win.strokeWeight(1);
		
		PVector color = colors.get(this.nodeType);
		
		win.fill(color.x, color.y, color.z);
		
		win.square(this.coords.x, this.coords.y, this.size);

		win.pop();

	}
	
	//Update the node
	public void update(PApplet win) {
		
		int mouseX = win.mouseX;
		int mouseY = win.mouseY;
		
		//See if mouse is over the node
		this.hovered = mouseX > this.coords.x && mouseX < this.coords.x + this.size && mouseY > this.coords.y && mouseY < this.coords.y + this.size;
		
		//Get click interaction for wall removal and creation
		//This effect occurs in update rather than click() because in update it is possible to click and drag.
		//Doing this in click would make putting and removing walls tedious
		if(win.mousePressed && this.hovered) {
			if(this.hovered) {
				if(win.mouseButton == PApplet.LEFT && this.nodeType == Type.EMPTY) {
					this.nodeType = Type.WALL;
				} else if(win.mouseButton == PApplet.RIGHT && this.nodeType == Type.WALL) {
					this.nodeType = Type.EMPTY;
				}
				
			}
		}
		
	}
	
	
	//sets node as start node
	public void makeStart() {
		this.nodeType = Type.START;
	}
	
	
	//Sets node as terget node
	public void makeEnd() {
		this.nodeType = Type.TARGET;
	}
	
	
	//Click event used for moving start and target nodes
	public Type click(PApplet window, Type pastType) {
		
		if(this.hovered) {
			
				//set node if middle mouse is clicked
				if(window.mouseButton == PApplet.CENTER) {
					
					Type temp = this.nodeType;
					this.nodeType = pastType;
					pastType = temp;
					
				}
				
		}
		
		//return past type. Past type will be the next node placed on middle mouse click
		return pastType;
		
	}
	

	
	
	//Sets a hashmap of colors used by the grid
	public static void createColors() {
		colors = new HashMap<Type, PVector>();
		
		//PVector as values represents the rgb value of the node type
		colors.put(Type.EMPTY, new PVector(255, 255, 255));
		colors.put(Type.WALL, new PVector(25, 25, 35));
		colors.put(Type.HOVERED, new PVector(66, 68, 76));
		colors.put(Type.START, new PVector(89, 205, 144));
		colors.put(Type.TARGET, new PVector(238, 99, 82));
		
	}
	
	public void setParent(Node n) {
		this.parent = n;
	}
	
	public void setDistance(int n) {
		this.distance = n;
	}
	
	public Type getType() {
		return this.nodeType;
	}

	@Override
	public String toString() {
		return this.nodeType.toString();
	}

}
