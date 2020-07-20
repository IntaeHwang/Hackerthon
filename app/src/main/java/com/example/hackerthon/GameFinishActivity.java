package com.example.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameFinishActivity extends BaseActivity {

    //게임 결과 화면을 종료하고 GameReady Activity로 넘어가기 위한 확인 버튼
    @BindView(R.id.button_GameFinishActivity_dismiss)
    Button buttonGameFinishActivityDismiss;

    //게임결과 리싸이클러뷰 관련 선언, 이 리싸이클러뷰에서는 게임 점수의 따라 참여자들의 등수를 보여줍니다.

    @BindView(R.id.recyclerView_GameFinishActivity_gameResult)
    RecyclerView recyclerViewGameFinishActivityGameResult;
    RecyclerView.Adapter gameListAdapter;
    RecyclerView.LayoutManager gameListLayoutManager;
    ArrayList<Game> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finish);
        ButterKnife.bind(this);

        String roomNumberKey = getIntent().getStringExtra("roomNumberKey");
        makeLog(new Object() {}.getClass().getEnclosingMethod().getName()+"()", "roomNumberKey : "+roomNumberKey );

        applicationClass.databaseReference.child("PLAYERLIST").child(roomNumberKey).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                makeLog(new Object() {}.getClass().getEnclosingMethod().getName()+"()", "snapshot : "+snapshot );
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
    }

    @OnClick(R.id.button_GameFinishActivity_dismiss)
    public void onViewClicked() {
        //현재 유저가 방장과 같으면 확인
    }
}