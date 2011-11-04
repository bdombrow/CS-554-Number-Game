package com.numbergame;

import com.numbergame.R;
    import com.numbergame.NumberPuzzleView;
	import android.app.Activity;
import android.content.SharedPreferences;
	import android.os.Bundle;
	import android.view.MotionEvent;
	import android.view.View;
import android.widget.TextView;
	
public class NumbersProjectActivity  extends Activity implements View.OnTouchListener
	{
	private final NumberPuzzle mNumberPuzzle = new NumberPuzzle();
	private NumberPuzzleView mNumberPuzzleView;
	    
    private static String PUZZLE_PREFS = "numbers-prefs";
	    private static String ICICLE_KEY = "numbers-view";

	    @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);

	        if (savedInstanceState == null) 
	        {
	            // We were just launched -- set up a new game
	        	SharedPreferences settings = getSharedPreferences(PUZZLE_PREFS, 0);
	        	mNumberPuzzle.restoreState(settings);
	        } 
	        else 
	        {
	            // We are being restored
	            Bundle numbermap = savedInstanceState.getBundle(ICICLE_KEY);
	            if (numbermap != null) 
	            {
	            	mNumberPuzzle.restoreState(numbermap);
	            } 
	            else 
	            {
	            	//mPuzzleView.setMode(PuzzleView.PAUSE);
	            }
	        }

	        setContentView(R.layout.numberpuzzle);
	        mNumberPuzzleView = (NumberPuzzleView) findViewById(R.id.numberpuzzle);
	        mNumberPuzzleView.setTextView((TextView) findViewById(R.id.score));
	        mNumberPuzzleView.setNumberPuzzleControl(mNumberPuzzle);
	        
	        mNumberPuzzleView.setOnTouchListener(this);

		/*	View.OnTouchListener mylistener = new View.OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					 return false;//mPuzzleView.onTouch(v, event);//false;
				}
			};*/
	        //mPuzzleView.setOnTouchListener(PuzzleProjectActivity.this);
	    }
	    
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			
			SharedPreferences settings = getSharedPreferences(PUZZLE_PREFS, 0);
			SharedPreferences.Editor editor = settings.edit();
			mNumberPuzzle.saveState(editor);
		}

	    @Override
	    protected void onPause() 
	    {
	        super.onPause();
	        // Pause the game along with the activity
	        //mPuzzleView.setMode(PuzzleView.PAUSE);
	    }
	    

	  //  @Override
	/*	public boolean onTouchEvent(MotionEvent event) {
			super.onTouchEvent(event);
			return mPuzzleView.onTouch(, event);
			
		}*/

		@Override
	    public void onSaveInstanceState(Bundle outState) 
	    {
	        //Store the game state
	    	Bundle map = new Bundle();
	    	mNumberPuzzle.saveState(map);
	        outState.putBundle(ICICLE_KEY, map);
	    }

		public boolean onTouch(View v, MotionEvent event) 
		{
			return mNumberPuzzleView.onTouch(v, event);
		}
	}



