package com.sonu.learning.services;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

public class SongDownloaderService extends Service {
    private static final String TAG = "##SongDownloaderService";
    HandlerThread handlerThread = new HandlerThread("handlerThread");
    DownloadHandler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        handlerThread.start();
        handler = new DownloadHandler(handlerThread.getLooper());
        handler.setSongDownloaderService(this, getApplicationContext());

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: startId " + startId);
        Message message = Message.obtain();
        message.what = startId;
        if (intent != null) {
            message.obj = intent.getStringExtra("songName");
        } else {
            message.obj = "empty song";
        }
        handler.sendMessage(message);

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}
