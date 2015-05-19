package com.LFP.pantalla;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.ActionBar;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Nuevavista extends SurfaceView implements SurfaceHolder.Callback {
private Hilo thread;
	private Bitmap iconos,fondo,avion;
	private int ancho,alto,tocar_x,tocar_y,contadorfacil,contadormedio,contadordificil;
	private SurfaceHolder contenedor;
	private boolean tocar;
	private int oroy,orox,platay,platax,broncex,broncey;
	private Context conte;
	
	
	
	public Nuevavista(Context context) {
		super(context);
		getHolder().addCallback(this);
		fondo=BitmapFactory.decodeResource(getResources(),R.drawable.movement);
		avion=BitmapFactory.decodeResource(getResources(), R.drawable.zemin);
		
		
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void surfaceChanged(SurfaceHolder contenedor, int ancho, int alto ,int format /*int arg3*/) {
		// TODO Auto-generated method stub
		this.ancho=ancho;
		this.alto=alto;
		
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder contenedor) {
		// TODO Auto-generated method stub
		thread = new Hilo(getHolder(),this);
		thread.SetRunning(true);
		thread.start(); 
		
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder /*contenedor*/ arg0 ) {
		// TODO Auto-generated method stub
		Log.e("surfaceDestroyed","Hilo detenido");
		boolean retry = true;
		thread.SetRunning(false);
		//parar hilo
		while(retry){			
			try{
				thread.join();
				retry=false;				
			}catch(InterruptedException e){
				
			}
		}
	}
    
	
	public boolean onTouchEvent(MotionEvent event){
	
	//tomar coordenadas de X&Y
	tocar_x= (int) event.getX();
	tocar_y= (int) event.getY();
	//codigos de accion
	int movimiento= event.getAction();
	//Log.e("tocar(XY)","("+tocar_x+","+tocar_y+")");
switch(movimiento){
case MotionEvent.ACTION_DOWN:
tocar=true;
Log.e("TourchEven ACTION_DOWN ","Se toco la pantalla");


break;
case MotionEvent.ACTION_MOVE:
	tocar=true;
	Log.e("TouchEven ACTION_MOVE","Nos desplazamos por la pantalla");
	//Log.e("tocar(XY)","("+tocar_x+","+tocar_y+")");
	
break;
case MotionEvent.ACTION_UP:
tocar=false;
Log.e("TouchEvent ACTION_UP","Ya no tocamos la pantalla");
break;

case MotionEvent.ACTION_CANCEL:
	tocar=false;
	Log.e("TouchEven ACTION_CANCEL"," ");
	
break;
case MotionEvent.ACTION_OUTSIDE:
	Log.e("TouchEven ACTION_OUTSIDE"," ");
	tocar=false;
	break;
	default:
}		
		
		
		return true;}
	

//canvas
	public void OnDraw(Canvas canvas){
		contadorfacil=80;
		contadormedio=40;
		contadordificil=20;
		int maximoy=976; 
		int minimoy=10;
		int maximox=600;
		int minimox=10;
		
		//Log.e("mas"," "+tocar_x);
		
		//
	
		//
		DisplayMetrics medidas = new DisplayMetrics();
	   int pantallalta=medidas.heightPixels;
		int pantallancha=medidas.widthPixels;
	
		pantallalta=getHeight();
		pantallancha=getWidth();
		Log.e("pantalla alta"," "+pantallalta);
		//Log.e("pantalla ancha"," "+pantallancha);
		fondo=Bitmap.createScaledBitmap(fondo, pantallancha, pantallalta, true);
		//tocar_x=(pantallalta/4);
		//tocar_y=880;
		//int ancho= canvas.getWidth();
		//int alto=canvas.getHeight();
		//canvas.drawColor(Color.GREEN);
		canvas.drawBitmap(fondo, 0, 0, null);
		
			
			
			/*
		//asteroide dorado
		Paint asteroide1 = new Paint();
		asteroide1.setColor(Color.parseColor("#FFC204"));//color dorado
		asteroide1.setStyle(Paint.Style.FILL);
		canvas.drawCircle(200, 100 ,35 , asteroide1);
		
		//asteroide plateado
		Paint asteroide2 = new Paint();
		asteroide2.setColor(Color.parseColor("#87848F"));//color plateado
		asteroide2.setStyle(Paint.Style.FILL);
		canvas.drawCircle(100, 100,40 , asteroide2);
		
		
		//asteroide bronceado
		Paint asteroide3 = new Paint();
		asteroide3.setColor(Color.parseColor("#BB8021"));//color bronce
		asteroide3.setStyle(Paint.Style.FILL);
		canvas.drawCircle(300, 100,40 , asteroide3);		
		Log.e("tocar(XY)","("+tocar_x+","+tocar_y+")");
		
		*/
		
		
		
		try {
			
			Log.e("Hilo","10 segundos");
			for(int x=1;x<8;x++){
				//posiciones ramdon de de los meteoros en "y"
				Random maxy =new Random();
				 oroy=maxy.nextInt(maximoy-minimoy +1)+ minimoy;
				 platay=maxy.nextInt(maximoy-minimoy +1)+ minimoy;
				 broncey=maxy.nextInt(maximoy-minimoy +1)+ minimoy;
				//posiciones random de los meteoros en "x";
				Random maxx= new  Random();			
				 orox=maxx.nextInt(maximox-minimox +1)+minimox;
				platax=maxx.nextInt(maximox-minimox +1)+minimox;
				 broncex=maxx.nextInt(maximox-minimox +1)+minimox;
				//Log.e("numero random y oro"," "+oroy);
				//Log.e("numero random x oro"," "+orox);
				Log.e("tocar(XY)","("+tocar_x+","+tocar_y+")");
				if((tocar_x==0)){
					Log.e("pantallanull"," No ha tocado la pantalla");					
					
				}else{
					
					//LinearLayout layout = new LinearLayout(conte);
					//TextView textView = new TextView(conte);
					//inicio contador de puntos
					
					if((tocar_x<=(orox+36)&& tocar_x>=(orox-36)) ){
						Paint letras = new Paint();
						letras.setStyle(Paint.Style.FILL);
						letras.setAntiAlias(true);
						letras.setTextSize(50);
						letras.setColor(Color.RED);
					contadorfacil=(contadorfacil+5);
					Log.e(" oro"," "+contadorfacil);
					canvas.drawText(Integer.toString(contadorfacil), (pantallalta/4), 800, letras);
					Paint letras2 = new Paint();
					letras2.setStyle(Paint.Style.FILL);
					letras2.setAntiAlias(true);
					letras2.setTextSize(50);
					letras2.setColor(Color.YELLOW);
					canvas.drawText("Este es su punteo:", (pantallalta/8), 700, letras2);
					tocar_x=0;
					tocar_y=0;
					
					}
					if((tocar_x<=(platax+40)&& tocar_x>=(platax-40))){
						contadorfacil=(contadorfacil-10);	
					Log.e("plata"," "+contadorfacil);
					//tocar_x=0;
					//tocar_y=0;
					Paint letras = new Paint();
					letras.setStyle(Paint.Style.FILL);
					letras.setAntiAlias(true);
					letras.setTextSize(50);
					letras.setColor(Color.RED);
					canvas.drawText(Integer.toString(contadorfacil), (pantallalta/4), 800, letras);
					Paint letras2 = new Paint();
					letras2.setStyle(Paint.Style.FILL);
					letras2.setAntiAlias(true);
					letras2.setTextSize(50);
					letras2.setColor(Color.YELLOW);
					canvas.drawText("Este es su punteo:", (pantallalta/8), 700, letras2);
					}
					if((tocar_x<=(broncex+40)&& tocar_x>=(broncex-40)) ){
						contadorfacil=(contadorfacil-5);	
						Log.e("bronce"," "+contadorfacil);
						tocar_x=0;
						tocar_y=0;
						Paint letras = new Paint();
						
						letras.setStyle(Paint.Style.FILL);
						letras.setAntiAlias(true);
						letras.setTextSize(50);
						letras.setColor(Color.RED);
						canvas.drawText(Integer.toString(contadorfacil), (pantallalta/4), 800, letras);
						Paint letras2 = new Paint();
						letras2.setStyle(Paint.Style.FILL);
						letras2.setAntiAlias(true);
						letras2.setTextSize(50);
						letras2.setColor(Color.YELLOW);
						canvas.drawText("Este es su punteo:", (pantallalta/8), 700, letras2);
					
					}
					
					
					//fin contado de puntos	
					
					
				}
				
				
			
			//asteroide dorado
			Paint asteroide1 = new Paint();
			asteroide1.setColor(Color.parseColor("#FFC204"));//color dorado
			asteroide1.setStyle(Paint.Style.FILL);
			//canvas.drawCircle(200, 100 *x,35 , asteroide1);
			canvas.drawCircle(oroy, orox,35 , asteroide1);
			//asteroide plateado
			Paint asteroide2 = new Paint();
			asteroide2.setColor(Color.parseColor("#87848F"));//color plateado
			asteroide2.setStyle(Paint.Style.FILL);
			//canvas.drawCircle(100, 100*x,40 , asteroide2);
			canvas.drawCircle(platay, platax,40 , asteroide2);
			
			//asteroide bronceado
			Paint asteroide3 = new Paint();
			asteroide3.setColor(Color.parseColor("#BB8021"));//color bronce
			asteroide3.setStyle(Paint.Style.FILL);
			//canvas.drawCircle(300, 100*x,40 , asteroide3);		
			canvas.drawCircle(broncey, broncex,40 , asteroide3);}
			Hilo.sleep(500);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//Log.e("Hilo","10 segundos");
			
		}
		//fin detener hilo 
		
	
	
	//fin asteroides
		
			
		
		
		
		
			}	
	
//fin canvas	


}
