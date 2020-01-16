package com.example.twichapp.twitchgames.repository;

import com.example.twichapp.http.TwitchAPI;
import com.example.twichapp.twitchgames.model.Game;
import com.example.twichapp.twitchgames.model.Twitch;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class TwitchGamesRepo implements Repository {

    private TwitchAPI mTwitchAPI;
    private List<Game> topGames;
    private long lastTimestamp;

    private static final long CACHE_LIFETIME = 20 * 1000; //Cache que durará 20 segundos

    public TwitchGamesRepo(TwitchAPI mTwitchAPI) {
        this.mTwitchAPI = mTwitchAPI;
        this.topGames = new ArrayList<>();
        this.lastTimestamp = System.currentTimeMillis();
    }

    public boolean isUpdated(){
        return (System.currentTimeMillis() - lastTimestamp) < CACHE_LIFETIME;
    }

    @Override
    public Observable<Game> getGamesFromNetwork() {
        Observable<Twitch> topMoviesRatedObservable = mTwitchAPI.getTopGamesObservable("41l7gp8rw3q0jm0ssiwd77i2y5497o");

        return topMoviesRatedObservable
                .concatMap(new Function<Twitch, Observable<Game>>() {
                    @Override
                    public Observable<Game> apply(Twitch games) {
                        return Observable.fromIterable(games.getGame());
                    }
                }).doOnNext(new Consumer<Game>() {
                    @Override
                    public void accept(Game result) {
                        topGames.add(result);
                    }
                });
    }

    @Override
    public Observable<Game> getGamesFromCache() {
        if (isUpdated()){
            return Observable.fromIterable(topGames);
        }else {
            lastTimestamp = System.currentTimeMillis();
            topGames.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Game> getGamesData() {
        return getGamesFromCache().switchIfEmpty(getGamesFromNetwork());
    }
}
