package com.numbergame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreenProjectActivity extends Activity{
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
					    	
				startActivity(new Intent("com.numbergame.PuzzleProjectActivity"));	
//				Intent msg = new Intent(MainScreenProjectActivity.this,PuzzleProjectActivity.class);	
//					MainScreenProjectActivity.this.startActivity(msg);	    	
					break;	    	
			case R.id.Numbers:
				Intent msg1 = new Intent(MainScreenProjectActivity.this,NumbersProjectActivity.class);	
				MainScreenProjectActivity.this.startActivity(msg1);	 
				break;
			case R.id.Exit:
				finish();
				break;
			case R.id.Help:
				AlertDialog.Builder alert = new AlertDialog.Builder(MainScreenProjectActivity.this);
				alert.setMessage("DUMMY DUMMY DUMMY DUMMY DUMMY DUMMYDUMMY DUMMY DUMMYDUMMY DUMMY DUMMY");
				alert.show();
				break;
				}
			}
		};
		

	}
    

