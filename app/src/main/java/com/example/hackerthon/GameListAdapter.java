package com.example.hackerthon;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {

    private ArrayList<Game> gameList;
    private Context context;
    private String roomNumberKey;
    private String masterName;

    public GameListAdapter(ArrayList<Game> gameList, Context context, String roomNumberKey, String masterName) {
        this.gameList = gameList;
        this.context = context;
        this.roomNumberKey = roomNumberKey;
        this.masterName = masterName;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_gamelist, parent, false);
        GameViewHolder gameViewHolder = new GameViewHolder(view);

        return gameViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Game currentGame = gameList.get(position);

        //다운로드 URL로 있는 이미지 값을 item의 해당 뷰에 넣어 준다.
        Glide.with(holder.itemView)
                .load(currentGame.getGamePicture())
                .into(holder.imageView_gamePicture);

        //이미지를 둥글게 하기 위한 코드
        GradientDrawable drawable = (GradientDrawable) context.getDrawable(R.drawable.image_round_frame_for_gamepicture);
        holder.imageView_gamePicture.setBackground(drawable);
        holder.imageView_gamePicture.setClipToOutline(true);

        holder.textView_gameName.setText(currentGame.getGameName());
    }

    @Override
    public int getItemCount() {
        return (gameList != null ? gameList.size() : 0);
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_gamePicture;
        TextView textView_gameName;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_gamePicture = itemView.findViewById(R.id.item_RecyclerView_gameList_GameListActivity_gamePicture);
            textView_gameName = itemView.findViewById(R.id.item_RecyclerView_gameList_GameListActivity_gameName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GameReadyActivity.class);
                    intent.putExtra("CurrentGame", gameList.get(getAdapterPosition()));
                    intent.putExtra("RoomNumberKey", roomNumberKey);
                    intent.putExtra("MasterName", masterName);
                    context.startActivity(intent);
                }
            });
        }
    }
}
