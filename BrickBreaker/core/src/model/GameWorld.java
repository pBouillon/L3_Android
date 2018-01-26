package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.cassebrique.views.GameScreen;

public class GameWorld {
    GameScreen gs ;
    Wall wall ;

    public GameWorld(GameScreen _gs) {
        gs = _gs ;
        wall = new Wall() ;
    }

    public void draw(SpriteBatch sb) {
        wall.draw(sb) ;
    }
}
