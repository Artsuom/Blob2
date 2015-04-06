package com.artsuo.blob.objects;

import java.util.HashMap;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Const;
import com.artsuo.blob.abilities.effects.Damage;
import com.artsuo.blob.abilities.forms.AcidForm;
import com.artsuo.blob.abilities.forms.BasicForm;
import com.artsuo.blob.abilities.forms.FireForm;
import com.artsuo.blob.abilities.forms.Form;
import com.artsuo.blob.abilities.forms.GasForm;
import com.artsuo.blob.abilities.forms.PoisonForm;
import com.artsuo.blob.abilities.forms.WaterForm;
import com.artsuo.blob.objects.components.Anim;
import com.artsuo.blob.objects.components.Movable;
import com.artsuo.blob.objects.components.Stats;
import com.artsuo.blob.util.Timer;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameCharacter extends GameObject {
	
	protected Form currentForm;
	protected HashMap<Integer, Form> forms;
	protected Movable movable;
	protected Rectangle collisionRect;
	protected HashMap<SpriteKey, Sprite> effects;
	protected Timer hitTimer;
	protected Stats stats;
	
	public GameCharacter(Vector2 pos, float sx, float sy, Type type, Side side, float speed, int[] allForms, int currentFormId, Stats stats) {
		init(pos, sx, sy, type, side);
		this.collisionRect = new Rectangle(position.x + bounds.width / 4, position.y + bounds.height / 4,
				bounds.width / 2, bounds.height / 2);
		this.movable = new Movable(this, collisionRect, speed);
		this.effects = new HashMap<SpriteKey, Sprite>();
		this.hitTimer = new Timer(Const.HIT_COOLDOWN);
		this.stats = stats;
		initForms(allForms, currentFormId);
	}
	
	private void initForms(int[] allForms, int currentFormId) {
		this.forms = new HashMap<Integer, Form>();
		for (int i = 0; i < allForms.length; i++) {
			if (allForms[i] == Form.BASIC_FORM) {
				BasicForm form = new BasicForm(this);
				forms.put(Form.BASIC_FORM, form);
				if (currentFormId == Form.BASIC_FORM) {
					currentForm = form;
				}
			} else if (allForms[i] == Form.FIRE_FORM) {
				FireForm form = new FireForm(this, Const.FIREFORM_DAMAGE);
				forms.put(Form.FIRE_FORM, form);
				if (currentFormId == Form.FIRE_FORM) {
					currentForm = form;
				}
			} else if (allForms[i] == Form.WATER_FORM) {
				WaterForm form = new WaterForm(this);
				forms.put(Form.WATER_FORM, form);
				if (currentFormId == Form.WATER_FORM) {
					currentForm = form;
				}
			} else if (allForms[i] == Form.GAS_FORM) {
				GasForm form = new GasForm(this);
				forms.put(Form.GAS_FORM, form);
				if (currentFormId == Form.GAS_FORM) {
					currentForm = form;
				}
			} else if (allForms[i] == Form.POISON_FORM) {
				PoisonForm form = new PoisonForm(this);
				forms.put(Form.POISON_FORM, form);
				if (currentFormId == Form.POISON_FORM) {
					currentForm = form;
				}
			} else if (allForms[i] == Form.ACID_FORM) {
				AcidForm form = new AcidForm(this);
				forms.put(Form.ACID_FORM, form);
				if (currentFormId == Form.ACID_FORM) {
					currentForm = form;
				}
			}
		}
		
	}
	
	public void changeForm(int formType) {
		if (forms.containsKey(formType)) {
			setForm(forms.get(formType));
		}
	}
	
	protected void setForm(Form form) {
		currentForm = form;
	}
	
	public abstract void update(float delta);
	
	//public abstract void onHit(Damage damage);
	
	public void onHit(Damage damage) {
		if (hitTimer.isOver()) {
			if (!stats.damage(damage.getType(), damage.getDamage())) {
				death();
			}
			hitTimer.restart();
		}
	}
	
	protected void death() {
		remove();
	}
	
	@Override
	public void updateBounds() {
		bounds.setPosition(position);
		collisionRect.setPosition(position.x + bounds.width / 4, position.y + bounds.height / 4);
	}
	
	public Movable getMovable() {
		return movable;
	}
	
	public Anim getAnim() {
		return currentForm.getAnim();
	}
	
	public Rectangle getCollisionRect() {
		return collisionRect;
	}
	
	public HashMap<SpriteKey, Sprite> getEffects() {
		return effects;
	}
	
	public Form getCurrentForm() {
		return currentForm;
	}
	
	public Stats getStats() {
		return stats;
	}

}
