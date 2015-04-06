package com.artsuo.blob.abilities.effects;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Const;
import com.artsuo.blob.objects.components.Stats.ElementType;
import com.badlogic.gdx.math.Vector2;

public class GasCloud extends Projectile {

	public GasCloud(Vector2 originPos, Vector2 targetPos, Side side, float rotation) {
		super(originPos, targetPos, Const.GASCLOUD_SIZE_X, Const.GASCLOUD_SIZE_Y, 
				Type.GASCLOUD, side, rotation, Const.GASCLOUD_SPEED, new Damage(Const.GASCLOUD_DAMAGE, ElementType.EARTH), SpriteKey.OBJECT_GASCLOUD);
		
	}

	@Override
	public void onHit() {
	}

}
