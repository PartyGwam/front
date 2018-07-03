package com.example.joe.depromeet_partygwam.Data.PartyList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Participant {
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
