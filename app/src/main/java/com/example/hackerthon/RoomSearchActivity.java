package com.example.hackerthon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;

public class RoomSearchActivity extends BaseActivity {

    String resultScannerStr; //스캔한 방번호 아이디 = 현재시간을 초 단위까지 받아온 데이터 (중복되지 않기위해)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_search);

        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                makeToast("QR코드 스캔 실패 : null값", LONG_TOAST);
                finish();
            } else {
                makeToast("QR코드 스캔 성공 : "+result.getContents(), LONG_TOAST);    //result.getContents() 값은 방만들기할때의 현재시간 데이터가 나옴
                // QR코드를 읽어서 가져오는 부분
                // null이 아니면 결과값이 있다는 것!
                resultScannerStr = result.getContents();
                makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", " resultScannerStr: " + resultScannerStr);

                //스캔했을때 QR 코드에서 받아온 값(=resultScannerStr) 다이얼로그 띄우기
                makeToast(resultScannerStr, SHORT_TOAST);

                //스캔에 성공했으니 결과로 받아온 룸번호 에 플레이어로 참여를 하게된다 -> 플레이어에 DB 추가 후 방만들기 화면으로 전환
                saveDatabaseAfterCreatePlayer();

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //DB 에 스캔한 룸번호의 플레이어로 참가하게된다 (플레이어 추가)
    public void saveDatabaseAfterCreatePlayer() {
        //룸 객체 생성
        User user = new User();
        user.id = applicationClass.currentUserEmailKey;
        String roomKey = "room@"+resultScannerStr;  //룸에서 날짜 키로 지정해 놓은 데이터가 다른 곳에서의 날짜키와 중복될수도 있으니 앞에 room@를 붙여줘서 구분해준다

        Map<String, Object> player = new HashMap<>();
        player.put("roomPlayerEmail", user.id);
        player.put("roomPlayerEmail", user.id);

        applicationClass.databaseReference.child("PLAYERLIST").child(roomKey).child(user.id).setValue(player);

        //DB에 플레이어 추가 후 방만들기 화면으로 전환하고 현재 화면 종료
        Intent createRoomIntent = new Intent(this, RoomCreateActivity.class);
        startActivity(createRoomIntent);
        finish();
    }
}
