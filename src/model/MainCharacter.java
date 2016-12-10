package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MainCharacter extends Entity{
	private Image hero = new Image(ClassLoader.getSystemResource("pic/soldier76_noarm.png").toString());
	public MainCharacter(int x, int y) {
		super(x, y);
		this.z = Integer.MAX_VALUE-12;
	}

	
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(hero,x-hero.getWidth()/2, y);
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
