package com.example.joe.depromeet_partygwam.Data.Parties.Owner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("party_owner")
    @Expose
    private Owner owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
