package com.examly.springappuser.model;

public class ErrorDto {
    private int status;
    private String message;

    public ErrorDto(int status, String message){
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
