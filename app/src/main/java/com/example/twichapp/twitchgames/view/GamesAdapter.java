package com.example.twichapp.twitchgames.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twichapp.R;
import com.example.twichapp.twitchgames.model.Game;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ListItemViewHolder>{

    public static final String TAG = GamesAdapter.class.getSimpleName();
    private List<Game> list;

    public GamesAdapter(List<Game> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_item, parent, false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getBoxArtUrl()).into(holder.avatar);
        holder.id.setText(list.get(position).getId());
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder{

        public ImageView avatar;
        public TextView id;
        public TextView name;

        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.image_game);
            id = itemView.findViewById(R.id.game_id);
            name = itemView.findViewById(R.id.game_name);
        }
    }
}
