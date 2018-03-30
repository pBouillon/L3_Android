package fr.ul.examen.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Timer;
import fr.ul.examen.Control;
import fr.ul.examen.dataFactory.SoundFactory;
import fr.ul.examen.dataFactory.TextureFactory;
import fr.ul.examen.model.GameState;
import fr.ul.examen.model.GameWorld;
import fr.ul.examen.model.Poele;

public class GameScreen extends ScreenAdapter {
    private int CREPE_DELAY   = 2 ;
    private int RESTART_DELAY = 3 ;

    private GameState state ;
    private GameWorld gw ;
    private SpriteBatch sb ;

    private OrthographicCamera cam;
    private Box2DDebugRenderer debugRenderer;

    private Timer.Task timerCrepe ;
    private Timer.Task timerRestart;

    public GameScreen() {
        gw = new GameWorld(this);
        sb = new SpriteBatch();
        state = new GameState();
        state.setState(GameState.State.Running) ;

        new Control(this);
        timerCrepe = new Timer.Task() {
            @Override
            public void run() {
                gw.addCrepe();
            }
        } ;

        timerRestart = new Timer.Task() {
            @Override
            public void run() {
                gw.restart();
                state.setState(GameState.State.Running) ;
            }
        } ;

        cam = new OrthographicCamera(1150, 700);

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

 debugRenderer = new Box2DDebugRenderer();
    }

    private void update() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if (Gdx.input.getX()
                    - TextureFactory.getTexPoele().getWidth() / 2
                    < gw.getPan().getPos().x) {
                gw.getPan().update(Poele.MOV_LEFT) ;
            } else {
                gw.getPan().update(Poele.MOV_RIGHT) ;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            gw.getPan().update(Poele.MOV_LEFT) ;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            gw.getPan().update(Poele.MOV_RIGHT) ;
        }


        gw.applyGravity() ;
        if (!timerCrepe.isScheduled()) {
            Timer.schedule (timerCrepe, CREPE_DELAY) ;
        }

        if (gw.isGameLose() && !timerRestart.isScheduled()) {
            state.setState(GameState.State.GameOver) ;
            SoundFactory.play(.5f, SoundFactory.PERTE_SOUND);
            Timer.schedule(timerRestart, RESTART_DELAY) ;
        }
    }

    @Override
    public void render(float delta){
        super.render(delta);

        cam.update();
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        if (state.getState() == GameState.State.Running) {
            gw.draw(sb);
        }
        else {
            sb.draw (
                    TextureFactory.getTexPerte(),
                    Gdx.graphics.getWidth()  / 2
                            - (TextureFactory.getTexFinPartie().getWidth()  / 2),
                    Gdx.graphics.getHeight() / 2
                            - (TextureFactory.getTexFinPartie().getHeight() / 2)
            ) ;
        }

        sb.end();

// debugRenderer.render(gw.getWorld(), cam.combined);
        update() ;
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.exit() ;
    }
}
