package com.numbergame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.ImageView;

public class MenuScreen extends Activity {
     private Button bHelp = null;
     private Button bExit = null;
     private Button bEquations = null;
     private Button bNumbers = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.mainmenu);
		// Button bHelp = (Button) findViewById(R.id.Help);
		// bHelp.setOnClickListener(new View.OnClickListener() {
			
			
		//})
		 this.bEquations = (Button) this.findViewById(R.id.Equation);
		 this.bNumbers = (Button) this.findViewById(R.id.Numbers);
		 this.bHelp = (Button) this.findViewById(R.id.Help);   
		 this.bExit = (Button) this.findViewById(R.id.Exit);
	     
	bEquations.setOnClickListener(myListener);
	bNumbers.setOnClickListener(myListener);
	bHelp.setOnClickListener(myListener);
	bExit.setOnClickListener(myListener);
}

	View.OnClickListener myListener = new View.OnClickListener(){   
		public void  onClick  (View  v){        	
			switch(v.getId()){
			case R.id.Equation:	

				startActivity(new Intent("com.numbergame.CrazyPuzzleActivity"));

				break;	    	
			case R.id.Numbers:
				break;
			case R.id.Exit:
				finish();
				break;
			case R.id.Help:
				AlertDialog.Builder alert = new AlertDialog.Builder(MenuScreen.this);
				alert.setMessage("Arrange the tiles to form math equations\n Swipe accross to submit the equation and score points.\n The same equation can not be used more than once.");
				alert.show();
				break;
			}
		}
	};
}