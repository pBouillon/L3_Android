package fr.ul.cassebrique.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.cassebrique.dataFactories.TextureFactory;
import model.GameState;
import model.GameWorld;
import model.Racket;

public class GameScreen extends ScreenAdapter {
    private GameState   state ;
    private GameWorld   gw ;
    private SpriteBatch sb ;

    public GameScreen() {
        gw = new GameWorld(this) ;
        sb = new SpriteBatch() ;
        state = new GameState() ;
    }

    private void update() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if (Gdx.input.getX()
                    - TextureFactory.getTexRacket().getWidth() / 2
                    < gw.getRacket().getPos().x) {
                gw.getRacket().update(Racket.MOV_LEFT) ;
            } else {
                gw.getRacket().update(Racket.MOV_RIGHT) ;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            gw.getRacket().update(Racket.MOV_LEFT) ;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            gw.getRacket().update(Racket.MOV_RIGHT) ;
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        sb.begin();
        gw.draw(sb);
        sb.end();
        update();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
