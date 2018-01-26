package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static model.Brick.*;

public class Wall {
    private int nbL, nbC ; // col, lignes

    private Brick[][] wall ;

    private static final Brick[][] wallInit = {
            {Bleue, Verte, Bleue, VerteAbimee, Bleue, Bleue, VerteAbimee, Bleue, Verte, Bleue},
            {Bleue, Bleue, Verte, Bleue, VerteAbimee, VerteAbimee, Bleue, Verte, Bleue, Bleue},
            {Bleue, Bleue, Bleue, Verte, Bleue, Bleue, Verte, Bleue, Bleue, Bleue},
            {Bleue, Bleue, Bleue, Bleue, Verte, Verte, Bleue, Bleue, Bleue, Bleue},
            {Vide, Bleue, Vide, Vide, Bleue, Bleue, Vide, Vide, Bleue, Vide}
    } ;

    public Wall () {
        nbC = wallInit.length ;
        nbL = wallInit[0].length ;

        setBricks(false) ;
    }

    private void setBricks(boolean isRandom) {
        wall = wallInit ;
    }

    void draw(SpriteBatch sb) {
        for (int x = 0; x < nbC; ++x) {
            for (int y = 0; y < nbL; ++y) {
                if (wall[x][y].getTex() == null) {
                    continue ;
                }
                sb.draw (
                        wall[x][y].getTex(),
                        200 + (50 * y),
                        100 + (50 * x)
                ) ;
            }
        }
    }
}
