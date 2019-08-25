package ru.niktop.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.niktop.game.FlyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlyDemo.WIDTH;
		config.height = FlyDemo.HEIGHT;
		config.title = FlyDemo.TITLE;
		new LwjglApplication(new FlyDemo(), config);
	}
}
