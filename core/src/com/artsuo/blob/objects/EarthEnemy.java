package com.artsuo.blob.objects;

import com.artsuo.blob.Const;
import com.artsuo.blob.Engine;
import com.artsuo.blob.abilities.effects.Damage;
import com.artsuo.blob.abilities.forms.Form;
import com.artsuo.blob.objects.components.Stats;
import com.artsuo.blob.objects.components.Stats.ElementType;
import com.artsuo.blob.util.Physics;
import com.artsuo.blob.util.RandomUtil;
import com.badlogic.gdx.math.Vector2;

public class EarthEnemy extends GameCharacter {
	
	private boolean chasing;
	private float sightRange;
	private static int[] allForms = {Form.BASIC_FORM};
	private Damage damage;
	
	public EarthEnemy(Vector2 pos) {
		super(pos, Const.HUMAN_SIZE_X, Const.HUMAN_SIZE_Y, 
				Type.EARTHENEMY, Side.ENEMY, 
				RandomUtil.getRandomBetween(Const.EARTHENEMY_MIN_SPEED, Const.EARTHENEMY_MAX_SPEED),
				allForms, Form.BASIC_FORM, new Stats(Const.EARTHENEMY_MAX_HEALTH, ElementType.EARTH));
		this.chasing = false;
		this.sightRange = Const.EARTHENEMY_SIGHT_RANGE;
		this.damage = new Damage(Const.EARTHENEMY_DAMAGE, ElementType.EARTH);
	}
	
	@Override
	public void update(float delta) {
		getMovable().update(delta);
		if (getMovable().isMoving()) {
			getMovable().move(delta);
			currentForm.getAnim().update(delta);
		} else {
			currentForm.getAnim().resetToFirstFrame();
			if (!chasing) { 
				startMovingToNewTarget();
			} 
		}
	}
	
	private void startMovingToNewTarget() {
		Vector2 newTarget = RandomUtil.getRandomPosition(position,
				RandomUtil.getRandomBetween(
						Const.EARTHENEMY_MIN_WALK_DISTANCE,
						Const.EARTHENEMY_MAX_WALK_DISTANCE));
		getMovable().startMoving(newTarget.x, newTarget.y);
	}
	
	public void processTargeting(Vector2 targetCenter) {
		// Check if within sight range
		if (Physics.inRange(getCenter(), targetCenter, sightRange)) {
			if (!chasing) {
				setChasing(true);
			}
			if (!getMovable().isMoving()) {
				Vector2 newTarget = Engine.ObjectManager.getPlayer()
						.getCenter();
				getMovable().startMoving(newTarget.x, newTarget.y);
			}
		} else {
			if (chasing) {
				setChasing(false);
			}
			if (!getMovable().isMoving()) {
				startMovingToNewTarget();
			}
		}
	}
	
	@Override
	protected void death() {
		if (RandomUtil.itemDrop()) {
			Engine.ObjectManager.addGreenBlob(position);
		}
		super.death();
	}
	
	public float getSightRange() {
		return sightRange;
	}
	
	public boolean isChasing() {
		return chasing;
	}
	
	public void setChasing(boolean chasing) {
		this.chasing = chasing;
	}
	
	public Damage getDamage() {
		return damage;
	}
}
