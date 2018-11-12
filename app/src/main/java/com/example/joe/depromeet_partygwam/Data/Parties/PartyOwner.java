package com.example.joe.depromeet_partygwam.Data.Parties;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartyOwner implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uuid);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.username);
        dest.writeString(this.profilePicture);
    }

    public PartyOwner() {
    }

    protected PartyOwner(Parcel in) {
        this.uuid = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.username = in.readString();
        this.profilePicture = in.readString();
    }

    public static final Parcelable.Creator<PartyOwner> CREATOR = new Parcelable.Creator<PartyOwner>() {
        @Override
        public PartyOwner createFromParcel(Parcel source) {
            return new PartyOwner(source);
        }

        @Override
        public PartyOwner[] newArray(int size) {
            return new PartyOwner[size];
        }
    };
}
