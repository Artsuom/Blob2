package com.artsuo.blob.objects.components.inventory;

import com.artsuo.blob.objects.GameCharacter;

public class Inventory {

	private BlobContainer blobs;
	
	public Inventory(GameCharacter gc) {
		this.blobs = new BlobContainer(gc);
	}
	
	public void update() {
		blobs.update();
	}
	
	public BlobContainer getBlobs() {
		return blobs;
	}
}
