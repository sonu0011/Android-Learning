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
        setContentView(R.layout.activity_threading_examples);
        Log.d(TAG, "onCreate: thread name "+Thread.currentThread().getName());
//        new exampleThread().start();
//        new exampleThread().start();
        button = findViewById(R.id.mybtn);
//        new Thread( new RunnableThread()).start();
            new Thread(() -> {
                button.setText("sdffsf");
            }).start();
//        new RunnableThread().run();
    }

    public void startThread(View view) {
        for (int i = 0; i <10 ; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

     class  exampleThread extends Thread{
        @Override
        public void run() {
            Log.d(TAG, "run: "+Thread.currentThread().getName());
            super.run();
            for (int i = 0; i <10 ; i++) {
                button.setText(String.valueOf(i));
                Log.d(TAG, "run: "+i);
            }
        }
    }

     class RunnableThread implements Runnable{

        @Override
        public void run() {
            Log.d(TAG, "run: "+Thread.currentThread().getName());
            for (int i = 0; i <10 ; i++) {
                button.setText(String.valueOf(i));
                Log.d(TAG, "run: "+i);
            }
        }
    }
}