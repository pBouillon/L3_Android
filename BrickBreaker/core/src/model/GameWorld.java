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
    private int currentBall ;

    private Fixture toDestroy ;

    public GameWorld (GameScreen _gs) {
        gs = _gs ;
        currentBall = 0 ;

        world = new World (
                    new Vector2 (0,0),
                    true
        ) ;

        racket = new Racket(this) ;
        wall = new Wall(this) ;
        background = new Background(this) ;

        billes = new ArrayList<Ball>() ;
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
                Vector2 normal = contact.getWorldManifold().getNormal();

                Fixture hitten ;
                Ball current = billes.get(currentBall) ;

                if (contact.getFixtureA().getBody() == billes.get(currentBall).getBody()) {
                    hitten = contact.getFixtureB();
                } else {
                    hitten = contact.getFixtureA();
                }

                // HACKS ---
                if (normal.x != 0) {
                    current.getBody().setLinearVelocity (
                            - current.getBody().getLinearVelocity().x,
                            current.getBody().getLinearVelocity().y
                    ) ;
                }

                if (normal.y != 0) {
                    current.getBody().setLinearVelocity (
                            current.getBody().getLinearVelocity().x,
                            - current.getBody().getLinearVelocity().y
                    ) ;
                }
                // --- HACKS

                if (hitten.getBody() == background.getBody()) {

                }
                else if (racket.getBody().contains(hitten.getBody())) {

                } else {
//                    current.getBody().applyForce (
//                            normal,
//                            current.getPosition(),
//                            true
//                    ) ;
                    toDestroy = hitten ;
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