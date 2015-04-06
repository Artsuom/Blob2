package com.artsuo.blob.world;

import com.artsuo.blob.AssetBank.SpriteKey;
import com.artsuo.blob.Const;
import com.artsuo.blob.Engine;
import com.artsuo.blob.objects.GameObject.Side;
import com.artsuo.blob.objects.GameObject.Type;
import com.artsuo.blob.util.Physics;
import com.artsuo.blob.util.RandomUtil;
import com.artsuo.blob.util.Timer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class World {
	
	private Rectangle bounds;
	private Timer spawnTimer;
	private Tile[][] tiles;
	private Tile exit;
	private int rocks;
	public static Vector2 worldSize;
	private Timer playerExitTimer;

	public World(int tilesX, int tilesY) {
		this.spawnTimer = new Timer(Const.SPAWN_COOLDOWN);
		this.playerExitTimer = new Timer(500);
		createMap(tilesX, tilesY);
	}
	
	public void createMap(int sx, int sy) {
		worldSize = new Vector2(sx, sy);
		this.bounds = new Rectangle(0, 0, sx * Const.TILE_SIZE, sy * Const.TILE_SIZE);
		this.tiles = new Tile[sx][sy];
		this.rocks = RandomUtil.getRandomIntBetween(Const.WORLD_MAX_SIZE_X / 5, Const.WORLD_MAX_SIZE_X * 2);
		for (int y = 0; y < sy; y++) {
			for (int x = 0; x < sx; x++) {
				if (rocks > 0 && RandomUtil.getRandomTileType() == Type.TILE_PILLAR) {
						tiles[x][y] = new Tile(new Vector2(x
								* Const.TILE_SIZE, y
								* Const.TILE_SIZE), false,
								SpriteKey.TILE_PILLAR,
								Type.TILE_PILLAR, Side.NEUTRAL);
						rocks--;
				} else {
					tiles[x][y] = new Tile(new Vector2(x * Const.TILE_SIZE, y * Const.TILE_SIZE), true, SpriteKey.TILE_FLOOR, Type.TILE_GROUND, Side.NEUTRAL);
				}
			}
		}
		placeExit();
	}
	
	private void placeExit() {
		int x = RandomUtil.getRandomInt((int)worldSize.x);
		int y = RandomUtil.getRandomInt((int)worldSize.y);
		Tile exit = new Tile(new Vector2(x
				* Const.TILE_SIZE, y
				* Const.TILE_SIZE), true,
				SpriteKey.TILE_EXIT,
				Type.TILE_EXIT, Side.NEUTRAL);
		tiles[x][y] = exit;
		this.exit = exit;
	}
	
	public void newMap(int sx, int sy) {
		createMap(sx, sy);
		Engine.ObjectManager.resetPlayerPos();
		Engine.ObjectManager.clearAll();
	}
	
	public void update(float delta) {
		if (spawnTimer.isOver()) {
			randomSpawn();
			spawnTimer.restart();
		}
		if (playerExitTimer.isOver()) {
			if (Physics.collision(Engine.ObjectManager.getPlayer().getCollisionRect(),
					exit.getBounds())) {
				newMap(RandomUtil.getRandomIntBetween(
						Const.WORLD_MIN_SIZE_X, Const.WORLD_MAX_SIZE_X), 
						RandomUtil.getRandomIntBetween(Const.WORLD_MIN_SIZE_Y, Const.WORLD_MAX_SIZE_Y));
			}
			playerExitTimer.restart();
		}
	}
	
	@SuppressWarnings("incomplete-switch")
	private void randomSpawn() {
		Type type = RandomUtil.getRandomType();
		if (type == null) {
			return;
		}
		switch(type) {
		case FIREENEMY:
			Vector2 fireEnemyPos = new Vector2();
			while(true) {
				fireEnemyPos = RandomUtil.getRandomPositionOnMap();
				if (Physics.isPassable(fireEnemyPos, Const.HUMAN_SIZE_X, Const.HUMAN_SIZE_Y)) {
					Engine.ObjectManager.addFireEnemy(fireEnemyPos);
					break;
				}
			}
			break;
		case WATERENEMY:
			Vector2 pos = new Vector2();
			while(true) {
				pos = RandomUtil.getRandomPositionOnMap();
				if (Physics.isPassable(pos, Const.HUMAN_SIZE_X, Const.HUMAN_SIZE_Y)) {
					Engine.ObjectManager.addWaterEnemy(pos);
					break;
				}
			}
			break;
		case EARTHENEMY:
			Vector2 earthEnemyPos = new Vector2();
			while(true) {
				earthEnemyPos = RandomUtil.getRandomPositionOnMap();
				if (Physics.isPassable(earthEnemyPos, Const.HUMAN_SIZE_X, Const.HUMAN_SIZE_Y)) {
					Engine.ObjectManager.addEarthEnemy(earthEnemyPos);
					break;
				}
			}
			break;
		case PICKUP_REDBLOB:
			Engine.ObjectManager.addRedBlob(RandomUtil.getRandomPositionOnMap());
			break;
		case PICKUP_BLUEBLOB:
			Engine.ObjectManager.addBlueBlob(RandomUtil.getRandomPositionOnMap());
			break;
		case PICKUP_GREENBLOB:
			Engine.ObjectManager.addGreenBlob(RandomUtil.getRandomPositionOnMap());
			break;
		case PICKUP_GOLDENBLOB:
			Engine.ObjectManager.addGoldenBlob(RandomUtil.getRandomPositionOnMap());
			break;
		}
		
	}
	
	// Return a tile matching the position of given world coordinates
	public Tile getTile(float worldX, float worldY) {
		int arrayX = (int) Math.floor(worldX / Const.TILE_SIZE);
		int arrayY = (int) Math.floor(worldY / Const.TILE_SIZE);
		if (arrayX >= 0 && arrayY >= 0 && arrayX < World.worldSize.x && arrayY < World.worldSize.y) {
			return tiles[arrayX][arrayY];
		} 
		return new Tile(new Vector2(0, 0), false, null, Type.TILE_PILLAR, Side.NEUTRAL);
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Timer getSpawnTimer() {
		return spawnTimer;
	}
}
