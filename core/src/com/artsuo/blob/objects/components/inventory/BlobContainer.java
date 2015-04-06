package com.artsuo.blob.objects.components.inventory;

import com.artsuo.blob.Const;
import com.artsuo.blob.Engine;
import com.artsuo.blob.abilities.forms.Form;
import com.artsuo.blob.objects.GameCharacter;
import com.artsuo.blob.util.Timer;

public class BlobContainer {
	
	public static int MAX_RED = Const.MAX_BLOBS;
	public static int MAX_GREEN = Const.MAX_BLOBS;
	public static int MAX_BLUE = Const.MAX_BLOBS;
	public static int MED_RED = Const.MED_BLOBS;
	public static int MED_GREEN = Const.MED_BLOBS;
	public static int MED_BLUE = Const.MED_BLOBS;
	public static int MIN_RED = Const.MIN_BLOBS;
	public static int MIN_GREEN = Const.MIN_BLOBS;
	public static int MIN_BLUE = Const.MIN_BLOBS;
	public static long DECREASE_INTERVAL = 5000;
	public static long PROCESS_INTERVAL = 1000;
	public static int DECREASE = 1;
	public static float OVERFLOW_RATIO = 3;
	
	private int red;
	private int green;
	private int blue;
	private int golden;
	private Timer decreaseTimer;
	private Timer processTimer;
	private GameCharacter gc;
	
	public BlobContainer(GameCharacter gc) {
		this.gc = gc;
		this.red = 0;
		this.green = 0;
		this.blue = 0;
		this.golden = 0;
		this.decreaseTimer = new Timer(DECREASE_INTERVAL);
		this.processTimer = new Timer(PROCESS_INTERVAL);
	}
	
	public void update() {
		// Process Blobs&Forms
		if (processTimer.isOver()) {
			// Process Red
			if (red == MAX_RED) {
				if (gc.getCurrentForm().getFormType() != Form.FIRE_FORM) {
					gc.changeForm(Form.FIRE_FORM);
				}
			} else if (red < MIN_RED) {
				if (gc.getCurrentForm().getFormType() == Form.FIRE_FORM || 
						gc.getCurrentForm().getFormType() == Form.GAS_FORM) {
					if (gc.getCurrentForm().getFormType() != Form.BASIC_FORM) {
						gc.changeForm(Form.BASIC_FORM);
					}
				}
			}
			// Process Green
			if (green == MAX_GREEN) {
				// MAX Green: Poison Form
				if (gc.getCurrentForm().getFormType() != Form.POISON_FORM) {
					gc.changeForm(Form.POISON_FORM);
				}
			} else if (green >= MED_GREEN && red >= MED_RED) {
				// MED Green & MED Red: Gas Form
				if (gc.getCurrentForm().getFormType() != Form.GAS_FORM) {
					gc.changeForm(Form.GAS_FORM);
				}
			} else if (green >= MED_GREEN && blue >= MED_BLUE) {
				// MED Green & MED Blue: Acid Form
				if (gc.getCurrentForm().getFormType() != Form.ACID_FORM) {
					gc.changeForm(Form.ACID_FORM);
				}
			} else if (green < MIN_GREEN) {
				if (gc.getCurrentForm().getFormType() == Form.POISON_FORM || 
						gc.getCurrentForm().getFormType() == Form.GAS_FORM ||
						gc.getCurrentForm().getFormType() == Form.ACID_FORM) {
					if (gc.getCurrentForm().getFormType() != Form.BASIC_FORM) {
						gc.changeForm(Form.BASIC_FORM);
					}
				}
			}
			// Process Blue
			if (blue == MAX_BLUE) {
				if (gc.getCurrentForm().getFormType() != Form.WATER_FORM) {
					gc.changeForm(Form.WATER_FORM);
				}
			} else if (blue < MIN_BLUE) {
				if (gc.getCurrentForm().getFormType() == Form.WATER_FORM || 
						gc.getCurrentForm().getFormType() == Form.ACID_FORM) {
					if (gc.getCurrentForm().getFormType() != Form.BASIC_FORM) {
						gc.changeForm(Form.BASIC_FORM);
					}
				}
			}
			processTimer.restart();
		}
		
		// Process decreases
		if (decreaseTimer.isOver()) {
			if (red > 0) {
				red -= DECREASE;
				Engine.gameScreen.getStatbar().updateFire(
						Engine.ObjectManager.getPlayer().getInv().getBlobs().getRed(), BlobContainer.MAX_RED);
			}
			if (green > 0) {
				green -= DECREASE;
				Engine.gameScreen.getStatbar().updateEarth(
						Engine.ObjectManager.getPlayer().getInv().getBlobs().getGreen(), BlobContainer.MAX_GREEN);
			}
			if (blue > 0) {
				blue -= DECREASE;
				Engine.gameScreen.getStatbar().updateWater(
						Engine.ObjectManager.getPlayer().getInv().getBlobs().getBlue(), BlobContainer.MAX_BLUE);
			}
				
			decreaseTimer.restart();
		}
	}
	
	public void addRed(int amount) {
		red += amount;
		if (red > MAX_RED) {
			gc.getStats().addHealth(amount / OVERFLOW_RATIO);
			red = MAX_RED;
		}
			
	}
	
	public void addGreen(int amount) {
		green += amount;
		if (green > MAX_GREEN) {
			gc.getStats().addHealth(amount / OVERFLOW_RATIO);
			green = MAX_GREEN;
		}
			
	}
	
	public void addBlue(int amount) {
		blue += amount;
		if (blue > MAX_BLUE) {
			green = MAX_GREEN;
			blue = MAX_BLUE;
		}
	}
	
	public void addGolden() {
		golden++;
		if (golden >= Const.MAX_GOLDENBLOBS) {
			Engine.gameWon();
		}
	}
	
	public int getRed() {
		return red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public int getGolden() {
		return golden;
	}
	
}
