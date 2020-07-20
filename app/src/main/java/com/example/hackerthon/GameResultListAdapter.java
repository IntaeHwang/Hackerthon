package com.example.hackerthon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameResultListAdapter extends RecyclerView.Adapter<GameResultListAdapter.PlayerResultViewHolder> {
    private ArrayList<Player> playerResultList;
    private Context context;

    public GameResultListAdapter(ArrayList<Player> playerResultList, Context context) {
        this.playerResultList = playerResultList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlayerResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_gamefinish, parent, false);
        PlayerResultViewHolder playerResultViewHolder = new PlayerResultViewHolder(view);

        return playerResultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerResultViewHolder holder, int position) {
        Player player = playerResultList.get(position);

        holder.textView_playerRank.setText(String.valueOf(playerResultList.size()));
        holder.textView_playerName.setText(player.getUserName());
        holder.textView_PlayerScore.setText(String.valueOf(player.getGameScore()));
    }

    @Override
    public int getItemCount() {
        return (playerResultList != null ? playerResultList.size() : 0);
    }


    public class PlayerResultViewHolder extends RecyclerView.ViewHolder {

        TextView textView_playerRank;
        TextView textView_playerName;
        TextView textView_PlayerScore;

        public PlayerResultViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_playerRank = itemView.findViewById(R.id.item_RecyclerView_gameResult_GameFinishActivity_rank);
            textView_playerName = itemView.findViewById(R.id.item_RecyclerView_gameResult_GameFinishActivity_name);
            textView_PlayerScore = itemView.findViewById(R.id.item_RecyclerView_gameResult_GameFinishActivity_score);
        }

    }
}
