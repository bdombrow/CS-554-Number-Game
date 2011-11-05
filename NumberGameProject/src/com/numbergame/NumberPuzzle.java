package com.numbergame;

import android.content.SharedPreferences;
import android.os.Bundle;


public class NumberPuzzle {

	private static final int NUM_1 = 1;
	private static final int NUM_2 = 2;
	private static final int NUM_3 = 3;
	private static final int NUM_4 = 4;
	private static final int NUM_5 = 5;
	private static final int NUM_6 = 6;
	private static final int NUM_7 = 7;
	private static final int NUM_8 = 8;
	private static final int NUM_9 = 9;
	private static final int NUM_10 = 10;
	private static final int NUM_11 = 11;
	private static final int NUM_12 = 12;
	private static final int NUM_13 = 13;
	private static final int NUM_14 = 14;
	private static final int NUM_15 = 15;
	private static final int NUM_16 = 16;
	private static final int NUM_17 = 17;
	private static final int NUM_18 = 18;
	private static final int NUM_19 = 19;
	private static final int NUM_20 = 20;
	private static final int NUM_21 = 21;
	private static final int NUM_22 = 22;
	private static final int NUM_23 = 23;
	private static final int NUM_24 = 24;

	private static final int BLANK = 25;

	protected static int mLevel;

	protected static int mXNumberBrickCount;
	protected static int mYNumberBrickCount;

	protected static int mXNumberBlankBrick;
	protected static int mYNumberBlankBrick;

	private int[][] mNumberPuzzleGrid;

	public int[][] CreateNewNumberPuzzle(int xCount, int yCount)
	{
		mXNumberBrickCount = xCount;
		mYNumberBrickCount = yCount;

		mNumberPuzzleGrid = new int[mXNumberBrickCount][mYNumberBrickCount];

		clearNumberBricks();

		setNumberBrick(NUM_1, 0, 0);
		setNumberBrick(NUM_2, 1, 0);
		setNumberBrick(NUM_3, 2, 0);
		setNumberBrick(NUM_4, 3, 0);
		setNumberBrick(NUM_5, 4, 0);
		setNumberBrick(NUM_6, 0, 1);
		setNumberBrick(NUM_7, 1, 1);
		setNumberBrick(NUM_8, 2, 1);
		setNumberBrick(NUM_9, 3, 1);
		setNumberBrick(NUM_10, 4, 1);
		setNumberBrick(NUM_11, 0, 2);
		setNumberBrick(NUM_12, 1, 2);
		setNumberBrick(NUM_13, 2, 2);
		setNumberBrick(NUM_14, 3, 2);
		setNumberBrick(NUM_15, 4, 2);
		setNumberBrick(NUM_16, 0, 3);
		setNumberBrick(NUM_17, 1, 3);
		setNumberBrick(NUM_18, 2, 3);
		setNumberBrick(NUM_19, 3, 3);
		setNumberBrick(NUM_20, 4, 3);
		setNumberBrick(NUM_21, 0, 4);
		setNumberBrick(NUM_22, 1, 4);
		setNumberBrick(NUM_23, 2, 4);
		setNumberBrick(NUM_24, 3, 4);
		//setNumberBrick(BLANK, 4, 4);

		mXNumberBlankBrick = mXNumberBrickCount - 1;
		mYNumberBlankBrick = mYNumberBrickCount - 1;
		
		
		setNumberBrick(BLANK, mXNumberBlankBrick, mYNumberBlankBrick);
		


		return mNumberPuzzleGrid;
	}

	public void clearNumberBricks() 
	{
		for (int x = 0; x < mXNumberBrickCount; x++) 
		{
			for (int y = 0; y < mYNumberBrickCount; y++) 
			{
				setNumberBrick(2012, x, y);
			}
		}
	}

	public void setNumberBrick(int Numberbrickindex, int x, int y) 
	{
		mNumberPuzzleGrid[x][y] = Numberbrickindex;
	}

	public int getXBrickCount()
	{
		return mXNumberBrickCount;
	}

	public int getYBrickCount()
	{
		return mYNumberBrickCount;
	}

	public int[][] GetNumberPuzzle()
	{
		return mNumberPuzzleGrid;
	}

	public boolean ChangeNumberPuzzle(int x, int y)
	{
		int i;

		if((x == mXNumberBlankBrick) && (y != mYNumberBlankBrick))
		{
			if(y < mYNumberBlankBrick)
			{
				for(i=mYNumberBlankBrick; i>y; i=i-1)
				{
					mNumberPuzzleGrid[x][i] = mNumberPuzzleGrid[x][i-1];
				}
				setNumberBrick(BLANK, x, i);
				mXNumberBlankBrick = x;
				mYNumberBlankBrick = y;
			}
			else
			{
				for(i=mYNumberBlankBrick; i<y; i=i+1)
				{
					mNumberPuzzleGrid[x][i] = mNumberPuzzleGrid[x][i+1];
				}
				setNumberBrick(BLANK, x, i);
				mXNumberBlankBrick = x;
				mYNumberBlankBrick = y;
			}
		}
		else if((x != mXNumberBlankBrick) && (y == mYNumberBlankBrick))
		{
			if(x < mXNumberBlankBrick)
			{
				for(i=mXNumberBlankBrick; i>x; i=i-1)
				{
					mNumberPuzzleGrid[i][y] = mNumberPuzzleGrid[i-1][y];
				}
				setNumberBrick(BLANK, i, y);
				mXNumberBlankBrick = x;
				mYNumberBlankBrick = y;
			}
			else
			{
				for(i=mXNumberBlankBrick; i<x; i=i+1)
				{
					mNumberPuzzleGrid[i][y] = mNumberPuzzleGrid[i+1][y];
				}
				setNumberBrick(BLANK, i, y);
				mXNumberBlankBrick = x;
				mYNumberBlankBrick = y;
			}
		}

		return true;
	}


	public void saveState(Bundle map) 
	{
		int i,j;

		//map.putIntArray("mAppleList", coordArrayListToArray(mAppleList));
		map.putInt("mXNumberBrickCount", Integer.valueOf(mXNumberBrickCount));
		map.putInt("mYNumberBrickCount", Integer.valueOf(mYNumberBrickCount));
		map.putInt("mXNumberBlankBrick", Integer.valueOf(mXNumberBlankBrick));
		map.putInt("mYNumberBlankBrick", Integer.valueOf(mYNumberBlankBrick));
		map.putInt("mLevel2", mLevel);
		//map.putLong("mScore", Long.valueOf(mScore));
		for(i=0;i<mXNumberBrickCount;i=i+1)
		{
			for(j=0;j<mYNumberBrickCount;j=j+1)
			{
				map.putInt(Integer.toString(i)+"-"+Integer.toString(j), Integer.valueOf(mNumberPuzzleGrid[i][j]));
			}
		}

		return;
	}

	public void restoreState(Bundle icicle) 
	{
		int i,j;
		boolean neadNewPuzzle = false;

		mXNumberBrickCount = icicle.getInt("mXNumberBrickCount");
		mYNumberBrickCount = icicle.getInt("mYNumberBrickCount");
		mXNumberBlankBrick = icicle.getInt("mXNumberBlankBrick");
		mYNumberBlankBrick = icicle.getInt("mYNumberBlankBrick");
		mLevel = icicle.getInt("mLevel2");

		mNumberPuzzleGrid = new int[mXNumberBrickCount][mYNumberBrickCount];
		for(i=0;i<mXNumberBrickCount;++i)
		{
			for(j=0;j<mYNumberBrickCount;++j)
			{
				mNumberPuzzleGrid[i][j] = icicle.getInt(Integer.toString(i)+"-"+Integer.toString(j));
				if(mNumberPuzzleGrid[i][j] == 2012)
				{
					neadNewPuzzle = true;
				}
			}
		}

		if(neadNewPuzzle)
		{
			mNumberPuzzleGrid = CreateNewNumberPuzzle(mXNumberBrickCount, mYNumberBrickCount);
		}
	}

	public void saveState(SharedPreferences.Editor editor)
	{
		int i,j;

		//editor.putString("name", name);
		editor.putInt("mXNumberBrickCount", mXNumberBrickCount);
		editor.putInt("mYNumberBrickCount", mYNumberBrickCount);
		editor.putInt("mXNumberBlankBrick", mXNumberBlankBrick);
		editor.putInt("mYNumberBlankBrick", mYNumberBlankBrick);
		for(i=0;i<mXNumberBrickCount;i=i+1)
		{
			for(j=0;j<mYNumberBrickCount;j=j+1)
			{
				editor.putInt(Integer.toString(i)+"-"+Integer.toString(j),mNumberPuzzleGrid[i][j]);
			}
		}
		editor.commit();
	}

	public void restoreState(SharedPreferences settings)
	{
		int i,j;
		boolean neadNewPuzzle = false;

		mXNumberBrickCount = settings.getInt("mSize2", 2);
		mYNumberBrickCount = mXNumberBrickCount;
		mXNumberBlankBrick = settings.getInt("mXNumberBlankBrick", 0);
		mYNumberBlankBrick = settings.getInt("mYNumberBlankBrick", 0);
		mLevel = settings.getInt("mLevel2", 1);

		mNumberPuzzleGrid = new int[mXNumberBrickCount][mYNumberBrickCount];
		for(i=0;i<mXNumberBrickCount;i=i+1)
		{
			for(j=0;j<mYNumberBrickCount;j=j+1)
			{
				mNumberPuzzleGrid[i][j] = settings.getInt(Integer.toString(i)+"-"+Integer.toString(j),2012);
				if(mNumberPuzzleGrid[i][j] == 2012)
				{
					neadNewPuzzle = true;
				}
			}
		}

		if(neadNewPuzzle)
		{
			mNumberPuzzleGrid = CreateNewNumberPuzzle(mXNumberBrickCount, mYNumberBrickCount);;
		}

	}
}


