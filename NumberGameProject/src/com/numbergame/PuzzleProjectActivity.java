package com.numbergame;

import com.numbergame.R;
import com.numbergame.PuzzleView;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PuzzleProjectActivity extends Activity implements View.OnTouchListener, View.OnClickListener
{
    private final Puzzle mPuzzle = new Puzzle();
    private PuzzleView mPuzzleView;
    
    private Button button1;
    private Button button2;
    
    private static String PUZZLE_PREFS = "puzzle-prefs";
    private static String ICICLE_KEY = "puzzle-view";

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) 
        {
            // We were just launched -- set up a new game
        	SharedPreferences settings = getSharedPreferences(PUZZLE_PREFS, 0);
        	mPuzzle.restoreState(settings);
        } 
        else 
        {
            // We are being restored
            Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
            if (map != null) 
            {
            	mPuzzle.restoreState(map);
            } 
            else 
            {
            	//mPuzzleView.setMode(PuzzleView.PAUSE);
            }
        }

        setContentView(R.layout.puzzle);
        mPuzzleView = (PuzzleView) findViewById(R.id.puzzle);
        mPuzzleView.setTextView((TextView) findViewById(R.id.score));
        mPuzzleView.setPuzzleControl(mPuzzle);
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
		
    }
    
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		SharedPreferences settings = getSharedPreferences(PUZZLE_PREFS, 0);
		SharedPreferences.Editor editor = settings.edit();
		mPuzzle.saveState(editor);
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
    	Bundle map = new Bundle();
    	mPuzzle.saveState(map);
        outState.putBundle(ICICLE_KEY, map);
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