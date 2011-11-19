package com.numbergame;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;


public class NumberGameProjectActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       // this.bHelp = (Button) this.findViewById(R.id.Help); 
        //bHelp.setOnClickListener(myListener);
       
                Thread logoTimer = new Thread(){
        	@Override
			public void run(){
        		try{
        			int logoTimer = 0;
        			while(logoTimer <5000){
        				sleep(100);
        				logoTimer = logoTimer + 300;
        			}
        			startActivity( new Intent("com.numbergame.MenuScreen"));
        		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		finally{
        			finish();
        		}
        	}
        };
        logoTimer.start();
        
    }
    
    /*
    View.OnClickListener myListener = new View.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.Help:
				AlertDialog.Builder alert = new AlertDialog.Builder(NumberGameProjectActivity.this);
				alert.setMessage("DUMMY DUMMY DUMMY DUMMY DUMMY DUMMYDUMMY DUMMY DUMMYDUMMY DUMMY DUMMY");
				alert.show();
				break;
			}
			
		}  
    	
    };*/
    
}


