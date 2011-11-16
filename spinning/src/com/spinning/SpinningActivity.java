package com.spinning;

import android.app.Activity;
import android.os.Bundle;

public class SpinningActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView(new GraphicsView(this)); 
        }
    }
