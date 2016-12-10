package modelText;

import holder.ConfigOption;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StageText extends Text{

	private double x;
	private double y;
	private int count = 0;
	private int count2 =0;
	private boolean isDestroy = false;
	public StageText(String name, GraphicsContext gc) {
		super(name, 0, gc);
		this.x = ConfigOption.width/2-font_width/2;
		this.y = ConfigOption.height/2+font_height/2-100;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isDestroy() {
		return isDestroy;
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

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		if(count2==3){
			if(count<30);
			else if(y>60) y-=2;
			gc.fillText(name, x, y);
			gc.strokeText(name, x, y);
		}
		else if(count<30){
			gc.fillText(name, x, y);
			gc.strokeText(name, x, y);
		}else if(count>59){
			count=0;
			count2++;
		}
		count++;
	}

	@Override
	public void drawFocus(GraphicsContext gc) {
	}
	public void setDestroy(boolean isDestroyed){
		this.isDestroy = isDestroyed;
	}

}
