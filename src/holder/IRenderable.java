package holder;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	/*
	 *  bg = -100;
	 *  zombie = 0;
	 *  maincharacter = 100;
	 *  bunger = 101;
	 *  gun = 102;
	 */
	int getZ();
	void draw(GraphicsContext gc);
	boolean isDestroy();
	boolean inHitBox();
	boolean isFocused();
	void setFocus(boolean isfouced);
}
