package com.artsuo.blob.objects;

import com.artsuo.blob.Const;
import com.artsuo.blob.Engine;
import com.artsuo.blob.abilities.effects.Damage;
import com.artsuo.blob.abilities.forms.Form;
import com.artsuo.blob.objects.components.Stats;
import com.artsuo.blob.objects.components.Stats.ElementType;
import com.artsuo.blob.objects.components.inventory.BlobContainer;
import com.artsuo.blob.objects.components.inventory.Inventory;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameCharacter {
	
	private static int[] allForms = {Form.BASIC_FORM, Form.FIRE_FORM, 
		Form.WATER_FORM, Form.GAS_FORM, Form.POISON_FORM, Form.ACID_FORM};
	
	private Inventory inv;
	
	public Player(Vector2 pos) {
		super(pos, Const.PLAYER_SIZE_X, Const.PLAYER_SIZE_Y, 
				Type.PLAYER, Side.PLAYER, 
				Const.PLAYER_DEFAULT_SPEED, allForms, Form.BASIC_FORM, new Stats(Const.PLAYER_MAX_HEALTH, ElementType.EARTH)
		);
		this.inv = new Inventory(this);
		//SpriteKey[] effects = {SpriteKey.EFFECT_PLAYER_SHIELD};
		//initEffects(effects);
	}
	/*
	public void initEffects(SpriteKey[] spriteKeys) {
		for (int i = 0; i < spriteKeys.length; i++) {
			Sprite spr = AssetBank.createObjectSprite(spriteKeys[i]);
			spr.setAlpha(0);
			effects.put(spriteKeys[i], spr);
		}
	}
	*/
	@Override
	public void update(float delta) {
		getMovable().update(delta);
		if (getMovable().isMoving()) {
			getMovable().move(delta);
			currentForm.getAnim().update(delta);
			updateBounds();
		} else {
			currentForm.getAnim().setCurrentFrame(currentForm.getAnim().getAnimation().getKeyFrames()[2]);
		}
		inv.update();
	}
	
	@Override
	public void onHit(Damage damage) {
		if (hitTimer.isOver()) {
			if (!stats.damage(damage.getType(), damage.getDamage())) {
				death();
			}
			Engine.gameScreen.getStatbar().updateHealth(stats.getCurrHealth(), stats.getMaxHealth());
			hitTimer.restart();
		}
	}
	
	public void abilityActivated(float posX, float posY) {
		currentForm.activateAbility(posX, posY);
	}
	
	@Override
	protected void death() {
		remove();
	}
	
	@Override
	public void remove() {
		setRemove(true);
		Engine.gameOver();
	}
	
	public Inventory getInv() {
		return inv;
	}
}
