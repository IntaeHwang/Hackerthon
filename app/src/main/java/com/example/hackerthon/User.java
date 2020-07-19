package com.example.hackerthon;

import java.util.HashMap;

public class User {

    String id;
    String pw;

    public User(String id, String pw) {
        this.id = id;
        this.pw = pw;
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

    HashMap<String, Object> toUserMap(){
        HashMap<String, Object> userMap = new HashMap<>();

        userMap.put("id", id);
        userMap.put("pw", pw);

        return userMap;
    }
}
