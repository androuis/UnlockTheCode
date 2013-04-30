package com.example.unlockthecode.adapters;

import java.util.ArrayList;
import java.util.List;

import com.example.unlockthecode.models.CodeTrial;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TrialsAdapter extends BaseAdapter {

	private List<CodeTrial> triedCodes = new ArrayList<CodeTrial>();
	
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
		// TODO Auto-generated method stub
		return null;
	}

}
