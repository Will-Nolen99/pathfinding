
import graphics.Canvas;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class Pathfinder extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Pathfinder");
	}

	private Canvas graphics = new Canvas();

	public void settings() {
		size(500, 500);
	}

	public void setup() {
		background(0);
		stroke(255);
		strokeWeight(10);
	}

	public void draw() {
		background(0);

		this.graphics.draw(this);
		this.graphics.update(this);
	}
	
public void mousePressed() {
    	
		//this.graphics.click();
    	
    }


public void mouseWheel(MouseEvent event) {
	
	float scroll = event.getCount();
	
	this.graphics.scroll(scroll);
	
}

	
	
}