package com.sonu.learning.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;


//stops after work finishes
// set deliveryIntent to true if you want to reschedule you service ie. it works like START_REDELIVERY_INTENT
// automatically calls stopSelf(id) so if sent intent processed it will not process that intent again
public class MyIntentService extends IntentService {
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


    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        downloadSong(intent.getStringExtra("songName"));
    }

    private void downloadSong(String songName) {
        SystemClock.sleep(4000);
        Log.d(TAG, "downloadSong: " + songName + " completed");
    }
}