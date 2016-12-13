package ui;

import java.io.FileNotFoundException;
import holder.ConfigOption;
import holder.IRenderable;
import holder.InputHolder;
import holder.RenderableHolder;
import holder.ThreadHolder;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;
import model.BackGround;
import modelText.HighscoreText;
import modelText.MenuText;
import modelText.OptionText;
import modelText.Text;

public class MenuScreen extends StackPane {

	private Canvas canvas;
	public GraphicsContext gc;
	private Font font = Font.font("Cloud", FontWeight.LIGHT, 30);
	

	public MenuScreen() {
		this.canvas = new Canvas(ConfigOption.width, ConfigOption.height);
		gc = this.canvas.getGraphicsContext2D();

		addListener();
		addMenuThread();

		this.getChildren().add(canvas);
	}

	public void initializeMenuScreen() {
		// Paint Home Menu
		gc.drawImage(BackGround.menubg, 0, 0);
		gc.setFont(font);
		this.gc.setFill(Color.WHITE);
		RenderableHolder.instance.add(new MenuText("START", 0, gc));
		RenderableHolder.instance.add(new MenuText("HIGH SCORE", 1, gc));
		RenderableHolder.instance.add(new MenuText("OPTION", 2, gc));
		RenderableHolder.instance.add(new MenuText("EXIT", 3, gc));
		RenderableHolder.instance.getEntities().get(0).setFocus(true);
	}

	public void initializeOptionScreen() {
		// BackGround
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, ConfigOption.width, ConfigOption.height);
		gc.setFont(font);
		this.gc.setFill(Color.WHITE);
		RenderableHolder.instance.add(new OptionText("< HEALTH >", ConfigOption.health + "", 0, gc));
		RenderableHolder.instance.add(new OptionText("< DIFFICULTY >", ConfigOption.difficulty, 1, gc));
		RenderableHolder.instance.add(new OptionText("< VOLUME >", ConfigOption.volume+"", 2, gc));
		RenderableHolder.instance.add(new MenuText("BACK", 3, gc));
		RenderableHolder.instance.getEntities().get(0).setFocus(true);
	}

	public void initializeHighScoreScreen() throws FileNotFoundException {

		// BackGround
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, ConfigOption.width, ConfigOption.height);
		gc.setFont(font);
		this.gc.setFill(Color.WHITE);
		ThreadHolder.instance.getThreads().clear();
		int order=0;
		for(String i: ConfigOption.highscore){
			String name = i.substring(0, i.indexOf(":"));
			int score = Integer.parseInt(i.substring(i.indexOf(":")+1));
			RenderableHolder.instance.add(new HighscoreText(name, score, order, gc));
			order++;
			RenderableHolder.instance.add(new MenuText("BACK", 4, gc));
		}
	}

	public void addMenuThread() {
		// Menu Thread
		ThreadHolder.instance.add(new Thread(new Runnable() {
			@Override
			public void run() {
				RenderableHolder.instance.removeAll();
				initializeMenuScreen();
				while (Main.getScene().equals("menuScene")) {
					for (int i = 0; i < RenderableHolder.instance.getEntities().size(); i++) {
						if (RenderableHolder.instance.getEntities().get(i).isFocused()) {
							((Text) RenderableHolder.instance.getEntities().get(i)).drawFocus(gc);
						} else
							RenderableHolder.instance.getEntities().get(i).draw(gc);
						try {
							Thread.sleep(17);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					InputHolder.postUpdate();
				}
			}
		}));
	}

	private void addListener() {
		// Event Handler Hovering Menu
		this.canvas.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				InputHolder.mouseOnScreen = true;
			}
		});

		this.canvas.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				InputHolder.mouseOnScreen = false;
			}
		});

		this.canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (InputHolder.mouseOnScreen) {
					InputHolder.mouseX = event.getX();
					InputHolder.mouseY = event.getY();
					for (IRenderable i : RenderableHolder.instance.getEntities()) {
						if (i.inHitBox() && (i instanceof MenuText || i instanceof OptionText)) {
							i.setFocus(true);
							for (IRenderable j : RenderableHolder.instance.getEntities()) {
								if (!i.equals(j))
									j.setFocus(false);
							}
						}
					}
				}
			}
		});

		this.canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (InputHolder.mouseOnScreen) {
					InputHolder.mouseX = event.getX();
					InputHolder.mouseY = event.getY();

				}
			}
		});

		this.canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().toString().equals("PRIMARY")) {
					if (!InputHolder.mouseLeftDown) {
						InputHolder.mouseLeftDownTrigger = true;
						for (int i = 0; i < RenderableHolder.instance.getEntities().size(); i++) {
							if (RenderableHolder.instance.getEntities().get(i).isFocused()
									&& RenderableHolder.instance.getEntities().get(i).inHitBox()) {
								String name;
								if (RenderableHolder.instance.getEntities().get(i) instanceof Text) {
									name = ((Text) RenderableHolder.instance.getEntities().get(i)).getName();
								} else name = null;
								// START
								if (name.equals("START")) {
									System.out.println("START");
									Main.toggleScene();
								}
								// click HIGH SCORE
								if (name.equals("HIGH SCORE")) {
									System.out.println("HIGH SCORE");

									RenderableHolder.instance.removeAll();
									try {
										initializeHighScoreScreen();
									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								// click OPTION
								if (name.equals("OPTION")) {
									System.out.println("OPTION");
									RenderableHolder.instance.removeAll();
									initializeOptionScreen();
								}
								// click EXIT
								if (name.equals("EXIT")) {
									System.out.println("EXIT");
									Main.getStage().close();
									System.exit(0);
								}
								// HEALTH
								if (name.equals("< HEALTH >")) {
									System.out.println("< HEALTH >");
									if (((OptionText) RenderableHolder.instance.getEntities().get(i)).inHitBoxRight()) {
										ConfigOption.health += 50;
										ConfigOption.setHealth(ConfigOption.health);
										gc.setFill(Color.BLACK);
										gc.fillRect(880, 80, 200, 60);
										((OptionText) RenderableHolder.instance.getEntities().get(i)).setValue(""+ConfigOption.health);
										
									} else if (((OptionText) RenderableHolder.instance.getEntities().get(i))
											.inHitBoxLeft()) {
										ConfigOption.health -= 50;
										ConfigOption.setHealth(ConfigOption.health);
										gc.setFill(Color.BLACK);
										gc.fillRect(880, 80, 200, 60);
										((OptionText) RenderableHolder.instance.getEntities().get(i)).setValue(""+ConfigOption.health);
									}
								}
								// DIFICULTY
								if (name.equals("< DIFFICULTY >")) {
									System.out.println("< DIFFICULTY >");
									if (((OptionText) RenderableHolder.instance.getEntities().get(i)).inHitBoxRight()) {
										ConfigOption.dif++;
										ConfigOption.setDifficulty(ConfigOption.dif);
										gc.setFill(Color.BLACK);
										gc.fillRect(880, 180, 200, 60);
										((OptionText) RenderableHolder.instance.getEntities().get(i)).setValue(ConfigOption.difficulty);
									} else if (((OptionText) RenderableHolder.instance.getEntities().get(i)).inHitBoxLeft()) {
										ConfigOption.dif--;
										ConfigOption.setDifficulty(ConfigOption.dif);
										gc.setFill(Color.BLACK);
										gc.fillRect(880, 180, 200, 60);
										((OptionText) RenderableHolder.instance.getEntities().get(i)).setValue(ConfigOption.difficulty);
									}
								}

								if (name.equals("< VOLUME >")) {
									System.out.println("< VOLUME >");
									if (((OptionText) RenderableHolder.instance.getEntities().get(i)).inHitBoxRight() &&
											ConfigOption.volume!=10) {
										ConfigOption.volume++;
										gc.setFill(Color.BLACK);
										gc.fillRect(880, 280, 200, 60);
										((OptionText) RenderableHolder.instance.getEntities().get(i)).setValue(ConfigOption.volume+"");
									} else if (((OptionText) RenderableHolder.instance.getEntities().get(i)).inHitBoxLeft() &&
											ConfigOption.volume!=0) {
										ConfigOption.volume--;
										gc.setFill(Color.BLACK);
										gc.fillRect(880, 280, 200, 60);
										((OptionText) RenderableHolder.instance.getEntities().get(i)).setValue(ConfigOption.volume+"");
									}
								}
								// BACK
								if (name.equals("BACK")) {
									System.out.println("BACK");
									RenderableHolder.instance.removeAll();
									initializeMenuScreen();
								}
							}
						}
						InputHolder.postUpdate();
					}
					InputHolder.mouseLeftDown = true;
				}
			}
		});

		this.canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().toString().equals("PRIMARY")) {
					InputHolder.mouseLeftDown = false;
				}
			}
		});
	}
}
 