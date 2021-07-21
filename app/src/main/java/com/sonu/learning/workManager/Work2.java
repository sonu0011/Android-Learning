package com.sonu.learning.workManager;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Random;

public class Work2 extends Worker {
    private static final String TAG = "###WORK2";

    public Work2(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: threadname" + Thread.currentThread().getName());
        generateRandmonNumber();
        return Result.success();
    }

    private void generateRandmonNumber() {
        int i = 0;
        while (i++ < 5 && !isStopped()) {
            SystemClock.sleep(1000);
            Log.d(TAG, "generateRandmonNumber: random no is " + new Random().nextInt(1000));
        }
    }

    @Override
    public void onStopped() {
        super.onStopped();
    }
}
