package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.cassebrique.dataFactories.TextureFactory;

class Background {
    Background() {}

    void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getTexBack(), 0, 0) ;
    }
}
