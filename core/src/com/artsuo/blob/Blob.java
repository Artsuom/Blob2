package com.artsuo.blob;

import com.artsuo.blob.screens.GameScreen;
import com.artsuo.blob.screens.LoadingScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Blob extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create() {
		this.batch = new SpriteBatch();
		AssetBank.init();
		setScreen(new LoadingScreen(this));
	}
	
	public void restartGame(GameScreen oldScreen) {
		Engine.restart();
		setScreen(new GameScreen(this));
		
		//oldScreen.hide();
		oldScreen.dispose();
		//setScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void pause() {
		super.pause();
	}
	
	@Override
	public void resume() {
		super.resume();
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}
