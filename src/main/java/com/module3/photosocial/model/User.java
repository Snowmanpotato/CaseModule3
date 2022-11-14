package com.module3.photosocial.model;

public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String timeCreateUser;
    private int access;

    public User(int userId, String userName, String userEmail, String userPassword, String timeCreateUser, int access) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.timeCreateUser = timeCreateUser;
        this.access = access;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getTimeCreateUser() {
        return timeCreateUser;
    }

    public void setTimeCreateUser(String timeCreateUser) {
        this.timeCreateUser = timeCreateUser;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }
}
