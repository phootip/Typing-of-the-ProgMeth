package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Dog extends Enemy{

	public Dog(int x, int y, String word, GraphicsContext gc) {
		super(x, y, word, gc);
		this.speed = 3;
	}

	@Override
	public void draw(GraphicsContext gc) {
		if(count<10){
			gc.drawImage(new Image(ClassLoader.getSystemResource("pic/Nualthedog.png").toString()), x, y);
		}else if(count<20){
			gc.drawImage(new Image(ClassLoader.getSystemResource("pic/Nualthedog_jump.png").toString()), x, y);
			if(count==19)count=0;
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

}
