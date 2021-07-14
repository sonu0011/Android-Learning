package com.sonu.learning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import static com.sonu.learning.Constants.HORROR_BOOK;

public class LoaderExample extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<String>> {
    private static final String TAG = "#####LoaderExample";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_example);
        textView = findViewById(R.id.textView);
        //if (getSupportLoaderManager().getLoader(0) != null){
        //  Log.d(TAG, "onCreate: loader is not null");
        getSupportLoaderManager().initLoader(0, null, this);
        //}

    }

    @NonNull
    @Override
    public Loader<List<String>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.d(TAG, "onCreateLoader: ");
        return new BookLoader(this, HORROR_BOOK);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<String>> loader, List<String> data) {

        Log.d(TAG, "onLoadFinished:  data " + data.toString());
        StringBuilder result = new StringBuilder();
        for (String s : data) {
            result.append(s).append(" ");
        }
        textView.setText(result.toString());
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<String>> loader) {
        Log.d(TAG, "onLoaderReset: ");
    }

}