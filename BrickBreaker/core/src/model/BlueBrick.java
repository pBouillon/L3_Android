package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlueBrick extends Brick {

    public BlueBrick() {
        super(BLUE_BRICK_LIFE, BLUE_TEX);
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
