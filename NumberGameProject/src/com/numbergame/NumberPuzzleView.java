package com.numbergame;
import com.numbergame.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
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
	private static final int NUM_10 =10;
	private static final int NUM_11 =11;
	private static final int NUM_12 =12;
	private static final int NUM_13 =13;
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

	private TextView nStatusText;

	protected static int nNumberBrickSize;

	protected static int nXNumberBrickCount;
	protected static int mYNumberBrickCount;

	private static int nXNumberOffset;
	private static int nYNumberOffset;

	private Bitmap[] nNumberBrickArray; 

	private int[][] nNumberBrickGrid;

	private final Paint nNumberPaint = new Paint();

	private NumberPuzzle nNumberPuzzle;
	
	private int nScrambleMoves;

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
		nStatusText = newView;

		// Set the StatusText to the current score.
		nStatusText.setText(nNumberPuzzle.getScore());
		nStatusText.setVisibility(View.VISIBLE);
	}

	public void setNumberPuzzleControl(NumberPuzzle _nNumberPuzzle) 
	{
		nNumberPuzzle = _nNumberPuzzle;
	}

	private void initNumberPuzzleView() 
	{
		setFocusable(true);

	}

	public void resetNumberBricks(int Numberbrickcount) 
	{
		nNumberBrickArray = new Bitmap[Numberbrickcount];
	}

	public void loadNumberBrick(int key, Drawable Numberbrick) 
	{
		Bitmap Numberbitmap = Bitmap.createBitmap(nNumberBrickSize, nNumberBrickSize, Bitmap.Config.ARGB_8888);
		Canvas Numbercanvas = new Canvas(Numberbitmap);
		Numberbrick.setBounds(0, 0, nNumberBrickSize, nNumberBrickSize);
		Numberbrick.draw(Numbercanvas);

		nNumberBrickArray[key] = Numberbitmap;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		Resources r = this.getContext().getResources();

		nXNumberBrickCount = nNumberPuzzle.getXBrickCount();
		mYNumberBrickCount = nNumberPuzzle.getYBrickCount();

		nNumberBrickGrid = nNumberPuzzle.GetNumberPuzzle();

		if(w > h)
		{
			nNumberBrickSize = (int) Math.floor(h / mYNumberBrickCount);
		}
		else
		{
			nNumberBrickSize = (int) Math.floor(w / nXNumberBrickCount);
		}

		resetNumberBricks(26);
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
		loadNumberBrick(NUM_10, r.getDrawable(R.drawable.pic_10_def)); 
		loadNumberBrick(NUM_11, r.getDrawable(R.drawable.pic_11_def));
		loadNumberBrick(NUM_12, r.getDrawable(R.drawable.pic_12_def));
		loadNumberBrick(NUM_13, r.getDrawable(R.drawable.pic_13_def));
		loadNumberBrick(NUM_14, r.getDrawable(R.drawable.pic_14_def));
		loadNumberBrick(NUM_15, r.getDrawable(R.drawable.pic_15_def));
		loadNumberBrick(NUM_16, r.getDrawable(R.drawable.pic_16_def));
		loadNumberBrick(NUM_17, r.getDrawable(R.drawable.pic_17_def));
		loadNumberBrick(NUM_18, r.getDrawable(R.drawable.pic_18_def));
		loadNumberBrick(NUM_19, r.getDrawable(R.drawable.pic_19_def));
		loadNumberBrick(NUM_20, r.getDrawable(R.drawable.pic_20_def));
		loadNumberBrick(NUM_21, r.getDrawable(R.drawable.pic_21_def));
		loadNumberBrick(NUM_22, r.getDrawable(R.drawable.pic_22_def));
		loadNumberBrick(NUM_23, r.getDrawable(R.drawable.pic_23_def));
		loadNumberBrick(NUM_24, r.getDrawable(R.drawable.pic_24_def));

		loadNumberBrick(BLANK, r.getDrawable(R.drawable.pic_blk));        


		nXNumberOffset = ((w - (nNumberBrickSize * nXNumberBrickCount)) / 2);
		nYNumberOffset = ((h - (nNumberBrickSize * mYNumberBrickCount)) / 2);
	}

	private Index CoordinateToIndex(float x, float y)
	{
		int i, j;

		for (i = 0; i < nXNumberBrickCount; i += 1) 
		{
			for (j = 0; j < mYNumberBrickCount; j += 1) 
			{
				if ((x > (nXNumberOffset + i * nNumberBrickSize)) && (x <  (nXNumberOffset + (i+1) * nNumberBrickSize)) && (y > (nYNumberOffset + j * nNumberBrickSize)) && (y < (nYNumberOffset + (j+1) * nNumberBrickSize))) 
				{
					return new Index(i, j);
				}
			}
		}

		return new Index(2012, 2012);
	}
	
	
    public void Scramble()
    {
    	nScrambleMoves = nNumberPuzzle.ScrambleNumberPuzzle();
		Toast toast = Toast.makeText(this.getContext(),"scramble moves = " + nScrambleMoves, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP|Gravity.RIGHT,30, 30);
		toast.show();
		nNumberBrickGrid = nNumberPuzzle.GetNumberPuzzle();
		invalidate();
    }
    
    public void UnScramble()
    {
		nNumberPuzzle.UnScrambleNumberPuzzle();
		nNumberBrickGrid = nNumberPuzzle.GetNumberPuzzle();
		nStatusText.setText(nNumberPuzzle.getScore());
		invalidate();
    }

    // For the AI puzzle wizard, the scrambled moves are played
    // back with a 500 milli-second delay between each move.
    // postInvalidate() is called to notify the UI thread
    // to redraw the tiles
    public class DelayedUnScrambleThread extends Thread
    {
        	public void run()
        	{
        		try
        		{
        	    	while(nNumberPuzzle.AIUnScrambleNumberPuzzle())
        	    	{
        	    		nNumberBrickGrid = nNumberPuzzle.GetNumberPuzzle();
        	    		postInvalidate();
        	    		long currentTime = System.currentTimeMillis();
        	    		long desiredDelay = currentTime + 500;
        	    		while (desiredDelay > currentTime)
        	    		{
        	    			Thread.sleep(100);
        	    			currentTime = System.currentTimeMillis();
        	    		}
            		}
        		}
        		catch (InterruptedException e)
        		{
        		}
    	}
    }   

    // For the AI puzzle wizard, a second thread is created
    // to play back the scrambled moves
    public void AIUnScramble()
    {
    	Thread delay = new DelayedUnScrambleThread();
		delay.start();
		nStatusText.setText(nNumberPuzzle.getScore());
		invalidate();
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

		//	View nStatusText;
		// The initial touch downward
		switch (event.getAction()) 
		{
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:

			nNumberPuzzle.ChangeNumberPuzzle(index.x, index.y);
			nNumberBrickGrid = nNumberPuzzle.GetNumberPuzzle();
			
			nNumberPuzzle.submit();
			nStatusText.setText(nNumberPuzzle.getScore());
			
			invalidate();
			
			if (nNumberPuzzle.IsPuzzleSolved())
			{
				int score = Integer.parseInt(nNumberPuzzle.getScore());
				String congrats = "Puzzle Solved";
				if (score > nScrambleMoves)
					congrats = congrats + "\n\nYOU ARE FANTASTIC!";
				else if (score == nScrambleMoves)
					congrats = congrats + "\nyou equaled the wizard" + "\n\nYOU ARE AMAZING!!";
				else if (score < nScrambleMoves)
					congrats = congrats + "\nyou beat the wizard" + "\n\nYOU ARE AWESOME!!!";
				Toast toast = Toast.makeText(this.getContext(),congrats, Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER,0,0);
				toast.show();
				
			}
			break;
		default:
			break;
		}

		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) 
	{		        
		if (keyCode == KeyEvent.KEYCODE_MENU)
		{
			nStatusText.setText("KEYCODE_MENU");
			nStatusText.setVisibility(View.VISIBLE);
		}

		return super.onKeyDown(keyCode, msg);
	}

	@Override
	public void onDraw(Canvas Numbercanvas) 
	{
		super.onDraw(Numbercanvas);
		for (int x = 0; x < nXNumberBrickCount; x += 1) 
		{
			for (int y = 0; y < mYNumberBrickCount; y += 1) 
			{
				if (nNumberBrickGrid[x][y] < 2012) 
				{
					Numbercanvas.drawBitmap(nNumberBrickArray[nNumberBrickGrid[x][y]], 
							nXNumberOffset + x * nNumberBrickSize,
							nYNumberOffset + y * nNumberBrickSize,
							nNumberPaint);
				}
			}
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


