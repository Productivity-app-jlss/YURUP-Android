package com.example.yurup;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("m9WWu8J6NDjKz7SYjRuzzddtSs68h6kWaIb5pr75")
                .clientKey("L6JnRTE0VbHOkuZNyIz0Y4DLVunIjGzJzbbQJutW")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
