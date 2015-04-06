package com.artsuo.blob.objects.pickupitems;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Const;
import com.artsuo.blob.Engine;
import com.artsuo.blob.objects.components.inventory.BlobContainer;
import com.artsuo.blob.objects.components.inventory.Inventory;
import com.badlogic.gdx.math.Vector2;

public class BlueBlob extends PickupItem {

	private int amount;
	
	public BlueBlob(Vector2 position) {
		super(position, SpriteKey.OBJECT_PICKUP_BLUEBLOB, Type.PICKUP_BLUEBLOB);
		this.amount = Const.BLUEBLOB_AMT;
	}
	
	public void onPickup(Inventory inv) {
		inv.getBlobs().addBlue(amount);
		Engine.gameScreen.getStatbar().updateWater(
				Engine.ObjectManager.getPlayer().getInv().getBlobs().getBlue(), 
				BlobContainer.MAX_BLUE);
		remove();
	}
}
