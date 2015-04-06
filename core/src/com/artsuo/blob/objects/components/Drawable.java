package com.artsuo.blob.objects.components;

import com.artsuo.blob.AssetBank;
import com.artsuo.blob.AssetBank.SpriteKey;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Drawable {

	private Sprite sprite;
	
	public Drawable(SpriteKey key, boolean isObject, float x, float y, float sx, float sy) {
		if (isObject) {
			this.sprite = new Sprite(AssetBank.createObjectSprite(key));
		} else {
			this.sprite = new Sprite(AssetBank.createMapSprite(key));
		}
		sprite.setBounds(x, y, sx, sy);
	}
	
	public void updatePosition(float x, float y) {
		sprite.setPosition(x, y);
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
