package model;

import com.badlogic.gdx.graphics.Texture;
import fr.ul.cassebrique.dataFactories.TextureFactory;

public enum Brick {
    Vide (null),
    Bleue(TextureFactory.getBriqueBleue()),
    Verte(TextureFactory.getBriqueVerte()),
    VerteAbimee(TextureFactory.getBriqueVerteCassee()) ;

    private Texture tex ;

    Brick(Texture _tex) {
        tex = _tex ;
    }

    public Texture getTex() {
        return tex;
    }
}
