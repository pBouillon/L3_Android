package fr.ul.cassebrique.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GreenBrick extends Brick {
    private Animation animation;
    private TextureAtlas textAt = new TextureAtlas(Gdx.files.internal("images/Anim2Ca.pack"));
    private float time = 0;

    GreenBrick() {
        super(GREEN_BRICK_LIFE, GREEN_TEX);
        animation = new Animation((1f/textAt.getRegions().size), textAt.getRegions(), Animation.PlayMode.LOOP);
    }

    GreenBrick(int life) {
        super(life, GREEN_TEX);
    }

    @Override
    public void decreaseLife(){
        super.decreaseLife();
        setTex(BROK_GREEN_TEX) ;
    }

    @Override
    void dispose (GameWorld gw) {
        gw.getWorld().destroyBody(getBody()) ;
    }

    @Override
    public void draw(SpriteBatch sb) {
        if (getLife() == 2) {
            animation = new Animation((1f/textAt.getRegions().size), textAt.getRegions(), Animation.PlayMode.LOOP);
            time += Gdx.graphics.getDeltaTime() * 2 ;
            TextureRegion img = (TextureRegion)animation.getKeyFrame(time, true) ;
            sb.draw(
                    img,
                    getPosX(),
                    getPosY()
            );
        } else {
            sb.draw(
                    getTex(),
                    getPosX(),
                    getPosY()
            );
        }
    }
}
