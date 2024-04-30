package com.twit.twit.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PostDTO{
    private int postID;
    private String postBody;
    private LocalDate date;
    private List<CommentDTO> comments;

    // Constructors
    public PostDTO() {
    }

    public PostDTO(int postID, String postBody, LocalDate date, List<CommentDTO> comments) {
        this.postID = postID;
        this.postBody = postBody;
        this.date = date;
        this.comments = comments;
    }

    // Getters and setters
    public int getPostID() {
        return this.postID;
    }

    public void setPostId(int postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return this.postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<CommentDTO> getComments() {
        return this.comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}

