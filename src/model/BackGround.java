package model;

import holder.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BackGround implements IRenderable{
	public static Image menubg = getImage("pic/start.jpg");
	public static Image gamebg = getImage("pic/road.png");
	
	@Override
	public int getZ() {
		return Integer.MIN_VALUE;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(gamebg, 0, 0);
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
		return img;
	}
	

}
