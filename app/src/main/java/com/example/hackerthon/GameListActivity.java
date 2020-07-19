package com.example.hackerthon;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameListActivity extends AppCompatActivity {

    @BindView(R.id.TextView_GameListActivity_masterText)
    TextView TextViewGameListActivityMasterText;
    @BindView(R.id.TextView_GameListActivity_masterName)
    TextView TextViewGameListActivityMasterName;
    @BindView(R.id.Button_GameListActivity_exit)
    Button ButtonGameListActivityExit;
    @BindView(R.id.RecyclerView_GameListActivity_gameList)
    RecyclerView RecyclerViewGameListActivityGameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        ButterKnife.bind(this);

        
    }

    @OnClick(R.id.Button_GameListActivity_exit)
    public void onButtonGameListActivityExitClicked() {

    }

    @OnClick(R.id.RecyclerView_GameListActivity_gameList)
    public void onRecyclerViewGameListActivityGameListClicked() {

    }


}
