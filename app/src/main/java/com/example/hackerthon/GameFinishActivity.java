package com.example.hackerthon;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;

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
    RecyclerView.Adapter gameResultListAdapter;
    RecyclerView.LayoutManager gameResultListLayoutManager;
    ArrayList<Player> gameResultList = new ArrayList<>();
    ArrayList<Player> tempGameResultList = new ArrayList<>();

    GameShakeActivity gameShakeActivity;
    GameSRPActivity gameSRPActivity;
    OrderGameActivity orderGameActivity;
    TapTapActivity tapTapActivity;

    String roomNumberKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finish);
        ButterKnife.bind(this);

        roomNumberKey = applicationClass.mySharedPref.getStringPref("key");

        gameShakeActivity = GameShakeActivity.activity;
        gameSRPActivity = GameSRPActivity.activity;
        orderGameActivity = OrderGameActivity.activity;
        tapTapActivity = TapTapActivity.activity;

        String roomNumberKey = getIntent().getStringExtra("roomNumberKey");
        makeLog(new Object() {}.getClass().getEnclosingMethod().getName()+"()", "roomNumberKey : "+roomNumberKey );

        recyclerViewGameFinishActivityGameResult.setHasFixedSize(true);
        gameResultListAdapter = new GameResultListAdapter(gameResultList, this);
        recyclerViewGameFinishActivityGameResult.setAdapter(gameResultListAdapter);
        recyclerViewGameFinishActivityGameResult.setLayoutManager( new LinearLayoutManager(this));

        applicationClass.databaseReference.child("PLAYERLIST").child(roomNumberKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                makeLog(new Object() {}.getClass().getEnclosingMethod().getName()+"()", "snapshot : "+snapshot );

                for(DataSnapshot e: snapshot.getChildren()){
                    Player player = e.getValue(Player.class);
                    tempGameResultList.add(player);
                }


                Comparator<Player> scoreReverse = Comparator.comparing(Player::getGameScore).reversed();
                tempGameResultList.sort(scoreReverse);

                for(Player e : tempGameResultList){
                    gameResultList.add(e);
                }

                applicationClass.databaseReference.child("PLAYERLIST").child(roomNumberKey).child(applicationClass.currentUserEmailKey).child("gameTotalScore").setValue(gameResultList.get(0).getGameTotalScore()+1);
                gameResultListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        applicationClass.databaseReference.child("ROOM").child(roomNumberKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Room room = snapshot.getValue(Room.class);
                if(room.isStartedGame == 0){
                    if(gameShakeActivity!=null){
                        gameShakeActivity.finish();
                    }
                    if(gameSRPActivity!=null){
                        gameSRPActivity.finish();
                    }
                    if(orderGameActivity!=null){
                        orderGameActivity.finish();
                    }
                    if(tapTapActivity!=null){
                        tapTapActivity.finish();
                    }

                    //자신을 finish()
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })
    }

    @OnClick(R.id.button_GameFinishActivity_dismiss)
    public void onViewClicked() {
        //현재 유저가 방장과 같은지 확인하여 같으면 모든 참가자 나가게 하기
        String masterName = applicationClass.mySharedPref.getStringPref("MasterName");
        if(masterName.contentEquals("no key")){
            makeToast("GameFinishActivity에서 방장이름을 shared에서 가지고 오지 못함", LONG_TOAST);
        }else{
            if(applicationClass.currentUserName.contentEquals(masterName)){

                applicationClass.databaseReference.child("ROOM").child(roomNumberKey).child("isStartedGame").setValue(0);

                applicationClass.databaseReference.child("PLAYERLIST").child(roomNumberKey).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot e:snapshot.getChildren()){
                            Player player = e.getValue(Player.class);

                            player.setGameScore(0);
                            applicationClass.databaseReference.child("PLAYERLIST").child(roomNumberKey).child(snapshot.getKey()).child("gameScore").setValue(player.getGameScore());
                        }
                        if(gameShakeActivity!=null){
                            gameShakeActivity.finish();
                        }
                        if(gameSRPActivity!=null){
                            gameSRPActivity.finish();
                        }
                        if(orderGameActivity!=null){
                            orderGameActivity.finish();
                        }
                        if(tapTapActivity!=null){
                            tapTapActivity.finish();
                        }

                        //자신을 finish()
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }else{
                //finish 이전 게임 액티비티와 GameFinishActivity를 죽이면 자동적은 GameReadyActvity로 가게 된다.
                //이전에 열린 게임 액티비티들이 있으면 해당 Activity finish()
                if(gameShakeActivity!=null){
                    gameShakeActivity.finish();
                }
                if(gameSRPActivity!=null){
                    gameSRPActivity.finish();
                }
                if(orderGameActivity!=null){
                    orderGameActivity.finish();
                }
                if(tapTapActivity!=null){
                    tapTapActivity.finish();
                }

                //자신을 finish()
                finish();
            }
        }




    }
}