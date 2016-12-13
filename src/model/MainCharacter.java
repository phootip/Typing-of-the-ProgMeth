package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

public class MainCharacter extends Entity{
	
	private Image hero = new Image(ClassLoader.getSystemResource("pic/soldier76.png").toString());
	private Image hero_shoot = new Image(ClassLoader.getSystemResource("pic/soldier76_bang.png").toString());
	private AudioClip typing = new AudioClip(ClassLoader.getSystemResource("sound/gunshot.wav").toString());
	private boolean shoot;
	private int count;
	public MainCharacter(int x, int y) {
		super(x, y);
		this.z = Integer.MAX_VALUE-12;
		this.shoot = false;
	}

	public void shoot(){
		typing.play();
		shoot = true;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		if(shoot&&count<5){
			count++;
			gc.drawImage(hero_shoot, x-hero.getWidth()/2, y);
		}else{
			gc.drawImage(hero,x-hero.getWidth()/2, y);
			count = 0;
			shoot = false;
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
	
}
