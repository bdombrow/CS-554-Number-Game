package com.numbergame;

import java.util.Hashtable;

import android.content.SharedPreferences;
import android.os.Bundle;

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

	private boolean mPuzzleOptionChanged;
	protected static int mLevel;

	protected int mXBrickCount;
	protected int mYBrickCount;

	protected static int mXBlankBrick;
	protected static int mYBlankBrick;

	private int[][] mPuzzleGrid;
	
	private int mScore = 0;

	// Hash table to hold the valid equations
	private Hashtable<String, Integer> equationTable = new Hashtable<String, Integer>();

	public int[][] CreateNewPuzzle(int xCount, int yCount) {
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

		numPuzzleScrambles = 0;
		numPuzzleRandomMoves = 0;
		numPuzzlePlayerMoves = 0;

		reset();

		return mPuzzleGrid;
	}

	public void clearBricks() {
		for (int x = 0; x < mXBrickCount; x++) {
			for (int y = 0; y < mYBrickCount; y++) {
				setBrick(2012, x, y);
			}
		}
	}

	public void setBrick(int brickindex, int x, int y) {
		mPuzzleGrid[x][y] = brickindex;
	}

	public int getXBrickCount() {
		return mXBrickCount;
	}

	public int getYBrickCount() {
		return mYBrickCount;
	}

	public int[][] GetPuzzle() {
		return mPuzzleGrid;
	}

	public boolean ChangePuzzle(int x, int y) {
		int i;
		if ((x == mXBlankBrick) && (y != mYBlankBrick)) {
			if (y < mYBlankBrick) {
				for (i = mYBlankBrick; i > y; i = i - 1) {
					mPuzzleGrid[x][i] = mPuzzleGrid[x][i - 1];
					PuzzlePlayerMoves[numPuzzlePlayerMoves] = 3;
					numPuzzlePlayerMoves++;
				}
				setBrick(BLANK, x, i);
				mXBlankBrick = x;
				mYBlankBrick = y;
			} else {
				for (i = mYBlankBrick; i < y; i = i + 1) {
					mPuzzleGrid[x][i] = mPuzzleGrid[x][i + 1];
					PuzzlePlayerMoves[numPuzzlePlayerMoves] = 4;
					numPuzzlePlayerMoves++;
				}
				setBrick(BLANK, x, i);
				mXBlankBrick = x;
				mYBlankBrick = y;
			}
		} else if ((x != mXBlankBrick) && (y == mYBlankBrick)) {
			if (x < mXBlankBrick) {
				for (i = mXBlankBrick; i > x; i = i - 1) {
					mPuzzleGrid[i][y] = mPuzzleGrid[i - 1][y];
					PuzzlePlayerMoves[numPuzzlePlayerMoves] = 1;
					numPuzzlePlayerMoves++;
				}
				setBrick(BLANK, i, y);
				mXBlankBrick = x;
				mYBlankBrick = y;
			} else {
				for (i = mXBlankBrick; i < x; i = i + 1) {
					mPuzzleGrid[i][y] = mPuzzleGrid[i + 1][y];
					PuzzlePlayerMoves[numPuzzlePlayerMoves] = 2;
					numPuzzlePlayerMoves++;
				}
				setBrick(BLANK, i, y);
				mXBlankBrick = x;
				mYBlankBrick = y;
			}
		}

		return true;
	}

	public void reset() {
		mScore = 0;

		// Load the hash table with valid equations
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				if ((i + j < 10) && (i <= j)) {
					equationTable.put((i + "+" + j + "=" + (i + j)), (i + j));
				}
				if (i - j >= 0) {
					equationTable.put((i + "-" + j + "=" + (i - j)),
							(i + j + 10));
				}
				if ((i * j < 10) && (i < j)) {
					equationTable.put((i + "*" + j + "=" + (i * j)),
							(i + j + 20));
				}
			}
		}
	}

	public String getScore() {
		return Integer.toString(mScore);
	}

	public int submit(CharSequence inputString) {
		Integer result = (Integer) equationTable
				.get(NormalizeEquation(inputString));
		if (result != null) {
			// We have a winner. Increment mScore and remove it from the table
			mScore += result;
			equationTable.remove(NormalizeEquation(inputString));
			return result;
		}
		return -1;

	}

	private String NormalizeEquation(CharSequence inputCharSequence) {
		// CharSequences are read only, convert to a string to manipulate.
		String inputString = inputCharSequence.toString();

		// Make sure it's the right length
		if (inputCharSequence.length() != 5) {
			return inputString;
		}

		// Is there an = at position 2?
		if (inputString.charAt(1) == '=') {
			// Need to flip it
			inputString = inputString.substring(2) + "="
					+ inputString.charAt(0);
		}
		// Is it addition or multiplication?
		if ((inputString.charAt(1) == '+') || (inputString.charAt(1) == '*')) {
			// Make sure the smaller number is on the left.
			char x = inputString.charAt(0);
			char y = inputString.charAt(2);
			if (x > y) { // Hope this part works.
				inputString = Character.toString(y) + inputString.charAt(1)
						+ Character.toString(x) + inputString.substring(3, 5);
			}
		}

		return inputString;
	}

	/*
	 * 
	 * 28 October 2011 - FWS Added the ScramblePuzzle() and UnScramblePuzzle()
	 * methods
	 */
	// protected static boolean playerMovedTile = true; // player moves are not
	// tracked yet
	protected static int maxScrambles = 10; // only 10 scrambles allowed
	protected static int numScrambleMoves = 100; // 100 random moves per
													// scramble
	protected static int maxPlayerMoves = 1000; // 1000 player moves before
												// unscramble required
	protected int maxRandomMoves = maxScrambles * numScrambleMoves;
	protected int[] PuzzleRandomMoves = new int[maxRandomMoves];
	private int[] PuzzlePlayerMoves = new int[maxPlayerMoves];

	protected int numPuzzleScrambles;
	protected int numPuzzleRandomMoves;
	protected int numPuzzlePlayerMoves;

	public void ScramblePuzzle() {
		if ((numPuzzleRandomMoves >= maxRandomMoves)
				|| (numPuzzlePlayerMoves > 0))
			return;

		// generate random moves of the blank space
		// left = 1
		// right = 2
		// up = 3
		// down = 4
		int low = 1;
		int high = 4;
		int firstIndex = numPuzzleScrambles * numScrambleMoves;
		int lastIndex = firstIndex + numScrambleMoves;
		for (int i = firstIndex; i < lastIndex; i++)
			PuzzleRandomMoves[i] = (int) (Math.random() * 100) % high + low;

		// calculate single number for the index of the blank space
		int BlankIndex = (mYBlankBrick * mYBrickCount) + mXBlankBrick;

		// newIndex will be the new index of the blank space
		// and it is also the current index of the tile that will move
		int newIndex = 0;

		for (int i = firstIndex; i < lastIndex; i++) {
			switch (PuzzleRandomMoves[i]) {
			// blank space left
			case 1:
				if (mXBlankBrick != 0) // cannot move left from leftmost column
					newIndex = BlankIndex - 1;
				else
					PuzzleRandomMoves[i] = 5; // 5 is a dummy place holder
												// meaning no move possible
				break;

			// blank space right
			case 2:
				if (mXBlankBrick != mXBrickCount - 1) // cannot move right from
														// rightmost column
					newIndex = BlankIndex + 1;
				else
					PuzzleRandomMoves[i] = 5; // 5 is a dummy place holder
												// meaning no move possible
				break;

			// blank space up
			case 3:
				if (mYBlankBrick != 0) // cannot move up from the topmost row
					newIndex = BlankIndex - mXBrickCount;
				else
					PuzzleRandomMoves[i] = 5; // 5 is a dummy place holder
												// meaning no move possible
				break;

			// blank space down
			case 4:
				if (mYBlankBrick != mYBrickCount - 1) // cannot move down from
														// the bottom most row
					newIndex = BlankIndex + mXBrickCount;
				else
					PuzzleRandomMoves[i] = 5; // 5 is a dummy place holder
												// meaning no move possible
				break;
			}

			if (PuzzleRandomMoves[i] != 5) {
				BlankIndex = newIndex; // set the new index of the blank space
				int x = newIndex % mXBrickCount; // calculate x or column index
				int y = (newIndex - x) / mYBrickCount; // calculate y or row
														// index
				ChangePuzzle(x, y);
				numPuzzlePlayerMoves--;
			}
		}
		numPuzzleScrambles++;
		numPuzzleRandomMoves += numScrambleMoves;
		return;
	}

	public void UnScramblePuzzle() {
		if (numPuzzlePlayerMoves > 0) {
			PlayBackMoves(PuzzlePlayerMoves, numPuzzlePlayerMoves);
			numPuzzlePlayerMoves = 0;
		}
		if (numPuzzleRandomMoves > 0) {
			PlayBackMoves(PuzzleRandomMoves, numPuzzleRandomMoves);
			numPuzzleScrambles = 0;
			numPuzzleRandomMoves = 0;
		}
		return;
	}

	private void PlayBackMoves(int[] moves, int numMoves) {
		int BlankIndex = (mYBlankBrick * mYBrickCount) + mXBlankBrick;
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

			if (moves[i] != 5) {
				BlankIndex = newIndex; // set the new index of the blank space
				int x = newIndex % mXBrickCount; // calculate x or column index
				int y = (newIndex - x) / mYBrickCount; // calculate y or row
														// index
				ChangePuzzle(x, y);
				numPuzzlePlayerMoves--;
			}
		}
		return;
	}

	public void saveState(Bundle map) {
		int i, j;
		Integer score;

		// map.putIntArray("mAppleList", coordArrayListToArray(mAppleList));
		map.putInt("mXBrickCount", Integer.valueOf(mXBrickCount));
		map.putInt("mYBrickCount", Integer.valueOf(mYBrickCount));
		map.putInt("mXBlankBrick", Integer.valueOf(mXBlankBrick));
		map.putInt("mYBlankBrick", Integer.valueOf(mYBlankBrick));
		map.putBoolean("mPuzzleOptionChanged", mPuzzleOptionChanged);
		map.putInt("mLevel1", mLevel);
		for (i = 0; i < mXBrickCount; i = i + 1) {
			for (j = 0; j < mYBrickCount; j = j + 1) {
				map.putInt(Integer.toString(i) + "-" + Integer.toString(j),
						Integer.valueOf(mPuzzleGrid[i][j]));
			}
		}
		map.putInt("mScore", Integer.valueOf(mScore));
		for (i = 0; i < 10; ++i) {
			for (j = 0; j < 10; ++j) {
				if ((i + j < 10) && (i <= j)) {
					score = (Integer) equationTable
							.get((i + "+" + j + "=" + (i + j)));
					if (score != null) {
						map.putInt((i + "+" + j + "=" + (i + j)), score);
					} else {
						map.putInt((i + "+" + j + "=" + (i + j)), 2012);
					}
				}
				if (i - j >= 0) {
					score = (Integer) equationTable
							.get((i + "-" + j + "=" + (i - j)));
					if (score != null) {
						map.putInt((i + "-" + j + "=" + (i - j)), score);
					} else {
						map.putInt((i + "-" + j + "=" + (i - j)), 2012);
					}
				}
				if ((i * j < 10) && (i < j)) {
					score = (Integer) equationTable
							.get((i + "*" + j + "=" + (i * j)));
					if (score != null) {
						map.putInt((i + "*" + j + "=" + (i * j)), score);
					} else {
						map.putInt((i + "*" + j + "=" + (i * j)), 2012);
					}
				}
			}
		}

		map.putInt("numPuzzleScrambles", numPuzzleScrambles);
		map.putInt("numPuzzleRandomMoves", numPuzzleRandomMoves);
		map.putInt("numPuzzlePlayerMoves", numPuzzlePlayerMoves);
		for (i = 0; i < numPuzzleRandomMoves; i = i + 1) {
			map.putInt(("PuzzleRandomMoves" + i), PuzzleRandomMoves[i]);
		}
		for (i = 0; i < numPuzzlePlayerMoves; i = i + 1) {
			map.putInt(("PuzzlePlayerMoves" + i), PuzzlePlayerMoves[i]);
		}

		return;
	}

	public void restoreState(Bundle icicle) {
		int i, j;
		int score;
		boolean neadNewPuzzle = false;

		mXBrickCount = icicle.getInt("mXBrickCount");
		mYBrickCount = icicle.getInt("mYBrickCount");
		mXBlankBrick = icicle.getInt("mXBlankBrick");
		mYBlankBrick = icicle.getInt("mYBlankBrick");
		mPuzzleOptionChanged = icicle.getBoolean("mPuzzleOptionChanged");
		mLevel = icicle.getInt("mLevel1");

		mPuzzleGrid = new int[mXBrickCount][mYBrickCount];
		for (i = 0; i < mXBrickCount; i = i + 1) {
			for (j = 0; j < mYBrickCount; j = j + 1) {
				mPuzzleGrid[i][j] = icicle.getInt(Integer.toString(i) + "-"
						+ Integer.toString(j));
				if (mPuzzleGrid[i][j] == 2012) {
					neadNewPuzzle = true;
				}
			}
		}

		mScore = icicle.getInt("mScore");
		for (i = 0; i < 10; ++i) {
			for (j = 0; j < 10; ++j) {
				if ((i + j < 10) && (i <= j)) {
					score = icicle.getInt((i + "+" + j + "=" + (i + j)));
					if (score != 2012) {
						equationTable.put((i + "+" + j + "=" + (i + j)),
								(i + j));
					}
				}
				if (i - j >= 0) {
					score = icicle.getInt((i + "-" + j + "=" + (i - j)), 2012);
					if (score != 2012) {
						equationTable.put((i + "-" + j + "=" + (i - j)),
								(i + j + 10));
					}
				}
				if ((i * j < 10) && (i < j)) {
					score = icicle.getInt((i + "*" + j + "=" + (i * j)), 2012);
					if (score != 2012) {
						equationTable.put((i + "*" + j + "=" + (i * j)),
								(i + j + 20));
					}
				}
			}

		}

		numPuzzleScrambles = icicle.getInt("numPuzzleScrambles", 0);
		numPuzzleRandomMoves = icicle.getInt("numPuzzleRandomMoves", 0);
		numPuzzlePlayerMoves = icicle.getInt("numPuzzlePlayerMoves", 0);
		for (i = 0; i < numPuzzleRandomMoves; i = i + 1) {
			PuzzleRandomMoves[i] = icicle.getInt(("PuzzleRandomMoves" + i), 5);
		}
		for (i = 0; i < numPuzzlePlayerMoves; i = i + 1) {
			PuzzlePlayerMoves[i] = icicle.getInt(("PuzzlePlayerMoves" + i), 5);
		}

		if (neadNewPuzzle) {
			mPuzzleGrid = CreateNewPuzzle(mXBrickCount, mYBrickCount);
			;
		}
	}

	public void saveState(SharedPreferences.Editor editor) {
		int i, j;
		Integer score;

		// editor.putString("name", name);
		editor.putInt("mXBrickCount", mXBrickCount);
		editor.putInt("mYBrickCount", mYBrickCount);
		editor.putInt("mXBlankBrick", mXBlankBrick);
		editor.putInt("mYBlankBrick", mYBlankBrick);
		editor.putBoolean("mPuzzleOptionChanged", mPuzzleOptionChanged);
		for (i = 0; i < mXBrickCount; i = i + 1) {
			for (j = 0; j < mYBrickCount; j = j + 1) {
				editor.putInt("mPuzzleGrid-" + Integer.toString(i) + "-"
						+ Integer.toString(j), mPuzzleGrid[i][j]);
			}
		}
		editor.putInt("mScore", mScore);
		for (i = 0; i < 10; ++i) {
			for (j = 0; j < 10; ++j) {
				if ((i + j < 10) && (i <= j)) {
					score = (Integer) equationTable
							.get((i + "+" + j + "=" + (i + j)));
					if (score != null) {
						editor.putInt((i + "+" + j + "=" + (i + j)), score);
					} else {
						editor.putInt((i + "+" + j + "=" + (i + j)), 2012);
					}
				}
				if (i - j >= 0) {
					score = (Integer) equationTable
							.get((i + "-" + j + "=" + (i - j)));
					if (score != null) {
						editor.putInt((i + "-" + j + "=" + (i - j)), score);
					} else {
						editor.putInt((i + "-" + j + "=" + (i - j)), 2012);
					}
				}
				if ((i * j < 10) && (i < j)) {
					score = (Integer) equationTable
							.get((i + "*" + j + "=" + (i * j)));
					if (score != null) {
						editor.putInt((i + "*" + j + "=" + (i * j)), score);
					} else {
						editor.putInt((i + "*" + j + "=" + (i * j)), 2012);
					}
				}
			}
		}

		editor.putInt("numPuzzleScrambles", numPuzzleScrambles);
		editor.putInt("numPuzzleRandomMoves", numPuzzleRandomMoves);
		editor.putInt("numPuzzlePlayerMoves", numPuzzlePlayerMoves);
		for (i = 0; i < numPuzzleRandomMoves; i = i + 1) {
			editor.putInt(("PuzzleRandomMoves" + i), PuzzleRandomMoves[i]);
		}
		for (i = 0; i < numPuzzlePlayerMoves; i = i + 1) {
			editor.putInt(("PuzzlePlayerMoves" + i), PuzzlePlayerMoves[i]);
		}

		editor.commit();
	}

	public void restoreState(SharedPreferences settings) {
		int i, j;
		int score;
		boolean neadNewPuzzle = false;

		mXBrickCount = settings.getInt("mXBrickCount", 5);
		mYBrickCount = settings.getInt("mYBrickCount", 5);
		mXBlankBrick = settings.getInt("mXBlankBrick", 3);
		mYBlankBrick = settings.getInt("mYBlankBrick", 3);
		mPuzzleOptionChanged = settings
				.getBoolean("mPuzzleOptionChanged", true);
		mLevel = settings.getInt("mLevel1", 1);

		mPuzzleGrid = new int[mXBrickCount][mYBrickCount];
		for (i = 0; i < mXBrickCount; i = i + 1) {
			for (j = 0; j < mYBrickCount; j = j + 1) {
				mPuzzleGrid[i][j] = settings.getInt(
						"mPuzzleGrid-" + Integer.toString(i) + "-"
								+ Integer.toString(j), 2012);
				if (mPuzzleGrid[i][j] == 2012) {
					neadNewPuzzle = true;
				}
			}
		}

		mScore = settings.getInt("mScore", 0);
		for (i = 0; i < 10; ++i) {
			for (j = 0; j < 10; ++j) {
				if ((i + j < 10) && (i <= j)) {
					score = settings
							.getInt((i + "+" + j + "=" + (i + j)), 2012);
					if (score != 2012) {
						equationTable.put((i + "+" + j + "=" + (i + j)),
								(i + j));
					}
				}
				if (i - j >= 0) {
					score = settings
							.getInt((i + "-" + j + "=" + (i - j)), 2012);
					if (score != 2012) {
						equationTable.put((i + "-" + j + "=" + (i - j)),
								(i + j + 10));
					}
				}
				if ((i * j < 10) && (i < j)) {
					score = settings
							.getInt((i + "*" + j + "=" + (i * j)), 2012);
					if (score != 2012) {
						equationTable.put((i + "*" + j + "=" + (i * j)),
								(i + j + 20));
					}
				}
			}
		}

		numPuzzleScrambles = settings.getInt("numPuzzleScrambles", 0);
		numPuzzleRandomMoves = settings.getInt("numPuzzleRandomMoves", 0);
		numPuzzlePlayerMoves = settings.getInt("numPuzzlePlayerMoves", 0);
		for (i = 0; i < numPuzzleRandomMoves; i = i + 1) {
			PuzzleRandomMoves[i] = settings
					.getInt(("PuzzleRandomMoves" + i), 5);
		}
		for (i = 0; i < numPuzzlePlayerMoves; i = i + 1) {
			PuzzlePlayerMoves[i] = settings
					.getInt(("PuzzlePlayerMoves" + i), 5);
		}

		if (neadNewPuzzle || mPuzzleOptionChanged) {
			mPuzzleGrid = CreateNewPuzzle(mXBrickCount, mYBrickCount);
			mPuzzleOptionChanged = false;
		}

	}
}
