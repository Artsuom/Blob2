package com.artsuo.blob.objects.pickupitems;

import com.artsuo.blob.Const;
import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Engine;
import com.artsuo.blob.objects.components.inventory.BlobContainer;
import com.artsuo.blob.objects.components.inventory.Inventory;
import com.badlogic.gdx.math.Vector2;

public class RedBlob extends PickupItem {

	private int amount;
	
	public RedBlob(Vector2 position) {
		super(position, SpriteKey.OBJECT_PICKUP_REDBLOB, Type.PICKUP_REDBLOB);
		this.amount = Const.REDBLOB_AMT;
	}
	
	public void onPickup(Inventory inv) {
		inv.getBlobs().addRed(amount);
		Engine.gameScreen.getStatbar().updateFire(
				Engine.ObjectManager.getPlayer().getInv().getBlobs().getRed(), 
				BlobContainer.MAX_RED);
		remove();
	}

}
