package com.example.hackerthon;

import java.util.HashMap;

public class Room {
    String roomId;
    String roomMasterEmail;
    int numberOfParticipants;
    int selectedStartAuthority;
    String gameStartUserEmail;

    public static final int ROOM_MASTER = 0;
    public static final int THE_FIRST = 1;
    public static final int THE_LAST = 2;

    public Room(String roomId, String roomMasterEmail, int numberOfParticipants, int selectedStartAuthority) {
        this.roomId = roomId;
        this.roomMasterEmail = roomMasterEmail;
        this.numberOfParticipants = numberOfParticipants;
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

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
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

    HashMap<String, Object> toRoomMap(){
        HashMap<String, Object> roomMap = new HashMap<>();

        roomMap.put("roomId", roomId);
        roomMap.put("roomMasterEmail", roomMasterEmail);
        roomMap.put("numberOfParticipants", numberOfParticipants);
        roomMap.put("selectedStartAuthority", selectedStartAuthority);
        roomMap.put("gameStartUserEmail", gameStartUserEmail);

        return roomMap;
    }
}
