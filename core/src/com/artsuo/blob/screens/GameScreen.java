package com.artsuo.blob.screens;

import com.artsuo.blob.AssetBank;
import com.artsuo.blob.Const;
import com.artsuo.blob.Blob;
import com.artsuo.blob.Engine;
import com.artsuo.blob.AssetBank.Asset;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {

	private Blob game;
	public static Engine engine;
	private boolean paused;
	private Stage stage;
	private Table table;
	private Statbar statbar;

	public GameScreen(Blob game) {
		this.game = game;
		initUi();
		engine = new Engine(this);
		this.paused = false;
	}
	
	private void initUi() {
		this.stage = new Stage();
		this.table = new Table();
		stage.setViewport(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		TextButton newGame = new TextButton("Play again", AssetBank.getSkin(Asset.SKIN_DEFAULT));
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
				//hideMenu();
				//DrinkAndDie newGame = new DrinkAndDie();
				//newGame.setScreen(new GameScreen(newGame));
				//game = newGame;
				// TO DO: Pass GameScreen, dispose of it and create a new one in core Game
				// Original problem: GameScreen
				game.restartGame(GameScreen.this);
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
		hideMenu();
		
		this.statbar = new Statbar(this);
	}

	@Override
	public void render(float delta) {
		if (!paused) {
			update(delta);
			engine.render(delta);
		}
		if (table.isVisible()) {
			stage.act();
			stage.draw();
		}
		statbar.render(delta);
	}

	private void update(float delta) {
		engine.update(delta);
	}

	@Override
	public void show() {

	}
	
	private void showMenu() {
		table.setVisible(true);
		//Gdx.input.setInputProcessor(stage);
	}
	
	private void hideMenu() {
		table.setVisible(false);
	}
	
	/**
	 * Handles screen resizing, such as switching between Portrait/Landscape orientation on Android
	 * 
	 * @param width	- New screen width
	 * @param height - New screen height
	 */
	@Override
	public void resize(int width, int height) {
		statbar.resize(width, height);
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		
		int scaleX = 0; int scaleY = 0;
        scaleX = Const.LANDSCAPE_SCALE_X; scaleY = Const.LANDSCAPE_SCALE_Y;
        for (Cell<?> cell : table.getCells()) {
        	cell.size(width / scaleX, height / scaleY);
        }
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		if (!paused) {
			togglePause();
		}
		//engine.saveGameState(); ---------------------------------
	}

	@Override
	public void resume() {
		if (paused) {
			togglePause();
		}
		//engine.loadGameState(); ---------------------------------
	}

	@Override
	public void dispose() {
		engine.dispose();
		statbar.dispose();
	}

	public OrthographicCamera getCamera() {
		return engine.getRenderer().getCamera();
	}
	
	public Blob getGame() {
		return game;
	}
	
	public Engine getEngine() {
		return engine;
	}
	
	public Statbar getStatbar() {
		return statbar;
	}
	
	public void gameOver() {
		//togglePause();
		showMenu();
		//game.setScreen(new GameOverScreen(this, game)); ---------------------------------
	}
	
	public void gameWon() {
		showMenu();
	}
	
	public void togglePause() {
		if (!paused) {
			paused = true;
		} else if (paused) {
			paused = false;
		}
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public Stage getStage() {
		return stage;
	}
}
