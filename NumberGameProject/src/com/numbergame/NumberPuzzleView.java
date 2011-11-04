package com.numbergame;
import com.numbergame.R;

	import android.content.Context;
	import android.content.res.Resources;
	import android.graphics.Bitmap;
	import android.graphics.Canvas;
	import android.graphics.Paint;
	import android.graphics.drawable.Drawable;
	import android.os.Bundle; 
	import android.util.AttributeSet;
	import android.view.KeyEvent;
	import android.view.MotionEvent;
	import android.view.View;
	import android.widget.TextView;
import android.widget.Toast;
	
public class NumberPuzzleView extends View
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
		    //for now
		    private static final int NUM_10 =10;
		    private static final int NUM_11 =11;
		    private static final int NUM_12 =12;
		    private static final int NUM_13 =13;
		  		   // private static final int OP_PL = 10;
		    //private static final int OP_MN = 11;
		    //private static final int OP_MT = 12;
		    //private static final int OP_EQ = 13;
		    private static final int BLANK = 14;
		    private static final int NUM_0_D = 15;
		    private static final int NUM_1_D = 16;
		    private static final int NUM_2_D = 17;
		    private static final int NUM_3_D = 18;
		    private static final int NUM_4_D = 19;
		    private static final int NUM_5_D = 20;
		    private static final int NUM_6_D = 21;
		    private static final int NUM_7_D = 22;
		    private static final int NUM_8_D = 23;
		    private static final int NUM_9_D = 24;
		    //for now
		    private static final int NUM_10_D =25;
		    private static final int NUM_11_D =26;
		    private static final int NUM_12_D =27;
		    private static final int NUM_13_D =28;
		    //private static final int OP_PL_D = 25;
		    //private static final int OP_MN_D = 26;
		    //private static final int OP_MT_D = 27;
		    //private static final int OP_EQ_D = 28;

		    private TextView mStatusText;
		    
		    protected static int mNumberBrickSize;

		    protected static int mXNumberBrickCount;
		    protected static int mYNumberBrickCount;

		    private static int mXNumberOffset;
		    private static int mYNumberOffset;

		    private Bitmap[] mNumberBrickArray; 

		    private int[][] mNumberBrickGrid;

		    private final Paint mNumberPaint = new Paint();
		    
		    private NumberPuzzle mNumberPuzzle;
		    
		    // Movement tracking
			private float upX = 0.0f;
			private float upY = 0.0f;
			private float downX = 0.0f;
			private float downY = 0.0f;
			
			// Math game
			private MathGame currentNumberGame = new MathGame();
			
		    public NumberPuzzleView(Context context, AttributeSet attrs) 
			{
				super(context, attrs);
				initNumberPuzzleView();
			}
			
			public NumberPuzzleView(Context context, AttributeSet attrs, int defStyle)
			{
				super(context, attrs, defStyle);
				initNumberPuzzleView();
			}
			
		    public void setTextView(TextView newView) 
		    {
		        mStatusText = newView;
		        
		        // Set the StatusText to the current score.
		        mStatusText.setText(currentNumberGame.getScore());
		        mStatusText.setVisibility(View.VISIBLE);
		    }
			
		    public void setNumberPuzzleControl(NumberPuzzle _mNumberPuzzle) 
		    {
		    	mNumberPuzzle = _mNumberPuzzle;
		    }
			
		    private void initNumberPuzzleView() 
		    {
		        setFocusable(true);

		        mXNumberBrickCount = 5;
		        mYNumberBrickCount = 5;
		    
		    }
		    
		    public void resetNumberBricks(int Numberbrickcount) 
		    {
		    	mNumberBrickArray = new Bitmap[Numberbrickcount];
		    }
		    
		    public void loadNumberBrick(int key, Drawable Numberbrick) 
		    {
		        Bitmap Numberbitmap = Bitmap.createBitmap(mNumberBrickSize, mNumberBrickSize, Bitmap.Config.ARGB_8888);
		        Canvas Numbercanvas = new Canvas(Numberbitmap);
		        Numberbrick.setBounds(0, 0, mNumberBrickSize, mNumberBrickSize);
		        Numberbrick.draw(Numbercanvas);
		        
		        mNumberBrickArray[key] = Numberbitmap;
		    }

		    @Override
		    protected void onSizeChanged(int w, int h, int oldw, int oldh) 
		    {
		        Resources r = this.getContext().getResources();
		        
		    	if(w > h)
		    	{
		    		mNumberBrickSize = (int) Math.floor(h / mYNumberBrickCount);
		    	}
		    	else
		    	{
		    		mNumberBrickSize = (int) Math.floor(w / mXNumberBrickCount);
		    	}
		    	
		        resetNumberBricks(29);
		        loadNumberBrick(NUM_0, r.getDrawable(R.drawable.pic_0_def));
		        loadNumberBrick(NUM_1, r.getDrawable(R.drawable.pic_1_def));
		        loadNumberBrick(NUM_2, r.getDrawable(R.drawable.pic_2_def));
		        loadNumberBrick(NUM_3, r.getDrawable(R.drawable.pic_3_def));
		        loadNumberBrick(NUM_4, r.getDrawable(R.drawable.pic_4_def));
		        loadNumberBrick(NUM_5, r.getDrawable(R.drawable.pic_5_def));
		        loadNumberBrick(NUM_6, r.getDrawable(R.drawable.pic_6_def));
		        loadNumberBrick(NUM_7, r.getDrawable(R.drawable.pic_7_def));
		        loadNumberBrick(NUM_8, r.getDrawable(R.drawable.pic_8_def));
		        loadNumberBrick(NUM_9, r.getDrawable(R.drawable.pic_9_def));
		        //for now
		        loadNumberBrick(NUM_10, r.getDrawable(R.drawable.pic_0_def)); //loadNumberBrick(OP_PL, r.getDrawable(R.drawable.pic_add_def));
		        loadNumberBrick(NUM_11, r.getDrawable(R.drawable.pic_0_def));//loadNumberBrick(OP_MN, r.getDrawable(R.drawable.pic_min_def));
		        loadNumberBrick(NUM_12, r.getDrawable(R.drawable.pic_0_def));//loadNumberBrick(OP_MT, r.getDrawable(R.drawable.pic_mul_def));
		        loadNumberBrick(NUM_13, r.getDrawable(R.drawable.pic_0_def));//loadNumberBrick(OP_EQ, r.getDrawable(R.drawable.pic_eq_def));
		        loadNumberBrick(BLANK, r.getDrawable(R.drawable.pic_blk));        
		        loadNumberBrick(NUM_0_D, r.getDrawable(R.drawable.pic_0_d));
		        loadNumberBrick(NUM_1_D, r.getDrawable(R.drawable.pic_1_d));
		        loadNumberBrick(NUM_2_D, r.getDrawable(R.drawable.pic_2_d));
		        loadNumberBrick(NUM_3_D, r.getDrawable(R.drawable.pic_3_d));
		        loadNumberBrick(NUM_4_D, r.getDrawable(R.drawable.pic_4_d));
		        loadNumberBrick(NUM_5_D, r.getDrawable(R.drawable.pic_5_d));
		        loadNumberBrick(NUM_6_D, r.getDrawable(R.drawable.pic_6_d));
		        loadNumberBrick(NUM_7_D, r.getDrawable(R.drawable.pic_7_d));
		        loadNumberBrick(NUM_8_D, r.getDrawable(R.drawable.pic_8_d));
		        loadNumberBrick(NUM_9_D, r.getDrawable(R.drawable.pic_9_d));
		        loadNumberBrick(NUM_10_D, r.getDrawable(R.drawable.pic_0_d));//loadNumberBrick(OP_PL_D, r.getDrawable(R.drawable.pic_add_d));
		        loadNumberBrick(NUM_11_D, r.getDrawable(R.drawable.pic_0_d));//loadNumberBrick(OP_MN_D, r.getDrawable(R.drawable.pic_min_d));
		        loadNumberBrick(NUM_12_D, r.getDrawable(R.drawable.pic_0_d));//loadNumberBrick(OP_MT_D, r.getDrawable(R.drawable.pic_mul_d));
		        loadNumberBrick(NUM_13_D, r.getDrawable(R.drawable.pic_0_d));//loadBrick(OP_EQ_D, r.getDrawable(R.drawable.pic_eq_d));
		        
		        mXNumberOffset = ((w - (mNumberBrickSize * mXNumberBrickCount)) / 2);
		        mYNumberOffset = ((h - (mNumberBrickSize * mYNumberBrickCount)) / 2);

		        mNumberBrickGrid = mNumberPuzzle.CreateNewNumberPuzzle(mXNumberBrickCount, mYNumberBrickCount);
		    }
		    
		    private Index CoordinateToIndex(float x, float y)
		    {
		    	int i, j;
		    	
		        for (i = 0; i < mXNumberBrickCount; i += 1) 
		        {
		            for (j = 0; j < mYNumberBrickCount; j += 1) 
		            {
		                if ((x > (mXNumberOffset + i * mNumberBrickSize)) && (x <  (mXNumberOffset + (i+1) * mNumberBrickSize)) && (y > (mYNumberOffset + j * mNumberBrickSize)) && (y < (mYNumberOffset + (j+1) * mNumberBrickSize))) 
		                {
		                	return new Index(i, j);
		                }
		            }
		        }
				
		    	return new Index(2012, 2012);
		    }
		    
		    public boolean onTouch(View v, MotionEvent event)
		    {
		    	
		    	Index index;

				float x = event.getX();
				float y = event.getY();
				
				
				// The touch was outside the grid, ignore it
				index = CoordinateToIndex(x, y);
				if(index.x == 2012 || index.y == 2012)
				{
					return true;
				}
				
			//	View mStatusText;
				// The initial touch downward
				switch (event.getAction()) 
				{
				case MotionEvent.ACTION_DOWN:
					
					// Record the initial down coordinates
					downX = event.getX();
					downY = event.getY();
					
					break;
				case MotionEvent.ACTION_MOVE:
					// What to do while the finger is moving.
					
					// Commenting out the break will fall through to ACTION_UP.
					
					// This will change the tiles while moving.
					// Need to undo this on ACTION_UP
					if (mNumberBrickGrid[index.x][index.y] < 2012) 
		        	{
		        		if(mNumberBrickGrid[index.x][index.y] < BLANK)
		        		{
		        			mNumberBrickGrid[index.x][index.y] = mNumberBrickGrid[index.x][index.y] + 15;
		        			invalidate();
		        		}
		        	}
					break;
				case MotionEvent.ACTION_UP:
					
					// Un-highlight tiles
					for (int i = 0; i < mXNumberBrickCount; ++i) {
						for (int j = 0; j < mYNumberBrickCount; ++j) {
							if (mNumberBrickGrid[i][j] > BLANK) {
								mNumberBrickGrid[i][j] -= 15;
							}
						}
						
					}
					invalidate();
					
					// Record the up coordinates
					upX = event.getX();
					upY = event.getY();

					//((TextView)findViewById(R.id.score)).setText("Up");
					
			    	Index downIndex;
			    	Index upIndex;
			    	
					downIndex = CoordinateToIndex(downX, downY);
					upIndex = CoordinateToIndex(upX, upY);
			
					// Check for drag on column
					if ((upIndex.x == downIndex.x) && (Math.abs(upIndex.y - downIndex.y) == 4)) {
						int points = currentNumberGame.submit(columnToString(upIndex.x));
						if (points > 0) {
							Toast.makeText(v.getContext(), Integer.toString(points), Toast.LENGTH_SHORT).show();
				        	 mStatusText.setText(currentNumberGame.getScore());
				        	mStatusText.setVisibility(View.VISIBLE);
						} else {
							// Play a sound and vibrate?
						}
						
						// Break out of the on up to avoid tile press detection.
						break;
					}
				
					// Check for drag on row
					if ((upIndex.y == downIndex.y) && (Math.abs(upIndex.x - downIndex.x) == 4)) {
						int points = currentNumberGame.submit(rowToString(upIndex.y));
						if (points > 0) {
							Toast.makeText(v.getContext(), Integer.toString(points), Toast.LENGTH_SHORT).show();
				        	 mStatusText.setText(currentNumberGame.getScore());
				        	mStatusText.setVisibility(View.VISIBLE);
						}
						
						
						// Break out of the on up to avoid tile press detection.
						break;
					}
					
					// No drag, then it should be a tile press.
					if (mNumberBrickGrid[index.x][index.y] < 2012) 
		        	{
		        		if(mNumberBrickGrid[index.x][index.y] > BLANK)
		        		{
		        			mNumberBrickGrid[index.x][index.y] = mNumberBrickGrid[index.x][index.y] - 15;
		        			invalidate();
		        		}
		        	}
		        	mNumberPuzzle.ChangeNumberPuzzle(index.x, index.y);
		        	mNumberBrickGrid = mNumberPuzzle.GetNumberPuzzle();
		        	invalidate();
		        	
					break;
				default:
					break;
				}
				
				return true;
		    }
		    
		    @Override
		    public boolean onKeyDown(int keyCode, KeyEvent msg) 
		    {
		        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) 
		        {
		        	mStatusText.setText("KEYCODE_DPAD_UP");
		        	mStatusText.setVisibility(View.VISIBLE);
		        }

		        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) 
		        {
		        	mStatusText.setText("KEYCODE_DPAD_DOWN");
		        	mStatusText.setVisibility(View.VISIBLE);
		        }

		        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) 
		        {
		        	mStatusText.setText("KEYCODE_DPAD_LEFT");
		        	mStatusText.setVisibility(View.VISIBLE);
		        }

		        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) 
		        {
		        	mStatusText.setText("KEYCODE_DPAD_RIGHT");
		        	mStatusText.setVisibility(View.VISIBLE);
		        }
		        
		        if (keyCode == KeyEvent.KEYCODE_MENU)
		        {
		        	mStatusText.setText("KEYCODE_MENU");
		        	mStatusText.setVisibility(View.VISIBLE);
		        }

		        return super.onKeyDown(keyCode, msg);
		    }
		    
		    @Override
		    public void onDraw(Canvas Numbercanvas) 
		    {
		        super.onDraw(Numbercanvas);
		        for (int x = 0; x < mXNumberBrickCount; x += 1) 
		        {
		            for (int y = 0; y < mYNumberBrickCount; y += 1) 
		            {
		                if (mNumberBrickGrid[x][y] < 2012) 
		                {
		                    Numbercanvas.drawBitmap(mNumberBrickArray[mNumberBrickGrid[x][y]], 
		                    		mXNumberOffset + x * mNumberBrickSize,
		                    		mYNumberOffset + y * mNumberBrickSize,
		                    		mNumberPaint);
		                }
		            }
		        }
		    }
		    
		    public Bundle saveState() 
		    {
		    	Bundle map = mNumberPuzzle.saveState();

		        //map.putIntArray("mAppleList", coordArrayListToArray(mAppleList));
		        //map.putInt("mDirection", Integer.valueOf(mDirection));
		        //map.putLong("mScore", Long.valueOf(mScore));

		        return map;
		    }
		    
		    public void restoreState(Bundle icicle) 
		    {
		        //setMode(PAUSE);

		        //mAppleList = coordArrayToArrayList(icicle.getIntArray("mAppleList"));
		        //mDirection = icicle.getInt("mDirection");
		        //mMoveDelay = icicle.getLong("mMoveDelay");
		    	mNumberPuzzle.restoreState(icicle);
		    }
		    
		    /*
		     * Functions for converting the brick grid into a string
		     */
		   
		    private String columnToString(int x){
		    	String equation = "";
		    	for (int i = 0; i < 5; ++i) {
		    		equation += brickToString(mNumberBrickGrid[x][i]);
		    	}
		    	return equation;
		    }
		    
		    private String rowToString(int y){
		    	String equation = "";
		    	for (int i = 0; i < 5; ++i) {
		    		equation += brickToString(mNumberBrickGrid[i][y]);
		    	}
		    	return equation;
		    }
		    
		    // Convert a brick grid element to its corresponding text
		    // There might be a better way to do this
		    private String brickToString(int x) {
		    	switch(x) {
		    	case NUM_0:
		    		return "0";
		    	case NUM_1:
		    		return "1";
		    	case NUM_2:
		    		return "2";
		    	case NUM_3:
		    		return "3";
		    	case NUM_4:
		    		return "4";
		    	case NUM_5:
		    		return "5";
		    	case NUM_6:
		    		return "6";
		    	case NUM_7:
		    		return "7";
		    	case NUM_8:
		    		return "8";
		    	case NUM_9:
		    		return "9";
		    	case NUM_10:
		    		return "10";
		    	case NUM_11:
		    		return "11";
		    	case NUM_12:
		    		return "12";
		    	case NUM_13:
		    		return "13";
		    	/*
		    	case OP_PL:
		    		return "+";
		    	case OP_MN:
		    		return "-";
		    	case OP_MT:
		    		return "*";
		    	case OP_EQ:
		    		return "=";*/
		    	default:
		    		return "";
		    	}
		    }
		    
		    private class Index 
		    {
		        public int x;
		        public int y;

		        public Index(int newX, int newY) 
		        {
		            x = newX;
		            y = newY;
		        }

		        public boolean equals(Index other) 
		        {
		            if (x == other.x && y == other.y) 
		            {
		                return true;
		            }
		            return false;
		        }
		    }

	}


