package com.sonu.learning;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import androidx.annotation.NonNull;

public class CustomHandlerThread extends HandlerThread {

    private static final String TAG = "CustomHandlerThread";
    private Handler handler;

    public CustomHandlerThread() {
        super("HandlerThread", Process.THREAD_PRIORITY_BACKGROUND);
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        //on looper prepared is in between looper.prepare and looper.loop  in run method of HandlerThread
//        handler = new Handler();

        //override handlemessage to process message

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {

                if (msg.what == 1) {
                    //process task A
                    for (int i = 0; i < 4; i++) {
                        Log.d(TAG, "run: task1 " + i);
                    }
                }
                if (msg.what == 2) {
                    for (int i = 0; i < 4; i++) {
                        Log.d(TAG, "run: task 2 " + i);
                    }
                }
                //other conditions
            }
        };
    }

    public Handler getHandler() {
        return handler;
    }
}
