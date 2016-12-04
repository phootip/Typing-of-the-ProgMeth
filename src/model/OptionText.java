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
	private double font_height;
	private double x;
	private double y;
	private boolean isfocused;
	public OptionText(String name,String value,int order,GraphicsContext gc){
		this.name = name;
		this.value = value;
		this.order = order;
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		this.font_width = fontLoader.computeStringWidth(value, gc.getFont());
		this.font_height = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		this.x = ConfigOption.width*5/6-font_width;
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
	
	public void drawFocus(GraphicsContext gc,double op){
		gc.setFill(Color.YELLOW);
		gc.setGlobalAlpha(op);
		gc.fillText(name, x, y);
		gc.setGlobalAlpha(1.0);
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
		if(InputHolder.mouseX >= x-20 && 
				InputHolder.mouseX <= x+font_width+20 &&
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
