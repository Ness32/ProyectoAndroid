package com.LFP.pantalla;
import android.graphics.*;
import android.util.Log;
import android.view.SurfaceHolder;
public class Hilo extends Thread {

	private SurfaceHolder mas;
	private Nuevavista ver;
	private boolean correr;
	
	//constructor
	 public Hilo(SurfaceHolder mas, Nuevavista ver){
	this.mas=mas;
	this.ver=ver;
	 correr= false;
		 		 	 }
	//correr hilo
	 public void SetRunning(boolean correr){
		 this.correr=correr;
		 
	 }
	 public void run(){
		 //Canvas
		 Canvas canvas;
		 //pintar continuamente
		 while(correr){
			canvas=null;
			try{
				//area nula en donde pintar
				canvas=mas.lockCanvas(null);
				synchronized(mas){
				ver.OnDraw(canvas);	
					
				}
				
			}finally{
				
				//liberar canvas
				if(canvas !=null)
					mas.unlockCanvasAndPost(canvas);
			}
		 }
		
	 }
}
