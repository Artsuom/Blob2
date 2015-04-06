package com.artsuo.blob;

import java.util.ArrayList;
import java.util.List;

import com.artsuo.blob.abilities.effects.Projectile;
import com.artsuo.blob.abilities.effects.TimedStationaryObject;
import com.artsuo.blob.objects.EarthEnemy;
import com.artsuo.blob.objects.FireEnemy;
import com.artsuo.blob.objects.GameCharacter;
import com.artsuo.blob.objects.GameObject;
import com.artsuo.blob.objects.GameObject.Type;
import com.artsuo.blob.objects.Player;
import com.artsuo.blob.objects.WaterEnemy;
import com.artsuo.blob.objects.pickupitems.PickupItem;
import com.artsuo.blob.screens.GameScreen;
import com.artsuo.blob.util.Clock;
import com.artsuo.blob.util.ObjectQuadTree;
import com.artsuo.blob.util.Physics;
import com.artsuo.blob.util.RandomUtil;
import com.artsuo.blob.world.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;

public class Engine {
	
	private boolean paused;
	public static RandomUtil rnd = new RandomUtil();
	public static World world = new World(RandomUtil.getRandomIntBetween(
			Const.WORLD_MIN_SIZE_X, Const.WORLD_MAX_SIZE_X), 
			RandomUtil.getRandomIntBetween(Const.WORLD_MIN_SIZE_Y, Const.WORLD_MAX_SIZE_Y));
	public static ObjectManager ObjectManager = new ObjectManager();
	public static Clock clock = new Clock();
	public static GameScreen gameScreen;
	private GameRenderer renderer;
	private GameInputProcessor gameInputProcessor;
	private InputMultiplexer inputMultiplexer;
	private ObjectQuadTree objectQuadTree;
	public static boolean gameOver;
	private List<GameObject> allObjects;

	public Engine(GameScreen screen) {
		gameScreen = screen;
		this.paused = false;
		gameOver = false;
		this.allObjects = new ArrayList<GameObject>();
		//initObjectList();
		this.renderer = new GameRenderer(world);
		this.gameInputProcessor = new GameInputProcessor(this, ObjectManager.getPlayer());
		this.inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(screen.getStage());
		inputMultiplexer.addProcessor(gameInputProcessor);
		Gdx.input.setInputProcessor(inputMultiplexer);
		this.objectQuadTree = new ObjectQuadTree(0, new Rectangle(0, 0, Const.WORLD_MAX_SIZE_X, Const.WORLD_MAX_SIZE_Y));
		initCooldowns();
	}
	
	public static void restart() {
		rnd = new RandomUtil();
		world = new World(Const.WORLD_MAX_SIZE_X, Const.WORLD_MAX_SIZE_Y);
		ObjectManager = new ObjectManager();
		clock = new Clock();
	}
	
	public void update(float delta) {
		if (!paused) {
			clock.update(delta);
			if (!gameOver) {
				// TODO: Update spawn rates and other logic
			}
			updateQuadTree();
			checkCollisions();
			ObjectManager.update(delta);
			resetQuadTree();
			world.update(delta);
		}
	}
	
	private void resetQuadTree() {
		allObjects.clear();
		/*
		allObjects.removeAll(ObjectManager.getGameCharacters());
		allObjects.removeAll(ObjectManager.getMovingObjects());
		allObjects.removeAll(ObjectManager.getPickupItems());
		allObjects.removeAll(ObjectManager.getTimedStationaryObjects());
		allObjects.removeAll(ObjectManager.getRemovableObjects());
		*/
		objectQuadTree.clear();
	}
	
	private void updateQuadTree() {
		// Add all objects
		allObjects.addAll(ObjectManager.getGameCharacters());
		allObjects.addAll(ObjectManager.getProjectiles());
		allObjects.addAll(ObjectManager.getPickupItems());
		allObjects.addAll(ObjectManager.getTimedStationaryObjects());

		for (int i = 0; i < allObjects.size(); i++) {
			objectQuadTree.insert(allObjects.get(i));
		}
	}
	
	private void checkCollisions() {
		ArrayList<GameObject> returnObjects = new ArrayList<GameObject>();
		for (int i = 0; i < allObjects.size(); i++) {
			returnObjects.clear();
			objectQuadTree.retrieve(returnObjects, allObjects.get(i));
			// Collisions for Player:
			if (allObjects.get(i).getType() == Type.PLAYER) {
				Player player = (Player)allObjects.get(i);
				for (int x = 0; x < returnObjects.size(); x++) {
					if (returnObjects.get(x) instanceof PickupItem) {
						if (Physics.collision(player.getCollisionRect(), returnObjects.get(x).getBounds())) {
							PickupItem pi = (PickupItem) returnObjects.get(x);
							pi.onPickup(player.getInv());
						}
					}
				}
			}
			
			// Collisions for FireEnemy:
			if (allObjects.get(i).getType() == Type.FIREENEMY && !allObjects.get(i).isRemove()) {
				FireEnemy fe = (FireEnemy)allObjects.get(i);
				for (int x = 0; x < returnObjects.size(); x++) {
					if (returnObjects.get(x).getType() == Type.PLAYER) {
						fe.processTargeting(returnObjects.get(x).getCenter());
					}
				}
			}
			
			// Collisions for WaterEnemy:
			if (allObjects.get(i).getType() == Type.WATERENEMY && !allObjects.get(i).isRemove()) {
				WaterEnemy we = (WaterEnemy)allObjects.get(i);
				for (int x = 0; x < returnObjects.size(); x++) {
					if (returnObjects.get(x).getType() == Type.PLAYER) {
						we.processTargeting(returnObjects.get(x).getCenter());
					}
				}
			}
			
			// Collisions for EarthEnemy:
			if (allObjects.get(i).getType() == Type.EARTHENEMY && !allObjects.get(i).isRemove()) {
				EarthEnemy ee = (EarthEnemy)allObjects.get(i);
					for (int x = 0; x < returnObjects.size(); x++) {
						if (returnObjects.get(x).getType() == Type.PLAYER) {
							ee.processTargeting(returnObjects.get(x).getCenter());
							if (Physics.collision(ee.getBounds(), returnObjects.get(x).getBounds())) {
								Player p = (Player)returnObjects.get(x);
								if (Physics.collision(ee.getBounds(), p.getCollisionRect())) {
									p.onHit(ee.getDamage());
								}
							}
						}
				}
			}
			
			// Collisions for Projectiles
			if (allObjects.get(i) instanceof Projectile) {
				for (int x = 0; x < returnObjects.size(); x++) {
					if (allObjects.get(i).getSide() != returnObjects.get(x).getSide()) {
						if (returnObjects.get(x) instanceof GameCharacter) {
							GameCharacter gc = (GameCharacter)returnObjects.get(x);
							Projectile pro = (Projectile)allObjects.get(i);
							if (Physics.collision(gc.getCollisionRect(), pro.getBounds())) {
								pro.onHit();
								gc.onHit(pro.getDamage());
							}
						}
					}
				}
			}
			if (allObjects.get(i) instanceof TimedStationaryObject) {
				for (int x = 0; x < returnObjects.size(); x++) {
					if (allObjects.get(i).getSide() != returnObjects.get(x).getSide()) {
						if (returnObjects.get(x) instanceof GameCharacter) {
							GameCharacter gc = (GameCharacter)returnObjects.get(x);
							TimedStationaryObject tso = (TimedStationaryObject)allObjects.get(i);
							if (Physics.collision(gc.getCollisionRect(), tso.getBounds())) {
								tso.onHit();
								gc.onHit(tso.getDamage());
							}
						}
					}
				}
			}
		}
	}
	
	private void initCooldowns() {
		Const.SPAWN_COOLDOWN = Const.INITIAL_SPAWN_COOLDOWN;
	}
	
	private void updateSpawnRates() {
		Const.SPAWN_COOLDOWN -= Const.SPAWN_COOLDOWN_REDUCTION;
	}
	
	public static void gameOver() {
		gameOver = true;
		gameScreen.gameOver();
	}
	
	public static void gameWon() {
		gameOver = true;
		gameScreen.gameWon();
	}
	
	public void render(float delta) {
		renderer.render(delta);
	}
	
	public World getWorld() {
		return world;
	}
	
	public GameRenderer getRenderer() {
		return renderer;
	}
	
	public void dispose() {
		
	}
	
	public void setInputProcessor(InputProcessor processor) {
		
	}
}
