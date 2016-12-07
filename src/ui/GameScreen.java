package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameScreen extends StackPane{

	public static final GameScreen instance = new GameScreen();
	private Canvas canvas;
	private GraphicsContext gc;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 40);
}
