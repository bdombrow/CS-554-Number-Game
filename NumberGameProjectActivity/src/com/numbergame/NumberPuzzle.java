package com.numbergame;

import android.content.Intent;
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
		numRandomPuzzleMoves = 0;
		numPlayerPuzzleMoves = 0;
		switch (nXNumberBrickCount) {
		
		// 2x2 puzzle always has 5 random moves for scramble
		case 2:
			numRandomMoves = 5;
			break;
			
		// 3x3 puzzle only has 53-80 random moves for scramble
		case 3:
			numRandomMoves = 50 + (3 * nLevel);
			break;
			
		// 4x4 puzzle only has 79-115 random moves for scramble
		case 4:
			numRandomMoves = 75 + (4 * nLevel);
			break;
			
		// 5x5 puzzle only has 105-150 random moves for scramble
		case 5: default:
			numRandomMoves = 100 + (5 * nLevel);
			break;
		}

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
					playerPuzzleMoves[numPlayerPuzzleMoves] = 3;
					numPlayerPuzzleMoves++;
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
					playerPuzzleMoves[numPlayerPuzzleMoves] = 4;
					numPlayerPuzzleMoves++;
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
					playerPuzzleMoves[numPlayerPuzzleMoves] = 1;
					numPlayerPuzzleMoves++;
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
					playerPuzzleMoves[numPlayerPuzzleMoves] = 2;
					numPlayerPuzzleMoves++;
				}
				setNumberBrick(BLANK, i, y);
				nXNumberBlankBrick = x;
				nYNumberBlankBrick = y;
			}
		}

		return true;
	}

	protected static int numRandomMoves; 
	protected static int maxMoves = 1000;
	protected static int numRandomPuzzleMoves; 
	protected static int numPlayerPuzzleMoves;
	
	protected int[] randomMoves = new int[maxMoves];
	protected int[] randomPuzzleMoves = new int[maxMoves];
	protected int[] playerPuzzleMoves = new int[maxMoves];
	
	public int ScrambleNumberPuzzle() {
		if ((numRandomPuzzleMoves > 0) || (numPlayerPuzzleMoves > 0))
			return numRandomPuzzleMoves;

		// generate random moves of the blank space
		// left = 1
		// right = 2
		// up = 3
		// down = 4
		int low = 1;
		int high = 4;
		for (int i = 0; i < maxMoves; i++)
			randomMoves[i] = (int) (Math.random() * 100) % high + low;

		// calculate single number for the index of the blank space
		int BlankIndex = (nYNumberBlankBrick * nYNumberBrickCount) + nXNumberBlankBrick;

		// newIndex will be the new index of the blank space
		// and it is also the current index of the tile that will move
		int newIndex = 0;

		for (int i = 0; i < maxMoves; i++) {
			switch (randomMoves[i]) {
			// blank space left
			case 1:
				if (nXNumberBlankBrick != 0) // cannot move left from leftmost column
					newIndex = BlankIndex - 1;
				else
					randomMoves[i] = 5; // 5 is a dummy place holder
												// meaning no move possible
				break;

			// blank space right
			case 2:
				if (nXNumberBlankBrick != nXNumberBrickCount - 1) // cannot move right from
														// rightmost column
					newIndex = BlankIndex + 1;
				else
					randomMoves[i] = 5; // 5 is a dummy place holder
												// meaning no move possible
				break;

			// blank space up
			case 3:
				if (nYNumberBlankBrick != 0) // cannot move up from the topmost row
					newIndex = BlankIndex - nXNumberBrickCount;
				else
					randomMoves[i] = 5; // 5 is a dummy place holder
												// meaning no move possible
				break;

			// blank space down
			case 4:
				if (nYNumberBlankBrick != nYNumberBrickCount - 1) // cannot move down from
														// the bottom most row
					newIndex = BlankIndex + nXNumberBrickCount;
				else
					randomMoves[i] = 5; // 5 is a dummy place holder
												// meaning no move possible
				break;
			default:
				randomMoves[i] = 5; // 5 is a dummy place holder
				break;
			}

			if (randomMoves[i] != 5) {
				BlankIndex = newIndex; // set the new index of the blank space
				int x = newIndex % nXNumberBrickCount; // calculate x or column index
				int y = (newIndex - x) / nYNumberBrickCount; // calculate y or row
														// index
				ChangeNumberPuzzle(x, y);
				randomPuzzleMoves[numRandomPuzzleMoves] = randomMoves[i];
				numRandomPuzzleMoves++;
				numPlayerPuzzleMoves--;
				if (numRandomPuzzleMoves == numRandomMoves)
				{
					if ( nXNumberBrickCount == 2 )
					{
						int adjust = (int) (Math.random() * 100) % 4;
						for (int k=0; k<adjust; k++)
						{
							boolean singlestep = true;
							PlayBackMoves(randomPuzzleMoves, numRandomPuzzleMoves, singlestep);
							numRandomPuzzleMoves--;
							numPlayerPuzzleMoves--;
						}
						if (IsPuzzleSolved())
						{
							boolean singlestep = true;
							PlayBackMoves(randomPuzzleMoves, numRandomPuzzleMoves, singlestep);
							numRandomPuzzleMoves--;
							numPlayerPuzzleMoves--;
						}
					}
					return numRandomPuzzleMoves; 
				}
			}
		}
		return numRandomPuzzleMoves; 
	} 
	
	private void PlayBackMoves(int[] moves, int numMoves, boolean singleStep) {
		int BlankIndex = (nYNumberBlankBrick * nYNumberBrickCount) + nXNumberBlankBrick;
		int newIndex = 0;
		for (int i = (numMoves - 1); i >= 0; i--) // read the moves in reverse
													// order
		{
			switch (moves[i]) {
			// blank space left for the original move right
			case 2:
				newIndex = BlankIndex - 1;
				break;

			// blank space right for the original move left
			case 1:
				newIndex = BlankIndex + 1;
				break;

			// blank space up for the original move down
			case 4:
				newIndex = BlankIndex - nXNumberBrickCount;
				break;

			// blank space down for the original move up
			case 3:
				newIndex = BlankIndex + nXNumberBrickCount;
				break;

			// no move
			case 5:
				break;
			}

			if (moves[i] != 5) {
				BlankIndex = newIndex; // set the new index of the blank space
				int x = newIndex % nXNumberBrickCount; // calculate x or column index
				int y = (newIndex - x) / nYNumberBrickCount; // calculate y or row
														// index
				ChangeNumberPuzzle(x, y);
				if (singleStep)
					return;
			}
		}
		return;
	}

	

	
	public boolean AIUnScrambleNumberPuzzle()
	{
		boolean singlestep;
		score = 0;
		if (numPlayerPuzzleMoves > 0) {
			singlestep = false;
			PlayBackMoves(playerPuzzleMoves, numPlayerPuzzleMoves,singlestep);
			numPlayerPuzzleMoves = 0;
		}
		if (numRandomPuzzleMoves > 0)
		{
			singlestep = true;
			PlayBackMoves(randomPuzzleMoves, numRandomPuzzleMoves,singlestep);
			numRandomPuzzleMoves--;
			numPlayerPuzzleMoves = 0;
		}
		if (numRandomPuzzleMoves > 0)
			return true;
		else
			return false;
	}
	
	
	
	public void UnScrambleNumberPuzzle()
	{
		boolean singlestep = false;
		if (numPlayerPuzzleMoves > 0) 
			PlayBackMoves(playerPuzzleMoves, numPlayerPuzzleMoves,singlestep);
		if (numRandomPuzzleMoves > 0) 
			PlayBackMoves(randomPuzzleMoves, numRandomPuzzleMoves,singlestep);
		numPlayerPuzzleMoves = 0;
		numRandomPuzzleMoves = 0;
		score = 0;
		return;
	}

	public boolean IsPuzzleSolved()
	{
		int counter = 1;
		for (int y = 0; y < nYNumberBrickCount; y++) 
		{
			for (int x = 0; x < nXNumberBrickCount; x++) 
			{
				if (nNumberPuzzleGrid[x][y] != counter)
				{
					if (nNumberPuzzleGrid[x][y] == BLANK && (x==(nXNumberBrickCount-1))
						&& (y==(nYNumberBrickCount-1)))
						return true;
					else
						return false;
				}
				else
					counter++;
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
		map.putInt("mLevel2", nLevel);
		//map.putLong("mScore", Long.valueOf(mScore));
		for(i=0;i<nXNumberBrickCount;i=i+1)
		{
			for(j=0;j<nYNumberBrickCount;j=j+1)
			{
				map.putInt(Integer.toString(i)+"-"+Integer.toString(j), Integer.valueOf(nNumberPuzzleGrid[i][j]));
			}
		}

		map.putInt("numRandomMoves", numRandomMoves);
		map.putInt("numRandomPuzzleMoves", numRandomPuzzleMoves);
		map.putInt("numPlayerPuzzleMoves", numPlayerPuzzleMoves);
		for (i = 0; i < numRandomPuzzleMoves; i = i + 1) {
			map.putInt(("randomPuzzleMoves" + i), randomPuzzleMoves[i]);
		}
		for (i = 0; i < numPlayerPuzzleMoves; i = i + 1) {
			map.putInt(("playerPuzzleMoves" + i), playerPuzzleMoves[i]);
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
		nLevel = icicle.getInt("mLevel2");

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

		numRandomMoves = icicle.getInt("numRandomMoves");
		numRandomPuzzleMoves = icicle.getInt("numRandomPuzzleMoves");
		numPlayerPuzzleMoves = icicle.getInt("numPlayerPuzzleMoves");
		for (i = 0; i < numRandomPuzzleMoves; i = i + 1) {
			randomPuzzleMoves[i] = icicle.getInt(("randomPuzzleMoves" + i));
		}
		for (i = 0; i < numPlayerPuzzleMoves; i = i + 1) {
			playerPuzzleMoves[i] = icicle.getInt(("playerPuzzleMoves" + i));
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

		editor.putInt("numRandomMoves", numRandomMoves);
		editor.putInt("numRandomPuzzleMoves", numRandomPuzzleMoves);
		editor.putInt("numPlayerPuzzleMoves", numPlayerPuzzleMoves);
		for (i = 0; i < numRandomPuzzleMoves; i = i + 1) {
			editor.putInt(("randomPuzzleMoves" + i), randomPuzzleMoves[i]);
		}
		for (i = 0; i < numPlayerPuzzleMoves; i = i + 1) {
			editor.putInt(("playerPuzzleMoves" + i), playerPuzzleMoves[i]);
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
		nLevel = settings.getInt("mLevel2", 1);

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

		numRandomMoves = settings.getInt("numRandomMoves", 0);
		numRandomPuzzleMoves = settings.getInt("numRandomPuzzleMoves", 0);
		numPlayerPuzzleMoves = settings.getInt("numPlayerPuzzleMoves", 0);
		for (i = 0; i < numRandomPuzzleMoves; i = i + 1) {
			randomPuzzleMoves[i] = settings.getInt(("randomPuzzleMoves" + i), 5);
		}
		for (i = 0; i < numPlayerPuzzleMoves; i = i + 1) {
			playerPuzzleMoves[i] = settings.getInt(("playerPuzzleMoves" + i), 5);
		}

		if(neadNewPuzzle || mNumberOptionChanged)
		{
			nNumberPuzzleGrid = CreateNewNumberPuzzle(nXNumberBrickCount, nYNumberBrickCount);;
			mNumberOptionChanged = false;
		}

	}
}


