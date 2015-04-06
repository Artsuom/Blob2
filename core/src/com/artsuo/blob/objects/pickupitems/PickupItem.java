package com.artsuo.blob.objects.pickupitems;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Const;
import com.artsuo.blob.objects.GameObject;
import com.artsuo.blob.objects.components.Drawable;
import com.artsuo.blob.objects.components.inventory.Inventory;
import com.badlogic.gdx.math.Vector2;

public abstract class PickupItem extends GameObject {

	protected Drawable drawable;
	
	public PickupItem(Vector2 pos, SpriteKey spriteKey, Type type) {
		init(pos, Const.PICKUP_SIZE_X, Const.PICKUP_SIZE_Y, type, Side.NEUTRAL);
		this.drawable = new Drawable(spriteKey, true, position.x, position.y, bounds.width, bounds.height);
	}
	
	public abstract void onPickup(Inventory inv);
		
	public Drawable getDrawable() {
		return drawable;
	}
}
