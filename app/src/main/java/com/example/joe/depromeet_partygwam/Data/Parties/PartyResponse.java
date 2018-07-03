package com.example.joe.depromeet_partygwam.Data.Parties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartyResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("message")
    @Expose
    private Object message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
