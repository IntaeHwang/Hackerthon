package com.example.hackerthon;

import java.util.HashMap;

public class Room {
    String roomId;
    String roomMasterEmail;
    int numberOfPlayers;
    int selectedStartAuthority;
    String gameStartUserEmail;

    public static final int ROOM_MASTER = 0;
    public static final int THE_FIRST = 1;
    public static final int THE_LAST = 2;

    public Room(){}
    public Room(String roomId, String roomMasterEmail, int numberOfPlayers, int selectedStartAuthority) {
        this.roomId = roomId;
        this.roomMasterEmail = roomMasterEmail;
        this.numberOfPlayers = numberOfPlayers;
        this.selectedStartAuthority = selectedStartAuthority;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomMasterEmail() {
        return roomMasterEmail;
    }

    public void setRoomMasterEmail(String roomMasterEmail) {
        this.roomMasterEmail = roomMasterEmail;
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

    HashMap<String, Object> toRoomMap(Room room){
        HashMap<String, Object> roomMap = new HashMap<>();

        roomMap.put("roomId", roomId);
        roomMap.put("roomMasterEmail", roomMasterEmail);
        roomMap.put("numberOfPlayers", numberOfPlayers);
        roomMap.put("selectedStartAuthority", selectedStartAuthority);
        roomMap.put("gameStartUserEmail", gameStartUserEmail);

        return roomMap;
    }
}
