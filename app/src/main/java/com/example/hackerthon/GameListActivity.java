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

    @BindView(R.id.textView_GameListActivity_masterText)
    TextView textViewGameListActivityMasterText;
    @BindView(R.id.textView_GameListActivity_masterName)
    TextView textViewGameListActivityMasterName;
    @BindView(R.id.button_GameListActivity_exit)
    Button buttonGameListActivityExit;
    @BindView(R.id.recyclerView_GameListActivity_gameList)
    RecyclerView recyclerViewGameListActivityGameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        ButterKnife.bind(this);

        
    }

    @OnClick(R.id.button_GameListActivity_exit)
    public void onButtonGameListActivityExitClicked() {

    }

    @OnClick(R.id.recyclerView_GameListActivity_gameList)
    public void onRecyclerViewGameListActivityGameListClicked() {

    }


}
