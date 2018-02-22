package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.dataFactories.TextureFactory;

import java.util.ArrayList;

import static model.Ball.RAYON;

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

    private Body bgauche ;
    private Body bmilieu ;
    private Body bdroite ;

    Racket(GameWorld gameWorld) {
        pos = new Vector2 (
                (TEXT_WIDTH - 50) / 2 - RACK_WIDTH / 2,
                50
        ) ;

        height = TextureFactory.getTexRacket()
                .getHeight() ;

        width  = TextureFactory.getTexRacket()
                .getWidth() ;

        gw = gameWorld ;

        int rayonBoule = TextureFactory.getTexRacket().getHeight() / 2 ;

        // bgauche
        BodyDef bodyDef1 = new BodyDef() ;
        bodyDef1.type    = BodyDef.BodyType.StaticBody ;
        bodyDef1.fixedRotation = false ;
        bodyDef1.position.set(pos.x + RAYON, pos.y + RAYON) ;
        bgauche = gw.getWorld().createBody(bodyDef1) ;

        CircleShape shapeBGauche = new CircleShape() ;
        shapeBGauche.setRadius(rayonBoule) ;

        FixtureDef fixtureDef1  = new FixtureDef() ;
        fixtureDef1.shape       = shapeBGauche ;
        fixtureDef1.density     = 1 ;
        fixtureDef1.restitution = 1 ;
        fixtureDef1.friction    = 0 ;
        bgauche.createFixture(fixtureDef1) ;

        // bmilieu
        int heightRack = TextureFactory.getTexRacket().getHeight();
        int widthRack  = TextureFactory.getTexRacket().getWidth() ;

        Vector2[] tab = new Vector2[4];
        //bas à gauche
        tab[0] = new Vector2(pos.x + rayonBoule * 2, pos.y);
        //haut à gauche
        tab[1] = new Vector2(pos.x + rayonBoule * 2, pos.y + heightRack);
        //haut à droite
        tab[2] = new Vector2(pos.x + widthRack - rayonBoule * 2, pos.y + heightRack);
        //bas à droite
        tab[3] = new Vector2(pos.x + widthRack - rayonBoule * 2, pos.y);

        PolygonShape shapeBMilieu = new PolygonShape();
        shapeBMilieu.set(tab);

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
        bmilieu = gw.getWorld().createBody(bodyDef2);

        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shapeBMilieu;
        fixtureDef2.density = 1;
        fixtureDef2.restitution = 1;
        fixtureDef2.friction = 0;
        bmilieu.createFixture(fixtureDef2);

        // bdroite
        BodyDef bodyDef3 = new BodyDef() ;
        bodyDef3.type    = BodyDef.BodyType.StaticBody ;
        bodyDef3.fixedRotation = false ;
        bodyDef3.position.set(pos.x + widthRack - RAYON, pos.y + RAYON) ;
        bdroite = gw.getWorld().createBody(bodyDef3) ;

        CircleShape shapeBDroite =  new CircleShape() ;
        shapeBDroite.setRadius(rayonBoule) ;

        FixtureDef fixtureDef3  = new FixtureDef() ;
        fixtureDef3.shape       = shapeBDroite ;
        fixtureDef3.density     = 1 ;
        fixtureDef3.restitution = 1 ;
        fixtureDef3.friction    = 0 ;
        bdroite.createFixture(fixtureDef3) ;
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

    int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void update(int mov) {
        float newPos = pos.x ;
        int step ;

        if (mov == MOV_RIGHT) {
            step = MOV_SPACE ;
        } else {
            step = - MOV_SPACE ;
        }
        newPos += step ;

        if (newPos < BORDER_WIDTH) {
            newPos = BORDER_WIDTH ;
            step = 0 ;
        }

        if (newPos > TEXT_WIDTH - BORDER_WIDTH * 2 - RACK_WIDTH) {
            newPos = TEXT_WIDTH - BORDER_WIDTH * 2 - RACK_WIDTH ;
            step = 0 ;
        }

        setPos(newPos, pos.y);

        bgauche.setTransform (
                bgauche.getPosition().x + step,
                bgauche.getPosition().y,
                0
        ) ;
        bmilieu.setTransform (
                bmilieu.getPosition().x + step,
                bmilieu.getPosition().y,
                0 ) ;

        bdroite.setTransform (
                bdroite.getPosition().x + step,
                bdroite.getPosition().y,
                0
        ) ;
    }

    ArrayList<Body> getBody() {
        ArrayList<Body> bodies = new ArrayList<Body>() ;
        bodies.add(bdroite) ;
        bodies.add(bmilieu) ;
        bodies.add(bgauche) ;

        return bodies ;
    }
}
