package com.sonu.learning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView textView;
    ImageDownloader downloader;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
        imageView = findViewById(R.id.imageView);
        downloader = new ImageDownloader(this);
//        downloader.execute("fsdf");
//        downloader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "intial value");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: outstate " + outState.toString());
    }

    public void clickMe(View view) {
//        downloader.cancel(true);
        new ImageDownloader(this).execute("fsd");
//        new ImageDownloader().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , "value");
    }

    static class AsynckTasl extends AsyncTaskLoader<String> {

        public AsynckTasl(@NonNull Context context) {
            super(context);
        }

        @Nullable
        @Override
        public String loadInBackground() {
            return null;
        }
    }


    static class ImageDownloader extends AsyncTask<String, String, String> {

        private WeakReference<MainActivity> reference;

        public ImageDownloader(MainActivity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        protected String doInBackground(String... strings) {

            Log.d(TAG, "doInBackground:  thread name " + Thread.currentThread().getName());
            for (int i = 0; i < 5; i++) {
                if (isCancelled()) {
                    return "task cancelled";
                }
                try {
                    Thread.sleep(1000);
                    publishProgress(String.valueOf(i));
                    Log.d(TAG, "doInBackground: i value is " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "work completed";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: ");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: result  " + s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d(TAG, "onCancelled: without result");
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            /*
            If an AsyncTask is canceled while doInBackground(Params... params) is still executing then the
            method onPostExecute(Result result) will NOT be called after doInBackground(Params... params)
            returns. The AsyncTask will instead call the onCancelled(Result result) to indicate that the task
            was cancelled during execution.
            */


            Log.d(TAG, "onCancelled: with result  " + s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            MainActivity mainActivity = reference.get();
            if (mainActivity == null || mainActivity.isFinishing()) {
                return;
            }
            mainActivity.textView.setText(mainActivity.textView.getText() + " " + values[0]);
        }
    }


}