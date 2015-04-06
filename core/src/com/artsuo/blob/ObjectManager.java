package com.artsuo.blob;

import java.util.ArrayList;
import java.util.List;

import com.artsuo.blob.abilities.effects.Projectile;
import com.artsuo.blob.abilities.effects.TimedStationaryObject;
import com.artsuo.blob.objects.EarthEnemy;
import com.artsuo.blob.objects.FireEnemy;
import com.artsuo.blob.objects.GameCharacter;
import com.artsuo.blob.objects.GameObject;
import com.artsuo.blob.objects.Player;
import com.artsuo.blob.objects.WaterEnemy;
import com.artsuo.blob.objects.pickupitems.BlueBlob;
import com.artsuo.blob.objects.pickupitems.GoldenBlob;
import com.artsuo.blob.objects.pickupitems.GreenBlob;
import com.artsuo.blob.objects.pickupitems.PickupItem;
import com.artsuo.blob.objects.pickupitems.RedBlob;
import com.artsuo.blob.util.Physics;
import com.artsuo.blob.util.RandomUtil;
import com.badlogic.gdx.math.Vector2;

public class ObjectManager {

	private List<GameCharacter> gameCharacters;
	private List<PickupItem> pickupItems;
	private List<TimedStationaryObject> timedStationaryObjects;
	private List<Projectile> projectiles;
	private List<GameObject> tempObjects;
	private List<GameObject> remove;
	private Player player;
	
	public ObjectManager() {
		this.gameCharacters = new ArrayList<GameCharacter>();
		this.pickupItems = new ArrayList<PickupItem>();
		this.timedStationaryObjects = new ArrayList<TimedStationaryObject>();
		this.projectiles = new ArrayList<Projectile>();
		this.tempObjects = new ArrayList<GameObject>();
		this.remove = new ArrayList<GameObject>();
		spawnPlayer();
	}
	
	private void spawnPlayer() {
		Vector2 pos = new Vector2();
		while(true) {
			pos = RandomUtil.getRandomPositionOnMap();
			if (Physics.isPassable(pos, Const.PLAYER_SIZE_X, Const.PLAYER_SIZE_Y)) {
				this.player = new Player(pos);
				break;
			}
		}
		gameCharacters.add(player);
	}
	
	public void resetPlayerPos() {
		Vector2 pos = new Vector2();
		while(true) {
			pos = RandomUtil.getRandomPositionOnMap();
			if (Physics.isPassable(pos, Const.PLAYER_SIZE_X, Const.PLAYER_SIZE_Y)) {
				this.player.setPosition(pos);
				break;
			}
		}
		if (player.getMovable().isMoving()) {
			player.getMovable().stopMoving();
		}
	}
	
	public void update(float delta) {
		// Update Objects
		updateGameCharacters(delta);
		updateProjectiles(delta);
		updatePickupItems(delta);
		updateTimedStationaryObjects(delta);
		
		if (!remove.isEmpty()) {
			for (GameObject go : remove) {
				if (go instanceof GameCharacter) {
					gameCharacters.remove(go);
				} else if (go instanceof PickupItem) {
					pickupItems.remove(go);
				} else if (go instanceof TimedStationaryObject) {
					timedStationaryObjects.remove(go);
				} else if (go instanceof Projectile) {
					projectiles.remove(go);
				}
			}
			remove.clear();
		}
		
		if (!tempObjects.isEmpty()) {
			for (GameObject go : tempObjects) {
				if (go instanceof GameCharacter) {
					gameCharacters.add((GameCharacter)go);
				} else if (go instanceof PickupItem) {
					pickupItems.add((PickupItem)go);
				} else if (go instanceof TimedStationaryObject) {
					timedStationaryObjects.add((TimedStationaryObject)go);
				} else if (go instanceof Projectile) {
					projectiles.add((Projectile)go);
				}
			}
			tempObjects.clear();
		}
	}
	
	private void updateGameCharacters(float delta) {
		for (GameCharacter ch : gameCharacters) {
			if (ch.isRemove()) {
				remove.add(ch);
			} else {
				ch.update(delta);
				ch.updateBounds();
			}
		}
	}
	
	private void updateProjectiles(float delta) {
		for (Projectile pro : projectiles) {
			if (pro.isRemove()) {
				remove.add(pro);
			} else {
				pro.update(delta);
				pro.updateBounds();
			}
		}
	}
	
	private void updatePickupItems(float delta) {
		for (PickupItem pi : pickupItems) {
			if (pi.isRemove()) {
				remove.add(pi);
			} 
		}
	}
	
	private void updateTimedStationaryObjects(float delta) {
		for (TimedStationaryObject tso : timedStationaryObjects) {
			if (tso.isRemove()) {
				remove.add(tso);
			} else {
				tso.update(delta);
			}
		}
	}
	
	public void clearAll() {
		gameCharacters.clear();
		gameCharacters.add(player);
		pickupItems.clear();
		timedStationaryObjects.clear();
		projectiles.clear();
	}
	
	public void addFireEnemy(Vector2 position) {
		tempObjects.add(new FireEnemy(position));
	}
	
	public void addWaterEnemy(Vector2 position) {
		tempObjects.add(new WaterEnemy(position));
	}
	
	public void addEarthEnemy(Vector2 position) {
		tempObjects.add(new EarthEnemy(position));
	}
	
	public void addProjectile(Projectile pro) {
		tempObjects.add(pro);
	}
	
	public void addTimedStationaryObject(TimedStationaryObject ob) {
		tempObjects.add(ob);
	}
	
	public void addRedBlob(Vector2 position) {
		tempObjects.add(new RedBlob(position));
	}
	
	public void addBlueBlob(Vector2 position) {
		tempObjects.add(new BlueBlob(position));
	}
	
	public void addGreenBlob(Vector2 position) {
		tempObjects.add(new GreenBlob(position));
	}
	
	public void addGoldenBlob(Vector2 position) {
		tempObjects.add(new GoldenBlob(position));
	}
	
	public List<GameCharacter> getGameCharacters() {
		return gameCharacters;
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public List<PickupItem> getPickupItems() {
		return pickupItems;
	}
	
	public List<TimedStationaryObject> getTimedStationaryObjects() {
		return timedStationaryObjects;
	}
	
	public List<GameObject> getRemovableObjects() {
		return remove;
	}
	
	public Player getPlayer() {
		return player;
	}
}
