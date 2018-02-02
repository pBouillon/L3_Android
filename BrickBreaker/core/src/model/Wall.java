package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static model.Brick.BLUE_TEX;
import static model.Brick.BROKEN_BRICK_LIFE;

class Wall {
    private final static int BASE_X = 50 ;
    private final static int BASE_Y = 555 ;

    private int nbL, nbC ;

    private Brick[][] wall ;

    private static final Brick[][] wallInit = {
            {new BlueBrick(), new GreenBrick(), new BlueBrick(), new GreenBrick(BROKEN_BRICK_LIFE), new BlueBrick(), new BlueBrick(), new GreenBrick(BROKEN_BRICK_LIFE), new BlueBrick(),  new GreenBrick(BROKEN_BRICK_LIFE), new BlueBrick()},
            {new BlueBrick(), new BlueBrick(), new GreenBrick(), new BlueBrick(), new GreenBrick(BROKEN_BRICK_LIFE), new GreenBrick(BROKEN_BRICK_LIFE), new BlueBrick(),  new GreenBrick(), new BlueBrick(), new BlueBrick()},
            {new BlueBrick(), new BlueBrick(), new BlueBrick(),  new GreenBrick(), new BlueBrick(), new BlueBrick(),  new GreenBrick(), new BlueBrick(), new BlueBrick(), new BlueBrick()},
            {new BlueBrick(), new BlueBrick(), new BlueBrick(), new BlueBrick(),  new GreenBrick(),  new GreenBrick(), new BlueBrick(), new BlueBrick(), new BlueBrick(), new BlueBrick()},
            {null, new BlueBrick(), null, null, new BlueBrick(), new BlueBrick(), null, null, new BlueBrick(), null}
    } ;

    Wall() {
        nbL = wallInit.length ;
        nbC = wallInit[0].length ;

        setBricks(false) ;
    }

    private void setBricks(boolean isRandom) {
        if (isRandom) {
        } else {
            wall = wallInit ;
        }
    }

    void draw(SpriteBatch sb) {
        for (int i = 0; i < nbL; ++i) {
            for (int j = 0; j < nbC; ++j) {
                if (wall[i][j] == null) {continue;}
                wallInit[i][j].setPosX(BASE_X + BLUE_TEX.getWidth()  * j);
                wallInit[i][j].setPosY(BASE_Y - BLUE_TEX.getHeight()  * i);
                wallInit[i][j].draw(sb) ;
            }
        }


    }
}
