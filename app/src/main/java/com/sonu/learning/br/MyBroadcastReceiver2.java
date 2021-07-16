package com.sonu.learning.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.sonu.learning.BroadcastExamples;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyBroadcastReceiver2 extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver2";

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d(TAG, "onReceive: BR2");
        //by default on recive works on main thread so if we want to do heavy running process we can go for goAsync method
        //this tells teh system that onrecieve will take more time or untill system finishes it


        final Handler handler = new Handler();
        //async means we are telling system thea onrecieve methods might take more time for work
        //so wait untill i am calling pendingResult.finsish() or till system finished this onreceive method
        final PendingResult pendingResult = goAsync();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);
                //here we need to get the data from pendingResult bec we are on diff thread
                int resultCode = pendingResult.getResultCode();
                String resultData = pendingResult.getResultData();
                Bundle bundle = pendingResult.getResultExtras(true); //if no result extra create empty bundle
                String extra = bundle.getString("extraString");

                resultCode++;
                extra += "  ->BR2";

                String toast = "BR2 \n" +
                        "result code " + resultCode + "\n" +
                        "resultData " + resultData + "\n" +
                        "extra " + extra;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
                    }
                });
                bundle.putString("extraString", extra);
                pendingResult.setResult(resultCode, resultData, bundle);
                pendingResult.finish(); //finish the work
            }
        }).start();
        // abortBroadcast();
    }
}
