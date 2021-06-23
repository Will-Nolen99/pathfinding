package graph;

import graph.Node.Type;
import processing.core.PApplet;
import processing.core.PVector;



// Class used to represent the overall grid containing nodes
public class Grid {

	private static final int NUM_CELLS = 100;
	private static final int MIN_CELL_SIZE = 10;
	private static final int MAX_CELL_SIZE = 50;

	private int cellSize = 25;
	private Node[][] cells = new Node[NUM_CELLS][NUM_CELLS];
	private PVector offset = new PVector(0, 0);
	private Type pastNode = Node.Type.EMPTY;

	public Grid() {
		
		this.setNodes();
		Node.createColors();
		
		//Set default start locations for start and end nodes. 
		// indecies are arbitrary as long as they are in array bounds
		this.cells[5][5].makeStart();
		this.cells[25][25].makeEnd();
		
	}

	//populate 2D array with initial nodes
	private void setNodes() {
		for (int i = 0; i < NUM_CELLS; i++) {
			for (int j = 0; j < NUM_CELLS; j++) {
				this.cells[i][j] = new Node(i * this.cellSize, j * this.cellSize, this.cellSize, i, j);
			}
		}
	}
	
	//change node coords and width to correspond with resizing
	private void resetNodes() {
		for (int i = 0; i < NUM_CELLS; i++) {
			for (int j = 0; j < NUM_CELLS; j++) {
				this.cells[i][j].reset(i * this.cellSize, j * this.cellSize, this.cellSize);
			}
		}
	}

	//draws each element of the grid to the screen
	public void draw(PApplet window) {

		for (int i = 0; i < NUM_CELLS; i++) {
			for (int j = 0; j < NUM_CELLS; j++) {

				this.cells[i][j].draw(window);

			}
		}

	}
	
	//update each element of the grid
	public void update(PApplet window) {
		
		for (int i = 0; i < NUM_CELLS; i++) {
			for (int j = 0; j < NUM_CELLS; j++) {

				this.cells[i][j].update(window);

			}
		}
		
	}
	
	// 
	public void click(PApplet window) {
		for (int i = 0; i < NUM_CELLS; i++) {
			for (int j = 0; j < NUM_CELLS; j++) {

				this.pastNode = cells[i][j].click(window, this.pastNode);

			}
		}
	}
	
	
	//on scroll scale the grid
	public void scroll(float amount) {
		
		this.cellSize += (int)amount;
		
		if(this.cellSize < MIN_CELL_SIZE ) {
			this.cellSize = MIN_CELL_SIZE;
		} else if (this.cellSize > MAX_CELL_SIZE) {
			this.cellSize = MAX_CELL_SIZE;
		}
		
		this.resetNodes();
		
		
	}
	

}
