package com.adambennett.ribbit.app;

import android.app.Application;

import com.adambennett.ribbit.app.ui.MainActivity;
import com.adambennett.ribbit.app.utilities.ParseConstants;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

public class RibbitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "ixuFQNprFtsDWfW70Yz204R4ccRoghvfPOD50NqG", "c1qLCMbi7d32fQZSo5IF8Hn1uFx0HUwHbVgr0paA");

        // set up notifications at named activity, ie MainActivity for Inbox
        // set icon
        PushService.setDefaultPushCallback(this, MainActivity.class,
                R.drawable.ic_stat_ic_launcher);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    public static void updateParseInstallation(ParseUser user) {
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put(ParseConstants.KEY_USER_ID, user.getObjectId());
        installation.saveInBackground();
    }

}
