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
import java.util.ArrayList;

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
        
        // Create the lists of valid equations and their scores
        ArrayList equationsAL = new ArrayList(152);
        ArrayList scoresAL = new ArrayList(152);
        
        for (int i = 0; i < 10; ++i) {
        	for (int j = 0; j < 10; ++j) {
        		if ((i + j < 10) && (i <= j)) {
        			equationsAL.add(i + "+" + j + "=" + (i + j) );
        			scoresAL.add((i+j));
        		}
        		if (i - j >= 0) {
        			equationsAL.add(i + "-" + j + "=" + (i - j));
        			scoresAL.add((i+j+10));
        		}
        		if ((i * j < 10) && (i < j)) {
        			equationsAL.add(i + "*" + j + "=" + (i * j));
        			scoresAL.add((i+j+20));
        		}
        	}
        }
    
        // Load the hash table
        for (int i = 0; i < scoresAL.size() ; ++i) {
        	equationTable.put(equationsAL.get(i), scoresAL.get(i));
        }
        
        // Add in a bogus equation for testing.
        equationsAL.add("1+1=1");

        // Add in the non-normalized equations
        for (int i = 0; i < 10; ++i) {
        	for (int j = 0; j < 10; ++j) {
        		if ((i + j < 10) && (i > j)) {
        			equationsAL.add(i + "+" + j + "=" + (i + j));
        		}
        		if ((i * j < 10) && (i > j)) {
        			equationsAL.add(i + "*" + j + "=" + (i * j));
        		}
        	}
        }
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, equationsAL));
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int postition, long id) {
        		// When clicked, show a toast with the TextView text
        		int score = CheckEquation(((TextView) view).getText());
        		Toast.makeText(getApplicationContext(), Integer.toString(score), Toast.LENGTH_SHORT).show();
        		}
        });
    }
    
    /*
     * Look up an equation in the hash table.
     * 
     * INPUT: string representing the equation to look up.
     * OUTPUT: The score or -1 if the equation is not found. 
     */
    public int CheckEquation(CharSequence inputString) {
    	Integer result = (Integer)equationTable.get(NormalizeEquation(inputString));
    	if (result != null) {
    		// Note, it would be possible to pull equation out of the table here.
    		return result;
    	}
    	return -1;
    	
    }
    
    public String NormalizeEquation(CharSequence inputCharSequence) {
    	// Make sure it's the right length
    	if (inputCharSequence.length() != 5) {
    		return null;
    	}
    	// CharSequences are read only, convert to a string to manipulate.
    	String inputString = inputCharSequence.toString();
    	
    	// Is there an = at position 2?
    	if (inputString.charAt(1) == '=') {
    		// Need to flip it
    		inputString = inputString.substring(2) + "=" + inputString.charAt(0);
    	}
    	// Is it addition or multiplication?
    	if ( (inputString.charAt(1) == '+') || (inputString.charAt(1) == '*')) {
    		// Make sure the smaller number is on the left.
    		char x = inputString.charAt(0);
    		char y = inputString.charAt(2);
    		if (x > y) { // Hope this part works.
    			inputString = Character.toString(y) + inputString.charAt(1) + Character.toString(x) + inputString.substring(3, 5);
    			//Toast.makeText(getApplicationContext(), "N:" + inputString, Toast.LENGTH_SHORT).show();
    		}
    	}
    	
    	return inputString;
    }
    
    private Hashtable equationTable = new Hashtable();
   
}