package com.example.twichapp.twitchgames;

import com.example.twichapp.http.TwitchAPI;
import com.example.twichapp.twitchgames.interactor.Interactor;
import com.example.twichapp.twitchgames.presenter.Presenter;
import com.example.twichapp.twitchgames.repository.Repository;
import com.example.twichapp.twitchgames.repository.TwitchGamesRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TwitchAppModule {

    @Provides
    public TwitchMVP.Presenter provideGamePresenter(TwitchMVP.Interactor interactor){
        return new Presenter(interactor);
    }

    @Provides
    public TwitchMVP.Interactor provideGameInteractor(Repository repository){
        return new Interactor(repository);
    }

    @Singleton
    @Provides
    public Repository provideGameRepository(TwitchAPI twitchAPI){
        return new TwitchGamesRepo(twitchAPI);
    }
}
