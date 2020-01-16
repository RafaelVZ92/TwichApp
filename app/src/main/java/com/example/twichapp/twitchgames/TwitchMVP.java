package com.example.twichapp.twitchgames;


import com.example.twichapp.twitchgames.model.Game;
import com.example.twichapp.twitchgames.model.Twitch;

import io.reactivex.Observable;

public interface TwitchMVP {

    interface View{
        void updateData(Game game);

        void showSnackbar(String msj);
    }

    interface Presenter{
        void loadData();

        void rxJavaUnSuscribe();

        void setView(TwitchMVP.View view);
    }

    interface Interactor{
        Observable<Game> result();
    }
}
