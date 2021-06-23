package graph;

import processing.core.PApplet;
import processing.core.PVector;

public class Grid {

	private static final int NUM_CELLS = 100;
	private static final int MIN_CELL_SIZE = 10;
	private static final int MAX_CELL_SIZE = 50;

	private int cellSize = 10;
	private Node[][] cells = new Node[NUM_CELLS][NUM_CELLS];
	private PVector offset = new PVector(0, 0);

	public Grid() {
		
		this.setNodes();
		Node.createColors();
		
	}
	
	private void setNodes() {
		for (int i = 0; i < NUM_CELLS; i++) {
			for (int j = 0; j < NUM_CELLS; j++) {
				this.cells[i][j] = new Node(i * this.cellSize, j * this.cellSize, this.cellSize);
			}
		}
	}
	
	private void resetNodes() {
		for (int i = 0; i < NUM_CELLS; i++) {
			for (int j = 0; j < NUM_CELLS; j++) {
				this.cells[i][j].reset(i * this.cellSize, j * this.cellSize, this.cellSize);
			}
		}
	}

	public void draw(PApplet window) {

		for (int i = 0; i < NUM_CELLS; i++) {
			for (int j = 0; j < NUM_CELLS; j++) {

				this.cells[i][j].draw(window);

			}
		}

	}
	
	public void update(PApplet window) {
		
		for (int i = 0; i < NUM_CELLS; i++) {
			for (int j = 0; j < NUM_CELLS; j++) {

				this.cells[i][j].update(window);

			}
		}
		
	}
	
	public void click() {
		
		for (int i = 0; i < NUM_CELLS; i++) {
			for (int j = 0; j < NUM_CELLS; j++) {

				this.cells[i][j].click();

			}
		}
	}
	
	public void scroll(float amount) {
		
		
		//testing these conditions twice fixes size jittering when trying to scroll when size is max or min
		if(this.cellSize > MIN_CELL_SIZE && amount > 0) {
			this.cellSize -= 1;
			
		} else if (this.cellSize < MAX_CELL_SIZE && amount < 0) {
			this.cellSize += 1;
		}
		
		this.resetNodes();
		
		
	}
	

}
