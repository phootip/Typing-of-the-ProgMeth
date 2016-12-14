package ui;

import holder.ConfigOption;
import holder.GameLogic;
import holder.InputHolder;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameScreen extends StackPane{

	public static GameScreen instance;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameLogic gameLogic;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 40);
	public GameScreen(GameLogic gamelogic){
		this.canvas = new Canvas(ConfigOption.width,ConfigOption.height);
		this.gc = canvas.getGraphicsContext2D();
		this.gameLogic = gamelogic;
		gameLogic.setGc(gc);
		
		this.getChildren().add(canvas);
	}
}
