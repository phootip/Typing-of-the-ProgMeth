package model;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ui.ConfigOption;
import ui.MenuScreen;

public class MenuText implements IRenderable{

	private String name;
	private int order;
	private double font_width;
	private double font_height;
	private double x;
	private double y;
	private boolean isfocused;
	public MenuText(String name,int order,GraphicsContext gc){
		this.name = name;
		this.order = order;
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		this.font_width = fontLoader.computeStringWidth(name, gc.getFont());
		this.font_height = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		this.x = ConfigOption.width/2-font_width/2;
		this.y = ConfigOption.height/2+font_height/2+order*100;
		this.isfocused = false;
	}
	
	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillText(name, x,y);
	}
	
	public void drawFocus(GraphicsContext gc){
		gc.setFill(Color.YELLOW);
		gc.setGlobalAlpha(0.5);
		gc.fillText(name, x, y);
	}

	@Override
	public boolean isDestroy() {
		return false;
	}

	@Override
	public boolean isFocused() {
		if(InputHolder.mouseX >= x-20 && 
				InputHolder.mouseX <= x+font_width+20 &&
				InputHolder.mouseY >= y-font_height-20 &&
				InputHolder.mouseY <= y+20){
			for(IRenderable i: RenderableHolder.instance.getEntities()){
				if(!i.equals(this)){
					i.setFocus(false);
				}
				else i.setFocus(true);
			}
		}
		return this.isfocused;
	}
	
	@Override
	public void setFocus(boolean isfocused){
		this.isfocused = isfocused;
	}

	public String getName(){
		return name;
	}
	public int getOrder(){
		return order;
	}
	
}
