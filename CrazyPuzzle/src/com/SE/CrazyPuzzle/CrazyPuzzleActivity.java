package com.SE.CrazyPuzzle;

import com.SE.CrazyPuzzle.R;
import com.SE.CrazyPuzzle.PuzzleView;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CrazyPuzzleActivity extends Activity implements View.OnTouchListener, View.OnClickListener
{
    private PuzzleView mPuzzleView;
    
    private Button button1;
    private Button button2;
    
    private static String ICICLE_KEY = "puzzle-view";

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        
        mPuzzleView = (PuzzleView) findViewById(R.id.puzzle);
        mPuzzleView.setTextView((TextView) findViewById(R.id.score));
        mPuzzleView.setOnTouchListener(this);
        
        /*
         * 
         * 28 October 2011 - FWS
         * Added the Scramble and Unscramble buttons
         * 
        */
		button1 = (Button)findViewById(R.id.scrambleButton);
		button1.setOnClickListener(this);

		button2 = (Button)findViewById(R.id.unscrambleButton);
		button2.setOnClickListener(this);
		

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

    /*
     * 
     * 28 October 2011 - FWS
     * Added the onClick() method
     * 
    */
	public void onClick(View v)
	{
		if (v == button1)
			mPuzzleView.Scramble();
		if (v == button2)
			mPuzzleView.UnScramble();
	}
}