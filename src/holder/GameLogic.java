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
	private GraphicsContext gc;
	private ArrayList<String> wave1 = new ArrayList<>();
	private ArrayList<String> wave2 = new ArrayList<>();
	private ArrayList<String> wave3 = new ArrayList<>();
	private ArrayList<String> used = new ArrayList<>();
	private ArrayList<Zombie> zombies = new ArrayList<>();
	private ArrayList<String> firstChar = new ArrayList<>();
	private int chapter = 1;
	private int wave = 1;
	private int hitting = 0;
	private boolean focusing = false;
	private boolean setupChapter = true;
	
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
				if(setupChapter){
					setupChapter = false;
					addZombies();
					removeSpace(wave1);
				}
				if(!focusing && InputHolder.keyTriggered.size()!=0){
					for(int i = 0;i<firstChar.size();i++){
						if(firstChar.get(i)==InputHolder.getLastTrigger()){
							focusing = true;
							System.out.println(i);
							hitting = i;
							//set focus on zombie                              //+1 Skip Bg
							((Zombie) RenderableHolder.instance.getEntities().get(hitting+1)).hit();
							wave1.set(hitting, wave1.get(hitting).substring(1));
						}
					}
				} else if(focusing && InputHolder.keyTriggered.size()!=0){
					if(InputHolder.getLastTrigger().equals(wave1.get(hitting).substring(0,1).toUpperCase())){
						((Zombie) RenderableHolder.instance.getEntities().get(hitting+1)).hit();
						wave1.set(hitting, wave1.get(hitting).substring(1));
					}
				}
				
				frameCount++;
				paint();
				InputHolder.postUpdate();
			}
			
		};
	}
	
	public void update(){
		
	}
	
	public void addZombies(){
		if(ConfigOption.dificulty=="EASY"){
			if(chapter == 1){
				//fetch word
				wave1.add("Hello World");
				firstChar.add("H");
				wave1.add("Prog Meth");
				firstChar.add("P");
				RenderableHolder.instance.add(new Zombie(800,390,wave1.get(0),gc));
				RenderableHolder.instance.add(new Zombie(750,600,wave1.get(1),gc));
			}
		}
	}
	
	public void removeSpace(ArrayList<String> wave){
		for(int i = 0;i<wave.size();i++){
			wave.set(i, wave.get(i).replaceAll("\\s",""));
		}
	}
	
	//Call draw in RenderableHolder
	public void paint(){
		for(int i=0;i<RenderableHolder.instance.getEntities().size();i++){
			RenderableHolder.instance.getEntities().get(i).draw(gc);
		}
	}
	//add basic Object
	public void setIRenderable(){
		RenderableHolder.instance.removeAll();
		//add bg
		RenderableHolder.instance.add(new BackGround());
		//add Main Character, Gun & Bunger
		RenderableHolder.instance.add(mainCharacter);
		RenderableHolder.instance.add(gun);
		RenderableHolder.instance.add(new Bunger(150,90));
	}
	public void GameLoopStart(){
		gameloop.start();
	}
	public void setGc(GraphicsContext gc){
		this.gc = gc;
	}
}
