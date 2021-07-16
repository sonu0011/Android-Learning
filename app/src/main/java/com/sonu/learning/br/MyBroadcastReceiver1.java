package com.sonu.learning.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sonu.learning.BroadcastExamples;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyBroadcastReceiver1 extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver1";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: BR1");

        int resultCode = getResultCode();
        String resultData = getResultData();
        Bundle bundle = getResultExtras(true); //if no result extra create empty bundle
        String extra = bundle.getString("extraString");

        resultCode++;
        extra += "  ->BR1";

        String toast = "BR1 \n" +
                "result code " + resultCode + "\n" +
                "resultData " + resultData + "\n" +
                "extra " + extra;

        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
        bundle.putString("extraString", extra);
        setResult(resultCode, resultData, bundle);
    }
}
