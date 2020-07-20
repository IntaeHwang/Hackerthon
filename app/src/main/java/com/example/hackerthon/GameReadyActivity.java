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
    String roomMasterName;  //현재 들어와 았는 방의 방장 이름이다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ready);
        ButterKnife.bind(this);

        currentGame = (Game)getIntent().getSerializableExtra("CurrentGame");
        roomNumberKey = getIntent().getStringExtra("RoomNumberKey");
        roomMasterName = getIntent().getStringExtra("MasterName");

        textViewActivityGameReadyInformationPhrase.setText(currentGame.gameInfo);
        textViewActivityGameReadyGameName.setText(currentGame.gameName);

        Glide.with(this)
                .load(currentGame.getGamePicture())
                .into(imageViewActivityGameReadyButtonImage);
    }

    @OnClick(R.id.button_activity_game_ready_backButton)
    public void onButtonActivityGameReadyBackButtonClicked() {
        super.onBackPressed();
    }

    @OnClick(R.id.button_activity_game_ready_startButton)
    public void onButtonActivityGameReadyStartButtonClicked() {
        Intent intent;
        if(currentGame.getGameName().contentEquals("탭탭")){
            intent = new Intent(getApplicationContext(), TapTapActivity.class);
            intent.putExtra("roomNumberKey",roomNumberKey);
        }else if(currentGame.getGameName().contentEquals("순서대로")){
            intent = new Intent(getApplicationContext(), OrderGameActivity.class);
            intent.putExtra("roomNumberKey",roomNumberKey);
        }else if(currentGame.getGameName().contentEquals("쉐킷쉐킷")){
            intent = new Intent(getApplicationContext(), GameShakeActivity.class);
            intent.putExtra("roomNumberKey",roomNumberKey);
        }else if(currentGame.getGameName().contentEquals("가위바위보")){
            intent = new Intent(getApplicationContext(), GameSRPActivity.class);
            intent.putExtra("roomNumberKey",roomNumberKey);
        }else{
            makeToast("게임 선택 오류 게임 객체의 이름이 잘못되었습니다.", LONG_TOAST);
            intent = new Intent();
        }

        intent.putExtra("RoomNumberKey", roomNumberKey);
        startActivity(intent);
    }

    //룸 데이터 DB에서 불러오기
    public void loadRoomData(String roomKey){
        applicationClass.databaseReference.child("ROOM").child(roomKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Room room = snapshot.getValue(Room.class);
                String roomMasterName = room.getRoomMasterName();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}