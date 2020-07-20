package com.example.hackerthon;

import java.util.HashMap;

public class Room {
    String roomId;
    String roomMasterName;
    int numberOfPlayers;
    int selectedStartAuthority;
    String gameStartUserEmail;

    String startedGameName;
    boolean isStartedGame;

    public static final int ROOM_MASTER = 0;
    public static final int THE_FIRST = 1;
    public static final int THE_LAST = 2;

    //어떤 게임인지 -> 탭탭
    //사작이 됬는지 안됬는지 여부 -> boolean -> true

    public Room(){

    }

    public Room(String roomId, String roomMasterName, int numberOfPlayers, int selectedStartAuthority, String gameStartUserEmail, String startedGameName, boolean isStartedGame) {
        this.roomId = roomId;
        this.roomMasterName = roomMasterName;
        this.numberOfPlayers = numberOfPlayers;
        this.selectedStartAuthority = selectedStartAuthority;
        this.gameStartUserEmail = gameStartUserEmail;
        this.startedGameName = startedGameName;
        this.isStartedGame = isStartedGame;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomMasterName() {
        return roomMasterName;
    }

    public void setRoomMasterName(String roomMasterName) {
        this.roomMasterName = roomMasterName;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getSelectedStartAuthority() {
        return selectedStartAuthority;
    }

    public void setSelectedStartAuthority(int selectedStartAuthority) {
        this.selectedStartAuthority = selectedStartAuthority;
    }

    public String getGameStartUserEmail() {
        return gameStartUserEmail;
    }

    public void setGameStartUserEmail(String gameStartUserEmail) {
        this.gameStartUserEmail = gameStartUserEmail;
    }

    public String getStartedGameName() {
        return startedGameName;
    }

    public void setStartedGameName(String startedGameName) {
        this.startedGameName = startedGameName;
    }

    public boolean isStartedGame() {
        return isStartedGame;
    }

    public void setStartedGame(boolean startedGame) {
        isStartedGame = startedGame;
    }

    HashMap<String, Object> toRoomMap(Room room){
        HashMap<String, Object> roomMap = new HashMap<>();

        roomMap.put("roomId", roomId);
        roomMap.put("roomMasterName", roomMasterName);
        roomMap.put("numberOfPlayers", numberOfPlayers);
        roomMap.put("selectedStartAuthority", selectedStartAuthority);
        roomMap.put("gameStartUserEmail", gameStartUserEmail);
        roomMap.put("startedGameName", startedGameName);
        roomMap.put("isStartedGame", isStartedGame);

        return roomMap;
    }
}
