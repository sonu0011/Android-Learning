package com.sonu.learning.services;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sonu.learning.R;
import com.sonu.learning.boundServices.MusicBindService;
import com.sonu.learning.postOreo.MyJobIntentService;
import com.sonu.learning.postOreo.MyJobService;

import java.util.ArrayList;
import java.util.List;

import static com.sonu.learning.boundServices.MusicBindService.MUSIC_COMPLETE;

public class ServiceActivity extends AppCompatActivity {
    List<String> songList = new ArrayList<>();
    private static final String TAG = "####ServiceActivity";
    TextView textView;
    BroadcastReceiver receiver;
    private Button playMusic;
    private boolean mConnected = false;
    private MusicBindService service;
    int count = 0;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.d(TAG, "onServiceConnected: ");
            mConnected = true;
            MusicBindService.MyBinder myBinder = (MusicBindService.MyBinder) binder;
            service = myBinder.getMusicBindService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
            mConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        textView = findViewById(R.id.song_comp_name);
        playMusic = findViewById(R.id.playMusic);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getStringExtra("intentExtra") == "musicCompleted") {
                    playMusic.setText("Play");
                } else {
                    textView.setText(textView.getText() + " \n" + intent.getStringExtra("songCompleted"));
                }
            }
        };
        playMusic.setOnClickListener(v -> {

            if (mConnected) {
                if (service.isPlaying()) {
                    service.pauseMusic();
                    playMusic.setText("Play");
                } else {
                    startService(new Intent(this, MusicBindService.class));
                    service.playMusic();
                    playMusic.setText("Pause");
                }
            }
        });
        songList.add("abc");
        songList.add("def");
        songList.add("ghi");
        songList.add("jkl");
        songList.add("mno");
        songList.add("pqr");
        songList.add("stu");
        songList.add("vwx");
        songList.add("yz");
    }

    @Override
    protected void onStart() {
//        bindService(new Intent(this , MusicBindService.class), serviceConnection , BIND_AUTO_CREATE);
        super.onStart();
//        Intent intent = new Intent(this, MyForegroundService.class);
//        startService(intent);
//        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("songCompleted"));
//        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(MUSIC_COMPLETE));
    }


    @Override
    protected void onStop() {
        super.onStop();
//        unbindService(serviceConnection);
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
//        Message.obtain(null, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // stopService(new Intent(this, SongDownloaderService.class));
//        startService(new Intent(this, ServerSideService.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void downloadSong(View view) {
//        for (int i = 0; i < songList.size(); i++) {
//            Intent intent = new Intent(this, MyIntentService.class);
//            intent.putExtra("songName", songList.get(i));
//            startService(intent);
////            SystemClock.sleep(6000);
//        }

//        MyJobIntentService.enqueueWork(this,new Intent(this , MyJobIntentService.class).putExtra("starter" , ++count));

//        ComponentName componentName = new ComponentName(this, MyJobService.class);
//        JobInfo info = new JobInfo.Builder(123, componentName)
//                .setRequiresCharging(false)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_CELLULAR)
//                .setPersisted(true) //for device reboot persis
//                .setPeriodic(15 * 60 * 1000)
//                .build();
//
//        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//
//        int resultCode = jobScheduler.schedule(info);
//        if (resultCode == JobScheduler.RESULT_SUCCESS) {
//            Log.d(TAG, "Job scheduled successfully");
//
//        } else {
//            Log.d(TAG, "job scheduled error ");
//        }

        ContextCompat.startForegroundService(this, new Intent(this, MyForegroundService.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelJob(View v) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }
}