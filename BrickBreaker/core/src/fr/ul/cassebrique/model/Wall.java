package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Fixture;

import java.util.ArrayList;
import java.util.Collections;

import static fr.ul.cassebrique.model.Brick.BLUE_TEX;
import static fr.ul.cassebrique.model.Brick.BROKEN_BRICK_LIFE;
import static fr.ul.cassebrique.model.Brick.GREEN_BRICK_LIFE;

public class Wall {
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

        setBricks(false) ;
        for (int i = 0; i < nbL; ++i) {
            for (int j = 0; j < nbC; ++j) {
                if (wall[i][j] == null) {continue;}
                wall[i][j].setPosX(BASE_X + BLUE_TEX.getWidth() * j)  ;
                wall[i][j].setPosY(BASE_Y - BLUE_TEX.getHeight() * i) ;
                wall[i][j].addPhysique(gw) ;
            }
        }
    }

    private void setBricks (boolean isRandom) {
        wall = new Brick[wallInit.length][wallInit[0].length] ;

        if (!isRandom) {
            for (int i = 0; i < wallInit.length; ++i) {
                System.arraycopy(wallInit[i], 0, wall[i], 0, wallInit[0].length);
            }
        }
        else {
            for (int i = 0; i < nbL; i++) {
                for (int j = 0; j < nbC; j++) {
                    float rdm = (float) (Math.random());
                    if (0 < rdm && rdm < 0.09) {
                        wall[i][j] = new GreenBrick(BROKEN_BRICK_LIFE);
                    } else if (rdm < 0.49) {
                        wall[i][j] = new GreenBrick(GREEN_BRICK_LIFE);
                    } else if (rdm < 0.89) {
                        wall[i][j] = new BlueBrick();
                    } else wall[i][j] = null;
                }
            }
        }

        brickCpt = 0 ;
        for (int i = 0; i < nbL; ++i) {
            for (int j = 0; j < nbC; ++j) {
                if (wall[i][j] == null) continue ;
                ++brickCpt ;
            }
        }
    }

    void draw (SpriteBatch sb) {
        for (int i = 0; i < nbL; ++i) {
            for (int j = 0; j < nbC; ++j) {
                if (wall[i][j] == null) continue ;
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
                        --brickCpt ;
                    }
                    break ;
                }
            }
        }
    }

    void nextLevel() {
        setBricks(true) ;
    }

    boolean isEmpty() {
        return brickCpt == 0 ;
    }

    void regenerate() {
        setBricks (false) ;

        for (int i = 0; i < nbL; ++i) {
            for (int j = 0; j < nbC; ++j) {
                if (wall[i][j] == null) continue ;
                wall[i][j].setPosX(BASE_X + BLUE_TEX.getWidth() * j)  ;
                wall[i][j].setPosY(BASE_Y - BLUE_TEX.getHeight() * i) ;
                wall[i][j].addPhysique(gw) ;
            }
        }
    }

    public int getBrickCpt() {
        return brickCpt;
    }
}
