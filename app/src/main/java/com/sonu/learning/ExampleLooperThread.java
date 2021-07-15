package com.sonu.learning;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class ExampleLooperThread extends Thread {
    private static final String TAG = "ExampleLooperThread";
    public Handler handler;
    public Looper looper;

    @Override
    public void run() {
        super.run();

        //create looper and message queue
        Looper.prepare();

        looper = Looper.myLooper();
        handler = new ThreadHandler();

        //infinite loop
        Looper.loop();

        Log.d(TAG, "run: thread ends");


    }
}
