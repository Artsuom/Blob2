package com.artsuo.blob.abilities.forms;

import com.artsuo.blob.AssetBank.Asset;
import com.artsuo.blob.Const;
import com.artsuo.blob.abilities.SpeedBoost;
import com.artsuo.blob.events.Event;
import com.artsuo.blob.objects.GameCharacter;
import com.artsuo.blob.objects.components.Anim;

public class BasicForm extends Form {
	
	private static final int ANIM_COLS = 5;
	private static final int ANIM_ROWS = 2;
	private static final float ANIM_DEF_SPEED = 0.08f;

	public BasicForm(GameCharacter master) {
		super(master, new Anim(Asset.TEXTURE_BASICFORM, ANIM_COLS, ANIM_ROWS, ANIM_DEF_SPEED, true, true), 
				new SpeedBoost(master.getMovable(), Const.SPEEDBOOST_COOLDOWN), Form.BASIC_FORM);
	}

	@Override
	public void activateAbility(float posX, float posY) {
		master.getMovable().startMoving(posX, posY);
		ability.activate(new Event());
	}
	
	
}
