package model;

public class Notification {
    public String title;
    public String content;
    public String url;

    public Notification() {
    }

    public Notification(String title, String content, String url) {
        this.title = title;
        this.content = content;
        this.url = url;
    }
}
