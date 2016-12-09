package modelText;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import holder.ConfigOption;
import holder.IRenderable;
import holder.InputHolder;
import holder.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ui.MenuScreen;

public class MenuText extends Text{

	private double x;
	private double y;
	public MenuText(String name,int order,GraphicsContext gc){
		super(name,order,gc);
		this.x = ConfigOption.width/2-font_width/2;
		this.y = ConfigOption.height/2+3.5*font_height+order*50;
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
	
	@Override
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
		gc.fillText(name, x, y);
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
