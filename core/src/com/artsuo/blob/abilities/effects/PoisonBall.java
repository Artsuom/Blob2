package com.artsuo.blob.abilities.effects;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Const;
import com.artsuo.blob.Engine;
import com.artsuo.blob.objects.components.Stats.ElementType;
import com.badlogic.gdx.math.Vector2;

public class PoisonBall extends Projectile {
	
	public PoisonBall(Vector2 originPos, Vector2 targetPos, Side side, float rotation) {
		super(originPos, targetPos, Const.POISONBALL_SIZE_X, Const.POISONBALL_SIZE_Y, 
				Type.POISONBALL, side, rotation, Const.POISONBALL_SPEED, 
				new Damage(Const.POISONBALL_DAMAGE, ElementType.EARTH), SpriteKey.OBJECT_POISONBALL);
	}

	@Override
	public void onHit() {
		remove();
	}
	
	@Override
	protected void targetReached() {
		Engine.ObjectManager.addTimedStationaryObject(new PoisonField(new Vector2
				(position.x - Const.POISONFIELD_SIZE_X / 2, position.y - Const.POISONFIELD_SIZE_Y / 2), 0, 
				side));
		super.targetReached();
	}
}
