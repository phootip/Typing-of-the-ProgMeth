package model;

import holder.ConfigOption;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HighscoreText extends Text{
	private String player_name;
	private int player_score;
	private double x;
	private double y;
	
	public HighscoreText(String name,int score,int order,GraphicsContext gc){
		super(name,order,gc);
		this.player_name=name;
		this.player_score=score;
		this.x = ConfigOption.width/5-font_width/2;
		this.y = ConfigOption.height/2+2*font_height+order*100;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillText(name, x,y);
	}
	
	@Override
	public void drawFocus(GraphicsContext gc){
		gc.setFill(Color.CRIMSON);
		gc.fillText(name, x, y);
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean inHitBox() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFocused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus(boolean isfouced) {
		// TODO Auto-generated method stub
		
	}
	
	private String getRecord() {
		return name.trim() + ":" + score;
	}
}
