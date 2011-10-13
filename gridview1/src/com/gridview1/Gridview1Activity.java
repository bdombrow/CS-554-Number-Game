package com.gridview1;

import android.app.Activity;
import android.os.Bundle;
//package com.GridViewwithimages;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Gridview1Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gridview1.R.layout.main);//R.layout.main);
        GridView gridview = (GridView) findViewById(com.gridview1.R.id.gridview);        //R.id.gridview); 
        gridview.setAdapter(new ImageAdapter(this));
    }
    public class ImageAdapter extends BaseAdapter {
    	private Context mContext;  
    	public ImageAdapter(Context c) {  
    		mContext = c;   
    		}    
    	public int getCount() {  
    		return mThumbIds.length;    
    		}    
    	public Object getItem(int position) {
    		return null;   
    		}
    	public long getItemId(int position) {   
    		return 0;   
    		}    // create a new ImageView for each item referenced by the Adapter 
    	public View getView(int position, View convertView, ViewGroup parent) {
    		ImageView imageView;       
    		if (convertView == null) {  
    			// if it's not recycled, initialize some attributes  
    			imageView = new ImageView(mContext);   
    			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));   
    			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);        
    			imageView.setPadding(8, 8, 8, 8); 
//    			imageView.setOnClickListener(new View.OnClickListener() { 
//    				 
//    			      @Override 
//    			      public void onClick(View view) { 
//    			        Log.d("onClick","position ["+position+"]"); 
//    			      };
//    			}

    			} else {         
    				imageView = (ImageView) convertView;
    				}
    		imageView.setImageResource(mThumbIds[position]);   
    		return imageView;    }    // references to our images  
    	private Integer[] mThumbIds = {       
    			//R.drawable.icon.png
    			//.R.drawable.
    			com.gridview1.R.drawable.icon, com.gridview1.R.drawable.icon,  
    			com.gridview1.R.drawable.icon, com.gridview1.R.drawable.icon,    
    			com.gridview1.R.drawable.icon, com.gridview1.R.drawable.icon,         
    			com.gridview1.R.drawable.icon, com.gridview1.R.drawable.icon,          
    			com.gridview1.R.drawable.icon, com.gridview1.R.drawable.icon,       
    			com.gridview1.R.drawable.icon, com.gridview1.R.drawable.icon,          
    			com.gridview1.R.drawable.icon, com.gridview1.R.drawable.icon,          
    			com.gridview1.R.drawable.icon, com.gridview1.R.drawable.icon,           
    			com.gridview1.R.drawable.icon, com.gridview1.R.drawable.icon,          
    			com.gridview1.R.drawable.icon, com.gridview1.R.drawable.icon,           
    			com.gridview1.R.drawable.icon, com.gridview1.R.drawable.icon  
    			};
    	}
    		

	
    
}