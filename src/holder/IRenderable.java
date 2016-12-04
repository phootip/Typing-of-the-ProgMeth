package holder;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	int getZ();
	void draw(GraphicsContext gc);
	boolean isDestroy();
	boolean inHitBox();
	boolean isFocused();
	void setFocus(boolean isfouced);
}
