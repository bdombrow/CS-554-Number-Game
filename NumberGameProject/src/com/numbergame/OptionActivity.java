/**Open-Android-CrazyPuzzle Copyright © 2011 
@author "Brent Dombrowski", 
@author "Hema Kumar",
@author "Frank Sliz"
@author "Derek Qian"
//** This file is part of Crazy puzzle.This is free software: you can redistribute it 
 * and/or modify it under the terms of the GNU General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or any later version.
 * Crazy Puzzle is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty ofMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See theGNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License along with Crazy Puzzle. 
 *  If not, see <http://www.gnu.org/licenses/>.For feedback please mail at either of the below mentioned email id
 *  bdombrow@cecs.pdx.edu /hemasid@gmail.com / fwsliz@gmail.com / electronseu@gmail.com
 *                             
 **/

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

public class OptionActivity extends Activity {
	private boolean mPuzzleOptionChanged;
	private boolean mNumberOptionChanged;
	private int mLevel1;
	private int mLevel2;
	private int mSize2;

	private static String PUZZLE_PREFS = "puzzle-prefs";
	private static String ICICLE_KEY = "option-view";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			// We were just launched -- set up a new game
			SharedPreferences settings = getSharedPreferences(PUZZLE_PREFS, 0);
			mPuzzleOptionChanged = settings.getBoolean("mPuzzleOptionChanged",
					true);
			mNumberOptionChanged = settings.getBoolean("mNumberOptionChanged",
					true);
			mLevel1 = settings.getInt("mLevel1", 1);
			mLevel2 = settings.getInt("mLevel2", 1);
			mSize2 = settings.getInt("mSize2", 2);
		} else {
			// We are being restored
			Bundle numbermap = savedInstanceState.getBundle(ICICLE_KEY);
			if (numbermap != null) {
				mPuzzleOptionChanged = numbermap
						.getBoolean("mPuzzleOptionChanged");
				mNumberOptionChanged = numbermap
						.getBoolean("mNumberOptionChanged");
				mLevel1 = numbermap.getInt("mLevel1");
				mLevel2 = numbermap.getInt("mLevel2");
				mSize2 = numbermap.getInt("mSize2");
			} else {
				// mPuzzleView.setMode(PuzzleView.PAUSE);
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

			public View getView(int position, View convertView, ViewGroup parent) {
				TextView tv = new TextView(OptionActivity.this);
				tv.setText("  level " + (position + 1));
				tv.setTextColor(0xff000000);
				tv.setTextSize(24);
				return tv;
			}
		};
		levelsp1.setAdapter(levelba1);
		levelsp1.setPrompt("select level");
		levelsp1.setSelection(mLevel1 - 1, true);
		levelsp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TextView tv = (TextView) view;
				mLevel1 = position + 1;
				mPuzzleOptionChanged = true;
			}

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

			public View getView(int position, View convertView, ViewGroup parent) {
				TextView tv = new TextView(OptionActivity.this);
				tv.setText("  level " + (position + 1));
				tv.setTextColor(0xff000000);
				tv.setTextSize(24);
				return tv;
			}
		};
		levelsp2.setAdapter(levelba2);
		levelsp2.setPrompt("select level");
		levelsp2.setSelection(mLevel2 - 1, true);
		levelsp2.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TextView tv = (TextView) view;
				mLevel2 = position + 1;
				mNumberOptionChanged = true;
			}

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

			public View getView(int position, View convertView, ViewGroup parent) {
				TextView tv = new TextView(OptionActivity.this);
				tv.setText("  size: " + (position + 2) + "x" + (position + 2));
				tv.setTextColor(0xff000000);
				tv.setTextSize(24);
				return tv;
			}
		};
		sizesp2.setAdapter(sizeba2);
		sizesp2.setPrompt("select size");
		sizesp2.setSelection(mSize2 - 2, true);
		sizesp2.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				TextView tv = (TextView) view;
				mSize2 = position + 2;
				mNumberOptionChanged = true;
			}

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
		editor.putBoolean("mPuzzleOptionChanged", mPuzzleOptionChanged);
		editor.putBoolean("mNumberOptionChanged", mNumberOptionChanged);
		editor.putInt("mLevel1", mLevel1);
		editor.putInt("mLevel2", mLevel2);
		editor.putInt("mSize2", mSize2);
		editor.commit();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Pause the game along with the activity
		// mPuzzleView.setMode(PuzzleView.PAUSE);
	}

	// @Override
	/*
	 * public boolean onTouchEvent(MotionEvent event) {
	 * super.onTouchEvent(event); return mPuzzleView.onTouch(, event);
	 * 
	 * }
	 */

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Store the game state
		Bundle map = new Bundle();
		map.putBoolean("mPuzzleOptionChanged",
				Boolean.valueOf(mPuzzleOptionChanged));
		map.putBoolean("mNumberOptionChanged",
				Boolean.valueOf(mNumberOptionChanged));
		map.putInt("mLevel1", Integer.valueOf(mLevel1));
		map.putInt("mLevel2", Integer.valueOf(mLevel2));
		map.putInt("mSize2", Integer.valueOf(mSize2));
		outState.putBundle(ICICLE_KEY, map);
	}
}
