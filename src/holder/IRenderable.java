package holder;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	/*
	 *  bg = -100;
	 *  maincharacter = 90;
	 *  bunger = 91;
	 *  zombie = 95;
	 *  focused zombie = 99;
	 *  stageText = 100;
	 */
	int getZ();
	void draw(GraphicsContext gc);
	boolean isDestroy();
	boolean inHitBox();
	boolean isFocused();
	void setFocus(boolean isfouced);
}
