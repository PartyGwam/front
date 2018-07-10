package com.example.joe.depromeet_partygwam.Data.UserResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Message {
    @SerializedName("non_field_errors")
    @Expose
    private List<String> nonFieldErrors = null;
    @SerializedName("email")
    @Expose
    private List<String> email = null;

    public Message(List<String> nonFieldErrors, List<String> email) {
        this.nonFieldErrors = nonFieldErrors;
        this.email = email;
    }

    public List<String> getNonFieldErrors() {
        return nonFieldErrors;
    }

    public void setNonFieldErrors(List<String> nonFieldErrors) {
        this.nonFieldErrors = nonFieldErrors;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }
}
