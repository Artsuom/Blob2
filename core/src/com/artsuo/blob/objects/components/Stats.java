package com.artsuo.blob.objects.components;

public class Stats {
	
	private static final float DIFF_ELEM_RATIO = 2f;
	private static final float SAME_ELEM_RATIO = 0.5f;
	private static final float DEFAULT_RATIO = 0.5f;
	
	public enum ElementType {
		FIRE, WATER, EARTH
	}
	
	private ElementType elementType;
	private float currHealth;
	private float maxHealth;
	
	public Stats(float maxHealth, ElementType elementType) {
		this.elementType = elementType;
		this.maxHealth = maxHealth;
		this.currHealth = this.maxHealth;
	}
	
	public boolean damage(ElementType damageType, float damage) {
		if (damageType == elementType) {
			currHealth -= damage * SAME_ELEM_RATIO;
			if (currHealth <= 0) 
				return false;
		} else if ((damageType == ElementType.FIRE && elementType == ElementType.WATER) ||
				damageType == ElementType.WATER && elementType == ElementType.FIRE) {
			currHealth -= damage * DIFF_ELEM_RATIO;
			if (currHealth <= 0) 
				return false;
		} else {
			currHealth -= damage * DEFAULT_RATIO;
			if (currHealth <= 0) 
				return false;
		}
		return true;
	}
	
	public void addHealth(float amt) {
		currHealth += amt;
		if (currHealth > maxHealth)
			currHealth = maxHealth;
	}
	
	public float getCurrHealth() {
		return currHealth;
	}
	
	public float getMaxHealth() {
		return maxHealth;
	}
}
