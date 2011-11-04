package com.numbergame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;

public class NumberPuzzle {
	 
			
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
		    private static final int NUM_10= 10;//OP_PL = 10;
		    private static final int NUM_11 = 11;//OP_MN = 11;
		    private static final int NUM_12= 12;//OP_MT = 12;
		    private static final int NUM_13 = 13;//OP_EQ = 13;
		    private static final int BLANK = 14;
		    
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
		        
		        setNumberBrick(NUM_0, 0, 0);
		        setNumberBrick(NUM_1, 0, 1);
		        setNumberBrick(NUM_2, 0, 2);
		        setNumberBrick(NUM_3, 0, 3);
		        setNumberBrick(NUM_4, 0, 4);
		        setNumberBrick(NUM_5, 1, 0);
		        setNumberBrick(NUM_6, 1, 1);
		        setNumberBrick(NUM_7, 1, 2);
		        setNumberBrick(NUM_8, 1, 3);
		        setNumberBrick(NUM_9, 1, 4);
		       //for now 
		        setNumberBrick(NUM_10, 2, 0);// setBrick(OP_PL, 2, 0);
		        setNumberBrick(NUM_11, 2, 1);//setBrick(OP_MN, 2, 1);
		        setNumberBrick(NUM_12, 2, 2);///setBrick(OP_MT, 2, 2);
		        setNumberBrick(NUM_13, 2, 3);//setBrick(OP_EQ, 2, 3);
		        setNumberBrick(NUM_0, 2, 4);
		        setNumberBrick(NUM_1, 3, 0);
		        setNumberBrick(NUM_2, 3, 1);
		        setNumberBrick(NUM_3, 3, 2);
		        setNumberBrick(NUM_4, 3, 3);
		        setNumberBrick(NUM_5, 3, 4);
		        setNumberBrick(NUM_6, 4, 0);
		        setNumberBrick(NUM_7, 4, 1);
		        setNumberBrick(NUM_8, 4, 2);
		        setNumberBrick(NUM_9, 4, 3);
		        setNumberBrick(BLANK, 4, 4);
		        
		        mXNumberBlankBrick = 4;
		        mYNumberBlankBrick = 4;

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
		    	
		    	mNumberPuzzleGrid = new int[mXNumberBrickCount][mYNumberBrickCount];
				for(i=0;i<mXNumberBrickCount;i=i+1)
				{
					for(j=0;j<mYNumberBrickCount;j=j+1)
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
					mNumberPuzzleGrid = CreateNewNumberPuzzle(mXNumberBrickCount, mYNumberBrickCount);;
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
		    	
		    	mXNumberBrickCount = settings.getInt("mXNumberBrickCount", 5);
		    	mYNumberBrickCount = settings.getInt("mYNumberBrickCount", 5);
		    	mXNumberBlankBrick = settings.getInt("mXNumberBlankBrick", 4);
		    	mYNumberBlankBrick = settings.getInt("mYNumberBlankBrick", 4);
		    	
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


