package com.example.unlockthecode.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.EditText;


import com.example.unlockthecode.R;

public class MultipleEditText extends ViewGroup implements OnTouchListener {

	private int numberOfEditTexts;
	private int charactersInEditText;
	private int spaceBetweenElements;
	
	private List<EditText> editTexts;
	private int selectedEditTextNumber = -1;	

	public MultipleEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MultipleEditText, 0, 0);
		try {
			numberOfEditTexts = ta.getInteger(R.styleable.MultipleEditText_numberOfEditTexts, 1);
			charactersInEditText = ta.getInteger(R.styleable.MultipleEditText_charactersInEditText, 1);
			spaceBetweenElements = ta.getDimensionPixelSize(R.styleable.MultipleEditText_spaceBetweenElements, 5);
		} finally {
			ta.recycle();
		}

		editTexts = new ArrayList<EditText>(numberOfEditTexts);
		for(int i = 0; i < getChildCount(); i++){
			editTexts.add(createDefaultEditText());
	        addView(editTexts.get(i));
	    }
		selectedEditTextNumber = 0;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////     OBJECT METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private EditText createDefaultEditText() {
		EditText et = new EditText(getContext());
		Random randomGenerator = new Random();
		et.setId(randomGenerator.nextInt());
		et.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		et.setGravity(Gravity.CENTER);
		et.setOnTouchListener(this);
		return et;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////     OVERRIDEN METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public int getChildCount() {
		return numberOfEditTexts;
	}	

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int itemWidth = (r - l) / getChildCount();
		for(int i = 0; i < getChildCount(); i++) {
			EditText et = (EditText) getChildAt(i);
			et.layout((itemWidth * i)+spaceBetweenElements, t+spaceBetweenElements, ((i + 1) * itemWidth)-spaceBetweenElements, 60 + spaceBetweenElements);
	    }
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		selectedEditTextNumber = editTexts.indexOf(v);
		v.requestFocus();
		return true;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////     PUBLIC METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public EditText getCurrentSelectedEditText() {
		return selectedEditTextNumber != -1 ? editTexts.get(selectedEditTextNumber) : null;
	}
	
	public void addText(String textToAdd) {
		String currentText = getCurrentSelectedEditText().getText().toString();
		String finalText = null;
		if (currentText.length() == charactersInEditText) {
			finalText = textToAdd;
		} else {
			finalText = currentText + textToAdd;
		}
		getCurrentSelectedEditText().setText(finalText);
		if (getCurrentSelectedEditText().getText().toString().length() == charactersInEditText) {
			focusTheNextView();
		}
	}

	public void focusTheNextView() {
		int nextSelectedEditTextNumber = selectedEditTextNumber == editTexts.size() - 1 ? 
										0 : 
										selectedEditTextNumber + 1;
		editTexts.get(nextSelectedEditTextNumber).requestFocus();
		selectedEditTextNumber = nextSelectedEditTextNumber;
	}
	
	public boolean areAllSpacesCompleted() {
		for (EditText et: editTexts) {
			if (et.getText().toString().length() == 0) {
				return false;
			}
		}
		return true;
	}

	public String[] getCode() {
		String[] code = new String[getChildCount()];
		int index = 0;
		for(EditText et: editTexts) {
			code[index++] = et.getText().toString();
	    }
		return code;
	}
	
	public int getMaxCharactersForCodeEntry() {
		return charactersInEditText;
	}

}
