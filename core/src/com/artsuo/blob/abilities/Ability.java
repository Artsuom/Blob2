package com.artsuo.blob.abilities;

import com.artsuo.blob.events.Event;
import com.artsuo.blob.util.Timer;

public abstract class Ability {
	
	protected Timer cooldown;
	
	public Ability(long cooldownTime) {
		this.cooldown = new Timer(cooldownTime);
	}
	
	public abstract void activate(Event event);
	
	public boolean isReady() {
		return cooldown.isOver();
	}
	
	public void restartCooldown() {
		cooldown.restart();
	}
}
