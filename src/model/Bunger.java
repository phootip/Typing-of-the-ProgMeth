package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bunger extends Entity{
	Image bunker = getImage("pic/samplebunger3.png");
	
	public Bunger(int x, int y) {
		super(x, y);
		this.z = Integer.MAX_VALUE-11; // between character and gun
	}

	@Override
	public void draw(GraphicsContext gc) {
		for(int i = this.y; i <=600 ; i +=70){
			gc.drawImage(bunker, x, i);
		}
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
