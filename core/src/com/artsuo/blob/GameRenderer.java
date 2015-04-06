package com.artsuo.blob;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.abilities.effects.Projectile;
import com.artsuo.blob.abilities.effects.TimedStationaryObject;
import com.artsuo.blob.objects.GameCharacter;
import com.artsuo.blob.objects.pickupitems.PickupItem;
import com.artsuo.blob.screens.Statbar;
import com.artsuo.blob.world.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameRenderer {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private World world;
	public static Rectangle viewportBounds;
	private Vector2 viewportCenter;

	public GameRenderer(World world) {
		this.world = world;
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, Const.VIEWPORT_WIDTH, 
				Const.VIEWPORT_HEIGHT);
		viewportBounds = new Rectangle(0, 0, Const.VIEWPORT_WIDTH, Const.VIEWPORT_HEIGHT);
		this.viewportCenter = new Vector2(Engine.ObjectManager.getPlayer().getCenter());
		camera.position.set(viewportCenter.x, viewportCenter.y, 0);
		camera.update();
		this.batch = new SpriteBatch();
	}

	public void render(float delta) {
		updateViewportPos();
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		batch.begin();
		renderWorld();
		renderObjects(delta);
		batch.end();
	}

	// TO DO: Culling, optimization
	private void renderWorld() {
		for (int y = 0; y < World.worldSize.y; y++) {
			for (int x = 0; x < World.worldSize.x; x++) {
				world.getTiles()[x][y].getDrawable().getSprite().draw(batch);
			}
		}
	}
	
	private void renderObjects(float delta) {
		// GameCharacters
		for (GameCharacter gc : Engine.ObjectManager.getGameCharacters()) {
			if (gc.getBounds().overlaps(viewportBounds)) {
				batch.draw(gc.getAnim().getCurrentFrame(), gc.getPosition().x, gc.getPosition().y,
						gc.getBounds().width / 2, gc.getBounds().height / 2,
						gc.getBounds().width, gc.getBounds().height, 1f, 1f, -gc.getMovable().getMovingAngle());
			}
			if (!gc.getEffects().isEmpty()) {
				for (SpriteKey key : gc.getEffects().keySet()) {
					if (gc.getEffects().get(key).getColor().a != 0)	{
						batch.draw(gc.getEffects().get(key), gc.getPosition().x, gc.getPosition().y, 
								gc.getBounds().width, gc.getBounds().height);
					}
				}
			}
		}
		// Projectiles
		for (Projectile pro : Engine.ObjectManager.getProjectiles()) {
			if (pro.getBounds().overlaps(viewportBounds)) {
				batch.draw(pro.getDrawable().getSprite(), pro.getPosition().x, pro.getPosition().y,
						pro.getBounds().width / 2, pro.getBounds().height / 2,
						pro.getBounds().width, pro.getBounds().height, 1f, 1f, -pro.getRot().getAngle());
			}
		}
		// PickupItems
		for (PickupItem pi : Engine.ObjectManager.getPickupItems()) {
			if (pi.getBounds().overlaps(viewportBounds)) {
				pi.getDrawable().getSprite().draw(batch);
			}
		}
		// TimedStationaryObjects
		for (TimedStationaryObject tso : Engine.ObjectManager.getTimedStationaryObjects()) {
			if (tso.getBounds().overlaps(viewportBounds)) {
				batch.draw(tso.getDrawable().getSprite(), tso.getPosition().x, tso.getPosition().y,
						tso.getBounds().width / 2, tso.getBounds().height / 2,
						tso.getBounds().width, tso.getBounds().height, 1f, 1f, -tso.getRotation().getAngle());
			}
		}
	}
	
	private void updateViewportPos() {
		viewportCenter.set(Engine.ObjectManager.getPlayer().getCenter());
		viewportBounds.setPosition(viewportCenter.x - viewportBounds.width / 2, viewportCenter.y - viewportBounds.height / 2);
		camera.position.set(viewportCenter.x, viewportCenter.y, 0);
		camera.update();
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
}
