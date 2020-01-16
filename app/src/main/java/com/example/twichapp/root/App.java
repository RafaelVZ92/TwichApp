package com.example.twichapp.root;

import android.app.Application;

import com.example.twichapp.http.TwitchModule;
import com.example.twichapp.twitchgames.TwitchAppModule;

public class App extends Application {

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .twitchModule(new TwitchModule())
                .twitchAppModule(new TwitchAppModule())
                .build();
    }

    public ApplicationComponent getAppComponent(){
        return appComponent;
    }
}
