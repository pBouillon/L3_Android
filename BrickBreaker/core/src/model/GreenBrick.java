package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GreenBrick extends Brick {

    GreenBrick() {
        super(GREEN_BRICK_LIFE, GREEN_TEX);
    }

    GreenBrick(int life) {
        super(life, GREEN_TEX);
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw (
                getTex(),
                getPosX(),
                getPosY()
        );
    }
}
