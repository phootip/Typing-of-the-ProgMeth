package holder;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.*;
import modelText.StageText;
import ui.GameScreen;

public class GameLogic {
	private int score=0;
	private int health;
	private String word;
	private String char1;
	private MainCharacter mainCharacter = new MainCharacter(100,395);
	private Gun gun = new Gun(115,410);
	private AnimationTimer gameloop;
	private GraphicsContext gc;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 30);
	private ArrayList<String> wave1 = new ArrayList<>();
	private ArrayList<String> used = new ArrayList<>();
	private int chapter = 1;
	private int wave = 1;
	private int hitting = 0;
	private boolean gameStart = true;
	private boolean setupChapter = false;
	private boolean focusing = false;
	private boolean endChapter = false;
	
	public GameLogic(){
		gameloop = new AnimationTimer(){
			Long start=0l;
			int frameCount = 0;
			int wait = 0;
			@Override
			public void handle(long now) {
				long diff = now-start;
				if(diff>=1000000000l){ // 1 second
					System.out.println(frameCount);
					frameCount=0;
					start = now;
				}
				
				if(gameStart){
					gc.setFill(Color.WHITE);
					gc.setStroke(Color.BLACK);
					if(wait==0)RenderableHolder.instance.add(new StageText("Ready",gc));
					wait++;
					if(wait>119){
						setupChapter = true;
						gameStart = false;
					}
				}
				//set Up Level
				if(setupChapter){
					for(IRenderable i: RenderableHolder.instance.getEntities()){
						if(i instanceof StageText)((StageText) i).setDestroy(true);
					}
					gc.setFont(font);
					RenderableHolder.instance.add(new StageText("Chapter "+chapter,gc));
					addZombies();
					removeSpace(wave1);
					setupChapter = false;
				}
				// isn't focusing
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
								endChapter = true;
							}
						}
					}
				}
				
				// Wait after all zombies are dead.
				if(endChapter){
					wait++;
					if(wait == 120){
						wait=0;
						setupChapter = true;
						endChapter = false;
					}
				}
				for(int i=0;i<RenderableHolder.instance.getEntities().size();i++){
					if(RenderableHolder.instance.getEntities().get(i) instanceof Zombie){
						((Zombie) RenderableHolder.instance.getEntities().get(i)).Move();
					}
				}
				frameCount++;
				removeDestroyedEntities();
				paint();
				InputHolder.postUpdate();
			}
			
		};
	}
	
	public void update(){
		
	}
	
	private void addZombies(){
		int order = 1;
		if(ConfigOption.dificulty=="EASY"){
			if(chapter == 1){
				//fetch word
				fetchWord(1,5,wave1);
				for(String i: wave1){
					RenderableHolder.instance.add(new Zombie(1000+200*order+(int)(Math.random()*401),
							90+(int)(Math.random()*601),i,gc));
					order++;
				}
				order =0;
			}
			if(chapter == 2){
				//fetch word
				fetchWord(3,5,wave1);
				for(String i: wave1){
					RenderableHolder.instance.add(new Zombie(1200+200*order+(int)(Math.random()*401),
							90+(int)(Math.random()*601),i,gc));
					order++;
				}
				order=0;
			}
		}
	}
	
	//fetch word
	private void fetchWord(int rank,int amount,ArrayList<String> wave){
		String[] a = ConfigOption.getRank(rank);
		int range = a.length;
		for(int i =0;i<amount;i++){
			do{
				word = a[(int)(Math.random()*range)];
				char1 = word.substring(0,1).toUpperCase();
			}while(used.contains(char1));
			used.add(char1);
			wave.add(word);
		}
		used.clear();
	}
	
	public void removeSpace(ArrayList<String> wave){
		for(int i = 0;i<wave.size();i++){
			wave.set(i, wave.get(i).replaceAll("\\s",""));
		}
	}
	
	private void removeDestroyedEntities(){
		for(int i=0;i<RenderableHolder.instance.getEntities().size();i++){
			if(RenderableHolder.instance.getEntities().get(i).isDestroy())RenderableHolder.instance.remove(i);
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
