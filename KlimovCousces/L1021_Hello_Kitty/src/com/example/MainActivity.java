package com.example;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tvHello;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //tvHello = (TextView) findViewById(R.id.textView1);
    }

    
    public void onClick(View v){
    	tvHello.setText("Hello Kitty!");
    }
}