package graphics;

import java.util.ArrayList;

import algorithms.AStar;
import algorithms.DepthFirstSearch;
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
	private Button dijkstra = new Button(1025, 50, "dijkstra");
	private Button dfs = new Button(1025, 150, "dfs");
	private Button reset = new Button(1025, 800, "reset");
	private Button clear = new Button(1025, 900, "clear");
	private Button plus = new Button(1025, 500, "+");
	private Button minus = new Button(1025, 625, "-");
	private Button aStar = new Button(1025, 250, "A*"); 
	private boolean searching = false;
	private boolean needReset = false;
	private int numSteps = 10;
	
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
		
		this.dijkstra.draw(window);
		this.dfs.draw(window);
		this.plus.draw(window);
		this.minus.draw(window);
		this.reset.draw(window);
		this.clear.draw(window);
		this.aStar.draw(window);
		
		window.text("Speed: " + this.numSteps, 1025, 600);
		
		
	}
	
	public void update(PApplet window) {
		
		this.plus.update(window);
		this.minus.update(window);
		
		if(!this.searching) {
			this.grid.update(window);
			this.dijkstra.update(window);
			this.dfs.update(window);
			this.reset.update(window);
			this.clear.update(window);
			this.aStar.update(window);
		} else {
			for(int i = 0; i < this.numSteps; i++) {
				this.searching = !pathFind.step(this.grid);
				if(!this.searching) {
					break;
				}
			}
		}
	}
	
	public void click(PApplet window) {
		this.grid.click(window);
		String button = this.dijkstra.click();
		String reset = this.reset.click();
		String clear = this.clear.click();
		System.out.println(button + reset + clear);
		
		System.out.println(this.needReset);
		if(button.equals("dijkstra") && !this.needReset) {
			System.out.println("here");
			this.pathFind = new Dijkstra();
			pathFind.start(this.grid);
			this.searching = true;
			this.needReset = true;
		}
		
		button = this.dfs.click();
		
		if(button.equals("dfs") && !this.needReset) {
			System.out.println("here");
			this.pathFind = new DepthFirstSearch();
			pathFind.start(this.grid);
			this.searching = true;
			this.needReset = true;
		}
		
		button = this.aStar.click();
		
		if(button.equals("A*") && !this.needReset) {
			this.pathFind = new AStar();
			pathFind.start(this.grid);
			this.searching = true;
			this.needReset = true;
		}
		
		if(reset.equals("reset")) {
			this.grid = new Grid();
			this.needReset = false;
		}
		
		if(this.plus.click().equals("+")) {
			if(this.numSteps < 100) {
				this.numSteps += 1;
			}
		}
		
		if(this.minus.click().equals("-")) {
			if(this.numSteps > 0) {
				this.numSteps -= 1;
			}
		}
		
		
		
		//This is terrible, needs fixed
		if(clear.equals("clear")) {
			Node[][] cells = this.grid.getCells();
			this.needReset = false;
			
			ArrayList<int[]> walls = new ArrayList<int[]>();
			int[] start = new int[2];
			int[] finish = new int[2];
			
			for(Node[] row: cells) {
				for(Node node: row) {
					if(node.getType() == Node.Type.WALL) {
						walls.add(node.getCoords());
					}
					
					if(node.getType() == Node.Type.START) {
						start = node.getCoords();
					}
					
					if(node.getType() == Node.Type.TARGET) {
						finish= node.getCoords();
					}
				}
			}
			
			this.grid = new Grid();
			cells = this.grid.getCells();
			
			for(Node[] row: cells) {
				for(Node node: row) {
					if(node.getType() == Node.Type.START || node.getType() == Node.Type.TARGET) {
						node.setType(Node.Type.EMPTY);
					}
				}
			}
			
			for(int[] coords: walls) {
				cells[coords[0]][coords[1]].setType(Node.Type.WALL);
			}
			
			cells[start[0]][start[1]].setType(Node.Type.START);
			cells[finish[0]][finish[1]].setType(Node.Type.TARGET);
			
			
			
		}
		
		
		
	}
	
	public void scroll(float amount) {
		this.grid.scroll(amount);
	}

}
