package com.artsuo.blob.objects.components;

public class Rotatable {
	
	private float rotation;
	
	public Rotatable(float rot) {
		this.rotation = rot;
	}
	
	public float getAngle() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	
	
}
