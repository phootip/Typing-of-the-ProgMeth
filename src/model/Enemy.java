package model;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Enemy extends Entity{

	private GraphicsContext gc;
	protected String word;
	protected int speed;
	private boolean isDead;
	protected boolean isFocused;
	private boolean movable;
	protected double font_width;
	protected double font_height;
	protected double font_width_remain;
	private FontLoader fontLoader;
	protected int count = 0;
	
	public Enemy(int x, int y,String word,GraphicsContext gc) {
		super(x, y);
		this.z = Integer.MAX_VALUE-5;
		this.speed = 1;
		this.gc = gc;
		this.word = word;
		this.isDead = false;
		fontLoader = Toolkit.getToolkit().getFontLoader();
		this.font_width = fontLoader.computeStringWidth(word,gc.getFont());
		this.font_height = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		this.font_width_remain = font_width;
		this.movable = true;
		this.isFocused = false;
	}
	
	public String getWord(){
		return this.word;
	}
	
	public void hit(){
		if(word.substring(0, 1).equals(" ")) word = word.substring(2);
		else word = word.substring(1);
		font_width_remain = fontLoader.computeStringWidth(word,gc.getFont());
	}
	
	public void miss(){
		new AnimationTimer(){
			int count = 0;
			int x2 = x;
			int y2 = y;
			@Override
			public void handle(long now) {
				if(count<=30){
					gc.setFill(Color.LIMEGREEN);
					gc.strokeText("MISS", x2, y2+80);
					gc.fillText("MISS", x2, y2+80);
					y2--;
				}else this.stop();
				count++;
			}
		}.start();
	}
	
	public boolean Move(){
		if(movable){
			x -= speed;
			if(x<200)movable=false;
		}
		return movable;
	}

	@Override
	public abstract void draw(GraphicsContext gc);

	@Override
	public boolean isDestroy() {
		return isDead;
	}
	public void setDestroy(boolean d) {
		this.isDead = d;
	}

	@Override
	public boolean inHitBox() {
		return false;
	}

	@Override
	public boolean isFocused() {
		return isFocused;
	}

	@Override
	public void setFocus(boolean isfouced) {
		this.isFocused = isfouced;
	}
	
	public void setZ(int z){
		this.z = z;
	}
	public int getX(){
		return x;
	}
}
