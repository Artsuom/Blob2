package com.artsuo.blob.objects.components;

import com.artsuo.blob.Const;
import com.artsuo.blob.objects.GameObject;
import com.artsuo.blob.util.Physics;
import com.artsuo.blob.util.Timer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Movable {

	private Vector2 targetPosition; 
	private Vector2 dist;	
	private float speed;
	private boolean moving;
	private GameObject master;
	private Rectangle masterCollisionBounds;
	private Rotatable rot;
	private Timer speedBoostTimer;
	
	public Movable(GameObject master, Rectangle masterBounds, float speed) {
		this.master = master;
		this.masterCollisionBounds = masterBounds;
		this.targetPosition = new Vector2(0, 0);
		this.dist = new Vector2(0, 0);
		this.speed = speed;
		this.moving = false;
		this.rot = new Rotatable(0);
		this.speedBoostTimer = new Timer(Const.SPEEDBOOST_DURATION, false);
	}
	
	public void move(float delta) {
		calculateDistanceToTarget();
		float length = (float) Math.sqrt(dist.x * dist.x + 
				dist.y * dist.y);
		
		dist.x /= length;
		dist.y /= length;
		
		if (dist.x > length) {
            dist.x = length;
        }
        if (dist.x < -length) {
            dist.x = -length;
        }
        if (dist.y > length) {
            dist.y = length;
        }
        if (dist.y < -length) {
            dist.y = -length;
        }
		
		dist.scl(speed);
		dist.scl(delta);
		
		rot.setRotation((float)Math.atan2(targetPosition.x - master.getCenter().x, targetPosition.y - master.getCenter().y) * (180 / (float)Math.PI));
		
		if (Physics.isPassable(masterCollisionBounds.x, masterCollisionBounds.y, dist, masterCollisionBounds.width, masterCollisionBounds.height)) {
			master.getPosition().add(dist);
		} else {
			stopMoving();
		}
		
		if (targetPosition.dst(master.getPosition().x + master.getBounds().width / 2, master.getPosition().y + master.getBounds().height / 2) < Const.STOP_DISTANCE) {
			stopMoving();
		}
	}
	
	public void update(float delta) {
		if (speedBoostTimer.isStarted()) {
			if (speedBoostTimer.isOver()) {
				stopSpeedBoost();
			}
		}
	}
	
	public void startMoving(float targetX, float targetY) {
		setMoving(true);
		setTargetPosition(targetX, targetY);
	}
	
	public void stopMoving() {
		setMoving(false);
		targetPosition.x = 0; targetPosition.y = 0;
		dist.x = 0; dist.y = 0;
	}
	
	public void calculateDistanceToTarget() {
		dist.x = targetPosition.x - (master.getPosition().x + master.getBounds().width / 2);
		dist.y = targetPosition.y - (master.getPosition().y + master.getBounds().height / 2);
	}
	
	public void setTargetPosition(float x, float y) {
		targetPosition.x = x;
		targetPosition.y = y;
	}
	
	public void speedBoost() {
		speedBoostTimer.restart();
		setSpeed(Const.PLAYER_BOOSTED_SPEED);
	}
	
	public void stopSpeedBoost() {
		speedBoostTimer.stop();
		setSpeed(Const.PLAYER_DEFAULT_SPEED);
	}
	
	public Vector2 getDist() {
		return dist;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public Vector2 getTargetPosition() {
		return targetPosition;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void setFacingAngle(float degrees) {
		rot.setRotation(degrees);
	}
	
	public float getMovingAngle() {
		return rot.getAngle();
	}
}
