package com.sonu.learning.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.sonu.learning.BroadcastExamples;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //intent.getStringExtra("com.sonu.EXMPLE_BROADCAST_EXTRA");
        Log.d("My broadcast receiver", "onReceive: " + intent.getAction());
        context.startActivity(new Intent(context, BroadcastExamples.class).setFlags(FLAG_ACTIVITY_NEW_TASK));
//        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
//            Toast.makeText(context, "Boot completed", Toast.LENGTH_SHORT).show();
//        } else
//        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
//            Toast.makeText(context, "Conectivity change", Toast.LENGTH_SHORT).show();
//            boolean extra = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
//
//            if (extra) {
//                Toast.makeText(context, "No internet ", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "Connected to internet", Toast.LENGTH_SHORT).show();
//            }
//        }

//        if ("com.sonu.EXAMPLE_BROADCAST".equals(intent.getAction())) {
//            Toast.makeText(context, "Custom Broadcast received", Toast.LENGTH_SHORT).show();
//        }

        Toast.makeText(context, "Broadcas received", Toast.LENGTH_SHORT).show();
    }
}
