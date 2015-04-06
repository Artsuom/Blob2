package com.artsuo.blob.screens;


import com.artsuo.blob.AssetBank;
import com.artsuo.blob.AssetBank.Asset;
import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Engine;
import com.artsuo.blob.objects.components.inventory.BlobContainer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Statbar {
	private static final float PAD_SMALL = 4;
	private static final float PAD_LARGE = 8;
	private static final float BAR_HEIGHT = 20;
	private static final float SCREEN_WIDTH = 800;
	private static final float SCREEN_HEIGHT = 480;
	private static final float SMALLBAR_WIDTH = 200;
	private GameScreen screen;
	private Stage stage;
	private Group resourceGroup;
	private Table table;
	private Table healthTable;
	private Table resourceTable;
	private Table statTable;
	private Image health;
	private Image fire;
	private Image earth;
	private Image water;
	private Image pegs;
	private Image blobs;
	private Label blobsLabel;
	//private ShapeRenderer debugRenderer;
	
	
	public Statbar(GameScreen screen) {
		this.screen = screen;
		//this.debugRenderer = new ShapeRenderer();
		initUi();
	}
	
	private void initUi() {
		this.stage = new Stage();
		this.resourceGroup = new Group();
		this.table = new Table();
		this.healthTable = new Table();
		this.resourceTable = new Table();
		this.statTable = new Table();
		stage.setViewport(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		//this.foodLabel = new Label("0", AssetBank.getSkin(Asset.SKIN_DEFAULT));
		//this.moneyLabel = new Label("0", AssetBank.getSkin(Asset.SKIN_DEFAULT));
		//this.monthLabel = new Label("Jan", AssetBank.getSkin(Asset.SKIN_DEFAULT));
		//this.dayLabel = new Label("1", AssetBank.getSkin(Asset.SKIN_DEFAULT));
		
		//foodImage = new Image(AssetBank.createUiSprite(SpriteKey.UI_FOOD));
		//moneyImage = new Image(AssetBank.createUiSprite(SpriteKey.UI_MONEY));
		
		//table.setFillParent(true);
		//table.setPosition(0, SCREEN_HEIGHT / 2 - 20);
		//table.setPosition(-220, 220);
		//table.setFillParent(true);
		//healthTable.setSize(SCREEN_WIDTH, BAR_HEIGHT * 2);
		this.health = new Image(AssetBank.createUiSprite(SpriteKey.UI_HEALTH));
		this.fire = new Image(AssetBank.createUiSprite(SpriteKey.UI_FIRE));
		this.earth = new Image(AssetBank.createUiSprite(SpriteKey.UI_EARTH));
		this.water = new Image(AssetBank.createUiSprite(SpriteKey.UI_WATER));
		this.pegs = new Image(AssetBank.createUiSprite(SpriteKey.UI_PEGS));
		this.blobs = new Image(AssetBank.createUiSprite(SpriteKey.UI_BLOBS));
		this.blobsLabel = new Label("0", AssetBank.getSkin(Asset.SKIN_DEFAULT));
		
		healthTable.add(health).width(SCREEN_WIDTH).height(BAR_HEIGHT);
		updateHealth(Engine.ObjectManager.getPlayer().getStats().getCurrHealth(),
				Engine.ObjectManager.getPlayer().getStats().getMaxHealth());
		
		fire.setSize(SMALLBAR_WIDTH, BAR_HEIGHT);
		updateFire(Engine.ObjectManager.getPlayer().getInv().getBlobs().getRed(), BlobContainer.MAX_RED);
		resourceGroup.addActor(fire);
		earth.setSize(SMALLBAR_WIDTH, BAR_HEIGHT);
		updateEarth(Engine.ObjectManager.getPlayer().getInv().getBlobs().getGreen(), BlobContainer.MAX_GREEN);
		resourceGroup.addActor(earth);
		earth.setX(fire.getX() + SMALLBAR_WIDTH);
		water.setSize(SMALLBAR_WIDTH, BAR_HEIGHT);
		updateWater(Engine.ObjectManager.getPlayer().getInv().getBlobs().getBlue(), BlobContainer.MAX_BLUE);
		resourceGroup.addActor(water);
		water.setX(earth.getX() + SMALLBAR_WIDTH);
		pegs.setSize(SMALLBAR_WIDTH * 3, BAR_HEIGHT);
		resourceGroup.addActor(pegs);
		resourceTable.add(resourceGroup).size(SMALLBAR_WIDTH * 3, BAR_HEIGHT);
		
		statTable.add(blobs).padRight(PAD_SMALL);
		statTable.add(blobsLabel);
		
		table.add(healthTable).row();
		table.add(resourceTable).row();
		table.add(statTable);
		table.setY(SCREEN_HEIGHT / 2.40f);
		
		stage.addActor(table);
	}
	
	public void updateHealth(float healthAmt, float maxHealth) {
		health.setWidth(SCREEN_WIDTH * healthAmt / maxHealth);
	}
	
	public void updateFire(float amt, float max) {
		fire.setWidth(SMALLBAR_WIDTH * amt / max);
	}
	
	public void updateEarth(float amt, float max) {
		earth.setWidth(SMALLBAR_WIDTH * amt / max);
	}
	
	public void updateWater(float amt, float max) {
		water.setWidth(SMALLBAR_WIDTH * amt / max);
	}
	
	public void updateGolden(int amt) {
		blobsLabel.setText(Integer.toString(amt));
	}
	
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
		/*
		debugRenderer.begin(ShapeType.Line);
		healthTable.drawDebug(debugRenderer);
		resourceTable.drawDebug(debugRenderer);
		resourceGroup.drawDebug(debugRenderer);
		debugRenderer.end();
		*/
	}
	
	public void resize(int width, int height) {
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
	}
	
	public Image getHealth() {
		return health;
	}
	
	public Image getFire() {
		return fire;
	}
	
	public Image getWater() {
		return water;
	}
	
	public Image getEarth() {
		return earth;
	}
	
	public Image getPegs() {
		return pegs;
	}
	
	public void dispose() {
		stage.dispose();
	}
}
