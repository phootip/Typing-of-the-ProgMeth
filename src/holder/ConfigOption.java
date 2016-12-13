package holder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigOption {
	public static int width = 1200;
	public static int height = 780;
	public static int dif = 0;
	public static int health = 100;
	public static int volume = 10;
	public static ArrayList<String> highscore = new ArrayList<>(10);
	
	public static String[] difficultylist = {"EASY","MEDIUM","HARD"};
	public static String difficulty = difficultylist[dif];
	
	public static final int[] healths = {50,100,150};
	public static void setHealth(int i){
		if(i > 150) health = 150;
		else if (i < 50) health = 50;
	}
	public static void setDifficulty(int i){
		if(i >= 2) dif = 2;
		else if (i <= 0) dif = 0;
		else dif = i;
		difficulty = difficultylist[dif];
	}
	
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
	public static String[] rank2 = {"Discrete","Com elec","Prog meth","Com eng ess","Its High Noon","Dig comp logic","Walking Dead",
			"Dead by daylight","Payload","DOTA2 = LOL2","Lisa Sherwood","Michael Myers","Final Fantasy XV","Good Project","Best game",
			"Time Lapse","Nightcore","MisNIGHT","Xenogenesis","World of Tank","Yumetourou","Communication","Eclipse NEON","Kimi no nawa","Indy Kuma"};
	public static String[] rank1 = {"Hello","Hi","ah...","OMG","Really","Ant","Elephant","Rat",
			"Mouse","Easy","Posinw","Hungry","Pain","Mist","Slow","Sleepy","Walking","Toptip","Waii",
			"Coffee","Death","Good","Ghoul","Sexy","Pride","DOTA","Java","KBTG","Arros","Wynter"};
	
	public static void loadHighScore(){
		highscore.clear();
		File f = new File("highscore.txt");
		try {
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				highscore.add(sc.nextLine());
			}
			System.out.println(highscore);
			sc.close();
			// Create and read defualt file again if file not found
		} catch (FileNotFoundException e) {
			createDefualtFile();
			loadHighScore();
		}
	}
	
	public static void createDefualtFile(){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("highscore.txt"));
			String str = "Hello test\nCreate Defualt";
			out.write(str);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sortHighScore(){
		for(int i=0;i<highscore.size();i++){
			for(int j=0;j<i;j++){
				if(Integer.parseInt(highscore.get(i).substring(highscore.get(i).indexOf(":")+1))>
				Integer.parseInt(highscore.get(j).substring(highscore.get(j).indexOf(":")+1))){
					String temp = highscore.get(j);
					highscore.set(j, highscore.get(i));
					highscore.set(i, temp);
				}
			}
		}
		System.out.println(highscore);
	}
	
	public static boolean checkHighScore(int score){
		if(score>Integer.parseInt(highscore.get(9).substring(highscore.get(9).indexOf(":")+1)))return true;
		else return false;
	}
	
	public static boolean addHighScore(String name,int score){
		String final_name = name.replaceAll("\\s","");
		if(final_name.length()<=1){
			
			return false;
		}
		else{
			final_name = final_name.substring(0, final_name.length()-1);
			String result = final_name+":"+score;
			highscore.add(result);
			sortHighScore();
			highscore.remove(10);
			saveHighScore();
			return true;
		}
	}
	
	public static void saveHighScore(){
		try {
			String str = "";
			for(String i:highscore){
				str += i+System.getProperty("line.separator");
			}
			BufferedWriter out = new BufferedWriter(new FileWriter("highscore.txt"));
			out.write(str);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
