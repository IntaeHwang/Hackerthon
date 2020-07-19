package com.example.hackerthon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomCreateActivity extends BaseActivity {

    @BindView(R.id.textview_roomCreateActivity_roomMasterName)
    TextView textviewRoomCreateActivityRoomMasterName;  //방장 이름 (방을 만든 방장 이름 데이터가 들어간다)
    @BindView(R.id.button_roomCreateActivity_roomExit)
    Button buttonRoomCreateActivityRoomExit;         //나가기 버튼 (나가기 버튼을 누르면 메인 화면으로 간다)
    @BindView(R.id.imageview_roomCreateActivity_qrCode)
    ImageView imageviewRoomCreateActivityQrCode;    //QR코드 (QR 코드를 인식한 플레이어는 플레이어 리스트에 추가된다)
    @BindView(R.id.radiogroup_roomCreateActivity_gameStartMaster)
    RadioButton radiogroupRoomCreateActivityGameStartMaster;    //게임시작권한 - 방장
    @BindView(R.id.radiogroup_roomCreateActivity_gameStartTheFirst)
    RadioButton radiogroupRoomCreateActivityGameStartTheFirst;  //게임시작권한 - 1등
    @BindView(R.id.radiogroup_roomCreateActivity_gameStartTheLast)
    RadioButton radiogroupRoomCreateActivityGameStartTheLast;  //게임시작권한 - 꼴등
    @BindView(R.id.radiogroup_roomCreateActivity_gameStartGroup)
    RadioGroup radiogroupRoomCreateActivityGameStartGroup;      //라디오버튼 그룹 (위 3개의 시작권한자를 감싸고 있다)
    @BindView(R.id.recyclerview_roomCreateActivity_playerList)
    RecyclerView recyclerviewRoomCreateActivityPlayerList;      //리사이클러뷰 - 플레이어 리스트
    @BindView(R.id.button_roomCreateActivity_gameList)
    Button buttonRoomCreateActivityGameList;    //게임리스트 액티비티로 화면전환하는 버튼

    String createRoomId; //생성된 방 아이디 = 현재시간을 초 단위까지 받아온 데이터 (중복되지 않기위해)
    String currentTimeStringData;    //현재시간 (초까지)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);
        ButterKnife.bind(this);

        //방 만들기를 한 user 이름을 받아서 setText() 해준다
//        textviewRoomCreateActivityRoomMaster.setText();

        //현재시간(초단위까지)을 받아와서 qr코드를 생성해준다 (qr코드 값은 = 현재시간을 데이터값으로 가진다)
        getCurrentTimeMillis();
        //리사이클러뷰를 초기화한다
//        recyclerviewInit();

    }

    //현재 시간을 받아오는 함수 -> RoomId 값으로 현재시간 데이터를 대입할거라서 현재시간을 구해야 한다
    public void getCurrentTimeMillis() {

        //캘린더 객체 생성
        Calendar calendar = Calendar.getInstance();
        //날짜와 시간을 나타내기위한 포맷을 지정한다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmsss");
        //String 변수에 현재날짜+시간 데이터 변수 저장
        currentTimeStringData = simpleDateFormat.format(calendar.getTime());

        makeLog(new Object() {
        }.getClass().getEnclosingMethod().getName() + "()", "currentTimeStringData : " + currentTimeStringData);


        //QR 코드 생성하는 함수 (QR코드 이미지, QR코드 인식하면 값 넘겨줄 데이터)
        createQRcode(imageviewRoomCreateActivityQrCode, currentTimeStringData);
    }

    //QR 코드 생성 함수
    public void createQRcode(ImageView img, String currentTimeData) {

        //roomId 는 초단위까지의 현재시간을 값으로 가진다
        createRoomId = currentTimeData;

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(createRoomId, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            //bitmap 형식의 이미지 파일로 만들어낸다
            img.setImageBitmap(bitmap);
        } catch (Exception e) {
        }
    }

    //리사이클러뷰 데이터 초기화하는 함수
    public void recyclerInit(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerviewRoomCreateActivityPlayerList.setLayoutManager(linearLayoutManager);

//        //어댑터 객체를 생성한다
//        adminMenulistAdapter = new AdminMenulistAdapter(this);
//        recyclerviewRoomCreateActivityPlayerList.setAdapter(adminMenulistAdapter);

    }

    @OnClick({R.id.button_roomCreateActivity_roomExit, R.id.button_roomCreateActivity_gameList})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_roomCreateActivity_roomExit:   //나가기 버튼 (메인화면으로 전환)
                break;
            case R.id.button_roomCreateActivity_gameList:   //게임리스트 화면으로 가기 버튼
                Intent gameListIntent = new Intent(this, GameListActivity.class);
                startActivity(gameListIntent);
                break;
        }
    }

}
