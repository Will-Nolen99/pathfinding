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
	
	public void update(PApplet window) {
		this.grid.update(window);
	}
	
	public void click(PApplet window) {
		this.grid.click(window);
	}
	
	public void scroll(float amount) {
		this.grid.scroll(amount);
	}

}
