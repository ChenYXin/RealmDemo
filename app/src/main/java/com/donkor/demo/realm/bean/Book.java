package com.donkor.demo.realm.bean;

import io.realm.RealmObject;

public class Book extends RealmObject {
    private String name;
    private String author;
    private String publishing;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }
}