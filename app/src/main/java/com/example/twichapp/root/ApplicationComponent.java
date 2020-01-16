package com.example.twichapp.root;

import com.example.twichapp.MainActivity;
import com.example.twichapp.http.TwitchModule;
import com.example.twichapp.twitchgames.TwitchAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, TwitchModule.class, TwitchAppModule.class})
public interface ApplicationComponent {
    void setUpInject(MainActivity mTarget);
}
