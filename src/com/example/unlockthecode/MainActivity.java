package com.example.unlockthecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unlockthecode.models.CodeTrial;
import com.example.unlockthecode.views.MultipleEditText;

public class MainActivity extends Activity implements OnKeyboardActionListener {

	private Keyboard keyboardLayout;
	private KeyboardView keyboardView;
	private MultipleEditText multipleEditText;
	
	private List<CodeTrial> triedCodes = new ArrayList<CodeTrial>();
	private List<String> theCode;
	
	/*
	 * Activity lifecycle methods
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initLogic();		
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	/*
	 * OnKeyboardActionListener override methods
	 */

	@Override
	public void onKey(int primaryCode, int[] keyCodes)  {}

	@Override
	public void onPress(int primaryCode) {
		if (primaryCode == KeyEvent.KEYCODE_ENTER) {
			if (!multipleEditText.areAllSpacesCompleted()) {
				Toast.makeText(this, "Complete the hole code", Toast.LENGTH_SHORT).show();
			} else {
				checkCode(multipleEditText.getCode());
			}
		}
	}

	@Override
	public void onRelease(int primaryCode) {
		if (primaryCode != KeyEvent.KEYCODE_ENTER) {
			multipleEditText.addText(String.valueOf(primaryCode - 7));
		} else {
			if (!multipleEditText.areAllSpacesCompleted()) {
				Toast.makeText(this, "Complete the hole code", Toast.LENGTH_SHORT).show();
			} else {
				checkCode(multipleEditText.getCode());
			}
		}
	}

	@Override
	public void onText(CharSequence text) {}

	@Override
	public void swipeDown() {}

	@Override
	public void swipeLeft() {}

	@Override
	public void swipeRight() {}

	@Override
	public void swipeUp() {}
	
	/*
	 * Activity logic methods
	 */
	
	/**
	 * Sets the contentView of the activity. <br/> 
	 * Instantiates, sets properties & listeners
	 * for all View and Layout objects
	 */
	private void initView() {
		setContentView(R.layout.activity_main);
		
		keyboardLayout = new Keyboard(this, R.layout.keyboard_layout);
		keyboardView = (KeyboardView) findViewById(R.id.keyboard_view);
		keyboardView.setKeyboard(keyboardLayout);
		keyboardView.setOnKeyboardActionListener(this);
		multipleEditText = (MultipleEditText) findViewById(R.id.keyboard_input);
	}

	/**
	 * Initializes the code to be guessed.
	 */
	private void initLogic() {
		Random randomGenerator = new Random();
		theCode = new ArrayList<String>(multipleEditText.getChildCount());
		for (int i = 0; i < multipleEditText.getChildCount(); i++) {
			theCode.add(String.valueOf(randomGenerator.nextInt(multipleEditText.getMaxCharactersForCodeEntry() * 10)));
		}
		
		TextView codeHint = (TextView) findViewById(R.id.code_hint);
		StringBuffer sb = new StringBuffer();
		for (String code: theCode) {
			sb.append(code);
		}
		codeHint.setText(sb.toString());
	}
	
	/**
	 * Creates a new CodeTrial that is set in the triedCodes list. <br/>
	 * Checks if the inputed code is correct.
	 * @param code the code entered by the user
	 */
	private void checkCode(String[] code) {
		CodeTrial codeTrial = new CodeTrial();
		for (int i = 0; i < code.length; i++) {
			codeTrial.addTrialEntry(code[i]);
			int index = -1;
			if ((index = theCode.indexOf(code[i])) != -1) {
				if (index == i) {
					codeTrial.addTrialState(CodeTrial.TrialState.OK);
				} else {
					codeTrial.addTrialState(CodeTrial.TrialState.AOK);
				}
			} else {
				codeTrial.addTrialState(CodeTrial.TrialState.NOK);
			}
		}
		
		triedCodes.add(codeTrial);
		if (codeTrial.isAllCorrect()) {
			Toast.makeText(this, "YOU WON!", Toast.LENGTH_SHORT).show();
		}
	}

}
