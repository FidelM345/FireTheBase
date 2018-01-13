package com.example.fidelmomolo.firebase;

/**
 * Created by Fidel M Omolo on 1/11/2018.
 */

public class Message {
    private String author;
    private String text;

    private Message() {}

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }



}
