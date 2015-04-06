package com.artsuo.blob.objects.pickupitems;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Engine;
import com.artsuo.blob.objects.components.inventory.BlobContainer;
import com.artsuo.blob.objects.components.inventory.Inventory;
import com.badlogic.gdx.math.Vector2;

public class GoldenBlob extends PickupItem {
	
	public GoldenBlob(Vector2 position) {
		super(position, SpriteKey.OBJECT_PICKUP_GOLDENBLOB, Type.PICKUP_GOLDENBLOB);
	}
	
	public void onPickup(Inventory inv) {
		inv.getBlobs().addGolden();
		Engine.gameScreen.getStatbar().updateGolden(inv.getBlobs().getGolden());
		remove();
	}
}
