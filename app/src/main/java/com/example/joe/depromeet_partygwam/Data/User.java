package com.example.joe.depromeet_partygwam.Data;

public class User {
    private String uuid;
    private String password;
    private String last_login;
    private String email;
    private String username;
    private String date_joined;
    private String last_logged_in;
    private String is_active;
    private String is_admin;

    public User(String uuid, String password, String last_login, String email, String username, String date_joined, String last_logged_in, String is_active, String is_admin) {
        this.uuid = uuid;
        this.password = password;
        this.last_login = last_login;
        this.email = email;
        this.username = username;
        this.date_joined = date_joined;
        this.last_logged_in = last_logged_in;
        this.is_active = is_active;
        this.is_admin = is_admin;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    public String getLast_logged_in() {
        return last_logged_in;
    }

    public void setLast_logged_in(String last_logged_in) {
        this.last_logged_in = last_logged_in;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }
}
