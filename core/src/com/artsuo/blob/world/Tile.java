package com.artsuo.blob.world;

import com.artsuo.blob.Const;
import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.objects.GameObject;
import com.artsuo.blob.objects.components.Drawable;
import com.badlogic.gdx.math.Vector2;

public class Tile extends GameObject {

	private Drawable drawable;
	private boolean passable;
	
	public Tile(Vector2 position, boolean passable, SpriteKey key, Type type, Side side) {
		init(position, Const.TILE_SIZE, Const.TILE_SIZE, type, side);
		this.passable = passable;
		this.drawable = new Drawable(key, false, position.x, position.y, bounds.width, bounds.height);
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Drawable getDrawable() {
		return drawable;
	}
	
	public boolean isPassable() {
		return passable;
	}
	
	public Type getType() {
		return type;
	}
}
