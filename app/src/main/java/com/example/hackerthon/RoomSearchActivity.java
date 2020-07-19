package com.example.hackerthon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class RoomSearchActivity extends BaseActivity {

    String getRoomId; //생성된 방 아이디 = 현재시간을 초 단위까지 받아온 데이터 (중복되지 않기위해)

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
                String resultScannerStr = result.getContents();
                makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", " resultScannerStr: " + resultScannerStr);

                //스캔했을때 QR 코드에서 받아온 값(=resultScannerStr) 다이얼로그 띄우기
                makeToast(resultScannerStr, SHORT_TOAST);

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
