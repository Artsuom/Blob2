package com.artsuo.blob.abilities.effects;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Const;
import com.artsuo.blob.objects.components.Stats.ElementType;
import com.badlogic.gdx.math.Vector2;

public class Icicle extends Projectile {

	public Icicle(Vector2 originPos, Vector2 targetPos, Side side, float rotation) {
		super(originPos, targetPos, Const.ICICLE_SIZE_X, Const.ICICLE_SIZE_Y,
				Type.ICICLE, side, rotation, Const.ICICLE_SPEED, 
				new Damage(Const.ICICLE_DAMAGE, ElementType.WATER), SpriteKey.OBJECT_ICICLE);
	}

	@Override
	public void onHit() {
		remove();
	}

}
