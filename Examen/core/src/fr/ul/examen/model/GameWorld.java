package fr.ul.examen.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.examen.dataFactory.SoundFactory;
import fr.ul.examen.dataFactory.TextureFactory;
import fr.ul.examen.views.GameScreen;

import java.util.ArrayList;

import static fr.ul.examen.dataFactory.SoundFactory.COLLECTE_SOUND;
import static fr.ul.examen.dataFactory.SoundFactory.SORTIE_SOUND;

public class GameWorld {

    private int score ;

    GameScreen gs ;
    World world ;
    Poele pan ;

    ArrayList<Crepe> crepes ;
    Body hitten ;

    BitmapFont font ;

    public GameWorld (GameScreen _gs) {
        gs = _gs ;

        score = 0 ;
        font = new BitmapFont() ;

        world = new World (
                new Vector2(0,0),
                true
        ) ;

        pan = new Poele(this) ;

        crepes = new ArrayList<Crepe>() ;
        crepes.add(new Crepe(this)) ;

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if (contact.getFixtureA().getBody() == pan.getBody()) {
                    hitten = contact.getFixtureB().getBody() ;
                } else {
                    hitten = contact.getFixtureA().getBody() ;
                }

                ++score ;
                SoundFactory.play(.5f, COLLECTE_SOUND) ;
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

    public void destroyCrepe(Body hitten) {
        if (hitten == null) return ;
        for (Crepe c : crepes) {
            if (c.getBody() == hitten) {
                crepes.remove(c) ;
                getWorld().destroyBody(c.getBody()) ;
            }
        }
    }

    public void draw (SpriteBatch sb) {
        sb.draw(TextureFactory.getTexCuisine(), 0, 0, 1150, 750);
        // poele
        pan.draw(sb) ;

        // crepes
        for (Crepe c : crepes) c.draw(sb);

        //score
        font.draw (
                sb,
                "Score: " + score,
                Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 20);

        world.step(6,2, 0);

        for (Crepe c : crepes) {
            if (c.isOut()) {
                --score ;
                SoundFactory.play(.5f, SORTIE_SOUND) ;
                hitten = c.body;
            }
        }
        if (hitten != null) {
            destroyCrepe(hitten) ;
            hitten = null ;
        }

    }

    public World getWorld() {
        return world;
    }

    public Poele getPan() {
        return pan;
    }

    public void applyGravity() {
        for (Crepe c : crepes) {
            c.update();
        }
    }

    public void addCrepe() {
        crepes.add(new Crepe(this)) ;
    }

    public boolean isGameLose() {
        return score < 0 ;
    }

    public void restart() {
        score = 0 ;

        crepes.clear() ;

        pan.reset() ;
    }
}
