package holder;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import model.*;
import ui.GameScreen;

public class GameLogic {
	private int score=0;
	private MainCharacter mainCharacter = new MainCharacter(100,395);
	private Gun gun = new Gun(115,410);
	private AnimationTimer gameloop;
	private int chapter = 1;
	private GraphicsContext gc;
	private ArrayList<String> wave1 = new ArrayList<>();
	private ArrayList<String> wave2 = new ArrayList<>();
	private ArrayList<String> wave3 = new ArrayList<>();
	private ArrayList<String> used = new ArrayList<>();
	private ArrayList<Zombie> zombies = new ArrayList<>();
	
	public GameLogic(){
		gameloop = new AnimationTimer(){
			Long start=0l;
			int frameCount = 0;
			@Override
			public void handle(long now) {
				long diff = now-start;
				if(diff>=1000000000l){ // 1 second
					System.out.println(frameCount);
					frameCount=0;
					start = now;
				}
				frameCount++;
				paint();
				InputHolder.postUpdate();
			}
			
		};
	}
	
	public void update(){
		
	}
	
	public void paint(){
		for(int i=0;i<RenderableHolder.instance.getEntities().size();i++){
			RenderableHolder.instance.getEntities().get(i).draw(gc);
		}
	}
	//add basic Object
	public void setIRenderable(){
		RenderableHolder.instance.removeAll();
		//add bg
		//add Main Character, Gun & Bunger
		RenderableHolder.instance.add(mainCharacter);
		RenderableHolder.instance.add(gun);
		RenderableHolder.instance.add(new Bunger(150,90));
		RenderableHolder.instance.add(new Zombie(600,390, "Hello World",gc));
		
	}
	public void GameLoopStart(){
		gameloop.start();
	}
	public void setGc(GraphicsContext gc){
		this.gc = gc;
	}
}
