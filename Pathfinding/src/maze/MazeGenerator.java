package maze;

import java.util.Random;
import graph.Grid;
import graph.Node;

public class MazeGenerator {

	public MazeGenerator() {
		// TODO Auto-generated constructor stub
	}

	public static void generate(Node[][] cells) {

		// coords are [width][height]

		if (cells.length > 2 &&  cells[0].length > 2) {

			int height = cells[0].length;
			int width = cells.length;

			String wallDirection = getOrientation(width, height);
			Random rand = new Random();

			if (wallDirection.equals("vert")) {

				int wall = rand.nextInt(width);
				int door = rand.nextInt(height);

				// Guild wall from top to bottom
				for (int i = 0; i < height; i++) {
					if (rand.nextInt(10) > 2) {
						cells[wall][i].setType(Node.Type.WALL);
					}
				}

				Node[][] left = new Node[wall][height];
				Node[][] right = new Node[width - wall - 1][height];

				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {

						// node in left section
						if (i < wall) {

							left[i][j] = cells[i][j];

						} else if (i > wall) {

							right[i - wall - 1][j] = cells[i][j];

						}

					}
				}

				generate(left);
				generate(right);

			} else { // horiz wall

				
				
				int wall = rand.nextInt(height);

				// Guild wall from left to right
				for (int i = 0; i < width; i++) {
					if (rand.nextInt(10) > 2) { // this line places many doors
						cells[i][wall].setType(Node.Type.WALL);
					}
				}

				Node[][] top = new Node[width][wall];
				Node[][] bottom = new Node[width][height - wall - 1];

				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {

						if (j < wall) {

							top[i][j] = cells[i][j];

						} else if (j > wall) {
							bottom[i][j - wall - 1] = cells[i][j];

						}

					}
				}

				generate(top);
				generate(bottom);

			}

		}

	}

	private static String getOrientation(int width, int height) {

		if (width > height) {
			return "vert";
		} else if (width < height){
			return "horiz";
		} else {
			
			Random r = new Random();
			
			if(r.nextBoolean()) {
				return "vert";
			}
			return "horiz";
			
		}

	}

}
