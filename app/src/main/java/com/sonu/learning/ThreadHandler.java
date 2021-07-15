package com.sonu.learning;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class ThreadHandler extends Handler {

    private static final String TAG = "ThreadHandler";

    @Override
    public void handleMessage(@NonNull Message msg) {
        if (msg.what == 1) {
            for (int i = 0; i < 10; i++) {
                Log.d(TAG, "JOB A" + Thread.currentThread().getName() + " " + i);
            }
        }

        if (msg.what == 2) {
            for (int i = 0; i < 10; i++) {
                Log.d(TAG, "JOB B" + Thread.currentThread().getName() + " " + i);
            }
        }
    }
}
