package model;

import holder.ConfigOption;
import holder.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BackGround implements IRenderable{

	@Override
	public int getZ() {
		return Integer.MIN_VALUE;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, ConfigOption.width, ConfigOption.height);
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
