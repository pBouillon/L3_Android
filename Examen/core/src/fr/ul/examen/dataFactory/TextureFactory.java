package fr.ul.examen.dataFactory;

import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {
    static final Texture texCrepe
            = new Texture("images/crepe.png") ;

    static final Texture texCuisine
            = new Texture("images/cuisine.jpeg") ;

    static final Texture texFinPartie
            = new Texture("images/finPartie.bmp") ;

    static final Texture texPerte
            = new Texture("images/perte.bmp") ;

    static final Texture texPoele
            = new Texture("images/poele.png") ;

    public static Texture getTexCrepe() {
        return texCrepe;
    }

    public static Texture getTexCuisine() {
        return texCuisine;
    }

    public static Texture getTexFinPartie() {
        return texFinPartie;
    }

    public static Texture getTexPerte() {
        return texPerte;
    }

    public static Texture getTexPoele() {
        return texPoele;
    }
}
