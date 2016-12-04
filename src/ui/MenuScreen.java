package ui;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.InputHolder;

public class MenuScreen extends StackPane{
	
	private Canvas canvas;
	private GraphicsContext gc;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 40);
	
	public MenuScreen(){
		this.canvas = new Canvas(ConfigOption.width,ConfigOption.height);
		gc = this.canvas.getGraphicsContext2D();
		
		//BackGround
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, ConfigOption.width, ConfigOption.height);
		
		paintHomeMenu();
		
		this.getChildren().add(canvas);
	}
	
	private void paintHomeMenu(){
		//Paint Home Menu
		gc.setFont(font);
		this.gc.setFill(Color.WHITE);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		
		double font_width = fontLoader.computeStringWidth("START", gc.getFont());
		gc.fillText("START", ConfigOption.width/2-font_width/2, ConfigOption.height/2);

		font_width = fontLoader.computeStringWidth("OPTION", gc.getFont());
		gc.fillText("OPTION", ConfigOption.width/2-font_width/2, ConfigOption.height/2+100);		
		
		font_width = fontLoader.computeStringWidth("Exit", gc.getFont());
		gc.fillText("Exit", ConfigOption.width/2-font_width/2, ConfigOption.height/2+200);
		
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
					if()
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
}
