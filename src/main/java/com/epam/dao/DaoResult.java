package com.epam.dao;

public enum DaoResult {
    SUCCESSFUL("OK"), FAILED("ERROR");

    private String message;
    private String status;

    DaoResult(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public DaoResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStatus() {
        return status;
    }
}
