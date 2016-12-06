package main;

import java.util.ArrayList;

import holder.IRenderable;
import holder.RenderableHolder;
import holder.ThreadHolder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.MenuScreen;

public class Main extends Application {
	
	public static Main instance = new Main();
	private Scene menuScene;
	private Scene gameScene;
	private static Stage stage;
	private static String scene_count; // indicate what scene to be shown.
	
	public static ArrayList<Integer> test1 = new ArrayList<Integer>();
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		scene_count = "menuScene";
		this.menuScene = new Scene(MenuScreen.instance);
		
		primaryStage.setScene(this.menuScene);
		primaryStage.setTitle("Typing of the Progmeth");
		primaryStage.show();
		
		ThreadHolder.instance.getThreads().get(0).start();
		
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public String getScene(){
		return scene_count;
	}
	public void setScene(String s){
		scene_count = s;
	}
}
