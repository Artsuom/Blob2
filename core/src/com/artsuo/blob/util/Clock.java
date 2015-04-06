package com.artsuo.blob.util;

import com.badlogic.gdx.math.MathUtils;

public class Clock {

	private float elapsedTime;

	public Clock() {
		this.elapsedTime = 0;
	}

	public void update(float delta) {
			elapsedTime += delta;
	}

	public int getElapsedTimeInSeconds() {
		return MathUtils.floor(elapsedTime);
	}

	public float getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(float elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
}
