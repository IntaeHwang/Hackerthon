package com.example.hackerthon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

    String createRoomId;    //생성된 방 아이디 = 현재시간을 초 단위까지 받아온 데이터 (중복되지 않기위해)
    String roomKey;         //room@현재시간 -> 파이어베이스 키
    String masterName;      //룸 방장 이름
    int numberOfPlayers;    //룸 플레이어 숫자
    String currentTimeStringData;    //현재시간 (초까지)
    int selectedStartAuthority = 1;     //시작권한자로 설정한 방장or1등or꼴등 중 선택한 사람의 값을 int = 1,2,3 으로 구분

    RoomPlayerListAdapter roomPlayerListAdapter;    //어댑터 객체 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);
        ButterKnife.bind(this);

        radiogroupRoomCreateActivityGameStartGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        //진입 경로 -> enterPath = 메인액티비티 & 방찾기액티비티 에서 진입할 수 있음
        Intent intent = getIntent();
        String enterPath = intent.getStringExtra("enterPath");

        //리사이클러뷰를 초기화한다
        recyclerviewInit();

        //RoomCreateActivity 에 들어온 경로 체크
        //MainActivity 에서 들어온 경우에만 룸 생성된다
        //방만들기의 방장으로서 진입시
        if(enterPath.contentEquals("MainActivity")){

            //현재시간(초단위까지)을 받아와서 qr코드를 생성해준다 (qr코드 값은 = 현재시간을 데이터값으로 가진다)
            getCurrentTimeMillis();

            //액티비티 최초 진입 시 룸 생성되는 함수 (키는 룸넘버(현재날짜데이터), 플레이어수, 방장이메일, 선택한시작권한자)
            createRoomData(createRoomId, applicationClass.currentUserEmailKey, 1, selectedStartAuthority, applicationClass.currentUserEmailKey);
            makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", " 들어간 경로 : MainActivity");

            //방장을 플레이어에 추가한다
            savePlayerListToDB(applicationClass.currentUserEmailKey, applicationClass.currentUserName, 0, 0);

            //플레이어 데이터를 불러온다
            loadPlayerListData(roomKey);
            //생성된 룸 데이터 불러온다
            loadRoomData(roomKey);

        //방찾기에서 참여자로서 진입시
        }else if(enterPath.contentEquals("RoomSearchActivity")){
            makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", " 들어간 경로 : RoomSearchActivity");

            String enterRoomKey = intent.getStringExtra("enterRoomKey");

            //생성된 룸 데이터 불러온다
            loadRoomData(enterRoomKey);
            //플레이어 데이터를 불러온다
            loadPlayerListData(enterRoomKey);

        }else {
            makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", " 들어간 경로 : Null");
        }

    }

    //DB 에 플레이어 리스트에 플레이어 추가하는 함수
    public void savePlayerListToDB(String userEmail, String userName, int gameScore, int gameTotalScore) {

        //플레이어 객체 생성 -> 유저이메일, 유저이름, 게임점수, 게임총이긴점수
        Player player = new Player(userEmail, userName, gameScore, gameTotalScore);
        //DB 플레이어리스트에 플레이어 추가
        applicationClass.databaseReference.child("PLAYERLIST").child(roomKey).child(applicationClass.currentUserEmailKey).setValue(player);

    }

    //라디오그룹 체크 리스너
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i == R.id.radiogroup_roomCreateActivity_gameStartMaster){
                selectedStartAuthority = 1;
                makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", "방장 선택 : " + radiogroupRoomCreateActivityGameStartMaster.isChecked()+" 구분번호 : "+selectedStartAuthority);
            }else if(i == R.id.radiogroup_roomCreateActivity_gameStartTheFirst){
                selectedStartAuthority = 2;
                makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", "1등 선택 : " + radiogroupRoomCreateActivityGameStartTheFirst.isChecked()+" 구분번호 : "+selectedStartAuthority);
            }else if(i == R.id.radiogroup_roomCreateActivity_gameStartTheLast){
                selectedStartAuthority = 3;
                makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", "꼴등 선택 : " + radiogroupRoomCreateActivityGameStartTheLast.isChecked()+" 구분번호 : "+selectedStartAuthority);
            }
        }
    };

    //현재 시간을 받아오는 함수 -> RoomId 값으로 현재시간 데이터를 대입할거라서 현재시간을 구해야 한다
    public void getCurrentTimeMillis() {

        //캘린더 객체 생성
        Calendar calendar = Calendar.getInstance();
        //날짜와 시간을 나타내기위한 포맷을 지정한다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmsss");
        //String 변수에 현재날짜+시간 데이터 변수 저장
        currentTimeStringData = simpleDateFormat.format(calendar.getTime());

        makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", "currentTimeStringData : " + currentTimeStringData);

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

    //룸 생성 후 DB에 생성한 ROOM 데이터 추가 (방번호, 방장이메일, 플레이어숫자, 게임시작권한자, 게임을시작하는유저이메일)
    public void createRoomData(String roomId, String roomMasterEmail,  int numberOfPlayers, int selectedStartAuthority, String gameStartUserEmail) {
        //룸 객체 생성
        Room room = new Room(roomId, roomMasterEmail, numberOfPlayers, selectedStartAuthority, gameStartUserEmail);
        roomKey = "room@"+createRoomId;  //룸에서 날짜 키로 지정해 놓은 데이터가 다른 곳에서의 날짜키와 중복될수도 있으니 앞에 room@를 붙여줘서 구분해준다
        applicationClass.databaseReference.child("ROOM").child(roomKey).setValue(room.toRoomMap(room));
    }

    //생성된 룸 데이터 DB에서 불러오기
    public void loadRoomData(String roomKey){

        applicationClass.databaseReference.child("ROOM").child(roomKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Room room = snapshot.getValue(Room.class);
                String roomMasterEmail = room.getRoomMasterEmail();
                numberOfPlayers = room.getNumberOfPlayers();

                //유저 데이터에서 방장이메일을 키값으로 가진 유저의 이름 정보 불러오기
                applicationClass.databaseReference.child("USER").child(roomMasterEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        masterName = user.getUserName();

                        //방장 이름을 나타내주는 TextView 에 setText() 해주기
                        textviewRoomCreateActivityRoomMasterName.setText(masterName);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //playerlist 리사이클러뷰 데이터 초기화하는 함수
    public void recyclerviewInit() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerviewRoomCreateActivityPlayerList.setLayoutManager(linearLayoutManager);

        //어댑터 객체를 생성한다
        roomPlayerListAdapter = new RoomPlayerListAdapter(this);
        recyclerviewRoomCreateActivityPlayerList.setAdapter(roomPlayerListAdapter);

    }

    //DB에서 플레이어 리스트 데이터를 불러온다
    public void loadPlayerListData(String updateRoomKey) {

        roomPlayerListAdapter.playerList.clear();

        applicationClass.databaseReference.child("PLAYERLIST").child(updateRoomKey)
                .addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

                        String userEmailKey = dataSnapshot.getKey(); //iii@naver,com
                        makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", "userEmailKey : " + userEmailKey);

                        Player player = dataSnapshot.getValue(Player.class);
                        roomPlayerListAdapter.playerList.add(player);
                        roomPlayerListAdapter.notifyDataSetChanged();

                        //룸 플레이어 숫자를 변경한다 (룸 : 1명 -> 2명 플레이어)
                        updateRoomData(updateRoomKey);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) { }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });
    }

    //DB에서 ROOM 데이터 수정하는 함수
    public void updateRoomData(String roomKey){
        makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", "플레이어숫자 1 : " + numberOfPlayers);
        Map<String, Object> roomValues = new HashMap<String,Object>();
        numberOfPlayers = numberOfPlayers+1;
        makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", "플레이어숫자 2 : " + numberOfPlayers);
        roomValues.put("numberOfPlayers", numberOfPlayers);
        makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", "플레이어숫자 3 : " + numberOfPlayers);
        applicationClass.databaseReference.child("ROOM").child(roomKey).updateChildren(roomValues);
    }

    @OnClick({R.id.button_roomCreateActivity_roomExit, R.id.button_roomCreateActivity_gameList})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_roomCreateActivity_roomExit:   //나가기 버튼 (메인화면으로 전환)
                break;
            case R.id.button_roomCreateActivity_gameList:   //게임리스트 화면으로 가기 버튼
                Intent gameListIntent = new Intent(this, GameListActivity.class);
                gameListIntent.putExtra("MasterName", masterName);          //방장이름
                gameListIntent.putExtra("RoomNumberKey", roomKey);  //룸번호 room@현재날짜
                startActivity(gameListIntent);
                break;
        }
    }

}
