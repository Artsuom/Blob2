package com.artsuo.blob.objects.components;

import com.artsuo.blob.AssetBank;
import com.artsuo.blob.AssetBank.Asset;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Anim {
	
	private Animation animation;
	private TextureRegion currentFrame;
	private boolean looping;
	private float stateTime;
	
	public Anim(Asset animationAsset, int cols, int rows, float speed, boolean looping, boolean pingpong) {
		this.looping = looping;
		this.animation = AssetBank.createAnimation(animationAsset, cols, rows, speed);
		if (looping && pingpong)
			animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		this.currentFrame = new TextureRegion();
		resetToFirstFrame();
		this.stateTime = 0;
	}
	
	public void update(float delta) {
		stateTime += delta;
		currentFrame = animation.getKeyFrame(stateTime, looping);
	}
	
	public void resetToFirstFrame() {
		setCurrentFrame(animation.getKeyFrames()[0]);
	}
	
	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}
	
	public boolean isLooping() {
		return looping;
	}
	
	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}
	
	public void setLooping(boolean looping) {
		this.looping = looping;
	}
	
	public Animation getAnimation() {
		return animation;
	}
	
	public float getStateTime() {
		return stateTime;
	}
}
