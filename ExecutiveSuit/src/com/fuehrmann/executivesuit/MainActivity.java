package com.fuehrmann.executivesuit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.fuehrmann.executivesuit.MESSAGE";
	
    @TargetApi(11)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll = (LinearLayout) findViewById(R.id.content);
        ll.setShowDividers(LinearLayout.SHOW_DIVIDER_BEGINNING);
        ll.setBackgroundColor(Color.BLACK);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	super.onDestroy();
    }
    
    public void nextScreen(View view){
    	Intent intent = new Intent(this, BeforeInterviewActivity.class);
    	TextView textView = (TextView) findViewById(R.id.text1);
    	String text = textView.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, text);
    	startActivity(intent);
    }
}
