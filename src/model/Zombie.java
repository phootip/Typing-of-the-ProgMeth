package model;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ui.GameScreen;

public class Zombie extends Entity{

	private String word;
	private boolean isDead;
	private boolean isFocused;
	private double font_width;
	private double font_height;
	private FontLoader fontLoader;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 100);
	
	public Zombie(int x, int y,String word,GraphicsContext gc) {
		super(x, y);
		this.z = 0;
		this.word = word;
		this.isDead = false;
		fontLoader = Toolkit.getToolkit().getFontLoader();
		this.font_width = fontLoader.computeStringWidth(word,gc.getFont());
		this.font_height = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
	}
	
	public String getWord(){
		return this.word;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFont(font);
		gc.setFill(Color.GREEN);
		gc.fillRect(x, y, 35, 100);
		gc.fillText(word, x, y);
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
