package com.artsuo.blob.screens;

import com.artsuo.blob.AssetBank;
import com.artsuo.blob.Const;
import com.artsuo.blob.Blob;
import com.artsuo.blob.AssetBank.Asset;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LoadingScreen implements Screen {
	
	private static final String TEXT_LOADING = "Loading...";
	private static final String TEXT_TAPSCREEN = "Tap screen to continue";
	private Blob game;
	private Stage stage;
	private Table table;
	private Label label;
	private Texture background;
	
	public LoadingScreen(Blob game) {
		this.game = game;
		loadPrerequisites();
		loadGameAssets();
		initMenu();
	}
	
	// Load resources needed by the loading screen itself
	private void loadPrerequisites() {
		AssetBank.loadSkin(Asset.SKIN_DEFAULT);
		AssetBank.loadTexture(Asset.TEXTURE_UI_BACKGROUND);
		AssetBank.getManager().finishLoading();
		this.background = AssetBank.getTexture(Asset.TEXTURE_UI_BACKGROUND);
	}
	
	// Load all game assets
	private void loadGameAssets() {
		// Load assets and Sprites for GameObjects and map
		AssetBank.loadTextureAtlas(Asset.ATLAS_OBJECTS);
		AssetBank.loadTextureAtlas(Asset.ATLAS_MAP);
		AssetBank.loadTextureAtlas(Asset.ATLAS_UI);
		// Load Textures
		AssetBank.loadTexture(Asset.TEXTURE_BASICFORM);
		AssetBank.loadTexture(Asset.TEXTURE_FIREFORM);
		AssetBank.loadTexture(Asset.TEXTURE_WATERFORM);
		AssetBank.loadTexture(Asset.TEXTURE_POISONFORM);
		AssetBank.loadTexture(Asset.TEXTURE_GASFORM);
		AssetBank.loadTexture(Asset.TEXTURE_ACIDFORM);
		//AssetBank.loadTexture(Asset.TEXTURE_DEFAULT_BACKGROUND);
		// Load assets and Sprites for Backgrounds
		// Load Sounds
		//AssetBank.loadSound(AssetKey.SOUND_COINDROP);
	}
	
	private void saveSprites() {
		//AssetBank.saveSprites(Asset.ATLAS_OBJECTS);
		//AssetBank.saveSprites(AssetKey.ATLAS_STATBAR);
	}
	
	private void initMenu() {
		this.stage = new Stage();
		this.table = new Table();
		stage.setViewport(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        label = new Label(TEXT_LOADING, AssetBank.getSkin(Asset.SKIN_DEFAULT));
		table.add(label);
		stage.addActor(table);
	}

	@Override
	public void render(float delta) {	
		if (AssetBank.getManager().update()) {
			if (label.getText().toString().equals(TEXT_LOADING)) {
				label.setText(TEXT_TAPSCREEN);
			}
			if (Gdx.input.isTouched()) {
				saveSprites();
				game.setScreen(new MainMenuScreen(game));
			}
		}
		Gdx.graphics.getGL20().glClearColor(0, 0, 0.2f, 1);
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.end();
		
		stage.act(delta);
		stage.draw();
	}

	/**
	 * Handles screen resizing, such as switching between Portrait/Landscape orientation on Android
	 * 
	 * @param width	- New screen width
	 * @param height - New screen height
	 */
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		
		int scaleX = 0; int scaleY = 0;
        scaleX = Const.LANDSCAPE_SCALE_X; scaleY = Const.LANDSCAPE_SCALE_Y;
        for (Cell<?> cell : table.getCells()) {
        	cell.size(width / scaleX, height / scaleY);
        }
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		background.dispose();
		stage.dispose();
	}
}
