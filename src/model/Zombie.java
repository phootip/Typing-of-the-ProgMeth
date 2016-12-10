package model;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import holder.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ui.GameScreen;

public class Zombie extends Entity{

	private GraphicsContext gc;
	private String word;
	private int speed;
	private boolean isDead;
	private boolean isFocused;
	private boolean movable;
	private double font_width;
	private double font_height;
	private double font_width_remain;
	private FontLoader fontLoader;
	private int count = 0;
	
	public Zombie(int x, int y,String word,GraphicsContext gc) {
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
	
	public void Move(){
		if(movable){
			x -= speed;
			if(x<200)movable=false;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		if(count<20){
			gc.drawImage(new Image(ClassLoader.getSystemResource("pic/ExampleZombie_move1.png").toString()), x, y);
		}else if(count<40){
			gc.drawImage(new Image(ClassLoader.getSystemResource("pic/ExampleZombie_move2.png").toString()), x, y);
		}else if(count<60){
			gc.drawImage(new Image(ClassLoader.getSystemResource("pic/ExampleZombie_move3.png").toString()), x, y);
		}else if(count<80){
			gc.drawImage(new Image(ClassLoader.getSystemResource("pic/ExampleZombie_move2.png").toString()), x, y);
			if(count>78) count =0;
		}
		if(isFocused){
			gc.setFill(Color.RED);
		}else{
			gc.setFill(Color.BLUE);			
		}
		gc.fillRect(x-15, y-font_height+5, font_width+30, font_height+10);
		gc.setFill(Color.BLACK);
		gc.fillRect(x-10, y-font_height+10, font_width+20, font_height);
		gc.setFill(Color.WHITE);
		gc.fillText(word, x+font_width-font_width_remain, y);
		count++;
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
	
	public void setZ(int z){
		this.z = z;
	}
	
}
