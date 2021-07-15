package com.sonu.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThreadingExamples extends AppCompatActivity {
    private static final String TAG = "###ThreadingExamples";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: "+Thread.currentThread().getName());
        setContentView(R.layout.activity_threading_examples);
        }
}