package com.LFP.pantalla;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class Myvista extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	MediaPlayer  mediaPlayer;
       	mediaPlayer = MediaPlayer.create(this,R.raw.musica);
      
       	mediaPlayer.start();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);     
      
        // setContentView(R.layout.activity_myvista);
        
        
        //aca comienza mi codigo
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(new Nuevavista(this));
        
        
        
        //aca termina
        
        
        
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.myvista, menu);
        return true;
    }
    */
}
