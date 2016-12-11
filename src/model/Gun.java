package model;

import holder.IRenderable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Gun extends Entity{
	
	Image img = new Image(ClassLoader.getSystemResource("pic/soldier76arm.png").toString());
	ImageView iv = new ImageView(img);
	SnapshotParameters params = new SnapshotParameters();
	Image rotatedImage;
	int c = 0;
	boolean shoot = false;
	
	public Gun(int x, int y) {
		super(x, y);
		rotatedImage = img;
		this.z = Integer.MAX_VALUE-10;
		params.setFill(Color.TRANSPARENT);
		
	}

	@Override
	public int getZ() {
		return z;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(rotatedImage, x-55, y-15);
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
