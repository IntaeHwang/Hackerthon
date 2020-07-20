package com.example.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameReadyActivity extends BaseActivity {

    @BindView(R.id.button_activity_game_ready_backButton)
    Button buttonActivityGameReadyBackButton;
    @BindView(R.id.imageView_activity_game_ready_buttonImage)
    ImageView imageViewActivityGameReadyButtonImage;
    @BindView(R.id.textView_activity_game_ready_gameName)
    TextView textViewActivityGameReadyGameName;
    @BindView(R.id.textView_activity_game_ready_arrow)
    TextView textViewActivityGameReadyArrow;
    @BindView(R.id.textView_activity_game_ready_informationPhrase)
    TextView textViewActivityGameReadyInformationPhrase;
    @BindView(R.id.button_activity_game_ready_startButton)
    Button buttonActivityGameReadyStartButton;

    Game currentGame;//현재 선택된 게임 정보를 갖고 있는 Game 객체이다. 이것으로 게임 시작시 어느 게임이 시작되야 하는지 알 수 있다.
    String roomNumberKey;//현재 들어와 있는 방번호를 알고 있는 키이다.
    String masterName;  //현재 들어와 았는 방의 방장 이름이다.

    //ROOM DB 에서 받아온 데이터
    String roomMasterNameFromDB;    //룸 방장 이름
    String startedGameNameFromDB;   //방장이 선택한 게임 이름
    int isStartedGameFromDB;    //방장이 선택한 게임 시작 유무

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ready);
        ButterKnife.bind(this);

        currentGame = (Game)getIntent().getSerializableExtra("CurrentGame");
        roomNumberKey = getIntent().getStringExtra("RoomNumberKey");
        masterName = getIntent().getStringExtra("MasterName");

        textViewActivityGameReadyInformationPhrase.setText(currentGame.gameInfo);
        textViewActivityGameReadyGameName.setText(currentGame.gameName);

        Glide.with(this)
                .load(currentGame.getGamePicture())
                .into(imageViewActivityGameReadyButtonImage);

        //ROOM 데이터 수신 대기중
        loadRoomData(roomNumberKey);
    }

    @OnClick(R.id.button_activity_game_ready_backButton)
    public void onButtonActivityGameReadyBackButtonClicked() {
        super.onBackPressed();
    }

    @OnClick(R.id.button_activity_game_ready_startButton)
    public void onButtonActivityGameReadyStartButtonClicked() {
//        Intent intent;

        if(currentGame.getGameName().contentEquals("탭탭")){

            if(applicationClass.currentUserName.contentEquals(roomMasterNameFromDB)){
                //로그인한 유저가 방장 이름과 같으면 -> 권한이 생김
                updateRoomData(roomNumberKey, "탭탭", 1);
                intent = new Intent(getApplicationContext(), TapTapActivity.class);
                intent.putExtra("roomNumberKey",roomNumberKey);
            }else{
                makeToast("방장만 게임 시작가능합니다", LONG_TOAST);
            }

        }else if(currentGame.getGameName().contentEquals("순서대로")){

            if(applicationClass.currentUserName.contentEquals(roomMasterNameFromDB)){
                //로그인한 유저가 방장 이름과 같으면 -> 권한이 생김
                updateRoomData(roomNumberKey, "순서대로", 1);
                intent = new Intent(getApplicationContext(), OrderGameActivity.class);
                intent.putExtra("roomNumberKey",roomNumberKey);
            }else{
                makeToast("방장만 게임 시작가능합니다", LONG_TOAST);
            }


        }else if(currentGame.getGameName().contentEquals("쉐킷쉐킷")){

            if(applicationClass.currentUserName.contentEquals(roomMasterNameFromDB)){
                //로그인한 유저가 방장 이름과 같으면 -> 권한이 생김
                updateRoomData(roomNumberKey, "쉐킷쉐킷", 1);
                intent = new Intent(getApplicationContext(), GameShakeActivity.class);
                intent.putExtra("roomNumberKey",roomNumberKey);
            }else{
                makeToast("방장만 게임 시작가능합니다", LONG_TOAST);
            }

        }else if(currentGame.getGameName().contentEquals("가위바위보")){

            if(applicationClass.currentUserName.contentEquals(roomMasterNameFromDB)){
                //로그인한 유저가 방장 이름과 같으면 -> 권한이 생김
                updateRoomData(roomNumberKey, "가위바위보", 1);
                intent = new Intent(getApplicationContext(), GameSRPActivity.class);
            }else{
                makeToast("방장만 게임 시작가능합니다", LONG_TOAST);
            }

        }else{
            makeToast("게임 선택 오류 게임 객체의 이름이 잘못되었습니다.", LONG_TOAST);
            intent = new Intent();
        }

        intent.putExtra("RoomNumberKey", roomNumberKey);
        intent.putExtra("MasterName", masterName);
        startActivity(intent);
    }


    //룸 데이터 DB에서 불러오기
    public void loadRoomData(String roomKey){
        applicationClass.databaseReference.child("ROOM").child(roomKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Room room = snapshot.getValue(Room.class);
                roomMasterNameFromDB = room.getRoomMasterName();
                startedGameNameFromDB = room.getStartedGameName();
                isStartedGameFromDB = room.getIsStartedGame();

                if(startedGameNameFromDB.contentEquals("탭탭") && isStartedGameFromDB==1){
                    Intent intent = new Intent(getApplicationContext(), TapTapActivity.class);
                    startActivity(intent);
                }else if(startedGameNameFromDB.contentEquals("순서대로") && isStartedGameFromDB==1){
                    Intent intent = new Intent(getApplicationContext(), OrderGameActivity.class);
                    startActivity(intent);
                }if(startedGameNameFromDB.contentEquals("쉐킷쉐킷") && isStartedGameFromDB==1){
                    Intent intent = new Intent(getApplicationContext(), GameShakeActivity.class);
                    startActivity(intent);
                }if(startedGameNameFromDB.contentEquals("가위바위보") && isStartedGameFromDB==1){
                    Intent intent = new Intent(getApplicationContext(), GameSRPActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //ROOM 데이터 DB에서 수정하기
    public void updateRoomData(String roomKey, String startedGameName, int isStartedGame){

        Map<String, Object> roomValues = new HashMap<String,Object>();
        roomValues.put("startedGameName", startedGameName);
        roomValues.put("isStartedGame", isStartedGame);
        applicationClass.databaseReference.child("ROOM").child(roomKey).updateChildren(roomValues);
    }
}