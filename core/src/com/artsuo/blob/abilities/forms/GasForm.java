package com.artsuo.blob.abilities.forms;

import com.artsuo.blob.AssetBank.Asset;
import com.artsuo.blob.Const;
import com.artsuo.blob.abilities.GasRangedAttack;
import com.artsuo.blob.events.RangedEvent;
import com.artsuo.blob.objects.GameCharacter;
import com.artsuo.blob.objects.GameObject.Type;
import com.artsuo.blob.objects.components.Anim;

public class GasForm extends Form {
	
	private static final int ANIM_COLS = 5;
	private static final int ANIM_ROWS = 2;
	private static final float ANIM_DEF_SPEED = 0.08f;

	public GasForm(GameCharacter master) {
		super(master, new Anim(Asset.TEXTURE_GASFORM, ANIM_COLS, ANIM_ROWS, ANIM_DEF_SPEED, true, true), 
				new GasRangedAttack(Const.GAS_FORM_COOLDOWN, Const.GASCLOUD_DAMAGE), Form.GAS_FORM);
	}

	@Override
	public void activateAbility(float posX, float posY) {
		setFacing(posX, posY);
		ability.activate(new RangedEvent(master.getCenter().x, master.getCenter().y, 
				posX, posY, master.getMovable().getMovingAngle(), 
				Const.GASCLOUD_RANGE, Type.GASCLOUD, master.getSide()));
	}

}
