package com.numbergame;

     import com.numbergame.R;
     import com.numbergame.PuzzleView;
	import android.app.Activity;
	import android.os.Bundle;
	import android.view.MotionEvent;
	import android.view.View;
import android.widget.TextView;
	
public class PuzzleProjectActivity extends Activity implements View.OnTouchListener
	{
	    private PuzzleView mPuzzleView;
	    
	    private static String ICICLE_KEY = "puzzle-view";

	    @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);

	        setContentView(R.layout.puzzle);
	        
	        mPuzzleView = (PuzzleView) findViewById(R.id.puzzle);
	        mPuzzleView.setTextView((TextView) findViewById(R.id.score));
	        
	        mPuzzleView.setOnTouchListener(this);

		/*	View.OnTouchListener mylistener = new View.OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					 return false;//mPuzzleView.onTouch(v, event);//false;
				}
			};*/
	        //mPuzzleView.setOnTouchListener(PuzzleProjectActivity.this);

	        if (savedInstanceState == null) 
	        {
	            // We were just launched -- set up a new game
	        	//mPuzzleView.setMode(PuzzleView.READY);
	        } 
	        else 
	        {
	            // We are being restored
	            Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
	            if (map != null) 
	            {
	            	mPuzzleView.restoreState(map);
	            } 
	            else 
	            {
	            	//mPuzzleView.setMode(PuzzleView.PAUSE);
	            }
	        }
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
	        outState.putBundle(ICICLE_KEY, mPuzzleView.saveState());
	    }

		public boolean onTouch(View v, MotionEvent event) 
		{
			return mPuzzleView.onTouch(v, event);
		}
	}
