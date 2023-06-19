package com.creativeinstall.chatbat.business.model;

public class User {

    private int id;
    private String username;
    private int passwordHash;
    private String email;
    private String firstName;
    private String lastName;

    public User(){}
    public User(String username, String password) {
        this.username = username;
        this.passwordHash = password.hashCode();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
