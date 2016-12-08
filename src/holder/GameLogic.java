package holder;

import javafx.animation.AnimationTimer;
import model.*;

public class GameLogic {
	public int score=0;
	private MainCharacter mainCharacter = new MainCharacter(100,395);
	private Gun gun = new Gun(115,410);
	public AnimationTimer gameloop;
	
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
				}
				frameCount++;
			}
			
		};
	}
	
	//add basic Object
	public void setIRenderable(){
		RenderableHolder.instance.removeAll();
		//add bg
		//add Main Character, Gun & Bunger
		RenderableHolder.instance.add(mainCharacter);
		RenderableHolder.instance.add(gun);
		RenderableHolder.instance.add(new Bunger(150,90));
		
	}
	public void GameLoopStart(){
		gameloop.start();
	}
}
