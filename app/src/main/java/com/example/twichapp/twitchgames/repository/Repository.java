package com.example.twichapp.twitchgames.repository;

import com.example.twichapp.twitchgames.model.Game;
import com.example.twichapp.twitchgames.model.Twitch;

import io.reactivex.Observable;

public interface Repository {

    Observable<Game> getGamesFromNetwork();
    Observable<Game> getGamesFromCache();
    Observable<Game> getGamesData();
}
