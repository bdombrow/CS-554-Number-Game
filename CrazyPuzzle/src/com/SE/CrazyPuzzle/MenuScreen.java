package com.SE.CrazyPuzzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.ImageView;

public class MenuScreen extends Activity 
{
	private Button bHelp = null;
	private Button bExit = null;
	private Button bEquations = null;
	private Button bNumbers = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu);

		this.bEquations = (Button) this.findViewById(R.id.Equation);
		this.bNumbers = (Button) this.findViewById(R.id.Numbers);
		this.bHelp = (Button) this.findViewById(R.id.Help);   
		this.bExit = (Button) this.findViewById(R.id.Exit);
		
		bEquations.setOnClickListener(btnListener);
		bNumbers.setOnClickListener(btnListener);
		bHelp.setOnClickListener(btnListener);
		bExit.setOnClickListener(btnListener);
	}

	View.OnClickListener btnListener = new View.OnClickListener(){   
		public void onClick (View v)
		{        	
			switch(v.getId())
			{
			case R.id.Equation:	
				startActivity( new Intent("com.SE.CrazyPuzzleActivity") );
				break;
				
			case R.id.Numbers:
				break;
				
			case R.id.Exit:
				finish();
				break;
				
			case R.id.Help:
				AlertDialog.Builder alert = new AlertDialog.Builder(MenuScreen.this);
				alert.setMessage("DUMMY DUMMY DUMMY DUMMY DUMMY DUMMYDUMMY DUMMY DUMMYDUMMY DUMMY DUMMY");
				alert.show();
				break;
			}
		}
	};
}