package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import holder.ConfigOption;
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
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelText.OptionText;
import modelText.Text;
import ui.GameScreen;
import ui.MenuScreen;

public class Main extends Application {
	
	public static Main instance;
	private static Scene menuScene;
	private static Scene gameScene;
	private GameScreen gameScreen;
	private static MenuScreen menuScreen;
	private static GameLogic gameLogic;
	private static Stage primaryStage;
	private static String scene_count; // indicate what scene to be shown.
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage){
		primaryStage.setResizable(false);
		ConfigOption.loadHighScore();
		ConfigOption.sortHighScore();
		this.primaryStage = primaryStage;
		scene_count = "menuScene";
		this.gameLogic = new GameLogic();
		this.menuScreen = new MenuScreen();
		this.menuScene = new Scene(this.menuScreen);
		this.gameScreen = new GameScreen(this.gameLogic);
		this.gameScene = new Scene(this.gameScreen);
		addListener();
		
		primaryStage.setScene(this.menuScene);
		primaryStage.setTitle("Typing of the Progmeth");
		primaryStage.show();
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
			RenderableHolder.instance.removeAll();
			ThreadHolder.instance.removeAll();
			primaryStage.setScene(menuScene);
			menuScreen.addMenuThread();
			ThreadHolder.instance.getThreads().get(0).start();
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
	
	private void addListener(){
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
		menuScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String key = e.getCode().toString();
				if(!InputHolder.keyPressed.contains(key)){
					InputHolder.keyTriggered.add(key);
					InputHolder.keyPressed.add(key);
					System.out.println(InputHolder.getLastTrigger());
					int current = 0;
					for (int i = 0; i < RenderableHolder.instance.getEntities().size(); i++) {
						if (RenderableHolder.instance.getEntities().get(i).isFocused())
							current = i;
					}
					String name = ((Text)RenderableHolder.instance.getEntities().get(current)).getName();
					if(InputHolder.getLastTrigger().equals("UP")&&current!=0){
						RenderableHolder.instance.getEntities().get(current-1).setFocus(true);
						RenderableHolder.instance.getEntities().get(current).setFocus(false);
					}else if(InputHolder.getLastTrigger().equals("DOWN")&&
							current!=RenderableHolder.instance.getEntities().size()-1){
						RenderableHolder.instance.getEntities().get(current+1).setFocus(true);
						RenderableHolder.instance.getEntities().get(current).setFocus(false);
					}else if(InputHolder.getLastTrigger().equals("ENTER")){
						if (name.equals("START")) {
							System.out.println("START");
							Main.toggleScene();
						}
						if(name.equals("HIGH SCORE")){
							System.out.println("HIGH SCORE");

							RenderableHolder.instance.removeAll();
							try {
								menuScreen.initializeHighScoreScreen();
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						// ENTER CODE
						if(name.equals("ENTER CODE")){
							System.out.println("ENTER CODE");
							menuScreen.addCodeThread();
							ThreadHolder.instance.getThreads().get(1).start();
						}
						//9999 HEALTH
						if(name.equals("9999 HEALTH")){
							System.out.println("9999 HEALTH");
							ConfigOption.health = 9999;
						}
						if (name.equals("OPTION")) {
							System.out.println("OPTION");
							RenderableHolder.instance.removeAll();
							menuScreen.initializeOptionScreen();
						}
						if (name.equals("EXIT")) {
							System.out.println("EXIT");
							Main.getStage().close();
							System.exit(0);
						}
						if (name.equals("BACK")) {
							System.out.println("BACK");
							RenderableHolder.instance.removeAll();
							menuScreen.initializeMenuScreen();
						}
					}else if(InputHolder.getLastTrigger().equals("LEFT")){
						if (name.equals("< HEALTH >")) {
							System.out.println("< HEALTH >");
							ConfigOption.health -= 50;
							ConfigOption.setHealth(ConfigOption.health);
							menuScreen.gc.setFill(Color.BLACK);
							menuScreen.gc.fillRect(880, 80, 200, 60);
							((OptionText) RenderableHolder.instance.getEntities().get(current)).setValue(""+ConfigOption.health);
						}
						if (name.equals("< DIFFICULTY >")) {
							ConfigOption.dif--;
							ConfigOption.setDifficulty(ConfigOption.dif);
							menuScreen.gc.setFill(Color.BLACK);
							menuScreen.gc.fillRect(880, 180, 200, 60);
							((OptionText) RenderableHolder.instance.getEntities().get(current)).setValue(ConfigOption.difficulty);
						}
					}else if(InputHolder.getLastTrigger().equals("RIGHT")){
						if (name.equals("< HEALTH >")) {
							System.out.println("< HEALTH >");
							ConfigOption.health += 50;
							ConfigOption.setHealth(ConfigOption.health);
							menuScreen.gc.setFill(Color.BLACK);
							menuScreen.gc.fillRect(880, 80, 200, 60);
							((OptionText) RenderableHolder.instance.getEntities().get(current)).setValue(""+ConfigOption.health);
						}
						if (name.equals("< DIFFICULTY >")) {
							ConfigOption.dif++;
							ConfigOption.setDifficulty(ConfigOption.dif);
							menuScreen.gc.setFill(Color.BLACK);
							menuScreen.gc.fillRect(880, 180, 200, 60);
							((OptionText) RenderableHolder.instance.getEntities().get(current)).setValue(ConfigOption.difficulty);
						}
					}
				}
				
			}
		});
		menuScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String key = e.getCode().toString();
				InputHolder.keyPressed.remove(key);
			}
		});
	}
}
