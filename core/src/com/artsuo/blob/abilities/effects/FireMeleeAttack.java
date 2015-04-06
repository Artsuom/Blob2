package com.artsuo.blob.abilities.effects;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Const;
import com.artsuo.blob.objects.components.Stats.ElementType;
import com.badlogic.gdx.math.Vector2;

public class FireMeleeAttack extends TimedStationaryObject {
	
	public static final float sx = 1f;
	public static final float sy = 0.5f;
	public static final long duration = 250;

	public FireMeleeAttack(Vector2 pos, float rotation, Side side) {
		super(pos, sx, sy, Type.EFFECT_MELEEATTACK, side, 
				SpriteKey.OBJECT_MELEEATTACK, duration, rotation, 
				new Damage(Const.FIREFORM_DAMAGE, ElementType.FIRE));
	}
}
