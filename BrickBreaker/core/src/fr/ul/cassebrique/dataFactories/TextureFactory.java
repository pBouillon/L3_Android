package fr.ul.cassebrique.dataFactories;

import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {
    static final Texture texBlueBrick
            = new Texture("images/Brique1C.png") ;

    static final Texture texGreenBrickA
            = new Texture("images/Brique2Ca.png") ;

    static final Texture texGreenBrickB
            = new Texture("images/Brique2Cb.png") ;

    private static final Texture texBall
            = new Texture("images/Bille.png") ;

    private static final Texture texBorder
            = new Texture("images/Contour.png") ;

    private static final Texture texRacket
            = new Texture("images/Barre.png") ;

    private static final Texture texBack = new Texture("images/Fond.png");

    public static Texture getBriqueBleue () {
        return texBlueBrick ;
    }

    public static Texture getBriqueVerte () {
        return texGreenBrickA ;
    }

    public static Texture getBriqueVerteCassee () {
        return texGreenBrickB ;
    }

    public static Texture getTexBall() {
        return texBall;
    }

    public static Texture getTexBorder() {
        return texBorder;
    }

    public static Texture getTexRacket() {
        return texRacket;
    }

    public static Texture getTexBack() {
        return texBack;
    }
}
