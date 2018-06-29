package com.example.joe.depromeet_partygwam.Data;

public class ResponseData {
    private boolean success;
    private Result result;
    private Message message;

    public ResponseData(boolean success, Result result, Message message) {
        this.success = success;
        this.result = result;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
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

class Result {
    String email;

    public Result(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class Message {
    String email;

    public Message(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
