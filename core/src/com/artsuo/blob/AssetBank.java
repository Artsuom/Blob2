package com.artsuo.blob;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetBank {
	
	public enum Asset {
		SKIN_DEFAULT, 
		ATLAS_OBJECTS, ATLAS_MAP, ATLAS_UI,
		TEXTURE_UI_BACKGROUND,TEXTURE_FIREFORM,
		TEXTURE_BASICFORM, TEXTURE_POISONFORM, TEXTURE_ACIDFORM, TEXTURE_WATERFORM,
		TEXTURE_GASFORM
	}
	
	public enum SpriteKey {
		TILE_FLOOR, TILE_PILLAR, TILE_EXIT,
		OBJECT_PICKUP_REDBLOB, OBJECT_PICKUP_BLUEBLOB, OBJECT_PICKUP_GREENBLOB,
		OBJECT_PICKUP_GOLDENBLOB,
		OBJECT_ICICLE, OBJECT_GASCLOUD, OBJECT_POISONBALL, OBJECT_POISONFIELD,
		OBJECT_ACIDPOOL, OBJECT_MELEEATTACK,
		UI_HEALTH, UI_FIRE, UI_EARTH, UI_WATER, UI_PEGS, UI_BLOBS
	}

	private static AssetManager manager;
	private static HashMap<Asset, String> filenames;
	private static HashMap<SpriteKey, String> sprites;

	public static void init() {
		manager = new AssetManager();
		filenames = new HashMap<Asset, String>();
		sprites = new HashMap<SpriteKey, String>();
		setFilePaths();
		setSpriteKeys();
	}

	private static void setFilePaths() {
		filenames.put(Asset.SKIN_DEFAULT, "data/graphics/uiskin.json");
		filenames.put(Asset.ATLAS_MAP, "data/graphics/map.txt");
		filenames.put(Asset.ATLAS_OBJECTS, "data/graphics/objects.txt");
		filenames.put(Asset.ATLAS_UI, "data/graphics/ui.txt");
		filenames.put(Asset.TEXTURE_UI_BACKGROUND, "data/graphics/uibackground.png");
		filenames.put(Asset.TEXTURE_FIREFORM, "data/graphics/fireform.png");
		filenames.put(Asset.TEXTURE_BASICFORM, "data/graphics/basicform.png");
		filenames.put(Asset.TEXTURE_WATERFORM, "data/graphics/waterform.png");
		filenames.put(Asset.TEXTURE_POISONFORM, "data/graphics/poisonform.png");
		filenames.put(Asset.TEXTURE_GASFORM, "data/graphics/gasform.png");
		filenames.put(Asset.TEXTURE_ACIDFORM, "data/graphics/acidform.png");
	}
	
	private static void setSpriteKeys() {
		sprites.put(SpriteKey.TILE_FLOOR, "floor");
		sprites.put(SpriteKey.TILE_PILLAR, "pillar");
		sprites.put(SpriteKey.TILE_EXIT, "exit");
		sprites.put(SpriteKey.OBJECT_PICKUP_REDBLOB, "redblob");
		sprites.put(SpriteKey.OBJECT_PICKUP_BLUEBLOB, "blueblob");
		sprites.put(SpriteKey.OBJECT_PICKUP_GREENBLOB, "greenblob");
		sprites.put(SpriteKey.OBJECT_PICKUP_GOLDENBLOB, "goldenblob");
		sprites.put(SpriteKey.OBJECT_ICICLE, "icicle");
		sprites.put(SpriteKey.OBJECT_GASCLOUD, "gascloud");
		sprites.put(SpriteKey.OBJECT_POISONBALL, "poisonball");
		sprites.put(SpriteKey.OBJECT_POISONFIELD, "poisonfield");
		sprites.put(SpriteKey.OBJECT_ACIDPOOL, "acidpool");
		sprites.put(SpriteKey.OBJECT_MELEEATTACK, "meleeattack");
		sprites.put(SpriteKey.UI_HEALTH, "health");
		sprites.put(SpriteKey.UI_FIRE, "fire");
		sprites.put(SpriteKey.UI_EARTH, "earth");
		sprites.put(SpriteKey.UI_WATER, "water");
		sprites.put(SpriteKey.UI_PEGS, "pegs");
		sprites.put(SpriteKey.UI_BLOBS, "gblob");
	}

	private static String getFilePath(Asset key) {
		return filenames.get(key);
	}

	public static void loadTextureAtlas(Asset key) {
		manager.load(getFilePath(key), TextureAtlas.class);
	}

	public static void loadTexture(Asset key) {
		manager.load(getFilePath(key), Texture.class);
	}

	public static void loadSkin(Asset key) {
		manager.load(getFilePath(key), Skin.class);
	}

	public static void loadSound(Asset key) {
		manager.load(getFilePath(key), Sound.class);
	}

	public static void loadMusic(Asset key) {
		manager.load(getFilePath(key), Music.class);
	}

	public static TextureAtlas getTextureAtlas(Asset key) {
		if (manager.isLoaded(getFilePath(key))) {
			return manager.get(getFilePath(key), TextureAtlas.class);
		}
		return null;
	}

	public static Texture getTexture(Asset key) {
		if (manager.isLoaded(getFilePath(key))) {
			return manager.get(getFilePath(key), Texture.class);
		}
		return null;
	}

	public static Skin getSkin(Asset key) {
		if (manager.isLoaded(getFilePath(key))) {
			return manager.get(getFilePath(key), Skin.class);
		}
		return null;
	}

	public static Sound getSound(Asset key) {
		if (manager.isLoaded(getFilePath(key))) {
			return manager.get(getFilePath(key), Sound.class);
		}
		return null;
	}

	public static Music getMusic(Asset key) {
		if (manager.isLoaded(getFilePath(key))) {
			return manager.get(getFilePath(key), Music.class);
		}
		return null;
	}

	// Sprites
	public static Sprite createMapSprite(SpriteKey key) {
		return getTextureAtlas(Asset.ATLAS_MAP).createSprite(sprites.get(key));
	}
	
	// Create a sprite for a game object and set its location & bounds
	public static Sprite createObjectSprite(SpriteKey key, float x, float y, float sx, float sy) {
		Sprite sprite = getTextureAtlas(Asset.ATLAS_OBJECTS).createSprite(sprites.get(key));
		sprite.setBounds(x, y, sx, sy);
		return sprite;
	}
	
	// Create a sprite for a game object without setting its location & bounds
	public static Sprite createObjectSprite(SpriteKey key) {
		return getTextureAtlas(Asset.ATLAS_OBJECTS).createSprite(sprites.get(key));
	}
	
	// Create a sprite for a game object without setting its location & bounds
	public static Sprite createUiSprite(SpriteKey key) {
		return getTextureAtlas(Asset.ATLAS_UI).createSprite(sprites.get(key));
	}
	
	public static Animation createAnimation(Asset asset, int sheetCols, int sheetRows, float speed) {
		Texture tempSheet = getTexture(asset);
		TextureRegion[][] tmp = TextureRegion.split(tempSheet, tempSheet.getWidth() / sheetCols, 
				tempSheet.getHeight() / sheetRows); 
		TextureRegion[] tempFrames = new TextureRegion[sheetCols * sheetRows];
		int index = 0;
		for (int i = 0; i < sheetRows; i++) {
			for (int j = 0; j < sheetCols; j++) {
				tempFrames[index++] = tmp[i][j];
			}
		}
		return new Animation(speed, tempFrames);
	}
	
	// AssetManager
	public static AssetManager getManager() {
		return manager;
	}

}
