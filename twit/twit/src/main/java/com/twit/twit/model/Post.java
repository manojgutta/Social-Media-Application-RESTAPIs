package com.twit.twit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Post {
    private String postBody;
    private int userID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postID;

    private LocalDate date;

    public Post() {
        this.date = LocalDate.now(); // Initialize date with current date
    }

    // Getter and setter for date
    public LocalDate getDate() {
        return date;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getPostID() {
        return postID;
    }
}
