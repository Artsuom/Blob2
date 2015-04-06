package com.artsuo.blob.abilities;

import com.artsuo.blob.events.Event;
import com.artsuo.blob.objects.components.Movable;

public class SpeedBoost extends Ability {
	
	private Movable mov;

	public SpeedBoost(Movable movable, long cooldownTime) {
		super(cooldownTime);
		this.mov = movable;
	}

	@Override
	public void activate(Event event) {
		if (isReady()) {
			mov.speedBoost();
			cooldown.restart();
		}
	}
	
}
