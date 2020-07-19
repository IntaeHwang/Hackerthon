package com.example.hackerthon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoomPlayerListAdapter extends RecyclerView.Adapter<RoomPlayerListAdapter.RoomPlayerListViewHolder> {

    Context context;
    AdapterView.OnItemClickListener clickListener;

    ArrayList<User> playerList = new ArrayList<User>();

    public RoomPlayerListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RoomPlayerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_recyclerview_payerlist, parent, false);
        RoomPlayerListViewHolder roomPlayerListViewHolder = new RoomPlayerListViewHolder(view);
        return roomPlayerListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomPlayerListViewHolder holder, int position) {

        User user = playerList.get(position);

//        holder.playerName.setText(user.getUserName());
//        holder.playerTotalScore.setText(user.getTotalScore());

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public void addItem(User user){
        playerList.add(user);
    }

    public User getItem(int position){
        return playerList.get(position);
    }

    //아이템 클릭 리스너 메소드
    public void setOnItemClickListener(AdapterView.OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public class RoomPlayerListViewHolder extends RecyclerView.ViewHolder {

        TextView playerName, playerTotalScore;

        AdapterView.OnItemClickListener clickListener;

        public RoomPlayerListViewHolder(@NonNull View itemView) {
            super(itemView);

            playerName = (TextView) itemView.findViewById(R.id.textview_item_recyclerview_playerList_roomCreateActivity_userName);
            playerTotalScore = (TextView) itemView.findViewById(R.id.textview_item_recyclerview_playerList_roomCreateActivity_userTotalScore);
        }
    }
}
