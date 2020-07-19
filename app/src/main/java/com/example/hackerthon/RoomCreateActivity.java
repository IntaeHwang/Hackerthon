package com.example.hackerthon;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomCreateActivity extends AppCompatActivity {

    @BindView(R.id.textview_roomCreateActivity_roomMaster)
    TextView textviewRoomCreateActivityRoomMaster;
    @BindView(R.id.button_roomCreateActivity_roomOut)
    Button buttonRoomCreateActivityRoomOut;
    @BindView(R.id.textview_roomCreateActivity_roomInfo)
    TextView textviewRoomCreateActivityRoomInfo;
    @BindView(R.id.imageview_roomCreateActivity_qrCode)
    ImageView imageviewRoomCreateActivityQrCode;
    @BindView(R.id.textview_roomCreateActivity_roomStart)
    TextView textviewRoomCreateActivityRoomStart;
    @BindView(R.id.linearlayout_roomCreateActivity_roomInfo)
    LinearLayout linearlayoutRoomCreateActivityRoomInfo;
    @BindView(R.id.textview_roomCreateActivity_playerInfo)
    TextView textviewRoomCreateActivityPlayerInfo;
    @BindView(R.id.recyclerview_roomCreateActivity_playerList)
    RecyclerView recyclerviewRoomCreateActivityPlayerList;
    @BindView(R.id.linearlayout_roomCreateActivity_playerList)
    LinearLayout linearlayoutRoomCreateActivityPlayerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);
        ButterKnife.bind(this);
    }
}
