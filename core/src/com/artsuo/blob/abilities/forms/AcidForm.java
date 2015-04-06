package com.artsuo.blob.abilities.forms;

import com.artsuo.blob.AssetBank.Asset;
import com.artsuo.blob.Const;
import com.artsuo.blob.abilities.Ability;
import com.artsuo.blob.abilities.AcidAttack;
import com.artsuo.blob.events.MeleeEvent;
import com.artsuo.blob.objects.GameCharacter;
import com.artsuo.blob.objects.GameObject.Type;
import com.artsuo.blob.objects.components.Anim;

public class AcidForm extends Form {

	private static final int ANIM_COLS = 5;
	private static final int ANIM_ROWS = 2;
	private static final float ANIM_DEF_SPEED = 0.08f;
	
	public AcidForm(GameCharacter master) {
		super(master, new Anim(Asset.TEXTURE_ACIDFORM, ANIM_COLS, ANIM_ROWS, ANIM_DEF_SPEED, true, true), 
				new AcidAttack(Const.ACID_FORM_COOLDOWN), Form.ACID_FORM);
	}

	@Override
	public void activateAbility(float posX, float posY) {
		ability.activate(new MeleeEvent(master.getCenter().x - Const.ACIDPOOL_SIZE_X / 2, master.getCenter().y - Const.ACIDPOOL_SIZE_Y / 2,
				posX, posY, 0, 0, Type.ACIDPOOL, master.getSide()));
	}

}
