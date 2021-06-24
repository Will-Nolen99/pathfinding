package graph;

import graph.Node.Type;
import processing.core.PApplet;
import processing.core.PVector;



// Class used to represent the overall grid containing nodes
public class Grid {

	private static final int NUM_CELLS = 100;
	private static final int MIN_CELLS = 10;
	private static final int MAX_CELLS = 100;

	private float cellSize = 10;
	
	private int cellsToDraw = 100;
	
	private Node[][] cells = new Node[NUM_CELLS][NUM_CELLS];
	private PVector offset = new PVector(0, 0);
	private Type pastNode = Node.Type.EMPTY;
	
	private String mode = "drawing";
	
	
	

	public Grid() {
		
		this.setNodes();
		Node.createColors();
		
		//Set default start locations for start and end nodes. 
		// Indices are arbitrary as long as they are in array bounds
		this.cells[10][10].makeStart();
		this.cells[90][90].makeEnd();
		
	}

	//populate 2D array with initial nodes
	private void setNodes() {
		for (int i = 0; i < NUM_CELLS; i++) {
			for (int j = 0; j < NUM_CELLS; j++) {
				this.cells[i][j] = new Node(i * this.cellSize, j * this.cellSize,(int)this.cellSize, i, j);
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

		for (int i = 0; i < this.cellsToDraw; i++) {
			for (int j = 0; j < this.cellsToDraw; j++) {

				this.cells[i][j].draw(window);

			}
		}

	}
	
	//update each element of the grid
	public void update(PApplet window) {
		
		//only update if in draw mode. 
		if(this.mode.equals("drawing")) {
			for (int i = 0; i < this.cellsToDraw; i++) {
				for (int j = 0; j < this.cellsToDraw; j++) {
	
					this.cells[i][j].update(window);
	
				}
			}
		}
		
	}
	
	// 
	public void click(PApplet window) {
		
		if(this.mode.equals("drawing")) {
			for (int i = 0; i < this.cellsToDraw; i++) {
				for (int j = 0; j < this.cellsToDraw; j++) {
	
					this.pastNode = cells[i][j].click(window, this.pastNode);
	
				}
			}
		}
	}
	
	
	//on scroll scale the grid
	public void scroll(float amount) {
		
		this.cellsToDraw += (int)amount;
		
		if(this.cellsToDraw < MIN_CELLS ) {
			this.cellsToDraw = MIN_CELLS;
		} else if (this.cellsToDraw > MAX_CELLS) {
			this.cellsToDraw = MAX_CELLS;
		}
		
		this.cellSize = (float) (1000.0 / this.cellsToDraw);
		
		
		this.resetNodes();
		
		
	}
	

}
