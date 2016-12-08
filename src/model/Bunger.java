package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bunger extends Entity{

	public Bunger(int x, int y) {
		super(x, y);
		this.z = Integer.MAX_VALUE-11; // between character and gun
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
		gc.fillRect(x, y, 50, 600);
	}

	@Override
	public boolean isDestroy() {
		return false;
	}

	@Override
	public boolean inHitBox() {
		return false;
	}

	@Override
	public boolean isFocused() {
		return false;
	}

	@Override
	public void setFocus(boolean isfouced) {
		
	}

}
