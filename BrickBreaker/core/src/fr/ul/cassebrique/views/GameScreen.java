package fr.ul.cassebrique.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import model.GameWorld;

public class GameScreen extends ScreenAdapter {
    GameWorld   gw ;
    SpriteBatch sb ;

    public GameScreen() {
        sb = new SpriteBatch() ;
        gw = new GameWorld(this) ;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(102, 255, 177, 11) ;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        gw.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
