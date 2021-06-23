package graph;

import processing.core.PApplet;
import processing.core.PVector;

public class Grid {

		private static final int NUM_CELLS = 100;
	
		private int cellSize = 25;
		private Node[][] cells = new Node[NUM_CELLS][NUM_CELLS];
		private PVector offset = new PVector(0, 0);
		
	
		public Grid() {
			
			for(int i = 0; i < NUM_CELLS; i++) {
				for (int j = 0; j < NUM_CELLS; j++) {
					this.cells[i][j] = new Node(i * this.cellSize, j * this.cellSize, this.cellSize);
				}
			}
			
		}
		
		public void draw(PApplet window) {
			
			
			for(int i = 0; i < NUM_CELLS; i++) {
				for (int j = 0; j < NUM_CELLS; j++) {
						
					this.cells[i][j].draw(window);
					
				}
			}
			
			
		}
		
		
		
	
}
