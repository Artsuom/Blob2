package com.artsuo.blob.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

	public enum Type {
		PLAYER, EARTHENEMY, FIREENEMY, WATERENEMY, PICKUP_REDBLOB, PICKUP_BLUEBLOB, PICKUP_GREENBLOB, 
		PICKUP_GOLDENBLOB,
		ICICLE, GASCLOUD, POISONBALL, POISONFIELD, ACIDPOOL,
		TILE_GROUND, TILE_PILLAR, TILE_EXIT,
		EFFECT_MELEEATTACK
	}
	
	public enum Side {
		PLAYER, ENEMY, NEUTRAL
	}
	
	protected Vector2 position;	
	protected Rectangle bounds;
	protected boolean remove;
	protected Type type;
	protected Side side;
	
	protected void init(Vector2 pos, float sx, float sy, Type type, Side side) {
		this.position = pos;
		this.remove = false;
		this.bounds = new Rectangle(position.x, position.y, sx, sy);
		this.type = type;
		this.side = side;
	}
	
	public void updateBounds() {
		bounds.setPosition(position);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Vector2 getCenter() {
		return bounds.getCenter(new Vector2());
	}
	
	public Type getType() {
		return type;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public void updatePosition(float x, float y) {
		position.x = x;
		position.y = y;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public boolean isRemove() {
		return remove;
	}
	
	public void remove() {
		setRemove(true);
	}
	
	public Side getSide() {
		return side;
	}
}
