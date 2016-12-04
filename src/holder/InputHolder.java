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
}
