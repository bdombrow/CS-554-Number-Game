package com.sample.dragdrop;

import android.app.Activity;
import android.os.Bundle;

/*public class DragNewActivity extends Activity {
 *//** Called when the activity is first created. *//*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}*/

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class DragNewActivity extends Activity {
	private View selected_item = null;
	private int offset_x = 0;
	private int offset_y = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ViewGroup vg = (ViewGroup)findViewById(R.id.vg);
		vg.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getActionMasked())
				{
				case MotionEvent.ACTION_MOVE:
					int x = (int)event.getX() - offset_x;
					int y = (int)event.getY() - offset_y;
					int w = getWindowManager().getDefaultDisplay().getWidth() - 100;
					int h = getWindowManager().getDefaultDisplay().getHeight() - 100;
					if(x > w)
						x = w;
					if(y > h)
						y = h;
					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
							new ViewGroup.MarginLayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));
					lp.setMargins(x, y, 0, 0);
					selected_item.setLayoutParams(lp);
					break;
				default:
					break;
				}
				return true;
			}
		});
		ImageView img = (ImageView)findViewById(R.id.img);
		img.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getActionMasked())
				{
				case MotionEvent.ACTION_DOWN:
					offset_x = (int)event.getX();
					offset_y = (int)event.getY();
					selected_item = v;
					break;
				default:
					break;
				}

				return false;
			}
		});
	}
}