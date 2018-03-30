package fr.ul.examen.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.ul.examen.dataFactory.TextureFactory;

import java.util.Random;

public class Crepe {
    private static final int BORDER_WIDTH  = TextureFactory.getTexCuisine().getWidth() ;
    private static final int CREPE_WIDTH = TextureFactory.getTexCrepe().getWidth() ;
    private static final int CREPE_HEIGHT = TextureFactory.getTexCrepe().getHeight() ;

    private float gravity = 1f;

    GameWorld gw ;
    private Vector2 pos ;
    Body body ;

    public Crepe(GameWorld _gw) {
        gw = _gw ;

        int rdm = (int) (Math.random() * (Gdx.graphics.getWidth() - CREPE_WIDTH));
        pos = new Vector2 (
                rdm,
                Gdx.graphics.getHeight() - (int)(CREPE_WIDTH * 1.25)
        ) ;

        Vector2[] tab = new Vector2[4];
        //bas à gauche
        tab[0] = new Vector2(pos.x, pos.y);
        //haut à gauche
        tab[1] = new Vector2(pos.x, pos.y + CREPE_HEIGHT);
        //haut à droite
        tab[2] = new Vector2(pos.x + CREPE_WIDTH,pos.y + CREPE_HEIGHT);
        //bas à droite
        tab[3] = new Vector2(pos.x + CREPE_WIDTH, pos.y);

        PolygonShape shape = new PolygonShape();
        shape.set(tab);

        BodyDef bodyDef1 = new BodyDef();
        bodyDef1.type = BodyDef.BodyType.DynamicBody;
        body = gw.getWorld().createBody(bodyDef1);

        FixtureDef fixtureDef1  = new FixtureDef() ;
        fixtureDef1.shape       = shape ;
        fixtureDef1.density     = 1 ;
        fixtureDef1.restitution = 1 ;
        fixtureDef1.friction    = 0 ;
        body.createFixture(fixtureDef1) ;
    }

    void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getTexCrepe(), pos.x, pos.y);
    }

    void setPos(float x, float y) {
        pos.x = x ;
        pos.y = y ;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void update() {
        setPos(pos.x, pos.y - gravity);
        body.setTransform(body.getPosition().x, body.getPosition().y - gravity, 0) ;

        gravity += gravity * .01 ;
    }

    public Body getBody() {
        return body;
    }

    public boolean isOut() {
        return pos.y < 0 - CREPE_HEIGHT ;
    }
}
