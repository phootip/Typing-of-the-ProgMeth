package main;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.MenuScreen;

public class Main extends Application {
	
	public static Main instance;
	private Scene menuScene;
	private Scene gameScene;
	
	public static ArrayList<Integer> test1 = new ArrayList<Integer>();
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.menuScene = new Scene(new MenuScreen());
		primaryStage.setScene(this.menuScene);
		primaryStage.setTitle("Typing of the Progmeth");
		primaryStage.show();
	}

}
