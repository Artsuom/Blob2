package com.artsuo.blob.abilities.forms;

import com.artsuo.blob.abilities.Ability;
import com.artsuo.blob.objects.GameCharacter;
import com.artsuo.blob.objects.components.Anim;

public abstract class Form {
	
	public static final int BASIC_FORM = 0;
	public static final int FIRE_FORM = 1;
	public static final int WATER_FORM = 2;
	public static final int POISON_FORM = 3;
	public static final int GAS_FORM = 4;
	public static final int ACID_FORM = 5;
	
	protected Anim anim;
	protected Ability ability;
	protected int formType;
	protected GameCharacter master;
	
	public Form(GameCharacter master, Anim anim, Ability ability, int formType) {
		this.master = master;
		this.anim = anim;
		this.ability = ability;
		this.formType = formType;
	}
	
	public abstract void activateAbility(float posX, float posY);
	
	protected void setFacing(float x, float y) {
		master.getMovable().stopMoving();
		master.getMovable().setFacingAngle((float)Math.atan2(x - master.getCenter().x, y - master.getCenter().y) * (180 / (float)Math.PI));
	}
	
	public Anim getAnim() {
		return anim;
	}
	
	public Ability getAbility() {
		return ability;
	}
	
	public int getFormType() {
		return formType;
	}
}
