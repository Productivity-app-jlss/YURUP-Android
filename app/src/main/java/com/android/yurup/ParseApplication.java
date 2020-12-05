package com.android.yurup;

import android.app.Application;

import com.android.yurup.Models.Challenge;
import com.android.yurup.Models.Participant;
import com.android.yurup.Models.User;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Challenge.class);
        ParseObject.registerSubclass(Participant.class);
        ParseObject.registerSubclass(User.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("qrT4jR244cDiJbDW32wqikMynTtLLPwwNMUo0H5R")
                .clientKey("ZPQZBokc3yWUM177Gkq5gCRyNTQrtCdSGO9qMejn")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
