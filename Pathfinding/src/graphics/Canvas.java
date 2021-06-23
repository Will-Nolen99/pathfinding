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
	
	public void click() {
		this.grid.click();
	}
	
	public void scroll(float amount) {
		this.grid.scroll(amount);
	}

}
