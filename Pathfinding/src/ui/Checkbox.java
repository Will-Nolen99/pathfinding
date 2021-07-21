package ui;

import processing.core.PApplet;

public class Checkbox {

	private int x, y;
	private boolean hovered = false;
	private int width = 10;
	private int height = 10;
	private boolean checked = false;
	
	
	
	public Checkbox(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void draw(PApplet window) {
		window.push();
		window.strokeWeight(1);
		window.stroke(0);
		
		if(this.hovered) {
			window.fill(100);
		} else {
			window.fill(255);
		}
		
		window.rect(this.x, this.y, this.width, this.height);
		
		
		if(this.checked) {
			window.push();
			window.fill(0);
			window.rect(this.x + 3, this.y + 3, this.width - 6, this.height - 6);
			window.pop();
		}
		

		window.pop();
		
		
	}
	
	
	
	public void update(PApplet window) {
		
		int mouseX = window.mouseX;
		int mouseY = window.mouseY;
		
		
		this.hovered = mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height;
		
	}
	
	public void click() {
		if (this.hovered) this.checked = !this.checked;
	}
	
	public boolean getChecked() {
		return this.checked;
	}
	

}
