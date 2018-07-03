package com.example.joe.depromeet_partygwam.Data.PartyList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paging {
    @SerializedName("previous")
    @Expose
    private String previous;
    @SerializedName("next")
    @Expose
    private String next;

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
