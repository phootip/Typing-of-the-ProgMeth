package model;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import holder.IRenderable;
import javafx.scene.canvas.GraphicsContext;

public abstract class Text implements IRenderable{
	protected String name;
	protected int order;
	protected double font_width;
	protected double font_height;
	protected boolean isfocused;
	protected int c=0;
	protected FontLoader fontLoader;
	public Text(String name,int order,GraphicsContext gc){
		this.name = name;
		this.order = order;
		fontLoader = Toolkit.getToolkit().getFontLoader();
		this.font_width = fontLoader.computeStringWidth(name, gc.getFont());
		this.font_height = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		this.isfocused = false;
	}
	
	public abstract void draw(GraphicsContext gc);
	
	public abstract void drawFocus(GraphicsContext gc);
	public String getName(){
		return name;
	}
	public int getOrder(){
		return order;
	}
}
