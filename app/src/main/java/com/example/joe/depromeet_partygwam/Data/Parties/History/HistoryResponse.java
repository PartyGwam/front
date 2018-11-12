package com.example.joe.depromeet_partygwam.Data.Parties.History;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryResponse {
    @SerializedName("message")
    @Expose
    private Message message;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("success")
    @Expose
    private boolean success;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
