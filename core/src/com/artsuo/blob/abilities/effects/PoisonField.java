package com.artsuo.blob.abilities.effects;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Const;
import com.artsuo.blob.objects.components.Stats.ElementType;
import com.badlogic.gdx.math.Vector2;

public class PoisonField extends TimedStationaryObject {
	
	public PoisonField(Vector2 pos, float rotation, Side side) {
		super(pos, Const.POISONFIELD_SIZE_X, Const.POISONFIELD_SIZE_Y, 
				Type.POISONFIELD, side, SpriteKey.OBJECT_POISONFIELD, 
				Const.POISONFIELD_DURATION, rotation, new Damage(Const.POISONFIELD_DAMAGE, ElementType.EARTH));
	}

}
