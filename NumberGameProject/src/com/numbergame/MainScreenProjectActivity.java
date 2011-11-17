package com.numbergame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreenProjectActivity extends Activity {
	private Button bHelp = null;
	private Button bExit = null;
	private Button bEquations = null;
	private Button bNumbers = null;
	private Button bMute = null;
	MediaPlayer mpSplash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu);
		// Button bHelp = (Button) findViewById(R.id.Help);
		// bHelp.setOnClickListener(new View.OnClickListener() {
		mpSplash = MediaPlayer.create(this, R.raw.daybreak);
	
		mpSplash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mp.start();
			}
		});
		mpSplash.start();

		// })
		this.bEquations = (Button) this.findViewById(R.id.Equation);
		this.bNumbers = (Button) this.findViewById(R.id.Numbers);
		this.bHelp = (Button) this.findViewById(R.id.Help);
		this.bExit = (Button) this.findViewById(R.id.Exit);
		this.bMute = (Button) this.findViewById(R.id.Mute);

		bEquations.setOnClickListener(myListener);
		bNumbers.setOnClickListener(myListener);
		bHelp.setOnClickListener(myListener);
		bExit.setOnClickListener(myListener);
		bMute.setOnClickListener(myListener);
	}

	View.OnClickListener myListener = new View.OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.Equation:

				startActivity(new Intent("com.numbergame.PuzzleProjectActivity"));
				// Intent msg = new
				// Intent(MainScreenProjectActivity.this,PuzzleProjectActivity.class);
				// MainScreenProjectActivity.this.startActivity(msg);
				break;
			case R.id.Numbers:
				Intent msg1 = new Intent(MainScreenProjectActivity.this,
						NumbersProjectActivity.class);
				MainScreenProjectActivity.this.startActivity(msg1);
				break;
			case R.id.Exit:
				// finish();
				startActivity(new Intent("com.numbergame.OptionActivity"));
				break;
			case R.id.Mute:
				// finish();
				if(mpSplash.isPlaying())
				{
				mpSplash.pause();
				}
				else
					mpSplash.start();
				break;
			case R.id.Help:
				AlertDialog.Builder alert = new AlertDialog.Builder(
						MainScreenProjectActivity.this);
				alert.setMessage("The Crazy Game has two modes.\nThe first allows users to define basic mathematic equations using digits 0 to 9 and the addition, subtraction and multiplication operators.\n"
						+ "Equations can be formed either horizontally from left to right or vertically from top to bottom.\n"
						+ " A candidate equation is submitted by dragging a finger across the screen.\n"
						+ "\nIn the second mode, the users are presented with a random board of numbers from 1 to 24.\nIn this mode, the board also contains the empty black tile.\n"
						+ "The users must order the numbers sequentially in ascending order.");
				CharSequence ok = "Ok";
				alert.setPositiveButton(ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
								arg0.dismiss();
							}
						});

				alert.show();
				break;
			}
		}
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mpSplash.release();

	}

}
