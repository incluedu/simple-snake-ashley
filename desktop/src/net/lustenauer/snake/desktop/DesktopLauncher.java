package net.lustenauer.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.lustenauer.snake.SimpleSnakeGame;
import net.lustenauer.snake.config.GameConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) GameConfig.WIDTH;
		config.height = (int) GameConfig.HEIGHT;

		new LwjglApplication(new SimpleSnakeGame(), config);
	}
}
