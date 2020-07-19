package com.example.hackerthon;

import java.util.HashMap;

public class User {

    String id;
    String pw;
    int totalScore;
    int gameScore;
    String userName;

    public User(String id, String pw, int totalScore, int gameScore, String userName) {
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

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    HashMap<String, Object> toUserMap(User user){
        HashMap<String, Object> userMap = new HashMap<>();

        userMap.put("id", id);
        userMap.put("pw", pw);
        userMap.put("totalScore", totalScore);
        userMap.put("gameScore", gameScore);
        userMap.put("userName",userName);

        return userMap;
    }
}
