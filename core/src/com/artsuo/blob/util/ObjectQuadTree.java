package com.artsuo.blob.util;

import java.util.ArrayList;
import java.util.List;

import com.artsuo.blob.objects.GameObject;
import com.badlogic.gdx.math.Rectangle;

public class ObjectQuadTree {
	
	private int MAX_OBJECTS = 10;
	private int MAX_LEVELS = 5;

	private int level;
	private List<GameObject> objects;
	private Rectangle bounds;
	private ObjectQuadTree[] nodes;

	/*
	 * Constructor
	 */
	public ObjectQuadTree(int pLevel, Rectangle pBounds) {
		level = pLevel;
		objects = new ArrayList<GameObject>();
		bounds = pBounds;
		nodes = new ObjectQuadTree[4];
		//GameRenderer.addDebugNodeRect(bounds);
	}

	/*
	 * Clears the ObjectQuadTree
	 */
	public void clear() {
		objects.clear();

		// Debug -----------------------------------
		//GameRenderer.clearDebugNodeRects();
		
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}
	
	/*
	 * Updates bounds position
	 */
	public void updateBounds(float x, float y) {
		bounds.setPosition(x, y);
		/*
		if (nodes != null) {
			for (int i = 0; i < nodes.length; i++) {
				nodes[i].updateBounds(x, y);
			}
		}
		*/
	}

	/*
	 * Splits the node into 4 subnodes
	 */
	private void split() {
		int subWidth = (int) (bounds.getWidth() / 2);
		int subHeight = (int) (bounds.getHeight() / 2);
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();

		nodes[0] = new ObjectQuadTree(level + 1, new Rectangle(x + subWidth, y,
				subWidth, subHeight));
		nodes[1] = new ObjectQuadTree(level + 1, new Rectangle(x, y, subWidth,
				subHeight));
		nodes[2] = new ObjectQuadTree(level + 1, new Rectangle(x, y + subHeight,
				subWidth, subHeight));
		nodes[3] = new ObjectQuadTree(level + 1, new Rectangle(x + subWidth, y
				+ subHeight, subWidth, subHeight));
	}

	/*
	 * Determine which node the object belongs to. -1 means object cannot
	 * completely fit within a child node and is part of the parent node
	 */
	private int getIndex(GameObject go) {
		Rectangle pRect = go.getBounds();
		int index = -1;
		double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
		double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);

		// Version 2:
		
		// Object can completely fit within the top quadrants
		boolean topQuadrant = (pRect.getY() > horizontalMidpoint);
		// Object can completely fit within the bottom quadrants
		boolean bottomQuadrant = (pRect.getY() < horizontalMidpoint && pRect
				.getY() + pRect.getHeight() < horizontalMidpoint);
		
		/*
		// Version 1 (Original):
		// Object can completely fit within the top quadrants
				boolean topQuadrant = (pRect.getY() < horizontalMidpoint && pRect
						.getY() + pRect.getHeight() < horizontalMidpoint);
						
				// Object can completely fit within the bottom quadrants
				boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);
		*/		
		// Object can completely fit within the left quadrants
		if (pRect.getX() < verticalMidpoint
				&& pRect.getX() + pRect.getWidth() < verticalMidpoint) {
			if (topQuadrant) {
				index = 1;
			} else if (bottomQuadrant) {
				index = 2;
			}
		}
		// Object can completely fit within the right quadrants
		else if (pRect.getX() > verticalMidpoint) {
			if (topQuadrant) {
				index = 0;
			} else if (bottomQuadrant) {
				index = 3;
			}
		}

		return index;
	}

	/*
	 * Insert the object into the ObjectQuadTree. If the node exceeds the capacity, it
	 * will split and add all objects to their corresponding nodes.
	 */
	// Version 1 (Original):
	public void insert(GameObject go) {
		if (nodes[0] != null) {
			int index = getIndex(go);

			if (index != -1) {
				nodes[index].insert(go);

				return;
			}
		}

		objects.add(go);

		if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if (nodes[0] == null) {
				split();
			}

			int i = 0;
			while (i < objects.size()) {
				int index = getIndex(objects.get(i));
				if (index != -1) {
					nodes[index].insert(objects.get(i));
					objects.remove(i);
				} else {
					i++;
				}
			}
		}
	}
	
	// Version 2:
	/*
	public void insert(GameObject go)
	{
		int index = getIndex(go);

		if (index >= 0) // pRect should always go in a child if possible
		{
			if (nodes[0] != null) // we have children
				nodes[index].insert(go); // so put pRect into the child
			else // we don't have children
			{
				if (level < MAX_LEVELS) 
				{
					split(); // so we make children if possible
					nodes[index].insert(go); // and put pRect into the child
				} 
				else // node can't split any more so put it here
					objects.add(go); 
			}
		}
		else
			objects.add(go); // index == -1 so add here
	}
	*/
	/*
	 * Return all objects that could collide with the given object
	 */
	public List<GameObject> retrieve(List<GameObject> returnObjects, GameObject go) {
		int index = getIndex(go);
		if (index != -1 && nodes[0] != null) {
			nodes[index].retrieve(returnObjects, go);
		}

		returnObjects.addAll(objects);

		return returnObjects;
	}


}
