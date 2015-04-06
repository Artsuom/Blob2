package com.artsuo.blob;

import com.artsuo.blob.abilities.forms.Form;
import com.artsuo.blob.objects.Player;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GameInputProcessor implements GestureListener, InputProcessor {

	private Engine engine;
	private Player player;
	private Vector3 touchPos;

	public GameInputProcessor(Engine engine, Player player) {
		this.engine = engine;
		this.player = player;
		this.touchPos = new Vector3();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.ESCAPE) {
			// Pause game, show menu
		}
		if (keycode == Keys.BACK) {
			// Pause game, show menu
		}
		if (keycode == Keys.MENU) {
			// Pause game, show menu
		}
		if (keycode == Keys.Z) {
			player.changeForm(Form.BASIC_FORM);
		}
		if (keycode == Keys.X) {
			player.changeForm(Form.FIRE_FORM);
		}
		if (keycode == Keys.C) {
			player.changeForm(Form.WATER_FORM);
		}
		if (keycode == Keys.V) {
			player.changeForm(Form.GAS_FORM);
		}
		if (keycode == Keys.B) {
			player.changeForm(Form.POISON_FORM);
		}
		if (keycode == Keys.N) {
			player.changeForm(Form.ACID_FORM);
		}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touchPos.set(screenX, screenY, 0);
		engine.getRenderer().getCamera().unproject(touchPos);
		if (button == Buttons.LEFT) {
			player.getMovable().startMoving(touchPos.x, touchPos.y);
		} else if (button == Buttons.RIGHT) {
			player.abilityActivated(touchPos.x, touchPos.y);
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
