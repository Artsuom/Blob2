package com.artsuo.blob.screens;

import com.artsuo.blob.AssetBank;
import com.artsuo.blob.Const;
import com.artsuo.blob.Blob;
import com.artsuo.blob.AssetBank.Asset;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class MainMenuScreen implements Screen {
	
	private Blob game;
	private Stage stage;
	private Table table;
	private Texture background;
	
	public MainMenuScreen(Blob game) {
		this.game = game;
		this.background = AssetBank.getTexture(Asset.TEXTURE_UI_BACKGROUND);
		initMenu();
	}
	
	private void initMenu() {
		this.stage = new Stage();
		this.table = new Table();
		stage.setViewport(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		/*
		if (FileHandler.savedGameStateExists()) {
			TextButton continueButton = new TextButton("Continue Game", AssetBank.getSkin(AssetKey.SKIN_UI));
			table.add(continueButton).space(10);
			table.row();
			continueButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					GameStateValues.init(GameStateValues.DIFFICULTY);
					GameScreen gScreen = new GameScreen(game);
					gScreen.getEngine().loadGameState();
					game.setScreen(gScreen);
				}
			});
		}
		*/
		TextButton newGame = new TextButton("Start game", AssetBank.getSkin(Asset.SKIN_DEFAULT));
		table.add(newGame).space(10);
		table.row();
		TextButton quit = new TextButton("Quit", AssetBank.getSkin(Asset.SKIN_DEFAULT));
		table.add(quit).space(10);
		
		newGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
			/*	if (FileHandler.savedGameStateExists()) {
					FileHandler.eraseSavedGameData();
				} */
				game.setScreen(new GameScreen(game));
			}
			
		});
		quit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				AssetBank.getManager().dispose();
				Gdx.app.exit();
			}
			
		});
		//table.debug();
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {	
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
