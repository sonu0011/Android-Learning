package com.sonu.learning.contextMemoryLeak;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import com.sonu.learning.R;

public class ContextMemoryLeakActivity extends AppCompatActivity {

    private static final String TAG = "ContextMemoryLeakActivi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_memory_leak);
//        Manager.getInstance(getBaseContext());//leak
//        Manager.getInstance(this);//leak
//        Manager.getInstance(getApplicationContext()); //no leak
//        Manager.getInstance(getApplication()); // no leak
        Manager.getInstance(getParent()); //
        ContextWrapper contextWrapper = new ContextWrapper(this);
    }
}