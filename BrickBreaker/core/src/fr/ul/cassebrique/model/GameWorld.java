package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.dataFactories.TextureFactory;
import fr.ul.cassebrique.views.GameScreen;

import java.util.ArrayList;

import static fr.ul.cassebrique.dataFactories.SoundFactory.HIT_RACK_SOUND;
import static fr.ul.cassebrique.dataFactories.SoundFactory.HIT_SOUND;
import static fr.ul.cassebrique.dataFactories.SoundFactory.play;
import static fr.ul.cassebrique.model.Ball.RAYON;

public class GameWorld {

    private final static int METERS_TO_PIXEL = 250 ;
    private final static int PIXEL_TO_METERS = 1/250 ;

    private static final int BALL_SPACING = 5 ;
    private static final int MIN_STEPS = 3 ;

    private Background background ;
    private GameScreen gs ;
    private Racket racket ;
    private Wall wall ;
    private World world ;

    private ArrayList<Ball> balls;
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

        balls = new ArrayList<Ball>() ;
        balls.add (
            new Ball (
                new Vector2 (
                        racket.getPos().x + racket.getWidth() / 2 - RAYON,
                        racket.getPos().y + racket.getHeight()
                ),
                this
        )) ;
        alterBalls(true);
        alterBalls(true);

        balls.get(currentBall).setSpeed() ;
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Vector2 normal = contact.getWorldManifold().getNormal();

                Fixture hitten ;
                Ball current = balls.get(currentBall) ;

                if (contact.getFixtureA().getBody() == balls.get(currentBall).getBody()) {
                    hitten = contact.getFixtureB();
                } else {
                    hitten = contact.getFixtureA();
                }

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

                if (hitten.getBody() == background.getBody()) {
                    play(.5f, HIT_RACK_SOUND) ;
                }
                else if (racket.getBody().contains(hitten.getBody())) {
                    play(.5f, HIT_RACK_SOUND) ;
                } else {
                    play(.5f, HIT_SOUND) ;
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

    public void draw (SpriteBatch sb) {
        background.draw(sb) ;
        wall.draw(sb) ;
        racket.draw(sb);

        for (Ball b : balls) b.draw(sb) ;

        for (int i = 0; i < 5; ++i)
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

    public boolean isWallEmpty() {
        return wall.isEmpty() ;
    }

    public boolean isBallLoss() {
        return balls.get(currentBall).isOut() ;
    }

    public int remainingBalls() {
        return balls.size() ;
    }

    private void alterBalls(boolean add) {
        if (add) {
            balls.add (
                new Ball (
                    new Vector2 (
                            TextureFactory.getTexBack().getWidth()
                                    - TextureFactory.getTexBorder().getWidth() / 2
                                    - RAYON,
                            BALL_SPACING + RAYON * 4 * (balls.size()-1)
                    ),
                    this
            )) ;
        }
        else {
            balls.remove(currentBall) ;
        }
    }

    private void rebootBallLose() {
        racket.reset() ;
        balls.get(currentBall).dispose() ;
        balls.remove(currentBall);
        currentBall = balls.size()-1;
        balls.get(currentBall).reset(racket) ;
        balls.get(currentBall).setSpeed() ;
    }

    private void rebootGameOver() {
        racket.reset() ;
        resetBalls()   ;
        wall.regenerate() ;
    }

    private void rebootWin() {
        alterBalls (true) ;
        racket.reset() ;
        balls.get(currentBall).reset(racket) ;
        balls.get(currentBall).setSpeed() ;
        wall.nextLevel() ;
    }

    private void resetBalls() {
        balls.clear() ;
        balls.add (
                new Ball (
                        new Vector2 (
                                racket.getPos().x + racket.getWidth() / 2 - RAYON,
                                racket.getPos().y + racket.getHeight()
                        ),
                        this
                )) ;
        balls.add (
                new Ball (
                        new Vector2 (
                                TextureFactory.getTexBack().getWidth()
                                        - TextureFactory.getTexBorder().getWidth() / 2
                                        - RAYON,
                                BALL_SPACING + RAYON
                        ),
                        this
                )) ;
        balls.add (
                new Ball (
                        new Vector2 (
                                TextureFactory.getTexBack().getWidth()
                                        - TextureFactory.getTexBorder().getWidth() / 2
                                        - RAYON,
                                BALL_SPACING + RAYON * 4
                        ),
                        this
                )) ;
        balls.get(currentBall).setSpeed() ;
    }

    public void reboot(GameState state) {
        /* rebooting */
        if (state.getState().equals(GameState.State.BallLoss)) {
            rebootBallLose() ;
        }
        else if (state.getState().equals(GameState.State.GameOver)) {
            rebootGameOver() ;
        }
        else if (state.getState().equals(GameState.State.Won)) {
            rebootWin() ;
        }
    }

    public Wall getWall() {
        return wall;
    }
}