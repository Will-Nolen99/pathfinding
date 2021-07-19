package graphics;

import algorithms.Dijkstra;
import algorithms.Pathfinder;
import graph.Grid;
import graph.Node;
import processing.core.PApplet;
import processing.core.PVector;
import ui.Button;

//Class that represents the screen itself. What is show and the interaction between user.
public class Canvas {

	
	static final int MENU_START_X = 1000;
	private Grid grid;
	private Button run = new Button(1025, 50, "run");
	private Button reset = new Button(1025, 150, "reset");
	private Button clear = new Button(1025, 250, "clear");
	private boolean searching = false;
	private boolean needReset = false;
	
	private Pathfinder pathFind;

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
		
		this.run.draw(window);
		this.reset.draw(window);
		this.clear.draw(window);
		
		
	}
	
	public void update(PApplet window) {
		if(!this.searching) {
			this.grid.update(window);
			this.run.update(window);
			this.reset.update(window);
			this.clear.update(window);
		} else {
			for(int i = 0; i < 100; i++) {
				this.searching = !pathFind.step(this.grid);
				if(!this.searching) {
					break;
				}
			}
		}
	}
	
	public void click(PApplet window) {
		this.grid.click(window);
		String button = this.run.click();
		String reset = this.reset.click();
		String clear = this.clear.click();
		System.out.println(button + reset + clear);
		
		System.out.println(this.needReset);
		if(button.equals("run") && !this.needReset) {
			System.out.println("here");
			this.pathFind = new Dijkstra();
			pathFind.start(this.grid);
			this.searching = true;
			this.needReset = true;
		}
		
		if(reset.equals("reset")) {
			this.grid = new Grid();
			this.needReset = false;
		}
		
		if(clear.equals("clear")) {
			Node[][] cells = this.grid.getCells();
			this.needReset = false;
			
			for(Node[] row: cells) {
				for(Node node: row) {
					if(node.getType() == Node.Type.SEARCH || node.getType() == Node.Type.PATH) {
						node.setType(Node.Type.EMPTY);
						
					}
				}
			}
			
		}
		
		
		
	}
	
	public void scroll(float amount) {
		this.grid.scroll(amount);
	}

}
