package com.artsuo.blob.abilities;

import com.artsuo.blob.Engine;
import com.artsuo.blob.abilities.effects.FireMeleeAttack;
import com.artsuo.blob.events.Event;
import com.artsuo.blob.events.MeleeEvent;
import com.artsuo.blob.util.Physics;

public class MeleeAttack extends Ability {
	
	private int damage;

	public MeleeAttack(int damage, long cooldownTime) {
		super(cooldownTime);
		this.damage = damage;
	}

	@Override
	public void activate(Event ev) {
		MeleeEvent event = (MeleeEvent)ev;
		if (isReady()) {
			Engine.ObjectManager.addTimedStationaryObject(new FireMeleeAttack(
					Physics.getMeleeAttackPoint(event.targetPosition, event.originPosition, 
							event.rot.getAngle(), event.range, 
							FireMeleeAttack.sx, FireMeleeAttack.sy), event.rot.getAngle(), event.side));
			cooldown.restart();
		}
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}

}
