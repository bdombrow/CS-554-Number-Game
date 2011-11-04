package com.numbergame;

import com.numbergame.R;
    import android.app.Activity;
import android.content.SharedPreferences;
	import android.os.Bundle;
	import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
	
public class OptionActivity  extends Activity
	{
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
	        	//mNumberPuzzle.restoreState(settings);
	        } 
	        else 
	        {
	            // We are being restored
	            Bundle numbermap = savedInstanceState.getBundle(ICICLE_KEY);
	            if (numbermap != null) 
	            {
	            	//mNumberPuzzle.restoreState(numbermap);
	            } 
	            else 
	            {
	            	//mPuzzleView.setMode(PuzzleView.PAUSE);
	            }
	        }

	        setContentView(R.layout.option);
	        
	        Spinner levelsp1 = (Spinner) findViewById(R.id.levelspin1);
	        BaseAdapter levelba1 = new BaseAdapter() {

	        	public int getCount() {
	        	return 10;
	        	}

	        	public Object getItem(int position) {
	        	return null;
	        	}

	        	public long getItemId(int position) {
	        	return 0;
	        	}

				@Override
	        	public View getView(int position, View convertView, ViewGroup parent) {
	        	TextView tv = new TextView(OptionActivity.this);
	        	tv.setText("  level "+(position+1));
	        	//tv.setTextColor(0xfd8d8d);
	        	tv.setTextSize(24);
	        	return tv;
	        	}
	        	};
	        	levelsp1.setAdapter(levelba1);
	        	levelsp1.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
	        	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
	        	TextView tv = (TextView) view;
	        	}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
	        	});
	        	
		        Spinner levelsp2 = (Spinner) findViewById(R.id.levelspin2);
		        BaseAdapter levelba2 = new BaseAdapter() {

		        	public int getCount() {
		        	return 10;
		        	}

		        	public Object getItem(int position) {
		        	return null;
		        	}

		        	public long getItemId(int position) {
		        	return 0;
		        	}

					@Override
		        	public View getView(int position, View convertView, ViewGroup parent) {
		        	TextView tv = new TextView(OptionActivity.this);
		        	tv.setText("level "+position);
		        	tv.setTextSize(24);
		        	return tv;
		        	}
		        	};
		        	levelsp2.setAdapter(levelba2);
		        	levelsp2.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
		        	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		        	TextView tv = (TextView) view;
		        	}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
		        	});
		        	
		        Spinner sizesp2 = (Spinner) findViewById(R.id.sizespin2);
		        BaseAdapter sizeba2 = new BaseAdapter() {

		        	public int getCount() {
		        	return 4;
		        	}

		        	public Object getItem(int position) {
		        	return null;
		        	}

		        	public long getItemId(int position) {
		        	return 0;
		        	}

					@Override
		        	public View getView(int position, View convertView, ViewGroup parent) {
		        	TextView tv = new TextView(OptionActivity.this);
		        	tv.setText("position:"+position);
		        	//tv.setTextSize(24);
		        	return tv;
		        	}
		        	};
		        	sizesp2.setAdapter(sizeba2);
		        	sizesp2.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
		        	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		        	TextView tv = (TextView) view;
		        	}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
		        	});
	   }
	    
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			
			SharedPreferences settings = getSharedPreferences(PUZZLE_PREFS, 0);
			SharedPreferences.Editor editor = settings.edit();
			//mNumberPuzzle.saveState(editor);
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
	    	//mNumberPuzzle.saveState(map);
	        outState.putBundle(ICICLE_KEY, map);
	    }
	}



