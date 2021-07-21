package com.sonu.learning.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Myservice extends Service {

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Message message = Message.obtain(null, 0);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
