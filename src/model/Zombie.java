package model;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import holder.GameLogic;
import holder.RenderableHolder;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ui.GameScreen;

public class Zombie extends Enemy{

	public Zombie(int x, int y,String word,GraphicsContext gc) {
		super(x, y,word,gc);
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
		gc.fillRect(x-15, y-font_height-5, font_width+30, font_height+10);
		gc.setFill(Color.BLACK);
		gc.fillRect(x-10, y-font_height, font_width+20, font_height);
		gc.setFill(Color.WHITE);
		gc.fillText(word, x+font_width-font_width_remain, y-10);
		count++;
	}
}
