package com.artsuo.blob.abilities.effects;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.objects.GameObject;
import com.artsuo.blob.objects.components.Drawable;
import com.artsuo.blob.objects.components.Movable;
import com.artsuo.blob.objects.components.Rotatable;
import com.badlogic.gdx.math.Vector2;

public abstract class Projectile extends GameObject {

	protected Drawable drawable;
	protected Rotatable rot;
	protected Movable movable;
	protected Vector2 target;
	protected Damage damage;
	
	public Projectile(Vector2 originPos, Vector2 targetPos, float sx, float sy, Type type, Side side, float rotation, float speed, Damage damage, SpriteKey sprKey) {
		init(new Vector2(originPos.x - sx / 2, originPos.y - sy / 2), 
				sx, sy, type, side);
		this.drawable = new Drawable(sprKey, true, position.x, position.y, bounds.width, bounds.height);
		this.rot = new Rotatable(rotation);
		this.movable = new Movable(this, getBounds(), speed);
		this.damage = damage;
		movable.startMoving(targetPos.x, targetPos.y);
	}
	
	public void update(float delta) {
		getMovable().update(delta);
		if (getMovable().isMoving()) {
			getMovable().move(delta);
			updateBounds();
		}
		if (!movable.isMoving()) {
			// Not moving, target reached
			targetReached();
		}
	}
	
	protected void targetReached() {
		remove();
	}
	
	public abstract void onHit();
	
	public Movable getMovable() {
		return movable;
	}
	
	public Rotatable getRot() {
		return rot;
	}
	
	public Drawable getDrawable() {
		return drawable;
	}
	
	public Damage getDamage() {
		return damage;
	}
}
