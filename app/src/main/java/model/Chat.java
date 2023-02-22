package model;

public class Chat {
    public String sender;
    public String message;
    public String timestamp;

    public Chat() {
    }

    public Chat(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }
}
