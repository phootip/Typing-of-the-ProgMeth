package modelText;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import holder.ConfigOption;
import holder.IRenderable;
import holder.InputHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class OptionText extends Text{

	private String value;
	private double font_width2;
	private double x;
	private double y;
	private GraphicsContext gc;
	public OptionText(String name,String value,double order,GraphicsContext gc){
		super(name,order,gc);
		this.value = value;
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		this.font_width2 = fontLoader.computeStringWidth(value, gc.getFont());
		this.x = ConfigOption.width*5/6-font_width2;
		this.y = 100+font_height/2+order*100;
		this.gc = gc;
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
		gc.fillText(value, x, y);
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
	
	//set Value
	public void setValue(String value){
		this.value = value;
		this.font_width2 = fontLoader.computeStringWidth(value, gc.getFont());
		this.x = ConfigOption.width*5/6-font_width2;
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
	
	public boolean inHitBoxRight(){
		if(InputHolder.mouseX >= 150+font_width+20 &&
				InputHolder.mouseX <= 200+font_width+20 &&
				InputHolder.mouseY >= y-font_height-20 &&
				InputHolder.mouseY <= y+20) return true;
		return false;
	}
	
	public boolean inHitBoxLeft(){
		if(InputHolder.mouseX >= 200-20 &&
				InputHolder.mouseX <= 200-20+50 &&
				InputHolder.mouseY >= y-font_height-20 &&
				InputHolder.mouseY <= y+20) return true;
		return false;
	}
	
	public String getName(){
		return name;
	}
	public double getOrder(){
		return order;
	}
}
