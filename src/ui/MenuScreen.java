package ui;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import holder.IRenderable;
import holder.InputHolder;
import holder.RenderableHolder;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Gun;
import model.MenuText;

public class MenuScreen extends StackPane{
	
	public static MenuScreen instance;
	private Canvas canvas;
	private GraphicsContext gc;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 40);
	
	public MenuScreen(){
		this.canvas = new Canvas(ConfigOption.width,ConfigOption.height);
		gc = this.canvas.getGraphicsContext2D();
		
		//BackGround
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, ConfigOption.width, ConfigOption.height);
		
		addHomeMenu();
		RenderableHolder.instance.add(new Gun(1000,600));
		
		for(IRenderable i : RenderableHolder.instance.getEntities()){
			i.draw(gc);
		}
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run(){
				for(IRenderable i: RenderableHolder.instance.getEntities()){
					
				}
			}
		});
		
		this.getChildren().add(canvas);
	}
	
	private void addHomeMenu(){
		//Paint Home Menu
		gc.setFont(font);
		this.gc.setFill(Color.WHITE);
		RenderableHolder.instance.add(new MenuText("START",0,gc));
		RenderableHolder.instance.add(new MenuText("OPTION",1,gc));
		RenderableHolder.instance.add(new MenuText("EXIT",2,gc));
		
		//Event Handler Hovering Menu
		this.canvas.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				InputHolder.mouseOnScreen = true;
			}
		});
		
		this.canvas.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				InputHolder.mouseOnScreen = false;
			}
		});
		
		this.canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				if(InputHolder.mouseOnScreen){
					InputHolder.mouseX = event.getX();
					InputHolder.mouseY = event.getY();
					for(IRenderable i: RenderableHolder.instance.getEntities()){
						if(i.inHitBox() && i instanceof MenuText){
							i.setFocus(true);
							((MenuText) i).drawFocus(gc);
							for(IRenderable j: RenderableHolder.instance.getEntities()){
								if(!i.equals(j))j.setFocus(false);
							}
						}
						else if(i.isFocused()){
							((MenuText) i).drawFocus(gc);
						}
						else i.draw(gc);
					}
				}
			}
		});
		
		this.canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				if(InputHolder.mouseOnScreen){
					InputHolder.mouseX = event.getX();
					InputHolder.mouseY = event.getY();
					
				}
			}
		});
	}
	
	public GraphicsContext getGc(){
		return this.gc;
	}
}
