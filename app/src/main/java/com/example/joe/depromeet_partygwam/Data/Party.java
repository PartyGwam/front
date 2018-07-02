package com.example.joe.depromeet_partygwam.Data;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Party {

    private String pId;
    private String pDate;
    private String pTime;
    private String pPlace;
    private String pTitle;

    public Party() {
    }

    public Party(String pId, String pDate, String pTime, String pPlace, String pTitle) {
        this.pId = pId;
        this.pDate = pDate;
        this.pTime = pTime;
        this.pPlace = pPlace;
        this.pTitle = pTitle;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }

    public String getpTime() {
        return pTime;
    }

    public void setpTime(String pTime) {
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
