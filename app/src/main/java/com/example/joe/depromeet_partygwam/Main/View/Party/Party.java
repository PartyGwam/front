package com.example.joe.depromeet_partygwam.Main.View.Party;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Party {

    private UUID pId;
    private Date pDate;
    private Date pTime;
    private String pPlace;
    private String pTitle;

    public Party(){
        this(UUID.randomUUID());
    }

    public Party(UUID id){
        pId = id;
        pDate = new Date();
        pTime = new Date();
    }


    public UUID getpId() {
        return pId;
    }

    public Date getpDate() {
        return pDate;
    }

    public void setpDate(Date pDate) {
        this.pDate = pDate;
    }

    public Date getpTime() {
        return pTime;
    }

    public void setpTime(Date pTime) {
        this.pTime = pTime;
    }

    public String getpPlace() {
        return pPlace;
    }

    public void setpPlace(String pPlace) {
        this.pPlace = pPlace;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }
}
