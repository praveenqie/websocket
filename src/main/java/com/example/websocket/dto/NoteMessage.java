package com.example.websocket.dto;

import java.util.List;

public class NoteMessage {
    private String text;
    private String userId;
    private boolean isForEveryone;
    private List<String> userIds;

    public NoteMessage(String text, String userId, boolean isForEveryone, List<String> userIds) {
        this.text = text;
        this.userId = userId;
        this.isForEveryone = isForEveryone;
        this.userIds = userIds;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isForEveryone() {
        return isForEveryone;
    }

    public void setForEveryone(boolean forEveryone) {
        isForEveryone = forEveryone;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "NoteMessage{" +
                "text='" + text + '\'' +
                ", userId='" + userId + '\'' +
                ", isForEveryone=" + isForEveryone +
                ", userIds=" + userIds +
                '}';
    }
}
