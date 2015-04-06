package com.artsuo.blob.objects.pickupitems;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Const;
import com.artsuo.blob.Engine;
import com.artsuo.blob.objects.components.inventory.BlobContainer;
import com.artsuo.blob.objects.components.inventory.Inventory;
import com.badlogic.gdx.math.Vector2;

public class GreenBlob extends PickupItem {

	private int amount;
	
	public GreenBlob(Vector2 pos) {
		super(pos, SpriteKey.OBJECT_PICKUP_GREENBLOB, Type.PICKUP_GREENBLOB);
		init(pos, Const.PICKUP_SIZE_X, Const.PICKUP_SIZE_Y, type, Side.NEUTRAL);
		this.amount = Const.GREENBLOB_AMT;
	}

	@Override
	public void onPickup(Inventory inv) {
		inv.getBlobs().addGreen(amount);
		Engine.gameScreen.getStatbar().updateEarth(
				Engine.ObjectManager.getPlayer().getInv().getBlobs().getGreen(), 
				BlobContainer.MAX_GREEN);
		remove();
	}

}
