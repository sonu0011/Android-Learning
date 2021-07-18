package com.sonu.learning.services;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class DownloadHandler extends Handler {
    public DownloadHandler(@NonNull Looper looper) {
        super(looper);
    }

    private static final String TAG = "DownloadHandler";
    SongDownloaderService songDownloaderService;
    private Context context;

    @Override
    public void handleMessage(@NonNull Message msg) {
        downloadSong((String) msg.obj, msg.what);
    }

    private void downloadSong(String songName, int startId) {

        SystemClock.sleep(4000);
        Log.d(TAG, "downloadSong: " + songName + " completed startId :  " + startId);
//        songDownloaderService.stopSelf(startId);
        songDownloaderService.stopSelfResult(startId);
        Intent intent = new Intent("songCompleted");
        intent.putExtra("songCompleted", songName);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public void setSongDownloaderService(SongDownloaderService songDownloaderService, Context context) {
        this.songDownloaderService = songDownloaderService;
        this.context = context;
    }
}
