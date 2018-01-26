package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static model.Brick.*;

class Wall {
    private final static int BASE_X = 75 ;
    private final static int BASE_Y = 400 ;

    private int nbL, nbC ;

    private Brick[][] wall ;

    private static final Brick[][] wallInit = {
            {Bleue, Verte, Bleue, VerteAbimee, Bleue, Bleue, VerteAbimee, Bleue, Verte, Bleue},
            {Bleue, Bleue, Verte, Bleue, VerteAbimee, VerteAbimee, Bleue, Verte, Bleue, Bleue},
            {Bleue, Bleue, Bleue, Verte, Bleue, Bleue, Verte, Bleue, Bleue, Bleue},
            {Bleue, Bleue, Bleue, Bleue, Verte, Verte, Bleue, Bleue, Bleue, Bleue},
            {Vide, Bleue, Vide, Vide, Bleue, Bleue, Vide, Vide, Bleue, Vide}
    } ;

    Wall() {
        nbC = wallInit.length ;
        nbL = wallInit[0].length ;

        setBricks(false) ;
    }

    private void setBricks(boolean isRandom) {
        if (isRandom) {

        } else {
            wall = wallInit ;
        }
    }

    void draw(SpriteBatch sb) {
        for (int x = 0; x < nbC; ++x) {
            for (int y = 0; y < nbL; ++y) {
                if (wall[x][y].getTex() == null) {continue;}
                sb.draw (
                        wall[x][y].getTex(),
                        BASE_X + wall[x][y].getTex().getWidth()  * y,
                        BASE_Y + wall[x][y].getTex().getHeight() * x
                ) ;
            }
        }
    }
}
