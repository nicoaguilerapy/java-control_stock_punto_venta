
package herramientas;

import java.applet.AudioClip;

public class Sonido {
    
    private AudioClip sonido=null;
    

    public Sonido(String nombre) {
        sonido = java.applet.Applet.newAudioClip(getClass().getResource("/audios/"+nombre+".wav"));
        sonido.play();
    }
    
}
