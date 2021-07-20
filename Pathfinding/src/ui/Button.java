package ui;

import processing.core.PApplet;

public class Button {
	
	private int x, y;
	private String name;
	private boolean hovered = false;
	
	private int width = 150;
	private int height = 50;
	
	
	public Button(int x, int y, String name) {
		
		this.x = x;
		this.y = y;
		this.name = name;
		
	}
	
	public void draw(PApplet window) {
		
		window.push();
		window.strokeWeight(5);
		window.stroke(0);
		
		if(this.hovered) {
			window.fill(100);
		} else {
			window.fill(255);
		}
		
		window.rect(this.x, this.y, this.width, this.height);
		
		window.textSize(32);
		window.fill(0);
		window.text(this.name, this.x + 25, this.y + this.height - 15);
		
		
		
		window.pop();
		
	}
	
	public void update(PApplet window) {
		
		int mouseX = window.mouseX;
		int mouseY = window.mouseY;
		
		
		this.hovered = mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height;
		
	}
	
	public String click() {
		if(this.hovered) {
			return this.name;
		}else {
			return "";
		}
	}
	
	
	
	
	
	
	
}
