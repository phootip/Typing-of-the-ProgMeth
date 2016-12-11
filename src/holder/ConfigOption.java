package holder;

import java.util.ArrayList;

public class ConfigOption {
	public static int width = 1200;
	public static int height = 780;
	
	
	public static String dificulty = "EASY";
	public static int health = 100;
	
	public static final int[] healths = {50,100,150};
	public static void setHealth(int i){
		if(i > 150) health = 300;
		else if (i < 50) health = 50;
	}
	
	public static ArrayList<Integer> highscore_score = new ArrayList<>(10);
	public static ArrayList<String> highscore_name = new ArrayList<>(10);
	
	public static String[] getRank(int rank){
		if(rank == 1)return rank1;
		else if(rank ==2)return rank2;
		else return rank3;
	}
	
	public static String[] rank3 = { "Death is not an escape", "You never dead alone", "We ain't ever getting older",
			"we don't talk anymore", "the more I drink", "the more I think about you", "I don't wanna know",
			"I have died every day", "ryuuga waga teki wo kurau", "ryujin no ken wo kurae", "justice rains from above",
			"I've got you in my sights", "I'm lowering the temperature", "Nano Boost administered",
			"Whe whe whe wheee whee wheee", "My servants never die", "YOU AND YOUR FRIENDS ARE DEAD",
			"The right man in the wrong place", "War. War never changes", "Requiescat in pace", "Its super effective",
			"Tell my wife, I had another wife", "A man chooses, a slave obeys", "Don't make a girl a promise", };
	public static String[] rank2 = {};
	public static String[] rank1 = {"Hello","Hi","ah...","OMG","Really","Ant","Elephant","Rat","Mouse"};
}
