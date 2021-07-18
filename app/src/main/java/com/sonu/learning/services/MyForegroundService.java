package com.sonu.learning.services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.sonu.learning.R;

public class MyForegroundService extends Service {
    private static final String TAG = "####MyForegroundService";

    public MyForegroundService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ShowNotification();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    SystemClock.sleep(2000);
                    Log.d(TAG, "run: service is running");
                }
                stopForeground(true);
                stopSelf();
            }
        }).start();
        return START_STICKY;
    }

    private void ShowNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "channelId");
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Music notificaiton")
                .setContentText("Music descirption");

        Notification notification = builder.build();
        startForeground(1, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}