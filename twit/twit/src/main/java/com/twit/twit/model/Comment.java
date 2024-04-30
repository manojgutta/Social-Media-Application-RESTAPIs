package com.twit.twit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Comment{
    private String commentBody;
    private int postID;
    private int userID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    private Date date;

    public Comment(){
        this.date = new Date(); // Initialize date with current date and time
    }

    // Getters and setters
    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getCommentID() {
        return commentID;
    }

    public Date getDate() {
        return this.date;
    }
}
