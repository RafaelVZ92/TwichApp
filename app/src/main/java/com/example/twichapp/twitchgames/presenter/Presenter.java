package com.example.twichapp.twitchgames.presenter;

import com.example.twichapp.twitchgames.GamesViewModel;
import com.example.twichapp.twitchgames.TwitchMVP;
import com.example.twichapp.twitchgames.model.Game;
import com.example.twichapp.twitchgames.model.Twitch;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements TwitchMVP.Presenter{

    private TwitchMVP.View mView;
    private TwitchMVP.Interactor mInteractor;
    private Disposable subscription = null;

    public Presenter(TwitchMVP.Interactor mInteractor) {
        this.mInteractor = mInteractor;
    }

    @Override
    public void loadData() {
        subscription = mInteractor.result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Game>() {
                    @Override
                    public void onNext(Game game) {
                        if (mView != null){
                            mView.updateData(game);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if(mView != null){
                            mView.showSnackbar("Error al descargar top games..");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (mView != null){
                            mView.showSnackbar("Información descargada con éxito");
                        }
                    }
                });
    }

    @Override
    public void rxJavaUnSuscribe() {
        if (subscription != null && !subscription.isDisposed()){
            subscription.dispose();
        }
    }

    @Override
    public void setView(TwitchMVP.View view) {
        this.mView = view;
    }
}
