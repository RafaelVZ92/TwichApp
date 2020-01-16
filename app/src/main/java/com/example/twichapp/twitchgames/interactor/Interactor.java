package com.example.twichapp.twitchgames.interactor;

import com.example.twichapp.twitchgames.GamesViewModel;
import com.example.twichapp.twitchgames.TwitchMVP;
import com.example.twichapp.twitchgames.model.Game;
import com.example.twichapp.twitchgames.model.Twitch;
import com.example.twichapp.twitchgames.repository.Repository;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class Interactor implements TwitchMVP.Interactor {
    private Repository mRepository;


    public Interactor(Repository mRepository) {
        this.mRepository = mRepository;
    }


    @Override
    public Observable<Game> result() {
        return Observable.defer(() -> mRepository.getGamesData());
    }
}

