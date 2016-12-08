package ui;

import java.util.ArrayList;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import holder.ConfigOption;
import holder.IRenderable;
import holder.InputHolder;
import holder.RenderableHolder;
import holder.ThreadHolder;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;
import model.BackGround;
import model.Gun;
import model.HighscoreText;
import model.MenuText;
import model.OptionText;
import model.Text;

public class MenuScreen extends StackPane{
	
	private Canvas canvas;
	private GraphicsContext gc;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 30);
	
	public MenuScreen(){
		this.canvas = new Canvas(ConfigOption.width,ConfigOption.height);
		gc = this.canvas.getGraphicsContext2D();
		
		addListener();
		addMenuThread();
		
		this.getChildren().add(canvas);
	}
	
	public void initializeMenuScreen(){
		//Paint Home Menu
		gc.drawImage(BackGround.menubg, 0, 0);
		gc.setFont(font);
		this.gc.setFill(Color.WHITE);
		RenderableHolder.instance.add(new MenuText("START",0,gc));
		RenderableHolder.instance.add(new MenuText("HIGH SCORE",1,gc));
		RenderableHolder.instance.add(new MenuText("OPTION",2,gc));
		RenderableHolder.instance.add(new MenuText("EXIT",3,gc));
		
	}
	
	public void initializeOptionScreen(){
		//BackGround
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, ConfigOption.width, ConfigOption.height);
		gc.setFont(font);
		this.gc.setFill(Color.WHITE);
		RenderableHolder.instance.add(new OptionText("< HEALTH >",ConfigOption.health+"",0,gc));
		RenderableHolder.instance.add(new OptionText("< DIFICULTY >",ConfigOption.dificulty,1,gc));
		RenderableHolder.instance.add(new OptionText("< SOUND >","10",2,gc));
		RenderableHolder.instance.add(new MenuText("BACK",3,gc));
	}
	
	public void initializeHighScoreScreen(){
		//BackGround
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, ConfigOption.width, ConfigOption.height);
		gc.setFont(font);
		this.gc.setFill(Color.WHITE);
		ArrayList<HighscoreText> highscore = new ArrayList<HighscoreText>(10);
		highscore.add(new HighscoreText("Hater", 100, 0, gc));
		highscore.add(new HighscoreText("Elle", 95, 1, gc));
		highscore.add(new HighscoreText("Lily",90, 2, gc));
		highscore.add(new HighscoreText("Leo", 85, 3, gc));
		highscore.add(new HighscoreText("OPuto", 80,4, gc));
		highscore.add(new HighscoreText("Ito", 75,5, gc));
		highscore.add(new HighscoreText("Tera", 70, 6, gc));
		highscore.add(new HighscoreText("Serena", 65, 7, gc));
		highscore.add(new HighscoreText("Mickie", 60, 8, gc));
		highscore.add(new HighscoreText("Error", 55, 9, gc));
		for(HighscoreText i : highscore){
			RenderableHolder.instance.add(i);
		}
		RenderableHolder.instance.add(new MenuText("BACK",4,gc));
	}
	
	public GraphicsContext getGc(){
		return this.gc;
	}
	
	private void addMenuThread(){
		// Menu Thread
			ThreadHolder.instance.add(new Thread(new Runnable() {
				@Override
				public void run(){
					RenderableHolder.instance.removeAll();
					initializeMenuScreen();
					while(Main.instance.getScene()=="menuScene"){
						for(int i=0;i<RenderableHolder.instance.getEntities().size();i++){
							if(RenderableHolder.instance.getEntities().get(i).isFocused()){
								((Text) RenderableHolder.instance.getEntities().get(i)).drawFocus(gc);
							}
							else RenderableHolder.instance.getEntities().get(i).draw(gc);
							try {
								Thread.sleep(17);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}));
	}

	private void addListener(){
		//Event Handler Hovering Menu
				this.canvas.setOnMouseEntered(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						// TODO Auto-generated method stub
						InputHolder.mouseOnScreen = true;
					}
				});
				
				this.canvas.setOnMouseExited(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event){
						InputHolder.mouseOnScreen = false;
					}
				});
				
				this.canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event){
						if(InputHolder.mouseOnScreen){
							InputHolder.mouseX = event.getX();
							InputHolder.mouseY = event.getY();
							for(IRenderable i: RenderableHolder.instance.getEntities()){
								if(i.inHitBox() && (i instanceof MenuText || i instanceof OptionText)){
									i.setFocus(true);
									for(IRenderable j: RenderableHolder.instance.getEntities()){
										if(!i.equals(j))j.setFocus(false);
									}
								}
							}
						}
					}
				});
				
				this.canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event){
						if(InputHolder.mouseOnScreen){
							InputHolder.mouseX = event.getX();
							InputHolder.mouseY = event.getY();
							
						}
					}
				});
				
				this.canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if (event.getButton().toString() == "PRIMARY") {
							if(InputHolder.mouseLeftDown == false){
								InputHolder.mouseLeftDownTrigger = true;
								for(int i=0;i<RenderableHolder.instance.getEntities().size();i++){
									if(RenderableHolder.instance.getEntities().get(i).isFocused() && RenderableHolder.instance.getEntities().get(i).inHitBox()){
										String name;
										if(RenderableHolder.instance.getEntities().get(i) instanceof MenuText){
											name = ((MenuText)RenderableHolder.instance.getEntities().get(i)).getName();
										}
										else name = ((OptionText)RenderableHolder.instance.getEntities().get(i)).getName();
										//START
										if(name == "START"){
											System.out.println("START");
											//BG
											gc.setFill(Color.BLACK);
											gc.fillRect(0, 0, ConfigOption.width, ConfigOption.height);
											Main.instance.toggleScene();
										}
										if(name == "HIGH SCORE"){
											System.out.println("HIGH SCORE");
											RenderableHolder.instance.removeAll();
											initializeHighScoreScreen();
										}
										//click OPTION
										if(name == "OPTION"){
											System.out.println("OPTION");
											RenderableHolder.instance.removeAll();
											initializeOptionScreen();
										}
										//click EXIT
										if(name == "EXIT"){
											System.out.println("EXIT");
											Main.instance.getStage().close();
											System.exit(0);
										}
										//HEALTH
										if(name == "< HEALTH >"){
											System.out.println("< HEALTH >");
											if(((OptionText)RenderableHolder.instance.getEntities().get(i)).inHitBoxRight()){
												ConfigOption.setHealth(1);
												ConfigOption.health++;
											}
											else if(((OptionText)RenderableHolder.instance.getEntities().get(i)).inHitBoxLeft()){
												ConfigOption.health--;
											}
										}
										//HEALTH
										if(name == "HEALTH"){
											System.out.println("HEALTH");
										}
										//BACK
										if(name == "BACK"){
											System.out.println("BACK");
											RenderableHolder.instance.removeAll();
											initializeMenuScreen();
										}
									}
								}
								InputHolder.postUpdate();
							}
							InputHolder.mouseLeftDown = true;
						}
					}
				});
				
				this.canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if (event.getButton().toString() == "PRIMARY") {
							InputHolder.mouseLeftDown = false;
						}
					}
				});
	}
}
 