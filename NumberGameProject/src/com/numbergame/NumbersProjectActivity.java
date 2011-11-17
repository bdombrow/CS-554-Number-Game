package com.numbergame;

import com.numbergame.R;
import com.numbergame.NumberPuzzleView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NumbersProjectActivity extends Activity implements
		View.OnTouchListener, View.OnClickListener {
	private final NumberPuzzle mNumberPuzzle = new NumberPuzzle();
	private NumberPuzzleView mNumberPuzzleView;

	private Button button1;
	private Button button2;
	private Button button3;

	private static String PUZZLE_PREFS = "puzzle-prefs";
	private static String ICICLE_KEY = "puzzle-view";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			// We were just launched -- set up a new game
			SharedPreferences settings = getSharedPreferences(PUZZLE_PREFS, 0);
			mNumberPuzzle.restoreState(settings);
		} else {
			// We are being restored
			Bundle numbermap = savedInstanceState.getBundle(ICICLE_KEY);
			if (numbermap != null) {
				mNumberPuzzle.restoreState(numbermap);
			} else {
				// mPuzzleView.setMode(PuzzleView.PAUSE);
			}
		}

		setContentView(R.layout.numberpuzzle);
		mNumberPuzzleView = (NumberPuzzleView) findViewById(R.id.numberpuzzle);
		mNumberPuzzleView.setNumberPuzzleControl(mNumberPuzzle);
		mNumberPuzzleView.setTextView((TextView) findViewById(R.id.score));

		mNumberPuzzleView.setOnTouchListener(this);

		button1 = (Button) findViewById(R.id.scrambleButton);
		button1.setOnClickListener(this);

		button2 = (Button) findViewById(R.id.unscrambleButton);
		button2.setOnClickListener(this);

		button3 = (Button) findViewById(R.id.wizardButton);
		button3.setOnClickListener(this);

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
	protected void onPause() {
		super.onPause();
		// Pause the game along with the activity
		// mPuzzleView.setMode(PuzzleView.PAUSE);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

		// Store the game state
		Bundle map = new Bundle();
		mNumberPuzzle.saveState(map);
		outState.putBundle(ICICLE_KEY, map);
	}

	public boolean onTouch(View v, MotionEvent event) {
		return mNumberPuzzleView.onTouch(v, event);
	}

	public void onClick(View v) {
		if (v == button1)
			mNumberPuzzleView.Scramble();
		else if (v == button2)
			mNumberPuzzleView.UnScramble();
		else if (v == button3)
			mNumberPuzzleView.AIUnScramble();

	}

}
