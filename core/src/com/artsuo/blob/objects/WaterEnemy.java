package com.artsuo.blob.objects;

import com.artsuo.blob.Const;
import com.artsuo.blob.Engine;
import com.artsuo.blob.abilities.forms.Form;
import com.artsuo.blob.objects.components.Stats;
import com.artsuo.blob.objects.components.Stats.ElementType;
import com.artsuo.blob.util.Physics;
import com.artsuo.blob.util.RandomUtil;
import com.badlogic.gdx.math.Vector2;

public class WaterEnemy extends GameCharacter {
	
	private boolean attacking;
	private float sightRange;
	private float attackRange;
	private static int[] allForms = {Form.WATER_FORM};
	
	public WaterEnemy(Vector2 pos) {
		super(pos, Const.HUMAN_SIZE_X, Const.HUMAN_SIZE_Y, 
				Type.WATERENEMY, Side.ENEMY, 
				RandomUtil.getRandomBetween(Const.WATERENEMY_MIN_SPEED, Const.WATERENEMY_MAX_SPEED),
				allForms, Form.WATER_FORM, new Stats(Const.WATERENEMY_MAX_HEALTH, ElementType.WATER));
		this.attacking = false;
		this.sightRange = Const.WATERENEMY_SIGHT_RANGE;
		this.attackRange = Const.WATERENEMY_ATTACK_RANGE;
	}
	
	@Override
	public void update(float delta) {
		getMovable().update(delta);
		if (getMovable().isMoving()) {
			getMovable().move(delta);
			currentForm.getAnim().update(delta);
		} else {
			currentForm.getAnim().resetToFirstFrame();
			if (!attacking) { 
				startMovingToNewTarget();
			} 
		}
	}
	
	private void startMovingToNewTarget() {
		Vector2 newTarget = RandomUtil.getRandomPosition(position,
				RandomUtil.getRandomBetween(
						Const.WATERENEMY_MIN_WALK_DISTANCE,
						Const.WATERENEMY_MAX_WALK_DISTANCE));
		getMovable().startMoving(newTarget.x, newTarget.y);
	}
	
	public void processTargeting(Vector2 targetCenter) {
		// Check if within sight range
		if (Physics.inRange(getCenter(), targetCenter, sightRange)) {
			// Check if within attack range
			if (Physics.inRange(getCenter(), targetCenter, attackRange)) {
				if (!attacking) {
					setAttacking(true);
				}
				currentForm.activateAbility(targetCenter.x + RandomUtil.getTargetingOffset(), 
						targetCenter.y + RandomUtil.getTargetingOffset());
			} else {
				if (attacking) {
					setAttacking(false);
				}
			}
		} else {
			if (attacking) {
				setAttacking(false);
			}
			if (!getMovable().isMoving()) {
				startMovingToNewTarget();
			}
		}
	}
	
	@Override
	protected void death() {
		if (RandomUtil.itemDrop()) {
			Engine.ObjectManager.addBlueBlob(position);
		}
		super.death();
	}
	
	public float getSightRange() {
		return sightRange;
	}
	
	public float getAttackRange() {
		return attackRange;
	}
	
	public boolean isAttacking() {
		return attacking;
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
}
