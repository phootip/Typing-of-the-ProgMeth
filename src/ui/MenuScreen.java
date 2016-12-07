package ui;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;
import model.BackGround;
import model.Gun;
import model.MenuText;
import model.OptionText;
import model.Text;

public class MenuScreen extends StackPane{
	
	public static final MenuScreen instance = new MenuScreen();
	private Canvas canvas;
	private GraphicsContext gc;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 40);
	
	public MenuScreen(){
		this.canvas = new Canvas(ConfigOption.width,ConfigOption.height);
		gc = this.canvas.getGraphicsContext2D();
		
		addListener();
		addMenuThread();
		
		this.getChildren().add(canvas);
	}
	
	public void initializeHomeMenu(){
		//Paint Home Menu
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, ConfigOption.width, ConfigOption.height);
		gc.setFont(font);
		this.gc.setFill(Color.WHITE);
		RenderableHolder.instance.add(new MenuText("START",0,gc));
		RenderableHolder.instance.add(new MenuText("OPTION",1,gc));
		RenderableHolder.instance.add(new MenuText("EXIT",2,gc));
		RenderableHolder.instance.add(new Gun(1000,600));
		
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
	
	public GraphicsContext getGc(){
		return this.gc;
	}
	
	private void addMenuThread(){
		// Menu Thread
			ThreadHolder.instance.add(new Thread(new Runnable() {
				@Override
				public void run(){
					RenderableHolder.instance.removeAll();
					initializeHomeMenu();
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
	private void addOptionThread(){
			ThreadHolder.instance.add(new Thread(new Runnable() {
				@Override
				public void run(){
					RenderableHolder.instance.removeAll();
					initializeOptionScreen();
					while(Main.instance.getScene()=="optionScene"){
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
								for(int i=RenderableHolder.instance.getEntities().size()-1;i>-1;i--){
									if(RenderableHolder.instance.getEntities().get(i).isFocused() && RenderableHolder.instance.getEntities().get(i).inHitBox()){
										String name;
										if(RenderableHolder.instance.getEntities().get(i) instanceof MenuText){
											name = ((MenuText)RenderableHolder.instance.getEntities().get(i)).getName();
										}
										else name = ((OptionText)RenderableHolder.instance.getEntities().get(i)).getName();
										//START
										if(name == "START"){
											System.out.println("START");
										}
										//click OPTION
										if(name == "OPTION"){
											System.out.println("OPTION");
											Main.instance.setScene("optionScene");
											ThreadHolder.instance.removeAll();
											addOptionThread();
											ThreadHolder.instance.getThreads().get(0).start();
										}
										//click EXIT
										if(name == "EXIT"){
											System.out.println("EXIT");
											Main.instance.getStage().close();
										}
										//HEALTH
										if(name == "< HEALTH >"){
											System.out.println("< HEALTH >");
											if(((OptionText)RenderableHolder.instance.getEntities().get(i)).inHitBoxRight()){
<<<<<<< HEAD
												ConfigOption.setHealth(1);
=======
												ConfigOption.health++;
											}
											else if(((OptionText)RenderableHolder.instance.getEntities().get(i)).inHitBoxLeft()){
												ConfigOption.health--;
>>>>>>> origin/master
											}
										}
										//HEALTH
										if(name == "HEALTH"){
											System.out.println("HEALTH");
										}
										//BACK
										if(name == "BACK"){
											System.out.println("BACK");
											Main.instance.setScene("menuScene");
											ThreadHolder.instance.removeAll();
											addMenuThread();
											ThreadHolder.instance.getThreads().get(0).start();
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
 