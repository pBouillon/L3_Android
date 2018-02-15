package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import fr.ul.cassebrique.dataFactories.TextureFactory;


public abstract class Brick {
    static final int BLUE_BRICK_LIFE  = 1 ;
    static final int GREEN_BRICK_LIFE = 2 ;
    static final int BROKEN_BRICK_LIFE = 1 ;

    static final Texture BLUE_TEX
            = TextureFactory.getBriqueBleue() ;
    static final Texture GREEN_TEX
            = TextureFactory.getBriqueVerte() ;
    static final Texture BROK_GREEN_TEX
            = TextureFactory.getBriqueVerteCassee() ;

    private Texture tex = null ;

    private int  vie  = 0 ;
    private int  posX = 0 ;
    private int  posY = 0 ;
    private Body body ;

    Brick (int coups, Texture _tex) {
        if (_tex == GREEN_TEX) {
            tex = (coups == GREEN_BRICK_LIFE)
                    ? GREEN_TEX
                    : BROK_GREEN_TEX ;
        } else {
            tex = _tex ;
        }

        vie = coups ;
        posX = 0 ;
        posY = 0 ;
    }

    public abstract void draw (SpriteBatch sb) ;

    public int getVie() {
        return vie;
    }

    Texture getTex() {
        return tex ;
    }

    int getPosX() {
        return posX;
    }

    int getPosY() {
        return posY;
    }

    void decreaseLife() {
        --vie ;
    }

    void setPosX (int x) {
        posX = x ;
    }

    void setPosY (int y) {
        posY = y ;
    }
}
