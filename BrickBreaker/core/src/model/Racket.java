package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.cassebrique.dataFactories.TextureFactory;

public class Racket {

    public  static final int MOV_LEFT  = 0 ;
    public  static final int MOV_RIGHT = 1 ;
    private static final int MOV_SPACE = 10 ;

    private static final int BORDER_WIDTH  = TextureFactory.getTexBorder().getWidth() ;
    private static final int TEXT_WIDTH    = TextureFactory.getTexBack().getWidth()   ;
    private static final int RACK_WIDTH    = TextureFactory.getTexRacket().getWidth() ;

    private GameWorld gw ;
    private Vector2 pos ;
    private int height ;
    private int width ;

    Racket() {
        pos = new Vector2 (
            (TEXT_WIDTH - 50) / 2 - RACK_WIDTH / 2,
            50
        ) ;
        height = TextureFactory.getTexRacket()
                                .getHeight() ;
        width  = TextureFactory.getTexRacket()
                                .getWidth() ;
    }

    void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getTexRacket(), pos.x, pos.y);
    }

    public Vector2 getPos() {
        return pos;
    }

    private void setPos(float x, float y) {
        this.pos.x = x ;
        this.pos.y = y ;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void update(int mov) {
        float newPos = pos.x ;

        if (mov == MOV_RIGHT) {
            newPos += MOV_SPACE ;
        } else {
            newPos -= MOV_SPACE ;
        }

        if (newPos < BORDER_WIDTH) {
            newPos = BORDER_WIDTH ;
        }

        if (newPos > TEXT_WIDTH - BORDER_WIDTH * 2 - RACK_WIDTH) {
            newPos = TEXT_WIDTH - BORDER_WIDTH * 2 - RACK_WIDTH ;
        }

        setPos(newPos, pos.y);
    }

}
