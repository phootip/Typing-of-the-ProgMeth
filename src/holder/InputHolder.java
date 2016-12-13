package holder;

import java.util.ArrayList;

public class InputHolder {
	//hold input value
	public static double mouseX, mouseY;
	public static boolean mouseLeftDown,mouseOnScreen;
	public static boolean mouseLeftDownTrigger;
	public static ArrayList<String> keyPressed = new ArrayList<>();
	public static ArrayList<String> keyTriggered = new ArrayList<>();
	
	public static void postUpdate(){
		mouseLeftDownTrigger = false;
		keyTriggered.clear();
	}
	
	public static String getLastTrigger(){
		if(keyTriggered.get(keyTriggered.size()-1).equals("PERIOD"))return ".";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("QUOTE"))return"'";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("COMMA"))return",";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("SPACE"))return" ";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("ALT"))return" ";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("CONTROL"))return" ";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("SLASH"))return"/";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("SPACE"))return" ";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("BACK_SLASH"))return"\\";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("MINUS"))return"-";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("ADD"))return"+";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("EQUALS"))return"=";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("SUBTRACT"))return"-";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("SHIFT"))return" ";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("TAB"))return" ";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("DIGIT1"))return"1";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("DIGIT2"))return"2";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("DIGIT3"))return"3";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("DIGIT4"))return"4";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("DIGIT5"))return"5";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("DIGIT6"))return"6";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("DIGIT7"))return"7";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("DIGIT8"))return"8";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("DIGIT9"))return"9";
		else if(keyTriggered.get(keyTriggered.size()-1).equals("DIGIT0"))return"0";
		return keyTriggered.get(keyTriggered.size()-1);
	}
}
