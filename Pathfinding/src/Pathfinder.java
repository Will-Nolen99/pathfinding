

import graphics.Canvas;
import processing.core.PApplet;

public class Pathfinder extends PApplet {

    // The argument passed to main must match the class name
    public static void main(String[] args) {
        PApplet.main("Pathfinder");
    }
    
    private Canvas graphics = new Canvas();
    
    

    // method for setting the size of the window
    public void settings(){
        size(500, 500);
    }

    // identical use to setup in Processing IDE except for size()
    public void setup(){
        background(0);
        stroke(255);
        strokeWeight(10);
    }

    // identical use to draw in Processing IDE
    public void draw(){
    	background(0);
    	
    	this.graphics.draw(this);
    	
      
    }
}