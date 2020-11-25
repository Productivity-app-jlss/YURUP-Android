package com.android.yurup;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Challenge.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("qrT4jR244cDiJbDW32wqikMynTtLLPwwNMUo0H5R")
                .clientKey("ZPQZBokc3yWUM177Gkq5gCRyNTQrtCdSGO9qMejn")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
