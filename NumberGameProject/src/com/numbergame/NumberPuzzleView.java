package com.numbergame;
import com.numbergame.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

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

		mNumberBrickArray[key] = Numberbitmap; // Crashing here.
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		Resources r = this.getContext().getResources();

		mXNumberBrickCount = mNumberPuzzle.getXBrickCount();
		mYNumberBrickCount = mNumberPuzzle.getYBrickCount();

		mNumberBrickGrid = mNumberPuzzle.GetNumberPuzzle();

		if(w > h)
		{
			mNumberBrickSize = (int) Math.floor(h / mYNumberBrickCount);
		}
		else
		{
			mNumberBrickSize = (int) Math.floor(w / mXNumberBrickCount);
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


		mXNumberOffset = ((w - (mNumberBrickSize * mXNumberBrickCount)) / 2);
		mYNumberOffset = ((h - (mNumberBrickSize * mYNumberBrickCount)) / 2);
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
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:

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


