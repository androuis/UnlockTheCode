package com.example.unlockthecode.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class EqualyDistributedLayout extends ViewGroup {

	private List<View> views = new ArrayList<View>();
	
	public EqualyDistributedLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public EqualyDistributedLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub

	}

}
