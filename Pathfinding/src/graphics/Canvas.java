package graphics;

import graph.Grid;
import processing.core.PApplet;

public class Canvas {

	
	private Grid grid;
	
	public Canvas() {
		this.grid = new Grid();
	}
	
	
	public void draw(PApplet window) {
		this.grid.draw(window);
	}
	
}
