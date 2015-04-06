package com.artsuo.blob.abilities;

import com.artsuo.blob.Engine;
import com.artsuo.blob.abilities.effects.Icicle;
import com.artsuo.blob.events.Event;
import com.artsuo.blob.events.RangedEvent;

public class WaterRangedAttack extends Ability {

	private int damage;
	
	public WaterRangedAttack(long cooldownTime, int damage) {
		super(cooldownTime);
		this.damage = damage;
	}

	@Override
	public void activate(Event ev) {
		RangedEvent event = (RangedEvent)ev;
		if (isReady()) {
			Engine.ObjectManager.addProjectile(new Icicle(event.originPosition, 
					event.targetPosition, event.side, event.rot.getAngle()));
			cooldown.restart();
		}
		
	}
	
	public int getDamage() {
		return damage;
	}

}
