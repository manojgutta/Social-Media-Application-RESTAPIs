package com.twit.twit.model;

public class UserDTO {
    private String name;
    private int userID;
    private String email;

    public UserDTO(String name, int userID, String email) {
        this.name = name;
        this.userID = userID;
        this.email = email;
    }
    public int getuserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // Getters and setters for email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters and setters for name
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}