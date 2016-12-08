package main;

import java.util.ArrayList;

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
	
	public static Main instance = new Main();
	private Scene menuScene;
	private Scene gameScene;
	private GameScreen gameScreen;
	private static Stage primaryStage;
	private static String scene_count; // indicate what scene to be shown.
	
	public static ArrayList<Integer> test1 = new ArrayList<Integer>();
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		scene_count = "menuScene";
		this.menuScene = new Scene(MenuScreen.instance);
		this.gameScene = new Scene(GameScreen.instance);
		
		primaryStage.setScene(this.menuScene);
		primaryStage.setTitle("Typing of the Progmeth");
		primaryStage.show();
		
		ThreadHolder.instance.getThreads().get(0).start();
		
	}
	
	public void toggleScene(){
		if(scene_count == "menuScene"){
			scene_count = "gameScene";
			primaryStage.setScene(gameScene);
			RenderableHolder.instance.removeAll();
			System.out.println("What");
			for(Thread i : ThreadHolder.instance.getThreads()){
				System.out.println(i.getState());
			}
			
		}
		else{
			scene_count = "menuScene";
		}
	}
	
	public Stage getStage(){
		return primaryStage;
	}
	
	public String getScene(){
		return scene_count;
	}
	public void setScene(String s){
		scene_count = s;
	}
}
