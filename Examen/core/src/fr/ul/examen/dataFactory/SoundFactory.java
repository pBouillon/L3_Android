package fr.ul.examen.dataFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundFactory {
    private static String AMBIANCE = "sounds/ambiance.mp3" ;
    private static String COLLECTE = "sounds/collecte.mp3" ;
    private static String PERTE    = "sounds/perte.mp3"  ;
    private static String SORTIE   = "sounds/sortie.mp3" ;

    public static Sound AMBIANCE_SOUND
            = Gdx.audio.newSound(Gdx.files.internal(AMBIANCE)) ;
    public static Sound COLLECTE_SOUND
            = Gdx.audio.newSound(Gdx.files.internal(COLLECTE)) ;
    public static Sound PERTE_SOUND
            = Gdx.audio.newSound(Gdx.files.internal(PERTE)) ;
    public static Sound SORTIE_SOUND
            = Gdx.audio.newSound(Gdx.files.internal(SORTIE)) ;

    public static void play (float vol, Sound s) {
        s.play(vol) ;
    }
}
