package com.sonu.learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.sonu.learning.br.LocalBroadcastRExample;
import com.sonu.learning.br.MyBroadcastReceiver1;

public class BroadcastExamples extends AppCompatActivity {
    MyBroadcastReceiver1 receiver = new MyBroadcastReceiver1();
        LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        setContentView(R.layout.activity_broadcast_examples);
        IntentFilter intentFilter = new IntentFilter("com.sonu.learning");
        intentFilter.setPriority(2);
        registerReceiver(receiver, intentFilter, Manifest.permission.VIBRATE, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onStart() {
        super.onStart();
        localBroadcastManager.registerReceiver(new LocalBroadcastRExample(), new IntentFilter("com.sonu.local.br"));
        //IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //registerReceiver(receiver, intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(receiver);
    }

    public void localBroadcast(View view) {
        localBroadcastManager.sendBroadcast(new Intent("com.sonu.local.br"));
    }
}