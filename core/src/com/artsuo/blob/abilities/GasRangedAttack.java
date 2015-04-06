package com.artsuo.blob.abilities;

import com.artsuo.blob.Engine;
import com.artsuo.blob.abilities.effects.GasCloud;
import com.artsuo.blob.events.Event;
import com.artsuo.blob.events.RangedEvent;

public class GasRangedAttack extends Ability {

	private int damage;
	
	public GasRangedAttack(long cooldownTime, int damage) {
		super(cooldownTime);
		this.damage = damage;
	}

	@Override
	public void activate(Event ev) {
		RangedEvent event = (RangedEvent)ev;
		if (isReady()) {
			Engine.ObjectManager.addProjectile(new GasCloud(event.originPosition,
					event.targetPosition, event.side, event.rot.getAngle()));
			cooldown.restart();
		}
	}

}
