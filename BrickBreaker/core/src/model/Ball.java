package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import fr.ul.cassebrique.dataFactories.TextureFactory;

class Ball {
    static float RAYON = 12 ;

    private Body body ;

    GameWorld gw ;

    Ball(Vector2 _pos, GameWorld _gw) {
        gw = _gw ;

        BodyDef bodyDef1 = new BodyDef() ;
        bodyDef1.type    = BodyDef.BodyType.DynamicBody ;
        bodyDef1.bullet  = true ;
        bodyDef1.fixedRotation = false ;
        bodyDef1.position.set(_pos) ;
        body = gw.getWorld().createBody(bodyDef1) ;

        CircleShape shape =  new CircleShape() ;
        shape.setRadius(RAYON) ;

        FixtureDef fixtureDef1  = new FixtureDef() ;
        fixtureDef1.shape       = shape ;
        fixtureDef1.density     = 1 ;
        fixtureDef1.restitution = 1 ;
        fixtureDef1.friction    = 0 ;
        body.createFixture(fixtureDef1) ;

        body.setTransform (_pos,0) ;

        setSpeedStatic();
    }

    void draw(SpriteBatch sb) {
        sb.draw (
                TextureFactory.getTexBall(),
                body.getPosition().x,
                body.getPosition().y
        ) ;
    }

    void setSpeed() {
        int rdm = (int) (Math.random() * (200 - (-200))) + (-200);
        body.setLinearVelocity(rdm, 200);
    }

    void setSpeedStatic() {
        body.setLinearVelocity(0, 0);
    }

    public Body getBody() {
        return body;
    }
}
