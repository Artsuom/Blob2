package com.artsuo.blob.abilities.effects;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.objects.GameObject;
import com.artsuo.blob.objects.components.Drawable;
import com.artsuo.blob.objects.components.Rotatable;
import com.artsuo.blob.util.Timer;
import com.badlogic.gdx.math.Vector2;

public class TimedStationaryObject extends GameObject {

	protected Drawable drawable;
	protected Timer timer;
	protected Rotatable rot;
	protected Damage damage;
	
	public TimedStationaryObject(Vector2 pos, float sx, float sy, Type type, Side side,
			SpriteKey key, long duration, float rotation, Damage damage) {
		init(pos, sx, sy, type, side);
		this.drawable = new Drawable(key, true, position.x, position.y, bounds.width, bounds.height);
		this.timer = new Timer(duration);
		this.rot = new Rotatable(rotation);
		this.damage = damage;
	}
	
	public void update(float delta) {
		if (timer.isOver()) {
			remove();
		}
	}
	
	public void onHit() {
		
	}
	
	public Drawable getDrawable() {
		return drawable;
	}
	
	public Rotatable getRotation() {
		return rot;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public Damage getDamage() {
		return damage;
	}
	
}
