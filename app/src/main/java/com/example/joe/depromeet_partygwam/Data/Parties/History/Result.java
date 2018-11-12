package com.example.joe.depromeet_partygwam.Data.Parties.History;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("data")
    @Expose
    private List<HistoryData> historyData;
    @SerializedName("paging")
    @Expose
    private Paging paging;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<HistoryData> getHistoryData() {
        return historyData;
    }

    public void setHistoryData(List<HistoryData> historyData) {
        this.historyData = historyData;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
