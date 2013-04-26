package com.example.unlockthecode;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private Keyboard keyboardLayout;
	private KeyboardView keyboardView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		keyboardLayout = new Keyboard(this, R.layout.keyboard_layout);
		keyboardView = (KeyboardView) findViewById(R.id.keyboard_view);
		keyboardView.setKeyboard(keyboardLayout);
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
