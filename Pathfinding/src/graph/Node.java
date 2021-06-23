package graph;


import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PVector;

public class Node {

	enum Type {

		EMPTY, WALL, PATH, SEARCH, TARGET, START, HOVERED

	}

	static Map<Type, PVector> colors;
	
	
	private Type nodeType = Type.EMPTY;
	private PVector coords;
	private int size;
	private boolean hovered = false;


	public Node(int xCoord, int yCoord, int size) {
		this.coords = new PVector(xCoord, yCoord);
		this.size = size;
	}
	
	public void reset(int xCoord, int yCoord, int size) {
		this.coords = new PVector(xCoord, yCoord);
		this.size = size;
	}
	public void draw(PApplet win) {
		win.push();

		win.stroke(0);
		win.strokeWeight(1);
		
		PVector color;
		

		color = colors.get(this.nodeType);
		
		
		win.fill(color.x, color.y, color.z);
		
		win.square(this.coords.x, this.coords.y, this.size);

		win.pop();

	}
	
	
	public void update(PApplet win) {
		
		int mouseX = win.mouseX;
		int mouseY = win.mouseY;
		
		this.hovered = mouseX > this.coords.x && mouseX < this.coords.x + this.size && mouseY > this.coords.y && mouseY < this.coords.y + this.size;
		
		
		if(win.mousePressed && this.hovered) {
			if(this.hovered) {
				if(win.mouseButton == PApplet.LEFT) {
					this.nodeType = Type.WALL;
				} else if(win.mouseButton == PApplet.RIGHT) {
					this.nodeType = Type.EMPTY;
				}
				
			}
		}
		
		
		
	}
	
	public static void createColors() {
		colors = new HashMap<Type, PVector>();
		
		// this seems like an odd way to do it but using hex doesn't work
		colors.put(Type.EMPTY, new PVector(255, 255, 255));
		colors.put(Type.WALL, new PVector(25, 25, 35));
		colors.put(Type.HOVERED, new PVector(66, 68, 76));
		
	}
	
	public void click() {
		
		if(this.hovered) {
			if(this.nodeType == Type.EMPTY) {
				this.nodeType = Type.WALL;
			} else if(this.nodeType == Type.WALL) {
				this.nodeType = Type.EMPTY;
			}
		}
		
	}

	@Override
	public String toString() {
		return this.nodeType.toString();
	}

}
