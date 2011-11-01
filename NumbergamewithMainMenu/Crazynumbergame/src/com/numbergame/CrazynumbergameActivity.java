package com.numbergame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class CrazynumbergameActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Thread logoTimer = new Thread(){
        	public void run(){
        		try{
        			int logoTimer = 0;
        			while(logoTimer <5000){
        				sleep(100);
        				logoTimer = logoTimer + 300;
        			}
        			startActivity( new Intent("com.numbergame.CrazyPuzzleActivity"));
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
}