package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
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

    public GameWorld (GameScreen _gs) {
        gs = _gs ;
        racket = new Racket() ;
        wall = new Wall() ;

        world = new World (
                    new Vector2 (0,0),
                    true
        ) ;
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
    }

    public void draw(SpriteBatch sb) {
        background.draw(sb) ;
        wall.draw(sb) ;
        racket.draw(sb);
        for (Ball b : billes) {
            b.draw(sb) ;
        }
        world.step(6,2, 0);
    }

    public Racket getRacket() {
        return racket;
    }

    World getWorld() {
        return world ;
    }
}