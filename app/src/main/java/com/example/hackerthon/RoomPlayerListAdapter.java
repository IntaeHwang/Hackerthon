package com.example.hackerthon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoomPlayerListAdapter extends RecyclerView.Adapter<RoomPlayerListAdapter.RoomPlayerListViewHolder> {

    Context context;
    View.OnClickListener onClickListener;

    ArrayList<User> userList = new ArrayList<User>();

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


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

//    public void addItem(User user){
//        u
//    }

    public class RoomPlayerListViewHolder extends RecyclerView.ViewHolder {
        public RoomPlayerListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
