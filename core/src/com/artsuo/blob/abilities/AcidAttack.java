package com.artsuo.blob.abilities;

import com.artsuo.blob.Engine;
import com.artsuo.blob.abilities.effects.AcidPool;
import com.artsuo.blob.events.Event;
import com.artsuo.blob.events.MeleeEvent;

public class AcidAttack extends Ability {

	public AcidAttack(long cooldownTime) {
		super(cooldownTime);
	
	}

	@Override
	public void activate(Event event) {
		MeleeEvent ev = (MeleeEvent)event;
		if (isReady()) {
			Engine.ObjectManager.addTimedStationaryObject(new AcidPool(
					ev.originPosition, ev.rot.getAngle(), ev.side));
			cooldown.restart();
		}
		
	}

}
