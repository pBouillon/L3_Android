package fr.ul.examen;
import com.badlogic.gdx.Game;
import fr.ul.examen.views.GameScreen;

public class Examen extends Game {
	GameScreen gs;

	@Override
	public void create () {
		gs = new GameScreen() ;
		setScreen(gs);
	}
	
	@Override
	public void dispose () {
		gs.dispose();
	}
}
