package model;

import holder.ConfigOption;
import holder.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class BackGround implements IRenderable{
	public static final Image bg = getImage("pic/start.jpg");
	
	
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
	private static Image getImage(String directory) {
		Image img = new Image(ClassLoader.getSystemResource(directory).toString());
		if(img != null) return img;
		return null;
	}
	
	public void drawBackground(GraphicsContext gc) {
		if (bg == null)
			return;
		gc.drawImage(bg, 0, 0);
	}

}
