package model;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ui.GameScreen;

public class Zombie extends Entity{

	private GraphicsContext gc;
	private String word;
	private boolean isDead;
	private boolean isFocused;
	private double font_width;
	private double font_width_remain;
	private FontLoader fontLoader;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 30);
	
	public Zombie(int x, int y,String word,GraphicsContext gc) {
		super(x, y);
		this.z = 0;
		this.gc = gc;
		this.word = word;
		this.isDead = false;
		gc.setFont(font);
		fontLoader = Toolkit.getToolkit().getFontLoader();
		this.font_width = fontLoader.computeStringWidth(word,gc.getFont());
		this.font_width_remain = font_width;
	}
	
	public String getWord(){
		return this.word;
	}
	
	public void hit(){
		if(word.substring(0, 1).equals(" ")) word = word.substring(2);
		else word = word.substring(1);
		font_width_remain = fontLoader.computeStringWidth(word,gc.getFont());
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFont(font);
		gc.drawImage(new Image(ClassLoader.getSystemResource("pic/ExampleZombie.png").toString()), x, y);
		gc.setFill(Color.GREEN);
		gc.fillText(word, x+font_width-font_width_remain, y);
	}

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
}
