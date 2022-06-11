package com.nicolas.nfchcejava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.nicolas.nfchcejava.log.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView logTextView = findViewById(R.id.logTextView);
        Log.setLogTextView(logTextView);
        Log.reset(TAG, "onCreate()");
    }
}
