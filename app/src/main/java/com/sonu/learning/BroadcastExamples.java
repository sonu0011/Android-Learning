package com.sonu.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.sonu.learning.br.MyBroadcastReceiver;

public class BroadcastExamples extends AppCompatActivity {
//    MyBroadcastReceiver receiver = new MyBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_examples);
//        registerReceiver(receiver , new IntentFilter("com.sonu.EXAMPLE_BROADCAST"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        //IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //registerReceiver(receiver, intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(receiver);
    }
}