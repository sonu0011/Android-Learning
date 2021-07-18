package com.sonu.learning.boundServices;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.sonu.learning.R;

public class MusicBindService extends Service {
    private static final String TAG = "####MusicBindService";
    MyBinder myBinder = new MyBinder();
    private MediaPlayer mediaPlayer;
    public static final String MUSIC_COMPLETE = "MUSIC_COMPLETED";

    public class MyBinder extends Binder {

        public MusicBindService getMusicBindService() {
            return MusicBindService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        mediaPlayer = MediaPlayer.create(this, R.raw.pubg);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent(MUSIC_COMPLETE).putExtra("intentExtra", "musicCompleted"));
                stopSelf();
            }
        });
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public void playMusic() {
        mediaPlayer.start();
    }

    public void pauseMusic() {
        mediaPlayer.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return START_NOT_STICKY;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind: ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        //return true if you rebind with service when started service is already running
        //return false if you don't bind
        return true;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
}
