package com.example.hackerthon;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameListActivity extends BaseActivity {

//    @BindView(R.id.textView_GameListActivity_masterText)
//    TextView textViewGameListActivityMasterText;

    //방장이름이다. 게임방을 만든 사람이 방장이 된다. 방장 이름은 게임리스트 상단에 항상 표시하므로 데이터가 필요하다.
    @BindView(R.id.textView_GameListActivity_masterName)
    TextView textViewGameListActivityMasterName;

    //뒤로 가기 버튼이다. 뒤로 가면 생성된 게임방 최초 화면으로 가게된다.
    @BindView(R.id.button_GameListActivity_back)
    Button buttonGameListActivityBack;

    //게임리스트 리싸이클라뷰 관련 선언, 이 리싸이클러뷰 안에는 게임 객체 데이터로써 채워지게 된다.
    //이 목록에서 게임 항목을 누르면 게임 준비 액티비티로 넘어가며 게임 설명 및 게임 시작 버튼이 존재한다.
    @BindView(R.id.recyclerView_GameListActivity_gameList)
    RecyclerView recyclerViewGameListActivityGameList;
    RecyclerView.Adapter gameListAdapter;
    RecyclerView.LayoutManager gameListLayoutManager;
    ArrayList<Game> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        ButterKnife.bind(this);

        gameList = new ArrayList<>();

        //방장 이름, 이전 액티비티(방 생성 시 최초 액티비티)를 사용해서 방장 이름을 가져온다
//        textViewGameListActivityMasterName.setText(getIntent().getStringExtra("MasterName"));

        //게임 목록을 불러오긴 위한 childListener 추가 및
        //childListener 추가시 동작될 오버라이딩 함수 정의 보통 최초로 가져올 시 onChildAdded() 메소드가 작동
        //onChildAdded()로 이미 추가된 데이터에 대해서 변경이 일어나면 onChildChanged() 메소드가 작동된다.
        applicationClass.databaseReference.child("GAME").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Game game = snapshot.getValue(Game.class); //Game 목록을 받아오기

                gameList.add(game);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerViewGameListActivityGameList.setHasFixedSize(true);
        gameListLayoutManager = new GridLayoutManager(this, 2);
        gameListAdapter = new GameListAdapter(gameList, this);
        recyclerViewGameListActivityGameList.setAdapter(gameListAdapter);

    }

    @OnClick(R.id.button_GameListActivity_back)
    public void onButtonGameListActivityExitClicked() {
        super.onBackPressed();  //이전 화면인 방 생성시 최초 화면으로 돌아간다.
    }

}
