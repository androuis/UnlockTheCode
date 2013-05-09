package com.example.unlockthecode.views;

import java.util.ArrayList;
import java.util.List;

import com.example.unlockthecode.helpers.UIHelper;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

public class EqualyDistributedLayout extends ViewGroup {

	private List<TextView> views = new ArrayList<TextView>();
	private int elementMaxSize;
	
	public EqualyDistributedLayout(Context context, int elementSize, int elementMargin, int elementsNumber) {
		this(context);
		elementMaxSize = elementSize + elementMargin;
		for (int i = 0; i < elementSize; i++) {
			views.add(createView());
			addView(views.get(views.size() - 1));
		}
	}
	
	public EqualyDistributedLayout(Context context, int elementsMaxSize, int elementsNumber, List<String> trialEntry) {
		this(context);
		this.elementMaxSize = elementsMaxSize;
		addTextViewElements(trialEntry);
		setLayoutParams(new AbsListView.LayoutParams(100, 16));
/*		for (int i = 0; i < elementsNumber; i++) {
			views.add(createTextView());
			addView(views.get(views.size() - 1));
		}*/
	}

	public EqualyDistributedLayout(Context context) {
		super(context);
	}

	public EqualyDistributedLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/*
	 * OBJECT METHODS
	 */
	
	private TextView createView() {
		TextView view = new TextView(getContext());
		view.setId(UIHelper.generateRandomInt());
		view.setLayoutParams(new AbsListView.LayoutParams(11, 11));	
		return view;
	}
	
	/*
	 * OVERRIDEN METHODS
	 */
	
	@Override
	public int getChildCount() {
		return views.size();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for(int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			int leftPosition = 0;
			if (i != 0) {
				leftPosition = views.get(i-1).getRight();
			} 
			view.layout(leftPosition, 50, leftPosition + (getMeasuredWidth() / elementMaxSize), 60);
	    }
	}
	
	@Override
	public void addView(View child) {
		views.add((TextView) child);
		super.addView(child);
	}
	
	/*
	 * PUBLIC METHODS
	 */

	public void addTextViewElements(List<String> trialEntry) {
		for (String trial: trialEntry) {
			TextView tv = createView();
			tv.setGravity(Gravity.CENTER_HORIZONTAL);
			tv.setTextSize(15);
			tv.setText(trial);
			tv.setTextColor(getResources().getColor(R.color.black));
			addView(tv);
		}
		
	}

	public void setTextViewElements(List<String> trialEntry) {
		for (int i = 0; i < trialEntry.size(); i++) {
			views.get(i).setText(trialEntry.get(i));
		}
	}
}
