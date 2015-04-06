package com.artsuo.blob.abilities.forms;

import com.artsuo.blob.AssetBank.Asset;
import com.artsuo.blob.abilities.MeleeAttack;
import com.artsuo.blob.events.MeleeEvent;
import com.artsuo.blob.objects.GameCharacter;
import com.artsuo.blob.objects.GameObject.Type;
import com.artsuo.blob.objects.components.Anim;

public class FireForm extends Form {

	private static final int ANIM_COLS = 5;
	private static final int ANIM_ROWS = 2;
	private static final float ANIM_DEF_SPEED = 0.08f;
	private static final long cooldown = 1000;
	
	public FireForm(GameCharacter master, int damage) {
		super(master, new Anim(Asset.TEXTURE_FIREFORM, ANIM_COLS, ANIM_ROWS, ANIM_DEF_SPEED, true, true), 
				new MeleeAttack(damage, cooldown), Form.FIRE_FORM);
	}

	@Override
	public void activateAbility(float posX, float posY) {
		setFacing(posX, posY);
		ability.activate(new MeleeEvent(master.getCenter().x, master.getCenter().y, posX, posY, master.getMovable().getMovingAngle(), master.getBounds().width * 0.75f, Type.EFFECT_MELEEATTACK, master.getSide()));
	}
	
	

}
