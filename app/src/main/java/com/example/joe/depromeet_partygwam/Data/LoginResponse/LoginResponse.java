package com.example.joe.depromeet_partygwam.Data.LoginResponse;

public class LoginResponse {
    private Boolean success;
    private Result result;
    private Message message;

    public LoginResponse(Boolean success, Result result, Message message) {
        this.success = success;
        this.result = result;
        this.message = message;
    }

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

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}