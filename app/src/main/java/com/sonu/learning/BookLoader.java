package com.sonu.learning;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import static com.sonu.learning.Constants.FANTASY_BOOK;
import static com.sonu.learning.Constants.HORROR_BOOK;

    public class BookLoader extends AsyncTaskLoader<List<String>> {
    private static final String TAG = "#####BookLoader";
    private final String bookType;

    public BookLoader(@NonNull Context context, String bookType) {
        super(context);
        this.bookType = bookType;
        Log.d(TAG, "BookLoader: init");
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(TAG, "onStartLoading: ");
        forceLoad();

    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        Log.d(TAG, "onForceLoad: ");
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d(TAG, "onStopLoading: ");
    }

    @Nullable
    @Override
    public List<String> loadInBackground() {

        Log.d(TAG, "loadInBackground: thread name " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        switch (bookType) {
            case FANTASY_BOOK:
                list.clear();
                list.add("A Game of Thrones");
                list.add("The Name of the Wind");
                list.add("Assassin's Apprentice");
                break;
            case HORROR_BOOK:
                list.clear();
                list.add("At the Mountains of Madness");
                list.add("The Bloody Chamber");
                list.add("The Case Against Satan");
                break;
        }
        return list;
    }
}
