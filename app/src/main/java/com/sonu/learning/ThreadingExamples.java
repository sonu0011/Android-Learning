package com.sonu.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThreadingExamples extends AppCompatActivity {
    private static final String TAG = "###ThreadingExamples";
    Button button;
//    Handler handler = new Handler();

    ExampleLooperThread thread = new ExampleLooperThread();
    CustomHandlerThread handlerThread = new CustomHandlerThread();
    private Runnable runnable1 = new Runnable1();
//    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: " + Thread.currentThread().getName());
        setContentView(R.layout.activity_threading_examples);
        //button = findViewById(R.id.mybtn);

        handlerThread.start();
//        handler = new Handler(handlerThread.getLooper());

    }


    public void doWork(View view) {
        // handlerThread.getHandler().postAtFrontOfQueue(new Runnable2());
        // handlerThread.getHandler().postDelayed(new Runnable1(), 0);
        //handlerThread.getHandler().postDelayed(new Runnable2(), 0);

//        Message message = Message.obtain();
//        message.what = 1;
//        message.obj = "new message";
//        handlerThread.getHandler().sendMessage(message);

        Message message1 = Message.obtain(handlerThread.getHandler());
        message1.what = 2;
        message1.obj = "second message";
//        message1.setTarget(handlerThread.getHandler());
        message1.sendToTarget();

    }

    public void removeMessage(View view) {
        //remove all callback and message that handler thread handler has put in it.
        handlerThread.getHandler().removeCallbacksAndMessages(null);

        handlerThread.getHandler().removeCallbacks(runnable1);
        handlerThread.getHandler().removeMessages(1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }

    static class Runnable2 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                Log.d(TAG, "run: runnable 2 " + i);
            }
        }
    }

    static class Runnable1 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                Log.d(TAG, "run: runnable 1 " + i);
            }
        }
    }

    class ThreadRunnable implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                SystemClock.sleep(1000);
                if (i == 5) {

                    Handler threadHandler = new Handler(Looper.getMainLooper());
                    threadHandler.post(() -> button.setText("fsdfss"));
                    //
//                    button.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            button.setText("asfsdf");
//                        }
//                    });

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            button.setText("fsdf");
//                        }
//                    });
//
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            button.setText("asfsdf");
//                        }
//                    });

                }
                Log.d(TAG, "run: " + i);
            }
        }
    }

    class ThreadExample extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 10; i++) {
                SystemClock.sleep(1000);
                if (i == 5) {
                    button.setText("asfsdf");
                }
                Log.d(TAG, "run: " + i);
            }
        }
    }

    public void clickMe(View view) {
//        new ThreadExample().start();
        new Thread(new ThreadRunnable()).start();
    }

    public void startThread(View view) {
        thread.start();
    }

    public void stopThread(View view) {
        thread.looper.quit();
    }

    public void addJobB(View view) {
//        thread.handler.post(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    Log.d(TAG, "JOB B" + Thread.currentThread().getName() + " " + i);
//                }
//            }
//        });

        Message message = Message.obtain();
        message.what = 2;
        thread.handler.sendMessage(message);

    }

    public void addJobA(View view) {

//        thread.handler.post(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    Log.d(TAG, "JOB A" + Thread.currentThread().getName() + " " + i);
//                }
//            }
//        });

        Message message = Message.obtain();
        message.what = 1;
        thread.handler.sendMessage(message);
    }

}