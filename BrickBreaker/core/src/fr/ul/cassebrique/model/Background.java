package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.dataFactories.TextureFactory;

class Background {
    private Body body;

    Background(GameWorld _gw) {
        Body body ;

        BodyDef bodyDef1 = new BodyDef() ;
        bodyDef1.type   = BodyDef.BodyType.StaticBody ;
        bodyDef1.bullet = true ;
        bodyDef1.fixedRotation = false ;
        body = _gw.getWorld().createBody(bodyDef1) ;

        ChainShape shape = new ChainShape() ;
        shape.createChain (
            new Vector2[] {
                // bas gauche
                new Vector2 (
                    TextureFactory.getTexBorder().getWidth(),
                    0
                ),
                // haut gauche
                new Vector2 (
                    TextureFactory.getTexBorder().getWidth(),
                    TextureFactory.getTexBack().getHeight()
                           - TextureFactory.getTexBorder().getHeight()
                ),
                // haut droit
                new Vector2 (
                        TextureFactory.getTexBack().getWidth()
                                - 2 * TextureFactory.getTexBorder().getHeight(),
                        TextureFactory.getTexBack().getHeight()
                                - TextureFactory.getTexBorder().getHeight()
                ),
                // bas droit
                new Vector2 (
                        TextureFactory.getTexBack().getWidth()
                                - 2 * TextureFactory.getTexBorder().getHeight(),
                    0
                ),

                // DEBUG
//                new Vector2 (
//                        TextureFactory.getTexBorder().getWidth(),
//                        0
//                )
            }
        ) ;

        FixtureDef fixtureDef1  = new FixtureDef() ;
        fixtureDef1.density     = 1 ;
        fixtureDef1.friction    = 0 ;
        fixtureDef1.restitution = 1 ;
        fixtureDef1.shape       = shape ;
        body.createFixture(fixtureDef1) ;
    }

    void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getTexBack(), 0, 0) ;
    }

    public Body getBody() {
        return body;
    }
}