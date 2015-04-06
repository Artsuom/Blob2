package com.artsuo.blob.objects;

import com.artsuo.blob.Const;
import com.artsuo.blob.Engine;
import com.artsuo.blob.abilities.forms.Form;
import com.artsuo.blob.objects.components.Stats;
import com.artsuo.blob.objects.components.Stats.ElementType;
import com.artsuo.blob.util.Physics;
import com.artsuo.blob.util.RandomUtil;
import com.badlogic.gdx.math.Vector2;

public class FireEnemy extends GameCharacter {
	
	private boolean chasing;
	private boolean attacking;
	private float sightRange;
	private float attackRange;
	private static int[] allForms = {Form.FIRE_FORM};
	
	public FireEnemy(Vector2 pos) {
		super(pos, Const.HUMAN_SIZE_X, Const.HUMAN_SIZE_Y, 
				Type.FIREENEMY, Side.ENEMY, 
				RandomUtil.getRandomBetween(Const.FIREENEMY_MIN_SPEED, Const.FIREENEMY_MAX_SPEED),
				allForms, Form.FIRE_FORM, new Stats(Const.FIREENEMY_MAX_HEALTH, ElementType.FIRE));
		this.chasing = false;
		this.attacking = false;
		this.sightRange = Const.FIREENEMY_SIGHT_RANGE;
		this.attackRange = Const.FIREENEMY_ATTACK_RANGE;
	}
	
	@Override
	public void update(float delta) {
		getMovable().update(delta);
		if (getMovable().isMoving()) {
			getMovable().move(delta);
			currentForm.getAnim().update(delta);
		} else {
			currentForm.getAnim().resetToFirstFrame();
			if (!chasing && !attacking) { 
				startMovingToNewTarget();
			} 
		}
	}
	
	private void startMovingToNewTarget() {
		Vector2 newTarget = RandomUtil.getRandomPosition(position,
				RandomUtil.getRandomBetween(
						Const.FIREENEMY_MIN_WALK_DISTANCE,
						Const.FIREENEMY_MAX_WALK_DISTANCE));
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
				currentForm.activateAbility(targetCenter.x, targetCenter.y);
			} else {
				if (!chasing) {
					setChasing(true);
				}
				if (!getMovable().isMoving()) {
					Vector2 newTarget = Engine.ObjectManager.getPlayer()
							.getCenter();
					getMovable().startMoving(newTarget.x, newTarget.y);
				}
				if (attacking) {
					setAttacking(false);
				}
			}
		} else {
			if (attacking) {
				setAttacking(false);
			}
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
			Engine.ObjectManager.addRedBlob(position);
		}
		super.death();
	}
	
	public float getSightRange() {
		return sightRange;
	}
	
	public float getAttackRange() {
		return attackRange;
	}
	
	public boolean isChasing() {
		return chasing;
	}
	
	public boolean isAttacking() {
		return attacking;
	}
	
	public void setChasing(boolean chasing) {
		this.chasing = chasing;
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
}
