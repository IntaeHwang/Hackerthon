package com.example.hackerthon;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomCreateActivity extends AppCompatActivity {


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

    String createRoomId; //생성된 방 아이디 = 현재시간을 초 단위까지 받아온 데이터 (중복되지 않기위해)
    String currentTimeStringData;    //TODO: 주석적기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);
        ButterKnife.bind(this);

        //방 만들기를 한 user 이름을 받아서 setText() 해준다
//        textviewRoomCreateActivityRoomMaster.setText();

//        getCurrentTimeMillis();
//        createORcode();

    }

    //현재 시간을 받아오는 함수 -> RoomId 값으로 현재시간 데이터를 대입할거라서 현재시간을 구해야 한다
    public void getCurrentTimeMillis(){
//        //현재 시간을 구한다
//        long nowTime = System.currentTimeMillis();
//        //현재 시간을 날짜 Data 에 저장한다
//        LocalDate nowDate = LocalDate.now();
//        //날짜와 시간을 나타내기위한 포맷을 지정한다
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyMMddHHmmss");
//        //String 변수에 현재날짜+시간 데이터 변수 저장
//        currentTimeStringData = simpleDateFormat.format(data);


    }

    //QR 코드 생성 함수
    public void createQRcode(ImageView img, String text) {

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            //bitmap 형식의 이미지 파일로 만들어낸다
            img.setImageBitmap(bitmap);
        } catch (Exception e) {
        }
    }

    //나가기 버튼 클릭 : 나가기 버튼을 누르면 메인 화면으로 돌아간다
    @OnClick(R.id.button_roomCreateActivity_roomExit)
    public void onViewClicked() {
    }
}
