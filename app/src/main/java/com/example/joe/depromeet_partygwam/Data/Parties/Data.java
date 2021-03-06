package com.example.joe.depromeet_partygwam.Data.Parties;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.joe.depromeet_partygwam.Data.Parties.Participant.Participant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("party_owner")
    @Expose
    private PartyOwner partyOwner;
    @SerializedName("participants")
    @Expose
    private List<Participant> participants = null;
    @SerializedName("comment_set")
    @Expose
    private List<CommentSet> commentSets = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
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

    public Data(Integer id, PartyOwner partyOwner, List<Participant> participants, List<CommentSet> commentSets, String title, String slug, String place, String description, String startTime, Integer currentPeople, Integer maxPeople, String createdAt, String lastUpdated, Boolean isNew, Boolean willStartSoon, Boolean hasStarted, Boolean canJoin) {
        this.id = id;
        this.partyOwner = partyOwner;
        this.participants = participants;
        this.commentSets = commentSets;
        this.title = title;
        this.slug = slug;
        this.place = place;
        this.description = description;
        this.startTime = startTime;
        this.currentPeople = currentPeople;
        this.maxPeople = maxPeople;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
        this.isNew = isNew;
        this.willStartSoon = willStartSoon;
        this.hasStarted = hasStarted;
        this.canJoin = canJoin;
    }

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public List<CommentSet> getCommentSets() {
        return commentSets;
    }

    public void setCommentSets(List<CommentSet> commentSets) {
        this.commentSets = commentSets;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeParcelable(this.partyOwner, flags);
        dest.writeList(this.participants);
        dest.writeList(this.commentSets);
        dest.writeString(this.title);
        dest.writeString(this.slug);
        dest.writeString(this.place);
        dest.writeString(this.description);
        dest.writeString(this.startTime);
        dest.writeValue(this.currentPeople);
        dest.writeValue(this.maxPeople);
        dest.writeString(this.createdAt);
        dest.writeString(this.lastUpdated);
        dest.writeValue(this.isNew);
        dest.writeValue(this.willStartSoon);
        dest.writeValue(this.hasStarted);
        dest.writeValue(this.canJoin);
    }

    protected Data(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.partyOwner = in.readParcelable(PartyOwner.class.getClassLoader());
        this.participants = new ArrayList<Participant>();
        in.readList(this.participants, Participant.class.getClassLoader());
        this.commentSets = new ArrayList<CommentSet>();
        in.readList(this.commentSets, CommentSet.class.getClassLoader());
        this.title = in.readString();
        this.slug = in.readString();
        this.place = in.readString();
        this.description = in.readString();
        this.startTime = in.readString();
        this.currentPeople = (Integer) in.readValue(Integer.class.getClassLoader());
        this.maxPeople = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdAt = in.readString();
        this.lastUpdated = in.readString();
        this.isNew = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.willStartSoon = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.hasStarted = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.canJoin = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
