package com.SE.CrazyPuzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartUp extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startup);
        Thread logoTimer = new Thread(){
        	public void run()
        	{
        		try
        		{
        			int logoTimer = 0;
        			while(logoTimer < 1000)
        			{
        				sleep(100);
        				logoTimer = logoTimer + 100;
        			}
        			startActivity( new Intent("com.SE.MenuScreen") );
        		} 
        		catch (InterruptedException e) 
        		{
					e.printStackTrace();
				}
        		finally
        		{
        			finish();
        		}
        	}
        };
        logoTimer.start();
	}
}