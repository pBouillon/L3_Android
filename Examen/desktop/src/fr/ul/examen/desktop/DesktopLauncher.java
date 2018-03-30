package fr.ul.examen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.ul.examen.Examen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.a = 8 ;
		config.height = 750  ;
		config.width  = 1150 ;

		new LwjglApplication(new Examen(), config);
	}
}
