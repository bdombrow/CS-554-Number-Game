package com.numbergame;

import android.content.SharedPreferences;
import android.os.Bundle;

public class Puzzle 
{
    private static final int NUM_0 = 0;
    private static final int NUM_1 = 1;
    private static final int NUM_2 = 2;
    private static final int NUM_3 = 3;
    private static final int NUM_4 = 4;
    private static final int NUM_5 = 5;
    private static final int NUM_6 = 6;
    private static final int NUM_7 = 7;
    private static final int NUM_8 = 8;
    private static final int NUM_9 = 9;
    private static final int OP_PL = 10;
    private static final int OP_MN = 11;
    private static final int OP_MT = 12;
    private static final int OP_EQ = 13;
    private static final int BLANK = 14;
    
    protected static int mLevel;
    
    protected int mXBrickCount;
    protected int mYBrickCount;
    
    protected static int mXBlankBrick;
    protected static int mYBlankBrick;

    private int[][] mPuzzleGrid;
    
	public int[][] CreateNewPuzzle(int xCount, int yCount)
	{
		mXBrickCount = xCount;
		mYBrickCount = yCount;
		
		mPuzzleGrid = new int[mXBrickCount][mYBrickCount];
		
        clearBricks();
        
        setBrick(NUM_4, 0, 0);
        setBrick(OP_MT, 0, 1);
        setBrick(NUM_2, 0, 2);
        setBrick(OP_EQ, 0, 3);
        setBrick(NUM_8, 0, 4);
        setBrick(OP_PL, 1, 0);
        setBrick(NUM_7, 1, 1);
        setBrick(OP_PL, 1, 2);
        setBrick(NUM_6, 1, 3);
        setBrick(OP_MN, 1, 4);
        setBrick(NUM_5, 2, 0);
        setBrick(OP_MN, 2, 1);
        setBrick(NUM_2, 2, 2);
        setBrick(OP_EQ, 2, 3);
        setBrick(NUM_3, 2, 4);
        setBrick(OP_EQ, 3, 0);
        setBrick(NUM_1, 3, 1);
        setBrick(OP_EQ, 3, 2);
        setBrick(BLANK, 3, 3);
        setBrick(OP_EQ, 3, 4);
        setBrick(NUM_9, 4, 0);
        setBrick(OP_MN, 4, 1);
        setBrick(NUM_4, 4, 2);
        setBrick(OP_EQ, 4, 3);
        setBrick(NUM_5, 4, 4);
        
        mXBlankBrick = 3;
        mYBlankBrick = 3;
        
        numScrambles = 0;
        numRandomMoves = 0;
        numPlayerMoves = 0;

        return mPuzzleGrid;
	}
    
    public void clearBricks() 
    {
        for (int x = 0; x < mXBrickCount; x++) 
        {
            for (int y = 0; y < mYBrickCount; y++) 
            {
                setBrick(2012, x, y);
            }
        }
    }
    
    public void setBrick(int brickindex, int x, int y) 
    {
    	mPuzzleGrid[x][y] = brickindex;
    }
    
    public int getXBrickCount()
    {
    	return mXBrickCount;
    }
    
    public int getYBrickCount()
    {
    	return mYBrickCount;
    }
    
    public int[][] GetPuzzle()
    {
    	return mPuzzleGrid;
    }
    
    public boolean ChangePuzzle(int x, int y)
    {
    	int i;
    	if((x == mXBlankBrick) && (y != mYBlankBrick))
    	{
    		if(y < mYBlankBrick)
    		{
    			for(i=mYBlankBrick; i>y; i=i-1)
    			{
    				mPuzzleGrid[x][i] = mPuzzleGrid[x][i-1];
    				PlayerMoves[numPlayerMoves] = 3;
    				numPlayerMoves++;
    			}
    			setBrick(BLANK, x, i);
    			mXBlankBrick = x;
    			mYBlankBrick = y;
    		}
    		else
    		{
    			for(i=mYBlankBrick; i<y; i=i+1)
    			{
    				mPuzzleGrid[x][i] = mPuzzleGrid[x][i+1];
    				PlayerMoves[numPlayerMoves] = 4;
    				numPlayerMoves++;
    			}
    			setBrick(BLANK, x, i);
    			mXBlankBrick = x;
    			mYBlankBrick = y;
    		}
    	}
    	else if((x != mXBlankBrick) && (y == mYBlankBrick))
    	{
    		if(x < mXBlankBrick)
    		{
    			for(i=mXBlankBrick; i>x; i=i-1)
    			{
    				mPuzzleGrid[i][y] = mPuzzleGrid[i-1][y];
    				PlayerMoves[numPlayerMoves] = 1;
    				numPlayerMoves++;
   			}
    			setBrick(BLANK, i, y);
    			mXBlankBrick = x;
    			mYBlankBrick = y;
    		}
    		else
    		{
    			for(i=mXBlankBrick; i<x; i=i+1)
    			{
    				mPuzzleGrid[i][y] = mPuzzleGrid[i+1][y];
    				PlayerMoves[numPlayerMoves] = 2;
    				numPlayerMoves++;
    			}
    			setBrick(BLANK, i, y);
    			mXBlankBrick = x;
    			mYBlankBrick = y;
    		}
    	}
    	
    	return true;
    }
    
    /*
     * 
     * 28 October 2011 - FWS
     * Added the ScramblePuzzle() and UnScramblePuzzle() methods
     * 
    */
    //protected static boolean playerMovedTile = true;  // player moves are not tracked yet 
    protected static int maxScrambles = 10;  // only 10 scrambles allowed 
    protected static int numScrambleMoves = 100;  // 100 random moves per scramble    
    protected static int maxPlayerMoves = 1000;  // 1000 player moves before unscramble required
    protected static int maxRandomMoves = maxScrambles * numScrambleMoves;
    protected static int[] RandomMoves = new int[maxRandomMoves];
    protected static int[] PlayerMoves = new int[maxPlayerMoves];
    
    protected static int numScrambles; 
    protected static int numRandomMoves; 
    protected static int numPlayerMoves; 

    public void ScramblePuzzle()
    {
    	if ( (numRandomMoves >= maxRandomMoves) || (numPlayerMoves > 0) )
    		return;
		   
	    // generate random moves of the blank space 
	    //   left  = 1
	    //   right = 2
	    //   up    = 3
	    //   down  = 4
	    int low = 1;
	    int high = 4;
	    int firstIndex = numScrambles * numScrambleMoves;
	    int lastIndex = firstIndex + numScrambleMoves;
	    for (int i=firstIndex; i<lastIndex; i++)
	    	RandomMoves[i] = (int)(Math.random() * 100) % high + low;
	    
	    // calculate single number for the index of the blank space
	    int BlankIndex =  (mYBlankBrick * mYBrickCount) + mXBlankBrick;
	    
	    // newIndex will be the new index of the blank space
	    // and it is also the current index of the tile that will move
	    int newIndex = 0;
	   
	    for (int i=firstIndex; i<lastIndex; i++)
	    {
	    	switch (RandomMoves[i])
	    	{
	        	// blank space left
	        	case 1:
	        		if (mXBlankBrick != 0) // cannot move left from leftmost column
	        			newIndex = BlankIndex - 1;
	        		else
	        			RandomMoves[i] = 5;  // 5 is a dummy place holder meaning no move possible
	        		break;

	        	// blank space right
	        	case 2:
	        		if (mXBlankBrick != mXBrickCount-1)  // cannot move right from rightmost column
	        			newIndex = BlankIndex + 1;
	        		else
	        			RandomMoves[i] = 5;  // 5 is a dummy place holder meaning no move possible
	        		break;
	          
	        	// blank space up
	        	case 3:
	        		if (mYBlankBrick != 0)  // cannot move up from the topmost row
	        			newIndex = BlankIndex - mXBrickCount;
	        		else
	        			RandomMoves[i] = 5;  // 5 is a dummy place holder meaning no move possible
	        		break;
	          
	        	// blank space down
	        	case 4:
	        		if (mYBlankBrick != mYBrickCount-1)  // cannot move down from the bottom most row
	        			newIndex = BlankIndex + mXBrickCount;
	        		else
	        			RandomMoves[i] = 5;  // 5 is a dummy place holder meaning no move possible
	        		break;
	    	}
	      
	    	if (RandomMoves[i] != 5)
	    	{
	    		BlankIndex = newIndex;  // set the new index of the blank space
	    	  	int x = newIndex % mXBrickCount;  // calculate x or column index
	    	  	int y = (newIndex - x)/ mYBrickCount;  // calculate y or row index
				ChangePuzzle(x,y);
				numPlayerMoves--;
	    	}
	    }
	    numScrambles++;
	    numRandomMoves += numScrambleMoves;
	    return;
	}
	    
    
    public void UnScramblePuzzle()
    {
		if (numPlayerMoves > 0)
		{
	    	PlayBackMoves(PlayerMoves, numPlayerMoves);
	    	numPlayerMoves = 0;
		}
    	if (numRandomMoves > 0)
    	{
    		PlayBackMoves(RandomMoves, numRandomMoves);
    		numScrambles = 0;
    		numRandomMoves = 0;
    	}
		return;
    }
    	
    private void PlayBackMoves(int[] moves, int numMoves)
    {
	    int BlankIndex =  (mYBlankBrick * mYBrickCount) + mXBlankBrick;
	    int newIndex = 0;
        for (int i=(numMoves-1); i>=0; i--)  // read the moves in reverse order
        {
        	switch (moves[i])
        	{
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
            		newIndex = BlankIndex - mXBrickCount;
            		break;
            		
            	// blank space down for the original move up
            	case 3:
            		newIndex = BlankIndex + mXBrickCount;
            		break;
            		
            	// no move
            	case 5:
            		break;
        	}
        	
	    	if (moves[i] != 5)
	    	{
	    		BlankIndex = newIndex;  // set the new index of the blank space
	    	  	int x = newIndex % mXBrickCount;  // calculate x or column index
	    	  	int y = (newIndex - x)/ mYBrickCount;  // calculate y or row index
				ChangePuzzle(x,y);
				numPlayerMoves--;
	    	}
        }
        return;
    }
    
    public void saveState(Bundle map) 
    {
    	int i,j;

        //map.putIntArray("mAppleList", coordArrayListToArray(mAppleList));
        map.putInt("mXBrickCount", Integer.valueOf(mXBrickCount));
        map.putInt("mYBrickCount", Integer.valueOf(mYBrickCount));
        map.putInt("mXBlankBrick", Integer.valueOf(mXBlankBrick));
        map.putInt("mYBlankBrick", Integer.valueOf(mYBlankBrick));
        map.putInt("mLevel1", mLevel);
        //map.putLong("mScore", Long.valueOf(mScore));
		for(i=0;i<mXBrickCount;i=i+1)
		{
			for(j=0;j<mYBrickCount;j=j+1)
			{
				map.putInt(Integer.toString(i)+"-"+Integer.toString(j), Integer.valueOf(mPuzzleGrid[i][j]));
			}
		}

        return;
    }
    
    public void restoreState(Bundle icicle) 
    {
    	int i,j;
    	boolean neadNewPuzzle = false;
    	
    	mXBrickCount = icicle.getInt("mXBrickCount");
    	mYBrickCount = icicle.getInt("mYBrickCount");
    	mXBlankBrick = icicle.getInt("mXBlankBrick");
    	mYBlankBrick = icicle.getInt("mYBlankBrick");
    	mLevel = icicle.getInt("mLevel1");
    	
		mPuzzleGrid = new int[mXBrickCount][mYBrickCount];
		for(i=0;i<mXBrickCount;i=i+1)
		{
			for(j=0;j<mYBrickCount;j=j+1)
			{
				mPuzzleGrid[i][j] = icicle.getInt(Integer.toString(i)+"-"+Integer.toString(j));
				if(mPuzzleGrid[i][j] == 2012)
				{
					neadNewPuzzle = true;
				}
			}
		}
		
		if(neadNewPuzzle)
		{
			mPuzzleGrid = CreateNewPuzzle(mXBrickCount, mYBrickCount);;
		}
    }
    
    public void saveState(SharedPreferences.Editor editor)
    {
    	int i,j;

    	//editor.putString("name", name);
    	editor.putInt("mXBrickCount", mXBrickCount);
    	editor.putInt("mYBrickCount", mYBrickCount);
    	editor.putInt("mXBlankBrick", mXBlankBrick);
    	editor.putInt("mYBlankBrick", mYBlankBrick);
		for(i=0;i<mXBrickCount;i=i+1)
		{
			for(j=0;j<mYBrickCount;j=j+1)
			{
				editor.putInt(Integer.toString(i)+"-"+Integer.toString(j),mPuzzleGrid[i][j]);
			}
		}
    	editor.commit();
    }
    
    public void restoreState(SharedPreferences settings)
    {
    	int i,j;
    	boolean neadNewPuzzle = false;
    	
    	mXBrickCount = settings.getInt("mXBrickCount", 5);
    	mYBrickCount = settings.getInt("mYBrickCount", 5);
    	mXBlankBrick = settings.getInt("mXBlankBrick", 3);
    	mYBlankBrick = settings.getInt("mYBlankBrick", 3);
		mLevel = settings.getInt("mLevel1", 1);
    	
		mPuzzleGrid = new int[mXBrickCount][mYBrickCount];
		for(i=0;i<mXBrickCount;i=i+1)
		{
			for(j=0;j<mYBrickCount;j=j+1)
			{
				mPuzzleGrid[i][j] = settings.getInt(Integer.toString(i)+"-"+Integer.toString(j),2012);
				if(mPuzzleGrid[i][j] == 2012)
				{
					neadNewPuzzle = true;
				}
			}
		}
		
		if(neadNewPuzzle)
		{
			mPuzzleGrid = CreateNewPuzzle(mXBrickCount, mYBrickCount);;
		}

    }

}
