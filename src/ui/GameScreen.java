package ui;

import holder.ConfigOption;
import holder.GameLogic;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameScreen extends StackPane{

	private Canvas canvas;
	private GraphicsContext gc;
	private GameLogic gameLogic;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 40);
	public GameScreen(){
		this.canvas = new Canvas(ConfigOption.width,ConfigOption.height);
		this.gc = canvas.getGraphicsContext2D();
		this.gameLogic = new GameLogic();
		
		
		this.getChildren().add(canvas);
	}
	
	public void initializeGameScreen(){
		gameLogic.setIRenderable();
	}
	
	public void GameStart(){
		gameLogic.GameLoopStart();
	}
}
