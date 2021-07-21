package com.sonu.learning.workManager;

import android.app.Notification;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.ForegroundInfo;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.sonu.learning.R;

import java.util.Random;

import static com.sonu.learning.App.CHANNEL_ID;

public class Work1 extends Worker {
    private static final String TAG = "###WORK1";

    public Work1(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: threadname" + Thread.currentThread().getName());
        setForegroundAsync(getForeGroundInfo());
        generateRandmonNumber();
        return Result.success();
    }

    private ForegroundInfo getForeGroundInfo() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Music notificaiton")
                .setContentTitle("Music Tile");
        Notification notification = builder.build();
        return new ForegroundInfo(123, notification);
    }


    private void generateRandmonNumber() {
        int i = 0;
//        while (i++ < 5 && !isStopped()) {
        while (true) {
            SystemClock.sleep(1000);
            Log.d(TAG, "generateRandmonNumber: random no is " + new Random().nextInt(1000));
        }
    }

    @Override
    public void onStopped() {
        super.onStopped();
    }
}
