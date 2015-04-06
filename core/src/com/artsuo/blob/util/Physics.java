package com.artsuo.blob.util;

import com.artsuo.blob.Engine;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Physics {
	
	public static boolean collision(Rectangle r1, Rectangle r2) {
		return r1.overlaps(r2);
	}
	
	public static boolean inRange(Vector2 p1, Vector2 p2, float range) {
		return p1.dst(p2) <= range;
	}
	
	public static Vector2 getMeleeAttackPoint(Vector2 clickPos, Vector2 charCenter, float angle, float meleeRange, float effectWidth, float effectHeight) {
		float distance = charCenter.dst(clickPos);
		//float tD = meleeRange / distance;
		float x = charCenter.x + (meleeRange * (clickPos.x - charCenter.x)) / distance;
		float y = charCenter.y + (meleeRange * (clickPos.y - charCenter.y)) / distance;
		// Offsets
		if (x > charCenter.x)
		x -= effectWidth;
		
		if (y > charCenter.y)
		y -= effectHeight;
		
		
		return new Vector2(x, y);
	}
	
	// Check if the position that is being moved to is passable
	public static boolean isPassable(Vector2 pos, Vector2 dist, float sx, float sy) {
		if (!Engine.world.getBounds().contains(pos.x + dist.x, pos.y + dist.y)) {
			return false;
		}
		if (!Engine.world.getBounds().contains((pos.x + sx) + dist.x, (pos.y + sy) + dist.y)) {
			return false;
		}
		if (dist.x > 0) {
			// Check for collisions on the right (bottom right, top right, right middle)
			if (!Engine.world.getTile((pos.x + sx) + dist.x, pos.y + dist.y).isPassable() ||
					!Engine.world.getTile((pos.x + sx) + dist.x, (pos.y + sy) + dist.y).isPassable() ||
						!Engine.world.getTile((pos.x + sx) + dist.x, (pos.y + sy / 2) + dist.y).isPassable()) {
							return false;
			}
		} else if (dist.x < 0) {
			// Check for collisions on the left (bottom left, top left, left middle)
			if (!Engine.world.getTile(pos.x + dist.x, pos.y + dist.y).isPassable()
					|| !Engine.world.getTile(pos.x + dist.x, (pos.y + sy) + dist.y).isPassable() ||
						!Engine.world.getTile(pos.x + dist.x, (pos.y + sy / 2) + dist.y).isPassable()) {
							return false;
			}
		}
		if (dist.y > 0) {
			// Check for collisions on top (top right, top left, top middle)
			if (!Engine.world.getTile((pos.x + sx) + dist.x, (pos.y + sy) + dist.y).isPassable()
					|| !Engine.world.getTile(pos.x + dist.x, (pos.y + sy) + dist.y).isPassable() ||
						!Engine.world.getTile((pos.x + sx / 2) + dist.x, (pos.y + sy) + dist.y).isPassable()) {
							return false;
			}
		} else if (dist.y < 0) {
			// Check for collisions on bottom (bottom right, bottom left, bottom middle)
			if (!Engine.world.getTile((pos.x + sx) + dist.x, pos.y + dist.y).isPassable()
					|| !Engine.world.getTile(pos.x + dist.x, pos.y + dist.y).isPassable() ||
						!Engine.world.getTile((pos.x + sx / 2) + dist.x, pos.y + dist.y).isPassable()) {
							return false;
			}
		}
		
		return true;
	}
	
	public static boolean isPassable(float x, float y, Vector2 dist, float sx, float sy) {
		if (!Engine.world.getBounds().contains(x + dist.x, y + dist.y)) {
			return false;
		}
		if (!Engine.world.getBounds().contains((x + sx) + dist.x, (y + sy) + dist.y)) {
			return false;
		}
		if (dist.x > 0) {
			// Check for collisions on the right (bottom right, top right, right middle)
			if (!Engine.world.getTile((x + sx) + dist.x, y + dist.y).isPassable() ||
					!Engine.world.getTile((x + sx) + dist.x, (y + sy) + dist.y).isPassable() ||
						!Engine.world.getTile((x + sx) + dist.x, (y + sy / 2) + dist.y).isPassable()) {
							return false;
			}
		} else if (dist.x < 0) {
			// Check for collisions on the left (bottom left, top left, left middle)
			if (!Engine.world.getTile(x + dist.x, y + dist.y).isPassable()
					|| !Engine.world.getTile(x + dist.x, (y + sy) + dist.y).isPassable() ||
						!Engine.world.getTile(x + dist.x, (y + sy / 2) + dist.y).isPassable()) {
							return false;
			}
		}
		if (dist.y > 0) {
			// Check for collisions on top (top right, top left, top middle)
			if (!Engine.world.getTile((x + sx) + dist.x, (y + sy) + dist.y).isPassable()
					|| !Engine.world.getTile(x + dist.x, (y + sy) + dist.y).isPassable() ||
						!Engine.world.getTile((x + sx / 2) + dist.x, (y + sy) + dist.y).isPassable()) {
							return false;
			}
		} else if (dist.y < 0) {
			// Check for collisions on bottom (bottom right, bottom left, bottom middle)
			if (!Engine.world.getTile((x + sx) + dist.x, y + dist.y).isPassable()
					|| !Engine.world.getTile(x + dist.x, y + dist.y).isPassable() ||
						!Engine.world.getTile((x + sx / 2) + dist.x, y + dist.y).isPassable()) {
							return false;
			}
		}
		
		return true;
	}
	
	
	// Check if the position that is being moved to is passable
		public static boolean isPassable(Vector2 pos, float sx, float sy) {
			if (!Engine.world.getBounds().contains(pos.x, pos.y)) {
				return false;
			}
			if (!Engine.world.getBounds().contains((pos.x + sx), (pos.y + sy))) {
				return false;
			}
				// Check for collisions on the right (bottom right, top right, right middle)
				if (!Engine.world.getTile((pos.x + sx), pos.y).isPassable() ||
						!Engine.world.getTile((pos.x + sx), (pos.y + sy)).isPassable() ||
							!Engine.world.getTile((pos.x + sx), (pos.y + sy / 2)).isPassable()) {
								return false;
				}
				// Check for collisions on the left (bottom left, top left, left middle)
				if (!Engine.world.getTile(pos.x, pos.y).isPassable()
						|| !Engine.world.getTile(pos.x, (pos.y + sy)).isPassable() ||
							!Engine.world.getTile(pos.x, (pos.y + sy / 2)).isPassable()) {
								return false;
				}
				// Check for collisions on top (top middle)
				if (!Engine.world.getTile((pos.x + sx / 2), (pos.y + sy)).isPassable()) {
								return false;
				}
				// Check for collisions on bottom (bottom middle)
				if (!Engine.world.getTile((pos.x + sx / 2), pos.y).isPassable()) {
								return false;
				}
			return true;
		}
}
