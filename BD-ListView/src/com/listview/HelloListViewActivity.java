/*
 * Brent Dombrowski
 * 10-15-2011
 * 
 * List View example from
 * http://developer.android.com/resources/tutorials/views/hello-listview.html
 * 
 * Goals: 
 * 	Replace the countries with the valid math equations.
 * 	Change the on click to display the points associated with the equation.
 */

package com.listview;

import java.util.Hashtable;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HelloListViewActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] equations = getResources().getStringArray(R.array.equations);
        String[] scores = getResources().getStringArray(R.array.scores);
    
        
        for (int i = 0; i < equations.length ; ++i) {
        	equationTable.put(equations[i], Integer.parseInt(scores[i]));
        }
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, equations));
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int postition, long id) {
        		// When clicked, show a toast with the TextView text
        		int score = CheckEquation(((TextView) view).getText());
        		Toast.makeText(getApplicationContext(), Integer.toString(score), Toast.LENGTH_SHORT).show();
        		//Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
        		}
        });
    }
    
    public int CheckEquation(CharSequence inputString) {
    	Integer result = (Integer)equationTable.get(inputString);
    	if (result != null) {
    		return result;
    	}
    	return 0;
    	
    }
    
    private Hashtable equationTable = new Hashtable();
   
}