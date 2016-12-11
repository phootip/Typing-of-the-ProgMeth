package holder;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.*;
import modelText.MenuText;
import modelText.StageText;
import ui.GameScreen;

public class GameLogic {
	private int score=0;
	private int health;
	private String word;
	private String char1;
	private AnimationTimer gameloop;
	private GraphicsContext gc;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 30);
	private ArrayList<String> wave1 = new ArrayList<>();
	private ArrayList<String> used = new ArrayList<>();
	private int chapter = 1;
	private int wave = 1;
	private int hitting = 0;
	private int hitting2 =0;
	private boolean gameStart = true;
	private boolean setupChapter = false;
	private boolean focusing = false;
	private boolean endChapter = false;
	private boolean gameOver = false;
	private String name = "";
	
	public GameLogic(){
		gameloop = new AnimationTimer(){
			Long start=0l;
			int frameCount = 0;
			int wait = 0;
			int hit_count = 0;
			@Override
			public void handle(long now) {
				long diff = now-start;
				if(diff>=1000000000l){ // 1 second
					System.out.println(frameCount);
					frameCount=0;
					start = now;
				}
				
				if(gameStart){
					gc.setFont(font);
					if(wait==0){
						health = ConfigOption.health;
						RenderableHolder.instance.add(new MenuText("Ready",-4.5,gc));
					}
					wait++;
					if(wait>119){
						setupChapter = true;
						gameStart = false;
						//Remove Ready Text
						RenderableHolder.instance.remove(1);
						wait=0;
					}
				}
				if(!gameOver){
					//set Up Level
					if(setupChapter){
						for(IRenderable i: RenderableHolder.instance.getEntities()){
							if(i instanceof StageText)((StageText) i).setDestroy(true);
						}
						
						RenderableHolder.instance.add(new StageText("Chapter "+chapter,gc));
						addZombies();
						removeSpace(wave1);
						setupChapter = false;
					}
					// isn't focusing
					if(!focusing && InputHolder.keyTriggered.size()!=0){
						for(int i = 0;i<wave1.size();i++){
							if(wave1.get(i).substring(0,1).toUpperCase().equals(InputHolder.getLastTrigger()) && 
									((Zombie) RenderableHolder.instance.getEntities().get(i+4)).getX()<1300){
								focusing = true;
								hitting = i;
								hitting2 = RenderableHolder.instance.getEntities().size();
								((Zombie) RenderableHolder.instance.getEntities().get(hitting+4)).setZ(Integer.MAX_VALUE-1);
								((Zombie) RenderableHolder.instance.getEntities().get(hitting+4)).setFocus(true);
								//set focus on zombie                              //+4 Skip Bg main bunger gun
								((Zombie) RenderableHolder.instance.getEntities().get(hitting+4)).hit();
								wave1.set(hitting, wave1.get(hitting).substring(1));
							}
						}
					} else if(focusing && InputHolder.keyTriggered.size()!=0){  // already set focused zombie
						if(InputHolder.getLastTrigger().equals(wave1.get(hitting).substring(0,1).toUpperCase())){
							((Zombie) RenderableHolder.instance.getEntities().get(hitting2-2)).hit();
							wave1.set(hitting, wave1.get(hitting).substring(1));
							// Zombie Dead
							if(wave1.get(hitting).equals("")){
								focusing = false;
								wave1.remove(hitting);
								RenderableHolder.instance.remove(hitting2-2);
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
							if(((Zombie) RenderableHolder.instance.getEntities().get(i)).Move());
							else{
								hit_count++;
								if(hit_count>29){
									health-=5;
									hit_count = 0;
									if(health<=0){
										gameOver = true;
										paint();
										//set gc for game over
										gc.setGlobalAlpha(0.1);
										gc.setLineWidth(10);
									}
								}
							}
						}
					}
					frameCount++;
					removeDestroyedEntities();
					RenderableHolder.instance.sort();
					paint();
					InputHolder.postUpdate();
				} 
				else{ // GAME OVER !!
					if(wait > 20){
						if(hit_count<5)gc.fillRect(0, 0, ConfigOption.width, ConfigOption.height);
						if(hit_count>=20){
							gc.setFill(Color.BLACK);
							gc.setGlobalAlpha(1);
							gc.fillText("ENTER YOUR NAME", ConfigOption.width/2-150, ConfigOption.height/2);
						}else{
							gc.fillText("GAME OVER", ConfigOption.width/2-100, ConfigOption.height/2-50);
							gc.strokeText("GAME OVER", ConfigOption.width/2-100, ConfigOption.height/2-50);
						}
						if(InputHolder.keyTriggered.size()!=0){
							
						}
						wait = 0;
						hit_count++;
					}
					wait++;
				}
			}
		};
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
				int ran = (int)(Math.random()*range);
				word = a[ran];
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
		gc.setFill(Color.RED);
		gc.fillText("HEALTH : "+health, 10, 50);
	}
	
	
	//add basic Object
	public void setIRenderable(){
		RenderableHolder.instance.removeAll();
		//add bg
		RenderableHolder.instance.add(new BackGround());
		//add Main Character, Gun & Bunger
		RenderableHolder.instance.add(new MainCharacter(100,395));
		RenderableHolder.instance.add(new Gun(115,410));
		RenderableHolder.instance.add(new Bunger(150,90));
	}
	public void GameLoopStart(){
		gameloop.start();
	}
	public void setGc(GraphicsContext gc){
		this.gc = gc;
	}
}
