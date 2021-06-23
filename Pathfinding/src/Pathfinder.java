
import graphics.Canvas;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class Pathfinder extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Pathfinder");
	}

	private Canvas graphics = new Canvas();

	// called once at program start to give default parameters of the window7
	public void settings() {
		size(1200, 1000);
	}

	// called once at program start to give default parameters of the canvas
	public void setup() {
		background(0);
		stroke(255);
		strokeWeight(10);
	}

	// method called repeatedly after setup. Main loop of program
	public void draw() {
		background(0);

		this.graphics.draw(this);
		this.graphics.update(this);
	}

//listener event for mouse click
	public void mousePressed() {

		this.graphics.click(this);

	}

	// Listener event for scroll wheel action
	public void mouseWheel(MouseEvent event) {

		// get sroll up or down
		float scroll = event.getCount();

		// pass info into graphics
		this.graphics.scroll(scroll);

	}

}