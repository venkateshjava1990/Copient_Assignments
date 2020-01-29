package com.nisum.csvtojson;

public class Chat {
    private String orderId;
    private String timeStamp;
    private String username;
    private String message;

    public Chat() {
    }

    public Chat(String orderId, String timeStamp, String username, String message) {
        this.orderId = orderId;
        this.timeStamp = timeStamp;
        this.username = username;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "orderId='" + orderId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", username='" + username + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usename) {
        this.username = usename;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
