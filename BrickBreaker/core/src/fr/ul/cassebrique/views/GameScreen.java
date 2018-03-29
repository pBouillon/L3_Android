package fr.ul.cassebrique.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Timer;
import fr.ul.cassebrique.controls.Listener;
import fr.ul.cassebrique.dataFactories.TextureFactory;
import fr.ul.cassebrique.model.GameState;
import fr.ul.cassebrique.model.GameWorld;
import fr.ul.cassebrique.model.Racket;

import static fr.ul.cassebrique.dataFactories.SoundFactory.*;

public class GameScreen extends ScreenAdapter {
    private GameState   state ;
    private GameWorld   gw ;
    private SpriteBatch sb ;

    private Sound s ;

    private OrthographicCamera cam;
    private Box2DDebugRenderer debugRenderer;

    private Timer.Task timer ;

    public GameScreen() {
        s = null ;
        gw = new GameWorld(this) ;
        sb = new SpriteBatch() ;
        state = new GameState() ;

        new Listener(this) ;

        timer = new Timer.Task() {
            @Override
            public void run() {
                gw.reboot (state) ;
                if (state.getState() != GameState.State.Pause){
                    setGsState(GameState.State.Running);
                }
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
            setGsState(GameState.State.Won);
        }
        else if (gw.isBallLoss()) {
            setGsState(GameState.State.BallLoss);
            if (gw.remainingBalls() - 1 == 0) {
                setGsState(GameState.State.GameOver);
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
        // accéléromtetre
        if (Gdx.input.getAccelerometerY() > 0) {
            gw.getRacket().update(Racket.MOV_RIGHT) ;
        }
        else if (Gdx.input.getAccelerometerY() < 0) {
            gw.getRacket().update(Racket.MOV_LEFT) ;
        }

        if(getState().getState().equals(GameState.State.Running)){
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                gw.getRacket().update(Racket.MOV_LEFT) ;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                gw.getRacket().update(Racket.MOV_RIGHT) ;
            }
        }
    }

    public void setGsState(GameState.State _state) {
        state.setState(_state);
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
                        TextureFactory.getTexBack().getWidth()/2 - (TextureFactory.getTexBallLose().getWidth() /2),
                        TextureFactory.getTexBack().getHeight()/2 - (TextureFactory.getTexBallLose().getHeight()/2)
                ) ;
                s =  BALL_LOSS_SOUND ;
            }
            else if (state.getState().equals(GameState.State.GameOver)) {
                sb.draw (
                        TextureFactory.getTexLose(),
                        TextureFactory.getTexBack().getWidth()/2 - (TextureFactory.getTexLose().getWidth() /2),
                        TextureFactory.getTexBack().getHeight()/2 - (TextureFactory.getTexLose().getHeight()/2)
                ) ;
                s = GAME_OVER_SOUND ;
            }
            else if (state.getState().equals(GameState.State.Won)) {
                sb.draw (
                        TextureFactory.getTexWin(),
                        TextureFactory.getTexBack().getWidth()/2 - (TextureFactory.getTexWin().getWidth() /2),
                        TextureFactory.getTexBack().getHeight()/2 - (TextureFactory.getTexWin().getHeight()/2)
                ) ;
                s = WON_SOUND ;
            }
            else if (state.getState().equals(GameState.State.Quit)) {
                dispose();
            }
            if (!timer.isScheduled()) {
                Timer.schedule (timer, 3) ;
                if (s != null) {
                    play(.5f, s) ;
                    s = null ;
                }
            }
        }
        sb.end();

//        debugRenderer.render(gw.getWorld(), cam.combined);

        update();
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.exit() ;
    }

    public GameState getState() {
        return state;
    }
}
