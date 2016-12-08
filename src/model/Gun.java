package model;

import holder.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Gun extends Entity{

	public Gun(int x, int y) {
		super(x, y);
		this.z = Integer.MAX_VALUE-10;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public void draw(GraphicsContext gc) {
//		Image img = new Image(ClassLoader.getSystemResource("pic/gun.png").toString());
//		gc.drawImage(img, x, y);
		gc.setFill(Color.YELLOW);
		gc.fillRect(x, y, 20, 10);
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

	@Override
	public boolean inHitBox() {
		return false;
	}

}
