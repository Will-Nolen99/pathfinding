package graphics;

import graph.Grid;
import processing.core.PApplet;

//Class that represents the screen itself. What is show and the interaction between user.
public class Canvas {

	
	static final int MENU_START_X = 1000;
	private Grid grid;

	public Canvas() {
		this.grid = new Grid();
	}

	public void draw(PApplet window) {
		this.grid.draw(window);
		
		window.push();
		window.noStroke();
		window.fill(36,33,36);
		window.rect(MENU_START_X, 0, window.width - MENU_START_X, window.height);
		
		window.pop();
		
		
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
