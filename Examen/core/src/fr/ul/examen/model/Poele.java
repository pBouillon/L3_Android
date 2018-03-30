package fr.ul.examen.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.ul.examen.dataFactory.TextureFactory;

public class Poele {
    public  static final int MOV_LEFT  = 0 ;
    public  static final int MOV_RIGHT = 1 ;
    private static final int MOV_SPACE = 10 ;

    private static final int BORDER_WIDTH  = TextureFactory.getTexCuisine().getWidth() ;
    private static final int PAN_WIDTH = TextureFactory.getTexPoele().getWidth() ;
    private static final int PAN_HEIGHT = TextureFactory.getTexPoele().getHeight() ;

    GameWorld gw ;
    private Vector2 pos ;
    Body body ;

    Poele(GameWorld _gw) {
        gw = _gw ;

        pos = new Vector2 (
                Gdx.graphics.getWidth() / 2 - PAN_WIDTH / 2,
                Gdx.graphics.getHeight() / 20
        ) ;

        Vector2[] tab = new Vector2[4];
        //bas à gauche
        tab[0] = new Vector2(pos.x, pos.y);
        //haut à gauche
        tab[1] = new Vector2(pos.x, pos.y + PAN_HEIGHT / 2);
        //haut à droite
        tab[2] = new Vector2(pos.x + PAN_WIDTH ,pos.y + PAN_HEIGHT / 2);
        //bas à droite
        tab[3] = new Vector2(pos.x + PAN_WIDTH , pos.y);

        PolygonShape shape = new PolygonShape();
        shape.set(tab);

        BodyDef bodyDef1 = new BodyDef();
        bodyDef1.type = BodyDef.BodyType.StaticBody;
        body = gw.getWorld().createBody(bodyDef1);

        FixtureDef fixtureDef1  = new FixtureDef() ;
        fixtureDef1.shape       = shape ;
        fixtureDef1.density     = 1 ;
        fixtureDef1.restitution = 1 ;
        fixtureDef1.friction    = 0 ;
        body.createFixture(fixtureDef1) ;
    }

    void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getTexPoele(), pos.x, pos.y);
    }

    void setPos(float x, float y) {
        pos.x = x ;
        pos.y = y ;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void update(int mov) {
        int step ;

        if (mov == MOV_RIGHT) {
            step = MOV_SPACE ;
        } else {
            step = -MOV_SPACE ;
        }

        if (pos.x + step < 0 ) {
            step = 0 ;
        }

        if (pos.x + step + PAN_WIDTH > Gdx.graphics.getWidth()) {
            step = 0 ;
        }

        setPos(pos.x + step, pos.y);
        body.setTransform(body.getPosition().x + step, body.getPosition().y, 0) ;
    }

    public Body getBody() {
        return body;
    }

    public void reset() {
        setPos (
                Gdx.graphics.getWidth() / 2 - PAN_WIDTH / 2,
                Gdx.graphics.getHeight() / 20
        ) ;
        body.setTransform (
                0,
                0,
                0
        ) ;
    }
}
