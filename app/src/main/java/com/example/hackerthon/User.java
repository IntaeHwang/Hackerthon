package com.example.hackerthon;

import java.util.HashMap;

public class User {

    String id;
    String pw;
    String totalScore;
    String gameScore;
    String userName;

    public User(String id, String pw, String totalScore, String gameScore, String userName) {
        this.id = id;
        this.pw = pw;
        this.totalScore = totalScore;
        this.gameScore = gameScore;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getGameScore() {
        return gameScore;
    }

    public void setGameScore(String gameScore) {
        this.gameScore = gameScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    HashMap<String, Object> toUserMap(){
        HashMap<String, Object> userMap = new HashMap<>();

        userMap.put("id", id);
        userMap.put("pw", pw);
        userMap.put("totalScore", totalScore);
        userMap.put("gameScore", gameScore);
        userMap.put("userName",userName);

        return userMap;
    }
}
