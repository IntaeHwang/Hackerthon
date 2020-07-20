package com.example.hackerthon;

public class Player {

    String userEmail;
    String userName;
    int gameScore;      //게임마다 점수
    int gameTotalScore; //게임 총 승리 횟수

    public Player(){

    }

    public Player(String userEmail, String userName, int gameScore, int gameTotalScore) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.gameScore = gameScore;
        this.gameTotalScore = gameTotalScore;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public int getGameTotalScore() {
        return gameTotalScore;
    }

    public void setGameTotalScore(int gameTotalScore) {
        this.gameTotalScore = gameTotalScore;
    }
}
