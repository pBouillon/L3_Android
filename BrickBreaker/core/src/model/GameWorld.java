package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.cassebrique.dataFactories.TextureFactory;
import fr.ul.cassebrique.views.GameScreen;

import java.util.ArrayList;

import static model.Ball.RAYON;

public class GameWorld {

    private static final int BALL_SPACING = 5 ;

    private Background background ;
    private GameScreen gs ;
    private Racket racket ;
    private Wall wall ;

    private ArrayList<Ball> billes ;

    public GameWorld(GameScreen _gs) {
        background = new Background() ;
        gs = _gs ;
        racket = new Racket() ;
        wall = new Wall() ;

        billes = new ArrayList<Ball>() ;
        billes.add(new Ball(
                new Vector2(
                        racket.getPos().x + racket.getWidth() / 2 - RAYON,
                        racket.getPos().y + racket.getHeight()
                )
        )) ;
        billes.add(new Ball(
                new Vector2(
                        TextureFactory.getTexBack().getWidth()
                                - TextureFactory.getTexBorder().getWidth() / 2
                                - RAYON,
                        BALL_SPACING + RAYON
                )
        )) ;
        billes.add(new Ball(
                new Vector2(
                        TextureFactory.getTexBack().getWidth()
                                - TextureFactory.getTexBorder().getWidth() / 2
                                - RAYON,
                        BALL_SPACING + RAYON * 4
                )
        )) ;
    }

    public void draw(SpriteBatch sb) {
        background.draw(sb) ;
        wall.draw(sb) ;
        racket.draw(sb);
        for (Ball b : billes) {
            b.draw(sb) ;
        }
    }

    public Racket getRacket() {
        return racket;
    }
}
