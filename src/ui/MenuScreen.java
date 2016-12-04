package ui;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuScreen extends StackPane{
	
	private Canvas canvas;
	private GraphicsContext gc;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 40);
	
	public MenuScreen(){
		this.canvas = new Canvas(ConfigOption.width,ConfigOption.height);
		gc = this.canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, ConfigOption.width, ConfigOption.height);
		
		paintHomeMenu();
		
		this.getChildren().add(canvas);
	}
	
	private void paintHomeMenu(){
		gc.setFont(font);
		this.gc.setFill(Color.WHITE);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double font_width = fontLoader.computeStringWidth("START", gc.getFont());
		gc.fillText("START", ConfigOption.width/2-font_width/2, 150);
	}
}
