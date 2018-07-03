package com.example.joe.depromeet_partygwam.Data.PartyList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("party_owner")
    @Expose
    private PartyOwner partyOwner;
    @SerializedName("participants")
    @Expose
    private List<Participant> participants = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("current_people")
    @Expose
    private Integer currentPeople;
    @SerializedName("max_people")
    @Expose
    private Integer maxPeople;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("is_new")
    @Expose
    private Boolean isNew;
    @SerializedName("will_start_soon")
    @Expose
    private Boolean willStartSoon;
    @SerializedName("has_started")
    @Expose
    private Boolean hasStarted;
    @SerializedName("can_join")
    @Expose
    private Boolean canJoin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PartyOwner getPartyOwner() {
        return partyOwner;
    }

    public void setPartyOwner(PartyOwner partyOwner) {
        this.partyOwner = partyOwner;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getCurrentPeople() {
        return currentPeople;
    }

    public void setCurrentPeople(Integer currentPeople) {
        this.currentPeople = currentPeople;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getWillStartSoon() {
        return willStartSoon;
    }

    public void setWillStartSoon(Boolean willStartSoon) {
        this.willStartSoon = willStartSoon;
    }

    public Boolean getHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(Boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public Boolean getCanJoin() {
        return canJoin;
    }

    public void setCanJoin(Boolean canJoin) {
        this.canJoin = canJoin;
    }
}
