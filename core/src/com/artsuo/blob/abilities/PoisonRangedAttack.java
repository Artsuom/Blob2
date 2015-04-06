package com.artsuo.blob.abilities;

import com.artsuo.blob.Engine;
import com.artsuo.blob.abilities.effects.PoisonBall;
import com.artsuo.blob.events.Event;
import com.artsuo.blob.events.RangedEvent;

public class PoisonRangedAttack extends Ability {
	
	private int damage;

	public PoisonRangedAttack(long cooldownTime, int damage) {
		super(cooldownTime);
		this.damage = damage;
	}

	@Override
	public void activate(Event event) {
		RangedEvent ev = (RangedEvent)event;
		if (isReady()) {
			Engine.ObjectManager.addProjectile(new PoisonBall(ev.originPosition, 
					ev.targetPosition, ev.side, ev.rot.getAngle()));
			cooldown.restart();
		}
		
	}

}
