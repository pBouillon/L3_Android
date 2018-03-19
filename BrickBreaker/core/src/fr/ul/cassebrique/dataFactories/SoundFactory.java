package fr.ul.cassebrique.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundFactory {
    private static String HIT        = "sounds/collision.wav" ;
    private static String HIT_RACK   = "sounds/impact.mp3" ;
    private static String LOOSE      = "sounds/perte.mp3" ;
    private static String BALL_LOOSE = "sounds/perteBalle.wav" ;
    private static String VICTORY    = "sounds/victoire.mp3" ;

    public static Sound HIT_SOUND = Gdx.audio.newSound(Gdx.files.internal(HIT));
    public static Sound HIT_RACK_SOUND = Gdx.audio.newSound(Gdx.files.internal(HIT_RACK));
    public static Sound GAME_OVER_SOUND = Gdx.audio.newSound(Gdx.files.internal(LOOSE));
    public static Sound BALL_LOSS_SOUND = Gdx.audio.newSound(Gdx.files.internal(BALL_LOOSE));
    public static Sound WON_SOUND = Gdx.audio.newSound(Gdx.files.internal(VICTORY));

    public static void play (float vol, Sound s) {
        s.play(vol) ;
    }
}
