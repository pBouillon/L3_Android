package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Fixture;

import java.util.ArrayList;
import java.util.Collections;

import static model.Brick.BLUE_TEX;
import static model.Brick.BROKEN_BRICK_LIFE;

class Wall {
    private final static int BASE_X = 50 ;
    private final static int BASE_Y = 555 ;

    private final static int SHUFFLE_CPT = 50 ;

    private final static int BRICK_NB = 44 ;

    private int brickCpt;
    private int nbL, nbC ;
    private GameWorld gw ;

    private Brick[][] wall ;

    private static final Brick[][] wallInit = {
            {new BlueBrick(), new GreenBrick(), new BlueBrick(), new GreenBrick(BROKEN_BRICK_LIFE), new BlueBrick(), new BlueBrick(), new GreenBrick(BROKEN_BRICK_LIFE), new BlueBrick(),  new GreenBrick(BROKEN_BRICK_LIFE), new BlueBrick()},
            {new BlueBrick(), new BlueBrick(), new GreenBrick(), new BlueBrick(), new GreenBrick(BROKEN_BRICK_LIFE), new GreenBrick(BROKEN_BRICK_LIFE), new BlueBrick(),  new GreenBrick(), new BlueBrick(), new BlueBrick()},
            {new BlueBrick(), new BlueBrick(), new BlueBrick(),  new GreenBrick(), new BlueBrick(), new BlueBrick(),  new GreenBrick(), new BlueBrick(), new BlueBrick(), new BlueBrick()},
            {new BlueBrick(), new BlueBrick(), new BlueBrick(), new BlueBrick(),  new GreenBrick(),  new GreenBrick(), new BlueBrick(), new BlueBrick(), new BlueBrick(), new BlueBrick()},
            {null, new BlueBrick(), null, null, new BlueBrick(), new BlueBrick(), null, null, new BlueBrick(), null}
    } ;

    Wall(GameWorld gameWorld) {
        nbL = wallInit.length ;
        nbC = wallInit[0].length ;

        gw = gameWorld ;

        brickCpt = BRICK_NB ;

        setBricks(false) ;

        for (int i = 0; i < nbL; ++i) {
            for (int j = 0; j < nbC; ++j) {
                if (wall[i][j] == null) {continue;}
                wallInit[i][j].setPosX(BASE_X + BLUE_TEX.getWidth() * j)  ;
                wallInit[i][j].setPosY(BASE_Y - BLUE_TEX.getHeight() * i) ;
                wallInit[i][j].addPhysique(gw) ;
            }
        }
    }

    private void setBricks (boolean isRandom) {
        wall = wallInit ;

        if (!isRandom) return ;

        ArrayList<Brick> brickList = new ArrayList<Brick>() ;
        for (Brick[] row : wall) Collections.addAll (brickList, row) ;

        for (int i = 0; i < SHUFFLE_CPT; ++i) Collections.shuffle(brickList) ;

        for (int i = 0; i < wall.length; ++i) {
            for (int j = 0; j < wall[0].length; ++j) {
                wall[i][j] = brickList.get(0) ;
                brickList.remove (0) ;
            }
        }

    }

    void draw (SpriteBatch sb) {
        for (int i = 0; i < nbL; ++i) {
            for (int j = 0; j < nbC; ++j) {
                if (wall[i][j] == null) {continue;}
                wallInit[i][j].draw(sb) ;
            }
        }
    }

    void destroy(Fixture toDestroy) {
        for (Brick[] row : wall) {
            for (int j = 0; j < wall[0].length; ++j) {
                if (row[j] == null) { continue ; }
                if (row[j].getBody() == toDestroy.getBody()) {
                    row[j].decreaseLife() ;
                    if (row[j].getLife() == 0) {
                        row[j].dispose(gw) ;
                        row[j] = null ;
                        --brickCpt;
                    }
                    break ;
                }
            }
        }
    }

    void nextLevel() {
        brickCpt = BRICK_NB ;
        setBricks(true) ;
    }

    boolean isEmpty() {
        return brickCpt == 0 ;
    }

    void dispose() {
        for (Brick[] row : wall) {
            for (Brick b : row) {
                if (b != null) b.dispose (gw) ;
            }
        }
    }

    void regenerate() {
        dispose() ;
        setBricks (false) ;

        brickCpt = BRICK_NB ;

        for (int i = 0; i < nbL; ++i) {
            for (int j = 0; j < nbC; ++j) {
                if (wall[i][j] == null) continue ;
                wallInit[i][j].setPosX(BASE_X + BLUE_TEX.getWidth() * j)  ;
                wallInit[i][j].setPosY(BASE_Y - BLUE_TEX.getHeight() * i) ;
                wallInit[i][j].addPhysique(gw) ;
            }
        }
    }
}
