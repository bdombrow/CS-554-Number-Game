package com.numbergame;

import android.content.SharedPreferences;
import android.os.Bundle;


public class NumberPuzzle {


	private static final int BLANK = 25;

	private boolean mNumberOptionChanged;
	protected static int nLevel;

	protected static int nXNumberBrickCount;
	protected static int nYNumberBrickCount;

	protected static int nXNumberBlankBrick;
	protected static int nYNumberBlankBrick;

	private int[][] nNumberPuzzleGrid;
	private static int score = 0;

	public int[][] CreateNewNumberPuzzle(int xCount, int yCount)
	{
		nXNumberBrickCount = xCount;
		nYNumberBrickCount = yCount;

		nNumberPuzzleGrid = new int[nXNumberBrickCount][nYNumberBrickCount];

		clearNumberBricks();

		int counter = 1;

		for (int i = 0; i < nXNumberBrickCount; ++i) {
			for (int j = 0; j < nYNumberBrickCount; ++j) {
				setNumberBrick(counter++, j, i);
			}
		}

		nXNumberBlankBrick = nXNumberBrickCount - 1;
		nYNumberBlankBrick = nYNumberBrickCount - 1;


		setNumberBrick(BLANK, nXNumberBlankBrick, nYNumberBlankBrick);

		score = 0;

		return nNumberPuzzleGrid;
	}

	public void clearNumberBricks() 
	{
		for (int x = 0; x < nXNumberBrickCount; x++) 
		{
			for (int y = 0; y < nYNumberBrickCount; y++) 
			{
				setNumberBrick(2012, x, y);
			}
		}
	}

	public void setNumberBrick(int Numberbrickindex, int x, int y) 
	{
		nNumberPuzzleGrid[x][y] = Numberbrickindex;
	}

	public int getXBrickCount()
	{
		return nXNumberBrickCount;
	}

	public int getYBrickCount()
	{
		return nYNumberBrickCount;
	}

	public int[][] GetNumberPuzzle()
	{
		return nNumberPuzzleGrid;
	}

	public boolean ChangeNumberPuzzle(int x, int y)
	{
		int i;

		if((x == nXNumberBlankBrick) && (y != nYNumberBlankBrick))
		{
			if(y < nYNumberBlankBrick)
			{
				for(i=nYNumberBlankBrick; i>y; i=i-1)
				{
					nNumberPuzzleGrid[x][i] = nNumberPuzzleGrid[x][i-1];
				}
				setNumberBrick(BLANK, x, i);
				nXNumberBlankBrick = x;
				nYNumberBlankBrick = y;
			}
			else
			{
				for(i=nYNumberBlankBrick; i<y; i=i+1)
				{
					nNumberPuzzleGrid[x][i] = nNumberPuzzleGrid[x][i+1];
				}
				setNumberBrick(BLANK, x, i);
				nXNumberBlankBrick = x;
				nYNumberBlankBrick = y;
			}
		}
		else if((x != nXNumberBlankBrick) && (y == nYNumberBlankBrick))
		{
			if(x < nXNumberBlankBrick)
			{
				for(i=nXNumberBlankBrick; i>x; i=i-1)
				{
					nNumberPuzzleGrid[i][y] = nNumberPuzzleGrid[i-1][y];
				}
				setNumberBrick(BLANK, i, y);
				nXNumberBlankBrick = x;
				nYNumberBlankBrick = y;
			}
			else
			{
				for(i=nXNumberBlankBrick; i<x; i=i+1)
				{
					nNumberPuzzleGrid[i][y] = nNumberPuzzleGrid[i+1][y];
				}
				setNumberBrick(BLANK, i, y);
				nXNumberBlankBrick = x;
				nYNumberBlankBrick = y;
			}
		}

		return true;
	}


	public String getScore() {
		return Integer.toString(score);
	}

	public void submit() {
		++score;
	}

	public void saveState(Bundle map) 
	{
		int i,j;

		//map.putIntArray("mAppleList", coordArrayListToArray(mAppleList));
		map.putInt("nXNumberBrickCount", Integer.valueOf(nXNumberBrickCount));
		map.putInt("nYNumberBrickCount", Integer.valueOf(nYNumberBrickCount));
		map.putInt("nXNumberBlankBrick", Integer.valueOf(nXNumberBlankBrick));
		map.putInt("nYNumberBlankBrick", Integer.valueOf(nYNumberBlankBrick));
		map.putBoolean("mNumberOptionChanged", mNumberOptionChanged);
		map.putInt("nLevel2", nLevel);
		//map.putLong("mScore", Long.valueOf(mScore));
		for(i=0;i<nXNumberBrickCount;i=i+1)
		{
			for(j=0;j<nYNumberBrickCount;j=j+1)
			{
				map.putInt(Integer.toString(i)+"-"+Integer.toString(j), Integer.valueOf(nNumberPuzzleGrid[i][j]));
			}
		}

		return;
	}

	public void restoreState(Bundle icicle) 
	{
		int i,j;
		boolean neadNewPuzzle = false;

		nXNumberBrickCount = icicle.getInt("nXNumberBrickCount");
		nYNumberBrickCount = icicle.getInt("nYNumberBrickCount");
		nXNumberBlankBrick = icicle.getInt("nXNumberBlankBrick");
		nYNumberBlankBrick = icicle.getInt("nYNumberBlankBrick");
		mNumberOptionChanged = icicle.getBoolean("mNumberOptionChanged");
		nLevel = icicle.getInt("nLevel2");

		nNumberPuzzleGrid = new int[nXNumberBrickCount][nYNumberBrickCount];
		for(i=0;i<nXNumberBrickCount;++i)
		{
			for(j=0;j<nYNumberBrickCount;++j)
			{
				nNumberPuzzleGrid[i][j] = icicle.getInt(Integer.toString(i)+"-"+Integer.toString(j));
				if(nNumberPuzzleGrid[i][j] == 2012)
				{
					neadNewPuzzle = true;
				}
			}
		}

		if(neadNewPuzzle)
		{
			nNumberPuzzleGrid = CreateNewNumberPuzzle(nXNumberBrickCount, nYNumberBrickCount);
		}
	}

	public void saveState(SharedPreferences.Editor editor)
	{
		int i,j;

		//editor.putString("name", name);
		editor.putInt("nXNumberBrickCount", nXNumberBrickCount);
		editor.putInt("nYNumberBrickCount", nYNumberBrickCount);
		editor.putInt("nXNumberBlankBrick", nXNumberBlankBrick);
		editor.putInt("nYNumberBlankBrick", nYNumberBlankBrick);
		editor.putBoolean("mNumberOptionChanged", mNumberOptionChanged);
		for(i=0;i<nXNumberBrickCount;i=i+1)
		{
			for(j=0;j<nYNumberBrickCount;j=j+1)
			{
				editor.putInt("nNumberPuzzleGrid-"+Integer.toString(i)+"-"+Integer.toString(j),nNumberPuzzleGrid[i][j]);
			}
		}
		editor.commit();
	}

	public void restoreState(SharedPreferences settings)
	{
		int i,j;
		boolean neadNewPuzzle = false;

		nXNumberBrickCount = settings.getInt("mSize2", 2);
		nYNumberBrickCount = nXNumberBrickCount;
		nXNumberBlankBrick = settings.getInt("nXNumberBlankBrick", 0);
		nYNumberBlankBrick = settings.getInt("nYNumberBlankBrick", 0);
		mNumberOptionChanged = settings.getBoolean("mNumberOptionChanged", true);
		nLevel = settings.getInt("nLevel2", 1);

		nNumberPuzzleGrid = new int[nXNumberBrickCount][nYNumberBrickCount];
		for(i=0;i<nXNumberBrickCount;i=i+1)
		{
			for(j=0;j<nYNumberBrickCount;j=j+1)
			{
				nNumberPuzzleGrid[i][j] = settings.getInt("nNumberPuzzleGrid-"+Integer.toString(i)+"-"+Integer.toString(j),2012);
				if(nNumberPuzzleGrid[i][j] == 2012)
				{
					neadNewPuzzle = true;
				}
			}
		}

		if(neadNewPuzzle || mNumberOptionChanged)
		{
			nNumberPuzzleGrid = CreateNewNumberPuzzle(nXNumberBrickCount, nYNumberBrickCount);;
			mNumberOptionChanged = false;
		}

	}
}


