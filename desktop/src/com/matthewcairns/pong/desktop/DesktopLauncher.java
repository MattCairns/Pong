package com.matthewcairns.pong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.matthewcairns.pong.Pong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Pong";
        config.width = 800;
        config.height = 480;
        config.vSyncEnabled = true;
		new LwjglApplication(new Pong(), config);
	}
}
