package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.cassebrique.dataFactories.TextureFactory;

class Ball {
    static float RAYON = 12 ;

    Vector2 pos ;
    GameWorld gw ;

    Ball(Vector2 _pos) {
         pos = _pos ;
    }

    void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getTexBall(), pos.x, pos.y) ;
    }
}
