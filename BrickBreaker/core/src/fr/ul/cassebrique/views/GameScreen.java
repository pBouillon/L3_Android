package fr.ul.cassebrique.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Timer;
import fr.ul.cassebrique.dataFactories.TextureFactory;
import model.GameState;
import model.GameWorld;
import model.Racket;

public class GameScreen extends ScreenAdapter {
    private GameState   state ;
    private GameWorld   gw ;
    private SpriteBatch sb ;

    private OrthographicCamera cam;
    private Box2DDebugRenderer debugRenderer;

    private Timer.Task timer ;

    public GameScreen() {
        gw = new GameWorld(this) ;
        sb = new SpriteBatch() ;
        state = new GameState() ;

        timer = new Timer.Task() {
            @Override
            public void run() {
                gw.reboot (state) ;
                state.setState(GameState.State.Running);
            }
        } ;

        cam = new OrthographicCamera(1150, 700);

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        debugRenderer = new Box2DDebugRenderer();
    }

    private void update() {
        /* States changes */
        if (gw.isWallEmpty()) {
            state.setState(GameState.State.Won) ;
        }
        else if (gw.isBallLoss()) {
            state.setState(GameState.State.BallLoss) ;
            if (gw.remainingBalls() - 1 == 0) {
                state.setState(GameState.State.GameOver) ;
            }
        }

        /* Movements */
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

        cam.update();
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        if (state.getState().equals(GameState.State.Running)) {
            gw.draw(sb);
        }
        else {
            if (state.getState().equals(GameState.State.BallLoss)) {
                sb.draw (
                        TextureFactory.getTexBallLose(),
                        0,
                        0
                ) ;
            }
            else if (state.getState().equals(GameState.State.GameOver)) {
                sb.draw (
                        TextureFactory.getTexLose(),
                        0,
                        0
                ) ;
            }
            else if (state.getState().equals(GameState.State.Won)) {
                sb.draw (
                        TextureFactory.getTexWin(),
                        0,
                        0
                ) ;
            }
            if (!timer.isScheduled()) {
                Timer.schedule (timer, 3) ;
            }
        }
        sb.end();

        debugRenderer.render(gw.getWorld(), cam.combined);

        update();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
