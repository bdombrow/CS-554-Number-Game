/*
 * The goal of this class is to have a higher level game class.
 * The real games will be subclassed.
 * A game object can then be instantiated as any of the real game types.
 */

package com.numbergame;

import java.util.Hashtable;

public abstract class Game {
	/* 
	 * Members and methods shared by all games
	 * 
	 */
	int score;
	int getScore(){
		return score;
	}
	void reset(){}

	/*
	 * Math (mode 1) specific members and methods
	 */
	public class MathGame {

		// Hash table to hold the valid equations
		private Hashtable equationTable = new Hashtable();

		public MathGame(){			
			this.reset();
		}

		public void reset() {
			score = 0;

			// Load the hash table with valid equations
			for (int i = 0; i < 10; ++i) {
				for (int j = 0; j < 10; ++j) {
					if ((i + j < 10) && (i <= j)) {
						equationTable.put((i + "+" + j + "=" + (i + j)) , (i+j));
					}
					if (i - j >= 0) {
						equationTable.put((i + "-" + j + "=" + (i - j)) , (i+j+10));	        		}
					if ((i * j < 10) && (i < j)) {
						equationTable.put((i + "*" + j + "=" + (i * j)) , (i+j+20));	        		}
				}
			}
		}

		public void submit(CharSequence inputString) {
			Integer result = (Integer)equationTable.get(NormalizeEquation(inputString));
			if (result != null) {
				// We have a winner. Increment score and remove it from the table
				score += result;
				equationTable.remove(NormalizeEquation(inputString));
			}
			return;

		}

		private String NormalizeEquation(CharSequence inputCharSequence) {
			// Make sure it's the right length
			if (inputCharSequence.length() != 5) {
				return null;
			}
			// CharSequences are read only, convert to a string to manipulate.
			String inputString = inputCharSequence.toString();

			// Is there an = at position 2?
			if (inputString.charAt(1) == '=') {
				// Need to flip it
				inputString = inputString.substring(2) + "=" + inputString.charAt(0);
			}
			// Is it addition or multiplication?
			if ( (inputString.charAt(1) == '+') || (inputString.charAt(1) == '*')) {
				// Make sure the smaller number is on the left.
				char x = inputString.charAt(0);
				char y = inputString.charAt(2);
				if (x > y) { // Hope this part works.
					inputString = Character.toString(y) + inputString.charAt(1) + Character.toString(x) + inputString.substring(3, 5);
					//Toast.makeText(getApplicationContext(), "N:" + inputString, Toast.LENGTH_SHORT).show();
				}
			}

			return inputString;
		}

	}

}
