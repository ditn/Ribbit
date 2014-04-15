package com.adambennett.ribbit.app;

import android.app.Application;

import com.parse.Parse;

public class RibbitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "ixuFQNprFtsDWfW70Yz204R4ccRoghvfPOD50NqG", "c1qLCMbi7d32fQZSo5IF8Hn1uFx0HUwHbVgr0paA");


    }

}
