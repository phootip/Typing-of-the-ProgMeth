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
					for(int i = 0;i<wave1.size();i++){
						if(wave1.get(i).substring(0,1).toUpperCase().equals(InputHolder.getLastTrigger())){
							focusing = true;
							hitting = i;
							//set focus on zombie                              //+1 Skip Bg
							((Zombie) RenderableHolder.instance.getEntities().get(hitting+1)).hit();
							wave1.set(hitting, wave1.get(hitting).substring(1));
						}
					}
				} else if(focusing && InputHolder.keyTriggered.size()!=0){
					System.out.println(InputHolder.getLastTrigger());
					System.out.println(wave1.get(hitting).substring(0,1).toUpperCase());
					if(InputHolder.getLastTrigger().equals(wave1.get(hitting).substring(0,1).toUpperCase())){
						((Zombie) RenderableHolder.instance.getEntities().get(hitting+1)).hit();
						wave1.set(hitting, wave1.get(hitting).substring(1));
						// Zombie Dead
						if(wave1.get(hitting).equals("")){
							focusing = false;
							wave1.remove(hitting);
							RenderableHolder.instance.remove(hitting+1);
							if(wave1.size()==0){
								chapter++;
								setupChapter = true;
							}
						}
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
				wave1.add("Prog Meth");
														// Have to random coordinate and time spawn
				RenderableHolder.instance.add(new Zombie(800,390,wave1.get(0),gc));
				RenderableHolder.instance.add(new Zombie(750,600,wave1.get(1),gc));
			}
			if(chapter == 2){
				//fetch word
				wave1.add("ah...");
				wave1.add("Mis Night");
				RenderableHolder.instance.add(new Zombie(800,100,wave1.get(0),gc));
				RenderableHolder.instance.add(new Zombie(500,250,wave1.get(1),gc));
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
