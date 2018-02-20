package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.dataFactories.TextureFactory;
import fr.ul.cassebrique.views.GameScreen;

import java.util.ArrayList;

import static model.Ball.RAYON;

public class GameWorld {

    private final static int METERS_TO_PIXEL = 250 ;
    private final static int PIXEL_TO_METERS = 1/250 ;

    private static final int BALL_SPACING = 5 ;

    private Background background ;
    private GameScreen gs ;
    private Racket racket ;
    private Wall wall ;
    private World world ;

    private ArrayList<Ball> billes ;

    private Fixture toDestroy ;

    public GameWorld (GameScreen _gs) {
        gs = _gs ;

        world = new World (
                    new Vector2 (0,0),
                    true
        ) ;

        racket = new Racket(this) ;
        wall = new Wall(this) ;
        background = new Background(this) ;

        billes = new ArrayList<>() ;
        billes.add (new Ball (
                new Vector2 (
                        racket.getPos().x + racket.getWidth() / 2 - RAYON,
                        racket.getPos().y + racket.getHeight()
                ),
                this
        )) ;
        billes.get(0).setSpeed() ;
        billes.add (new Ball (
                new Vector2 (
                        TextureFactory.getTexBack().getWidth()
                                - TextureFactory.getTexBorder().getWidth() / 2
                                - RAYON,
                        BALL_SPACING + RAYON
                ),
                this
        )) ;
        billes.add (new Ball (
                new Vector2 (
                        TextureFactory.getTexBack().getWidth()
                                - TextureFactory.getTexBorder().getWidth() / 2
                                - RAYON,
                        BALL_SPACING + RAYON * 4
                ),
                this
        )) ;

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                WorldManifold norm = contact.getWorldManifold() ;
                Fixture otherObj ;

                if (contact.getFixtureA().getBody() == billes.get(0).getBody()) {
                    otherObj = contact.getFixtureB() ;
                }

                else {
                    otherObj = contact.getFixtureA() ;
                }

                Ball currentBall = billes.get(0) ;

                boolean isRack = false ;
                // Racket
                for (Body bRack : racket.getBody()) {
                    if (otherObj.getBody() == bRack) {
                        currentBall.getBody().setLinearVelocity (
                                currentBall.getBody().getLinearVelocity().x * 2,
                                - currentBall.getBody().getLinearVelocity().y * 2
                        ) ;
                        isRack = true ;
                        break ;
                    }
                }

                // Other
                if (!isRack
                        ) {
                    if (norm.getNormal().x != 0) {
                        currentBall.getBody().setLinearVelocity (
                                - currentBall.getBody().getLinearVelocity().x,
                                currentBall.getBody().getLinearVelocity().y
                        ) ;
                    }

                    if (norm.getNormal().y != 0) {
                        currentBall.getBody().setLinearVelocity (
                                currentBall.getBody().getLinearVelocity().x,
                                -  currentBall.getBody().getLinearVelocity().y
                        ) ;
                    }

                    toDestroy = otherObj ;
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    public void draw(SpriteBatch sb) {
        background.draw(sb) ;
        wall.draw(sb) ;
        racket.draw(sb);

        for (Ball b : billes) {
            b.draw(sb) ;
        }

        world.step(6,2, 0);

        if (toDestroy != null) {
            wall.destroy(toDestroy) ;
            toDestroy = null ;
        }
    }

    public Racket getRacket() {
        return racket;
    }

    public World getWorld() {
        return world ;
    }
}