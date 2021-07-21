package com.sonu.learning.postOreo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: ");
        //return true to indicate that the job will take long time and return false for
        //small time taking job
        for (int i = 0; i < 100; i++) {
            SystemClock.sleep(1000);
            Log.d(TAG, "onStartJob: thread " + Thread.currentThread().getName() + " work " + i);
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: ");
        //return true to reschedule the job and false for not
        return true;
    }
}
