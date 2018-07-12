package com.example.joe.depromeet_partygwam.Data.UserResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("last_login")
    @Expose
    private String last_login;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("date_joined")
    @Expose
    private String date_joined;
    @SerializedName("last_logged_in")
    @Expose
    private String last_logged_in;
    @SerializedName("is_active")
    @Expose
    private String is_active;
    @SerializedName("is_admin")
    @Expose
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

    public User(String password, String email, String username) {
        this.password = password;
        this.email = email;
        this.username = username;
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
