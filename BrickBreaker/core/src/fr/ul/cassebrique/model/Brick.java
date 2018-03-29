package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
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
    private GameWorld gw ;

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

    public int getLife() {
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

    void addPhysique(GameWorld g){
        int heightBrick = TextureFactory.getBriqueBleue().getHeight();
        int widthBrick = TextureFactory.getBriqueBleue().getWidth();

        Vector2[] tab = new Vector2[4];
        //bas à gauche
        tab[0] = new Vector2(posX, posY);
        //haut à gauche
        tab[1] = new Vector2(posX, posY + heightBrick);
        //haut à droite
        tab[2] = new Vector2(posX + widthBrick,posY + heightBrick);
        //bas à droite
        tab[3] = new Vector2(posX + widthBrick, posY);

        PolygonShape shape = new PolygonShape();
        shape.set(tab);

        gw = g;

        BodyDef bodyDef1 = new BodyDef();
        bodyDef1.type = BodyDef.BodyType.StaticBody;
        body = gw.getWorld().createBody(bodyDef1);

        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape    = shape ;
        fixtureDef1.density  = 1 ;
        fixtureDef1.friction = 0 ;
        fixtureDef1.restitution = 1 ;
        body.createFixture(fixtureDef1) ;
    }

    Body getBody() {
        return body;
    }

    void setTex(Texture tex) {
        this.tex = tex;
    }

    abstract void dispose(GameWorld gw) ;
}
