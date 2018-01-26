package fr.ul.cassebrique.dataFactories;

import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {
    static final Texture texBlueBrick
            = new Texture("images/Brique1C.png") ;

    static final Texture texGreenBrickA
            = new Texture("images/Brique2Ca.png") ;

    static final Texture texGreenBrickB
            = new Texture("images/Brique2Cb.png") ;

    public static Texture getBriqueBleue () {
        return texBlueBrick ;
    }

    public static Texture getBriqueVerte () {
        return texGreenBrickA ;
    }

    public static Texture getBriqueVerteCassee () {
        return texGreenBrickB ;
    }
}
