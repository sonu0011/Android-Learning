package com.sonu.learning.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class ServerSideService extends Service {
    private static final String TAG = "###ServerSideService";
    public static final int RANDOM_NUMBER = 0;
    private int mRandomNumber = 0;
    private boolean mIsRandomGeneratorOn;

    private final int MIN = 0;
    private final int MAX = 100;

    private class ResponseHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            try {
                switch (msg.what) {
                    case RANDOM_NUMBER:
                        Log.d(TAG, "handleMessage: randomumber");
                        Message message = Message.obtain(null, RANDOM_NUMBER);
                        message.arg1 =  new Random().nextInt(MAX) + MIN;
                        msg.replyTo.send(message);
                        break;
                    default:
                        break;
                }

            } catch (RemoteException e) {
                Log.i(TAG, "ERROR :" + e.getMessage());
            }
        }
    }

    private final Messenger responseMessenger = new Messenger(new ResponseHandler());

    public int getRandomNumber() {
        return mRandomNumber;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        stopGeneratingRandomNumber();
    }

    private void stopGeneratingRandomNumber() {
        mIsRandomGeneratorOn = false;
        Toast.makeText(this, "service stops", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        mIsRandomGeneratorOn = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomNumberGenerator();
            }
        }).start();
        return START_STICKY;
    }

    private void startRandomNumberGenerator() {
        while (mIsRandomGeneratorOn) {
            SystemClock.sleep(1000);
            mRandomNumber = new Random().nextInt(MAX) + MIN;
            Log.i(TAG, "startRandomNumberGenerator: number is " + mRandomNumber);
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        Toast.makeText(this, "Service binded", Toast.LENGTH_SHORT).show();
        return responseMessenger.getBinder();
    }
}
