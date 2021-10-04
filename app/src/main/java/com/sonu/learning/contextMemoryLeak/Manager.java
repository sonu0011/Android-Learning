package com.sonu.learning.contextMemoryLeak;

import android.content.Context;

class Manager {
    private static Manager ourInstance;

    public  Context context;

    static Manager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new Manager(context);
        }
        return ourInstance;
    }

    private Manager(Context context) {
        this.context = context;
    }
}
