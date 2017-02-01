package com.example.vasiliy.aninterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SubClass.MyCallback {

    private TextView mResultTextView;
    private SubClass mSubClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResultTextView = (TextView)findViewById(R.id.textView3);

        mSubClass = new SubClass();
        mSubClass.registerCallBack(this);


    }

    public void onClick(View v) {
        Log.i("myTag", "MainActivity - onClick");
        mSubClass.doSomething();
    }

    @Override
    public void callBackReturn() {
        Log.i("myTag", "MainActivity - callBackReturn");
        mResultTextView.setText("Вызван метод обратного вызова");
    }
}
