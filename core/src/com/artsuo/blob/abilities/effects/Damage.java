package com.artsuo.blob.abilities.effects;

import com.artsuo.blob.objects.components.Stats.ElementType;

public class Damage {
	
	private float damage;
	private ElementType type;
	
	public Damage(float dmg, ElementType type) {
		this.damage = dmg;
		this.type = type;
	}
	
	public float getDamage() {
		return damage;
	}
	
	public ElementType getType() {
		return type;
	}
}
