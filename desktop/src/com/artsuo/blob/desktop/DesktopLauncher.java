package com.artsuo.blob.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.artsuo.blob.Blob;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Blob";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new Blob(), config);
	}
}
