package com.spinning;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
//import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class GraphicsView extends View {
	private static final String QUOTE = 
			"Crazy Puzzle-  Drives you Crazy.";       
	private Animation anim;     
	public GraphicsView(Context context) {   
		super(context);     
		}
	private void createAnim(Canvas canvas) {    
		anim = new RotateAnimation(0, 360, canvas.getWidth() / 2, canvas
				.getHeight() / 2);           
		anim.setRepeatMode(Animation.REVERSE);   
		anim.setRepeatCount(Animation.INFINITE); 
		anim.setDuration(10000L);            
		anim.setInterpolator(new AccelerateDecelerateInterpolator());          
		startAnimation(anim); 
		}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		          // creates the animation the first time        
		if (anim == null) {           
			createAnim(canvas);         
			}           
		Path circle = new Path();   
		int centerX = canvas.getWidth() / 2;    
		int centerY = canvas.getHeight() / 2;      
		int r = Math.min(centerX, centerY);         
		circle.addCircle(centerX, centerY, r, Direction.CW);      
		Paint paint = new Paint();            paint.setColor(Color.BLUE);   
		paint.setTextSize(30);            paint.setAntiAlias(true);     
		canvas.drawTextOnPath(QUOTE, circle, 0, 30, paint);        
		
	}
	
}


