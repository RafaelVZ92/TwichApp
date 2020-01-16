package com.example.twichapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.example.twichapp.root.App;
import com.example.twichapp.twitchgames.GamesViewModel;
import com.example.twichapp.twitchgames.TwitchMVP;
import com.example.twichapp.twitchgames.model.Game;
import com.example.twichapp.twitchgames.view.GamesAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements TwitchMVP.View{

    public static final String TAG = MainActivity.class.getSimpleName();
    private List<Game> resultList = new ArrayList<>();
    private GamesAdapter listAdapter;
    private RecyclerView recyclerView;
    private ViewGroup viewGroup;

    @Inject
    TwitchMVP.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getAppComponent().setUpInject(this);
        Log.d(TAG, "onCreate: ");
        setUpViews();
        listAdapter = new GamesAdapter(resultList);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpViews() {
        recyclerView = findViewById(R.id.recycler_view_games);
        viewGroup = findViewById(R.id.activity_root_view);
    }


    @Override
    public void updateData(Game game) {
        Log.d(TAG, "updateData: "+ game.getName());
        resultList.add(game);
        listAdapter.notifyItemInserted(resultList.size()-1);

    }

    @Override
    public void showSnackbar(String msj) {
        Snackbar.make(viewGroup, msj, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        mPresenter.setView(this);
        mPresenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.rxJavaUnSuscribe();
        resultList.clear();
        listAdapter.notifyDataSetChanged();
    }

}
