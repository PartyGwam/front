package com.example.joe.depromeet_partygwam.Data.Parties.Participant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("currentPeople")
    @Expose
    private int currentPeople;
    @SerializedName("participants")
    @Expose
    private List<Participant> participants;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCurrentPeople() {
        return currentPeople;
    }

    public void setCurrentPeople(int currentPeople) {
        this.currentPeople = currentPeople;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}
