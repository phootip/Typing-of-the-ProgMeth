package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MainCharacter extends Entity{

	public MainCharacter(int x, int y) {
		super(x, y);
		this.z = Integer.MAX_VALUE-12;
	}

	
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillRect(x, y, 30, 60);
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
