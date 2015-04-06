package com.artsuo.blob.abilities.effects;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Const;
import com.artsuo.blob.objects.components.Stats.ElementType;
import com.badlogic.gdx.math.Vector2;

public class AcidPool extends TimedStationaryObject {

	public AcidPool(Vector2 pos, float rotation, Side side) {
		super(pos, Const.ACIDPOOL_SIZE_X, Const.ACIDPOOL_SIZE_Y, 
				Type.ACIDPOOL, side, SpriteKey.OBJECT_ACIDPOOL, 
				Const.ACIDPOOL_DURATION, rotation, new Damage(Const.ACIDPOOL_DAMAGE, ElementType.EARTH));
	}

}
