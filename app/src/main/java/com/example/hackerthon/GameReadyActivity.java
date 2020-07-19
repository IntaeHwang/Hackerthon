package com.example.hackerthon;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ready);
        ButterKnife.bind(this);

        Game currentGame = (Game)getIntent().getSerializableExtra("CurrentGame");

        textViewActivityGameReadyInformationPhrase.setText(currentGame.gameInfo);
        textViewActivityGameReadyGameName.setText(currentGame.gameName);

        Glide.with(this)
                .load(currentGame.getGamePicture())
                .into(imageViewActivityGameReadyButtonImage);
    }

    @OnClick(R.id.button_activity_game_ready_backButton)
    public void onButtonActivityGameReadyBackButtonClicked() {

    }

    @OnClick(R.id.button_activity_game_ready_startButton)
    public void onButtonActivityGameReadyStartButtonClicked() {

    }
}