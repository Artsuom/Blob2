package com.artsuo.blob.util;

import java.util.Random;

import com.artsuo.blob.Const;
import com.artsuo.blob.Engine;
import com.artsuo.blob.objects.GameObject.Type;
import com.badlogic.gdx.math.Vector2;

public class RandomUtil {
	
	private static Random random;
	private static int earthEnemySpawnChance;
	private static int fireEnemySpawnChance;
	private static int waterEnemySpawnChance;
	private static int redSpawnChance;
	private static int blueSpawnChance;
	private static int rockCreateChance;
	private static int greenSpawnChance;
	private static int goldenSpawnChance;
	private static int dropChance;
	
	public RandomUtil() {
		random = new Random(System.currentTimeMillis());
		// Object spawns
		earthEnemySpawnChance = 10;
		fireEnemySpawnChance = 20;
		waterEnemySpawnChance = 30;
		redSpawnChance = 40;
		greenSpawnChance = 50;
		blueSpawnChance = 60;
		goldenSpawnChance = 62;
		// Tiles
		rockCreateChance = 5;
		// Drops
		dropChance = 25;
	}

	// TO DO: Check if location within map bounds
	// Get a new random position within specified distance from current position
	public static Vector2 getRandomPosition(Vector2 currentPos, float maxDist) {
		float maxX = currentPos.x + maxDist;
		float minX = currentPos.x - maxDist;
		float maxY = currentPos.y + maxDist;
		float minY = currentPos.y - maxDist;
		
		float x = minX + (random.nextFloat() * (maxX - minX));
		float y = minY + (random.nextFloat() * (maxY - minY));
		
		return new Vector2(x, y);
	}
	
	// Get a new random position within specified distance from current position,
	// With the specified inner area excluded
	public static Vector2 getRandomPosition(Vector2 currentPos, float maxDist, float exclusionRange) {
		float maxX = 0, minX = 0, maxY = 0, minY = 0;
		Vector2 result = new Vector2();
		int rnd = random.nextInt(3);
		switch(rnd) {
			// Left
			case 0:
				maxX = currentPos.x - exclusionRange;
				minX = currentPos.x - maxDist;
				maxY = currentPos.y + maxDist;
				minY = currentPos.y - maxDist;
				break;
			// Right	
			case 1:
				maxX = currentPos.x + maxDist;
				minX = currentPos.x + exclusionRange;
				maxY = currentPos.y + maxDist;
				minY = currentPos.y - maxDist;
				break;
			// Top
			case 2:
				maxX = currentPos.x + maxDist;
				minX = currentPos.x - maxDist;
				maxY = currentPos.y + maxDist;
				minY = currentPos.y + exclusionRange;
				break;
			// Bottom	
			case 3:
				maxX = currentPos.x + maxDist;
				minX = currentPos.x - maxDist;
				maxY = currentPos.y - exclusionRange;
				minY = currentPos.y - maxDist;
				break;
		}
		result = getRandomPosition(maxX, minX, maxY, minY);
		if (Engine.world.getBounds().contains(result)) {
			return result;
		}
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				maxX = currentPos.x - exclusionRange;
				minX = currentPos.x - maxDist;
				maxY = currentPos.y + maxDist;
				minY = currentPos.y - maxDist;
				result = getRandomPosition(maxX, minX, maxY, minY);
				if (Engine.world.getBounds().contains(result)) {
					return result;
				}
			} else if (i == 1) {
				maxX = currentPos.x + maxDist;
				minX = currentPos.x + exclusionRange;
				maxY = currentPos.y + maxDist;
				minY = currentPos.y - maxDist;
				result = getRandomPosition(maxX, minX, maxY, minY);
				if (Engine.world.getBounds().contains(result)) {
					return result;
				}
			} else if (i == 2) {
				maxX = currentPos.x + maxDist;
				minX = currentPos.x - maxDist;
				maxY = currentPos.y + maxDist;
				minY = currentPos.y + exclusionRange;
				result = getRandomPosition(maxX, minX, maxY, minY);
				if (Engine.world.getBounds().contains(result)) {
					return result;
				}
			} else if (i == 3) {
				maxX = currentPos.x + maxDist;
				minX = currentPos.x - maxDist;
				maxY = currentPos.y - exclusionRange;
				minY = currentPos.y - maxDist;
				result = getRandomPosition(maxX, minX, maxY, minY);
				if (Engine.world.getBounds().contains(result)) {
					return result;
				}
			}
		}
		return null;
	}
	
	
	// Get a random position within specified dimensions
	public static Vector2 getRandomPosition(float maxX, float minX, float maxY, float minY) {
		float x = minX + (random.nextFloat() * (maxX - minX));
		float y = minY + (random.nextFloat() * (maxY - minY));
		return new Vector2(x, y);
	}
	
	public static Vector2 getRandomPositionOnMap() {
		float x = random.nextFloat() * Engine.world.getBounds().width;
		if (x > Engine.world.getBounds().width)
			 x -= Const.HUMAN_SIZE_X;
		float y = random.nextFloat() * Engine.world.getBounds().height;
		if (y > Engine.world.getBounds().height)
			 y -= Const.HUMAN_SIZE_Y;
		return new Vector2(x, y);
	}
	
	public static Vector2 getRandomTopPosition() {
		return new Vector2(getRandomBetween(0, Engine.world.getBounds().width), Engine.world.getBounds().height);
	}
	
	public static Vector2 getRandomBottomPosition() {
		return new Vector2(getRandomBetween(0, Engine.world.getBounds().width), 0);
	}
	
	public static Vector2 getRandomLeftPosition() {
		return new Vector2(0, getRandomBetween(0, Engine.world.getBounds().height));
	}
	
	public static Vector2 getRandomRightPosition() {
		return new Vector2(Engine.world.getBounds().width, getRandomBetween(0, Engine.world.getBounds().height));
	}
	
	public static float getRandomBetween(float min, float max) {
		return min + (random.nextFloat() * (max - min));
	}
	// Bug:
	public static long getRandomBetween(long min, long max) {
		return min + (long)(random.nextDouble() * (max - min));
	}
	
	public static int getRandomInt(int max) {
		return random.nextInt(max);
	}
	
	public static int getRandomIntBetween(int min, int max) {
		return min + random.nextInt(max);
	}
	
	public static Type getRandomType() {
		int rnd = getRandomInt(100);
		if (rnd <= earthEnemySpawnChance) {
			return Type.EARTHENEMY;
		} else if (rnd <= fireEnemySpawnChance) {
			return Type.FIREENEMY;
		} else if (rnd <= waterEnemySpawnChance) {
			return Type.WATERENEMY;
		} else if (rnd <= redSpawnChance) {
			return Type.PICKUP_REDBLOB;
		} else if (rnd <= greenSpawnChance) {
			return Type.PICKUP_GREENBLOB;
		} else if (rnd <= blueSpawnChance) {
			return Type.PICKUP_BLUEBLOB;
		} else if (rnd <= goldenSpawnChance) {
			return Type.PICKUP_GOLDENBLOB;
		}
		return null;
	}
	
	public static Type getRandomTileType() {
		int rnd = getRandomInt(100);
		if (rnd <= rockCreateChance) {
			return Type.TILE_PILLAR;
		}
		return Type.TILE_GROUND;
	}
	
	public static boolean getRandomBoolean() {
		return random.nextBoolean();
	}
	
	public static boolean itemDrop() {
		int rnd = getRandomInt(100);
		if (rnd <= dropChance) {
			return true;
		}
		return false;
	}
	
	public static float getTargetingOffset() {
		float rnd = random.nextFloat() * Const.ENEMY_RANGED_DAMPING;
		if (!getRandomBoolean()) {
			rnd *= -1;
		}
		return rnd;
	}
}
