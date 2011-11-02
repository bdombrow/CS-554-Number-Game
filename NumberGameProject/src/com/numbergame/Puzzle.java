package com.numbergame;

public class Puzzle {
	
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
	    
	    protected static int mXBrickCount;
	    protected static int mYBrickCount;
	    
	    protected static int mXBlankBrick;
	    protected static int mYBlankBrick;

	    private int[][] mPuzzleGrid;
	    
		public int[][] CreateNewPuzzle(int xCount, int yCount)
		{
			mXBrickCount = xCount;
			mYBrickCount = yCount;
			
			mPuzzleGrid = new int[mXBrickCount][mYBrickCount];
			
	        clearBricks();
	        
	        setBrick(NUM_0, 0, 0);
	        setBrick(NUM_1, 0, 1);
	        setBrick(NUM_2, 0, 2);
	        setBrick(NUM_3, 0, 3);
	        setBrick(NUM_4, 0, 4);
	        setBrick(NUM_5, 1, 0);
	        setBrick(NUM_6, 1, 1);
	        setBrick(NUM_7, 1, 2);
	        setBrick(NUM_8, 1, 3);
	        setBrick(NUM_9, 1, 4);
	        setBrick(OP_PL, 2, 0);
	        setBrick(OP_MN, 2, 1);
	        setBrick(OP_MT, 2, 2);
	        setBrick(OP_EQ, 2, 3);
	        setBrick(NUM_0, 2, 4);
	        setBrick(NUM_1, 3, 0);
	        setBrick(NUM_2, 3, 1);
	        setBrick(NUM_3, 3, 2);
	        setBrick(NUM_4, 3, 3);
	        setBrick(NUM_5, 3, 4);
	        setBrick(NUM_6, 4, 0);
	        setBrick(NUM_7, 4, 1);
	        setBrick(NUM_8, 4, 2);
	        setBrick(NUM_9, 4, 3);
	        setBrick(BLANK, 4, 4);
	        
	        mXBlankBrick = 4;
	        mYBlankBrick = 4;

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
	    			}
	    			setBrick(BLANK, i, y);
	    			mXBlankBrick = x;
	    			mYBlankBrick = y;
	    		}
	    	}
	    	
	    	return true;
	    }
	}


