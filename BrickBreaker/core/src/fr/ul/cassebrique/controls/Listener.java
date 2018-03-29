package fr.ul.cassebrique.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import fr.ul.cassebrique.views.GameScreen;
import fr.ul.cassebrique.model.GameState;

import javax.swing.*;
import java.awt.*;

public class Listener implements InputProcessor {
    private GameScreen gs ;
    Frame frame;

    public Listener(GameScreen _gs) {
        Gdx.input.setInputProcessor(this);
        gs = _gs ;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            if (gs.getState().getState() == GameState.State.Running) {
                gs.setGsState(GameState.State.Pause);
            } else {
                gs.setGsState(GameState.State.Running);
            }
        }
        else if (keycode == Input.Keys.ESCAPE) {
            Object[] options = {"Yes","No"};
            if (JOptionPane.showOptionDialog(frame,
                    "Exit game ?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0])
                    == JOptionPane.YES_NO_OPTION){
                gs.setGsState(GameState.State.Quit);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
