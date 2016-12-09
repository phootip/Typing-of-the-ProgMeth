package main;

import java.util.ArrayList;

import holder.GameLogic;
import holder.IRenderable;
import holder.InputHolder;
import holder.RenderableHolder;
import holder.ThreadHolder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.GameScreen;
import ui.MenuScreen;

public class Main extends Application {
	
	public static Main instance;
	private Scene menuScene;
	private static Scene gameScene;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;
	private static GameLogic gameLogic;
	private static Stage primaryStage;
	private static String scene_count; // indicate what scene to be shown.
	
	public static ArrayList<Integer> test1 = new ArrayList<Integer>();
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setResizable(false);
		this.primaryStage = primaryStage;
		scene_count = "menuScene";
		this.gameLogic = new GameLogic();
		this.menuScreen = new MenuScreen();
		this.menuScene = new Scene(this.menuScreen);
		this.gameScreen = new GameScreen(this.gameLogic);
		this.gameScene = new Scene(this.gameScreen);
		addGameListener();
		
		primaryStage.setScene(this.menuScene);
		primaryStage.setTitle("Typing of the Progmeth");
		primaryStage.show();
		
		ThreadHolder.instance.getThreads().get(0).start();
		
	}
	
	public static void toggleScene(){
		if(scene_count == "menuScene"){
			scene_count = "gameScene";
			primaryStage.setScene(gameScene);
			RenderableHolder.instance.removeAll();
			ThreadHolder.instance.removeAll();
			gameLogic.setIRenderable();
			gameLogic.GameLoopStart();
		}
		else{
			scene_count = "menuScene";
		}
	}
	
	public static Stage getStage(){
		return primaryStage;
	}
	
	public static String getScene(){
		return scene_count;
	}
	public void setScene(String s){
		scene_count = s;
	}
	
	private void addGameListener(){
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String key = e.getCode().toString();
				if(!InputHolder.keyPressed.contains(key)){
					InputHolder.keyTriggered.add(key);
					InputHolder.keyPressed.add(key);
				}
				
			}
		});
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String key = e.getCode().toString();
				InputHolder.keyPressed.remove(key);
			}
		});
	}
}
