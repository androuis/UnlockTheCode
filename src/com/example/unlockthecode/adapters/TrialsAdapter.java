package com.example.unlockthecode.adapters;

import java.util.ArrayList;
import java.util.List;

import com.example.unlockthecode.models.CodeTrial;
import com.example.unlockthecode.views.EqualyDistributedLayout;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TrialsAdapter extends BaseAdapter {

	private List<CodeTrial> triedCodes = new ArrayList<CodeTrial>();
	private Context viewContext;
	private int elementMaxSize;
	private int elementsNumber;
	
	public TrialsAdapter(Context viewContext) {
		this.viewContext = viewContext;
	}
	
	public TrialsAdapter(Context viewContext, int elementSize, int elementMargin, int elementsNumber) {
		this(viewContext);
		this.elementMaxSize = elementSize + elementMargin;
		this.elementsNumber = elementsNumber;
	}
	
	@Override
	public int getCount() {
		return triedCodes.size();
	}

	@Override
	public CodeTrial getItem(int position) {
		return triedCodes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		EqualyDistributedLayout equalyDistributedLayout = null;
		if (convertView == null) {
			equalyDistributedLayout = new EqualyDistributedLayout(viewContext, elementMaxSize, elementsNumber, getItem(position).getTrialEntry());
		} else {
			equalyDistributedLayout = (EqualyDistributedLayout) convertView;
			equalyDistributedLayout.setTextViewElements(getItem(position).getTrialEntry());
		}
		return equalyDistributedLayout;
	}

	public void addTrial(CodeTrial codeTrial) {
		triedCodes.add(codeTrial);
		notifyDataSetChanged();		
	}

}
