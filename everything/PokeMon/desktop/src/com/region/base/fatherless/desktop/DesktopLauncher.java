package com.region.base.fatherless.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.region.base.fatherless.UnknownTrainer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1280;
		config.height = 720;
		config.title = "Pokemon : Begie (Unknown Challenger)";
		//config.useGL30 = true; DON'T USE.

		new LwjglApplication(new UnknownTrainer(), config);
	}
}
