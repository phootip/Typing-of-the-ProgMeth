package holder;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NonameException extends Exception{
	
	private Alert alert;
	public NonameException(){
		alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("ERROR");
		alert.setContentText("PLEASE ENTER YOUR NAME");
		System.out.println("ERROR");
		InputHolder.postUpdate();
	}
	public void show(){
		alert.show();
	}
}