/**Open-Android-CrazyPuzzle Copyright © 2011 
@author "Brent Dombrowski", 
@author "Hema Kumar",
@author "Frank Sliz"
@author "Derek Qian"
//** This file is part of Crazy puzzle.This is free software: you can redistribute it 
 * and/or modify it under the terms of the GNU General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or any later version.
 * Crazy Puzzle is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty ofMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See theGNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License along with Crazy Puzzle. 
 *  If not, see <http://www.gnu.org/licenses/>.For feedback please mail at either of the below mentioned email id
 *  bdombrow@cecs.pdx.edu /hemasid@gmail.com / fwsliz@gmail.com / electronseu@gmail.com.
 *                             
 **/

package com.numbergame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class NumberGameProjectActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// this.bHelp = (Button) this.findViewById(R.id.Help);
		// bHelp.setOnClickListener(myListener);

		Thread logoTimer = new Thread() {
			@Override
			public void run() {
				try {
					int logoTimer = 0;
					while (logoTimer < 5000) {
						sleep(100);
						logoTimer = logoTimer + 300;
					}
					startActivity(new Intent("com.numbergame.MenuScreen"));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					finish();
				}
			}
		};
		logoTimer.start();

	}

	/*
	 * View.OnClickListener myListener = new View.OnClickListener(){
	 * 
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub switch(v.getId()){ case R.id.Help: AlertDialog.Builder alert = new
	 * AlertDialog.Builder(NumberGameProjectActivity.this); alert.setMessage(
	 * "DUMMY DUMMY DUMMY DUMMY DUMMY DUMMYDUMMY DUMMY DUMMYDUMMY DUMMY DUMMY");
	 * alert.show(); break; }
	 * 
	 * }
	 * 
	 * };
	 */

}
