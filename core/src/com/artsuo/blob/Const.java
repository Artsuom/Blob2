package com.artsuo.blob;


public class Const {

	public static final float VIEWPORT_WIDTH = 25f; // 800px (x32)
	public static final float VIEWPORT_HEIGHT = 15f; // 480px (x32)
	public static final float VIEWPORT_BOUNDS_OFFSET = 0.1f;
	public static final int LANDSCAPE_SCALE_X = 7;
	public static final int LANDSCAPE_SCALE_Y = 10;
	
	public static final float TILE_SIZE = 1.5f;
	public static final float WORLD_TO_PIX_RATIO = 32f;
	
	public static final int WORLD_MAX_SIZE_X = 20;
	public static final int WORLD_MIN_SIZE_X = 10;
	public static final int WORLD_MAX_SIZE_Y = 20;
	public static final int WORLD_MIN_SIZE_Y = 10;
	public static final float HUMAN_SIZE_X = 2f;
	public static final float HUMAN_SIZE_Y = 2f;
	
	public static final float PLAYER_SIZE_X = 2f;
	public static final float PLAYER_SIZE_Y = 2f;
	public static final float PLAYER_MAX_HEALTH = 100f;
	
	public static final float ICICLE_SIZE_X = 0.5f;
	public static final float ICICLE_SIZE_Y = 1f;
	public static final float ICICLE_SPEED = 6f;
	public static final int ICICLE_DAMAGE = 10;
	public static final float ICICLE_RANGE = 6f;
	public static final long WATER_FORM_COOLDOWN = 1500;
	
	public static final float GASCLOUD_SIZE_X = 1f;
	public static final float GASCLOUD_SIZE_Y = 1f;
	public static final float GASCLOUD_SPEED = 4f;
	public static final int GASCLOUD_DAMAGE = 3;
	public static final float GASCLOUD_RANGE = 4f;
	public static final long GAS_FORM_COOLDOWN = 1000;
	
	public static final float POISONBALL_SIZE_X = 0.5f;
	public static final float POISONBALL_SIZE_Y = 0.5f;
	public static final float POISONBALL_SPEED = 5f;
	public static final int POISONBALL_DAMAGE = 5;
	public static final float POISONBALL_RANGE = 5f;
	public static final long POISON_FORM_COOLDOWN = 2000;
	
	public static final float POISONFIELD_SIZE_X = 1.5f;
	public static final float POISONFIELD_SIZE_Y = 1.5f;
	public static final int POISONFIELD_DAMAGE = 5;
	public static final long POISONFIELD_DURATION = 4000;
	
	public static final long ACID_FORM_COOLDOWN = 3000;
	public static final float ACIDPOOL_SIZE_X = 1.5f;
	public static final float ACIDPOOL_SIZE_Y = 1.5f;
	public static final int ACIDPOOL_DAMAGE = 15;
	public static final long ACIDPOOL_DURATION = 5000;
	
	public static final float PICKUP_SIZE_X = 1f;
	public static final float PICKUP_SIZE_Y = 1f;

	public static final float ENEMY_MAX_SPAWN_RANGE = 8;
	public static final float ENEMY_MIN_SPAWN_RANGE = 2.5f;
	public static final float STOP_DISTANCE = 0.1f;
	public static final float PLAYER_DEFAULT_SPEED = 2f;
	public static final float PLAYER_BOOSTED_SPEED = 3f;
	public static final float FIREENEMY_MIN_SPEED = 1.5f;
	public static final float FIREENEMY_MAX_SPEED = 2.5f;
	public static final float FIREENEMY_MIN_WALK_DISTANCE = 2.5f;
	public static final float FIREENEMY_MAX_WALK_DISTANCE = 9f;
	public static final float FIREENEMY_SIGHT_RANGE = 4f;
	public static final float FIREENEMY_ATTACK_RANGE = 1f;
	public static final float FIREENEMY_MAX_HEALTH = 20f;
	public static final long DRUNK_MIN_TIME = 10000;
	public static final long DRUNK_MAX_TIME = 45000;
	
	// WaterEnemy
	public static final float WATERENEMY_MIN_SPEED = 1f;
	public static final float WATERENEMY_MAX_SPEED = 2f;
	public static final float WATERENEMY_MIN_WALK_DISTANCE = 2f;
	public static final float WATERENEMY_MAX_WALK_DISTANCE = 7f;
	public static final float WATERENEMY_SIGHT_RANGE = 8f;
	public static final float WATERENEMY_ATTACK_RANGE = 8f;
	public static final float WATERENEMY_MAX_HEALTH = 20f;
	
	// EarthEnemy
	public static final float EARTHENEMY_MIN_SPEED = 1.75f;
	public static final float EARTHENEMY_MAX_SPEED = 2.75f;
	public static final float EARTHENEMY_MIN_WALK_DISTANCE = 3f;
	public static final float EARTHENEMY_MAX_WALK_DISTANCE = 8f;
	public static final float EARTHENEMY_SIGHT_RANGE = 3f;
	public static final float EARTHENEMY_ATTACK_RANGE = 1f;
	public static final float EARTHENEMY_MAX_HEALTH = 10f;
	public static final float EARTHENEMY_DAMAGE = 5f;
	
	public static final float ENEMY_RANGED_DAMPING = 2;
	
	public static final long PUDDLE_MIN_TIME = 30000;
	public static final long PUDDLE_MAX_TIME = 70000;
	
	public static final float BARREL_SLOW_SPEED = 4f;
	
	public static final long INITIAL_SPAWN_COOLDOWN = 2000;
	public static final long SPAWN_COOLDOWN_REDUCTION = 20;
	
	public static long SPAWN_COOLDOWN = 3000;
	
	public static final long SECONDS_TO_DAYS = 15;
	
	public static final int DAYS_TO_WIN = 32;
	
	public static final long SPEEDBOOST_DURATION = 4000;
	public static final long SHIELDED_DURATION = 7500;
	
	public static final int MAX_BLOBS = 100;
	public static final int MED_BLOBS = 50;
	public static final int MIN_BLOBS = 25;
	public static final int MAX_GOLDENBLOBS = 50;
	
	// Cooldowns
	public static final long HIT_COOLDOWN = 2000;
	public static final long SPEEDBOOST_COOLDOWN = 10000;
	
	public static final int COLLISION_DAMAGE = 10;
	
	public static final int FIREFORM_DAMAGE = 10;
	
	// Pickups and inventory
	public static final int REDBLOB_AMT = 10;
	public static final int GREENBLOB_AMT = 10;
	public static final int BLUEBLOB_AMT = 10;
	
	/*
	public static final String SAVED_VALUES_PATH = "data/temp/values.sav";
	public static final String SAVED_SPEEDTIMER_PATH = "data/temp/speedTimer.sav";
	public static final String SAVED_DOUBLETIMER_PATH = "data/temp/doubleTimer.sav";
	public static final String SAVED_STATS_PATH = "data/temp/stats.sav";
	public static final String SAVED_COINS_PATH = "data/temp/coins.sav";
	*/
	public static final float DEFAULT_SOUND_VOLUME = 0.5f;
	public static final float DEFAULT_MUSIC_VOLUME = 0.5f;
}
