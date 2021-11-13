package com.example.ideation.Model;

import java.util.ArrayList;

public class User {

    String profilePic;
    String emailId;
    String name;
    String password;
    String userId;
    String summary;
    ArrayList<String> skills;
    ArrayList<Experience> experiences;
    ArrayList<Projects> projects;
    ArrayList<Contact> contacts;

    public User(){};

    public User(String name,String emailId,String password){
        this.emailId = emailId;
        this.name = name;
        this.password = password;
    }

    public String getAbout() {
        return summary;
    }

    public void setAbout(String summary) {
        this.summary = summary;
    }

    public User(String profilePic, String emailId, String name, String password, String userId, String lastMessage, String summary) {
        this.profilePic = profilePic;
        this.emailId = emailId;
        this.name = name;
        this.password = password;
        this.userId = userId;
        this.summary = summary;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
