package model;

import javafx.scene.canvas.GraphicsContext;

public class Gun implements IRenderable{

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		
	}

	@Override
	public boolean isDestroy() {
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
