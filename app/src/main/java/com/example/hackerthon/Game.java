package com.example.hackerthon;

import java.io.Serializable;
import java.util.HashMap;

public class Game implements Serializable {
    String gamePicture;
    String gameName;
    String gameInfo;

    public Game(){};

    public Game(String gamePicture, String gameName, String gameInfo) {
        this.gamePicture = gamePicture;
        this.gameName = gameName;
        this.gameInfo = gameInfo;
    }

    public String getGamePicture() {
        return gamePicture;
    }

    public void setGamePicture(String gamePicture) {
        this.gamePicture = gamePicture;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(String gameInfo) {
        this.gameInfo = gameInfo;
    }

    HashMap<String, Object> toGameMap(Game game){
        HashMap<String, Object> gameMap = new HashMap<>();

        gameMap.put("gamePicture", gamePicture);
        gameMap.put("gameName", gameName);
        gameMap.put("gameInfo", gameInfo);

        return gameMap;
    }
}
