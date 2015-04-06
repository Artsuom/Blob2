package com.artsuo.blob.events;

import com.artsuo.blob.objects.GameObject.Side;
import com.artsuo.blob.objects.GameObject.Type;
import com.artsuo.blob.objects.components.Rotatable;
import com.badlogic.gdx.math.Vector2;

public class RangedEvent extends Event {
	
	public Vector2 targetPosition;
	public Vector2 originPosition;
	public float range;
	public Rotatable rot;
	public Type type;
	public Side side;
	
	public RangedEvent(float originX, float originY, float targetX, float targetY, float angle, float range, Type objectType, Side objectSide) {
		this.originPosition = new Vector2(originX, originY);
		this.targetPosition = new Vector2(targetX, targetY);
		this.range = range;
		this.rot = new Rotatable(angle);
		this.type = objectType;
		this.side = objectSide;
	}
}
