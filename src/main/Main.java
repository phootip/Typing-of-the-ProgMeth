package main;

import java.util.ArrayList;

import holder.GameLogic;
import holder.IRenderable;
import holder.RenderableHolder;
import holder.ThreadHolder;
import javafx.application.Application;
import javafx.scene.Scene;
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
			gameLogic.setIRenderable();;
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
}
