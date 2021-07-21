package com.sonu.learning.postOreo;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import java.util.Random;


public class MyJobIntentService extends JobIntentService {
    private static final String TAG = "MyJobIntentService";

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, MyJobIntentService.class, 101, intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public boolean onStopCurrentWork() {
        Log.d(TAG, "onStopCurrentWork: ");

        return super.onStopCurrentWork();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "onHandleWork: threadName " + Thread.currentThread().getName());
        while (true) {
            if (isStopped()) return;
            SystemClock.sleep(1000);
            int a = new Random().nextInt(1000);
            Log.d(TAG, "onHandleWork: random numb is " + a);
        }
    }
}
