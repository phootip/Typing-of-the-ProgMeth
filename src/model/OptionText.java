package model;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import holder.ConfigOption;
import holder.IRenderable;
import holder.InputHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class OptionText implements IRenderable{

	private String name;
	private String value;
	private int order;
	private double font_width;
	private double font_width2;
	private double font_height;
	private double x;
	private double y;
	private boolean isfocused;
	private int c=0;
	public OptionText(String name,String value,int order,GraphicsContext gc){
		this.name = name;
		this.value = value;
		this.order = order;
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		this.font_width = fontLoader.computeStringWidth(name, gc.getFont());
		this.font_width2 = fontLoader.computeStringWidth(value, gc.getFont());
		this.font_height = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		this.x = ConfigOption.width*5/6-font_width2;
		this.y = 100+font_height/2+order*100;
		this.isfocused = false;
	}
	
	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillText(name, 200,y);
		gc.fillText(value, x, y);
	}
	
	public void drawFocus(GraphicsContext gc){
		if(c<3){
			gc.setFill(Color.YELLOW);
			c++;
		}
		else if(c<6){
			c++;
			gc.setFill(Color.WHITE);
		}
		else c=0;
		gc.fillText(name, 200, y);
	}

	@Override
	public boolean isDestroy() {
		return false;
	}

	@Override
	public boolean isFocused() {
		return isfocused;
	}
	
	@Override
	public void setFocus(boolean isfocused){
		this.isfocused = isfocused;
	}

	@Override
	public boolean inHitBox(){
		if(InputHolder.mouseX >= 200-20 && 
				InputHolder.mouseX <= 200+font_width+20 &&
				InputHolder.mouseY >= y-font_height-20 &&
				InputHolder.mouseY <= y+20){
			return true;
		}
		else return false;
	}
	
	public String getName(){
		return name;
	}
	public int getOrder(){
		return order;
	}
}
